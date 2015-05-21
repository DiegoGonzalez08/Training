<%@page import="entity.Author"%>
<%@page import="java.util.List"%>
<%@page import="service.AdministratorService"%>
<%

	List<Author> authors = new AdministratorService().getAllAuthors();

%>
<%@include file="index.jsp"%>
<script>

	var aId;
	
	function deleteAuthor(id) {
		
		document.getElementById("authorId").value = id;
		document.deleteFrm.submit();
	}
	
	function modal(id) {
		aId = id;
		$(document).ready(function(){
		    $(".btn").click(function(){
		    	$("#myModal").modal('show');
		    });
		});
	}
	
	function editAuthor() {
		document.getElementById("jackId").value = aId;
		document.editFrm.submit();
	}

</script>

<div style="margin-top:80px;margin-left:10px;">
<section>
<table class="table">
	<tr>
		<td>Author Id</td>
		<td>Author Name</td>
		<td>Edit</td>
		<td>Delete</td>
	</tr>
	<%for(Author a : authors) { %>	
	<tr>
		<td><%=a.getAuthorId()%></td>
		<td><%=a.getAuthorName()%></td>
		<td><button class="btn btn-success"onClick="javascript:modal(<%=a.getAuthorId()%>)">Edit</button></td>
		<td><button class="btn btn-danger" onclick="javascript:deleteAuthor(<%=a.getAuthorId()%>);">Delete</button></td>
	</tr>
	<% } %>
</table>
<form action="deleteAuthor" method="post" name="deleteFrm">
	<input type="hidden" name="authorId" id="authorId"/>
</form>
<form action="editAuthor" method="post" name="editFrm">
</form>

<div id="myModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Edit Author</h4>
            </div>
            <div class="modal-body">
                <form method="post" action="editAuthor">
						<tr>
							<td><input type="text" placeholder="Enter Author's name" name="authorName"/></td>
							<input type="hidden" name="jackId" id="jackId"/>
							<td><button class="btn btn-success"onClick="javascript:editAuthor()">Submit Changes</button></td>
						</tr>
				</form>
            </div>
        </div>
    </div>
</div>

</section>
</div>