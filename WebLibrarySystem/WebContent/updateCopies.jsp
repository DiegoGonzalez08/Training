<%@page import="entity.Book"%>
<%@page import="entity.BookCopies"%>
<%@page import="entity.Genre"%>
<%@page import="entity.Publisher"%>
<%@page import="java.util.List"%>
<%@page import="service.AdministratorService"%>
<%
	AdministratorService admin = new AdministratorService();
	List<Book> books = admin.getAllBooks();
	List<Genre> genres = admin.getAllGenres();
	List<Publisher> publishers = admin.getAllPublishers();

%>
<%@include file="index.jsp"%>
<script>
	
	var bkId;
	var branchId;
	
	function modal(id, bId) {
		bkId = id;
		branchId = bId;
		$(document).ready(function(){
		    $(".btn").click(function(){
		    	$("#myModal").modal('show');
		    });
		});
	}
	
	function updateCopies() {
		document.getElementById("bId").value = bkId;
		document.getElementById("branchId").value = branchId;
		document.updateFrm.submit();
	}

</script>

<div style="margin-top:80px;margin-left:10px;">
<section>
<table class="table">
	<tr>
		<td>Book Id</td>
		<td>Title</td>
		<td>Copies</td>
		<td>Edit</td>
	</tr>
	<%for(Book b : books) { %>	
	<tr>
		<%
		//int branchId = Integer.parseInt(request.getParameter("boId"))
		BookCopies copies = admin.getBookCopies(b.getBookId(), Integer.parseInt(request.getParameter("branchId")));
		int num;
		if (copies == null) {
			num = 0;
		} else {
			num = copies.getNoOfCopies();
		}
		%>
		<td><%=b.getBookId()%></td>
		<td><%=b.getTitle()%></td>
		<td><%=num%></td>
		<td><button class="btn btn-success"onclick="javascript:modal(<%=b.getBookId()%>, <%=request.getAttribute("branchId")%>)">Edit</button></td>
	</tr>
	<% } %>
</table>
<form action="updateCopies" method="post" name="updateFrm">
</form>
</section>

<div id="myModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Edit Copies</h4>
            </div>
            <div class="modal-body">
                <form method="post" action="updateCopies">
					<tr>
						<td><input type="text" placeholder="Enter number of Copies" name="noCopies" id="noCopies"/></td>
						<input type="hidden" name="branchId" id="branchId"/>
						<input type="hidden" name="bId" id="bId"/>
						<td><button class="btn btn-success"onClick="javascript:updateCopies()">Submit Changes</button></td>
					</tr>
				</form>
            </div>
        </div>
    </div>
</div>

</div>