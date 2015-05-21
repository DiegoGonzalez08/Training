<%@page import="entity.Genre"%>
<%@page import="java.util.List"%>
<%@page import="service.AdministratorService"%>
<%

	List<Genre> genres = new AdministratorService().getAllGenres();

%>
<%@include file="index.jsp"%>
<script>

	var genreId;
	
	function deleteGenre(id) {
		//document.location.href = "deleteAuthor?authorId="+id;
		
		document.getElementById("genreId").value = id;
		document.deleteFrm.submit();
	}
	
	function modal(id) {
		genreId = id;
		$(document).ready(function(){
		    $(".btn").click(function(){
		    	$("#myModal").modal('show');
		    });
		});
	}
	
	function editGenre() {
		document.getElementById("gId").value = genreId;
		document.editFrm.submit();
	}
	
</script>

<div style="margin-top:80px;margin-left:10px;">
<section>
<table class="table">
	<tr>
		<td>Genre Id</td>
		<td>Genre Name</td>
		<td>Edit</td>
		<td>Delete</td>
	</tr>
	<%for(Genre g : genres) { %>	
	<tr>
		<td><%=g.getGenreId()%></td>
		<td><%=g.getGenreName()%></td>
		<td><button class="btn btn-success" onClick="javascript:modal(<%=g.getGenreId()%>)">Edit</button></td>
		<td><button class="btn btn-danger" onclick="javascript:deleteGenre(<%=g.getGenreId()%>);">Delete</button></td>
	</tr>
	<% } %>
</table>
<form action="deleteGenre" method="post" name="deleteFrm">
	<input type="hidden" name="genreId" id="genreId"/>
</form>
<form action="editGenre" method="post" name="editFrm"></form>

<div id="myModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Edit Genre</h4>
            </div>
            <div class="modal-body">
                <form method="post" action="editGenre">
						<tr>
							<td><input type="text" placeholder="Enter Genre name" name="genreName"/></td>
							<input type="hidden" name="gId" id="gId"/>
							<td><button class="btn btn-success"onClick="javascript:editGenre()">Submit Changes</button></td>
						</tr>
				</form>
            </div>
        </div>
    </div>
</div>


</section>
</div>