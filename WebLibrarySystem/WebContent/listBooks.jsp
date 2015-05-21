<%@page import="entity.Book"%>
<%@page import="entity.Author"%>
<%@page import="entity.Genre"%>
<%@page import="entity.Publisher"%>
<%@page import="java.util.List"%>
<%@page import="service.AdministratorService"%>
<%
	AdministratorService admin = new AdministratorService();
	List<Book> books = admin.getAllBooks();
	List<Author> authors = admin.getAllAuthors();
	List<Genre> genres = admin.getAllGenres();
	List<Publisher> publishers = admin.getAllPublishers();

%>
<%@include file="index.jsp"%>
<script>
	
	var bkId;
	
	function deleteBook(id) {
		//document.location.href = "deleteAuthor?authorId="+id;
		
		document.getElementById("bookId").value = id;
		document.deleteFrm.submit();
	}
	
	function modal(id) {
		bkId = id;
		$(document).ready(function(){
		    $(".btn").click(function(){
		    	$("#myModal").modal('show');
		    });
		});
	}
	
	function editBook() {
		document.getElementById("bId").value = bkId;
		document.editFrm.submit();
	}

</script>

<div style="margin-top:80px;margin-left:10px;">
<section>
<table class="table">
	<tr>
		<td>Book Id</td>
		<td>Title</td>
		<td>Edit</td>
		<td>Delete</td>
	</tr>
	<%for(Book b : books) { %>	
	<tr>
		<td><%=b.getBookId()%></td>
		<td><%=b.getTitle()%></td>
		<td><button class="btn btn-success"onclick="javascript:modal(<%=b.getBookId()%>);">Edit</button></td>
		<td><button class="btn btn-danger" onclick="javascript:deleteBook(<%=b.getBookId()%>);">Delete</button></td>
	</tr>
	<% } %>
</table>
<form action="deleteBook" method="post" name="deleteFrm">
	<input type="hidden" name="bookId" id="bookId"/>
</form>
<form action="editBook" method="post" name="editFrm"></form>
</section>

<div id="myModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Edit Book</h4>
            </div>
            <div class="modal-body">
                <form method="post" action="editBook">
						<tr>
							<input type="text" placeholder="Enter Title" name="title"/><br/>
								<select multiple name="authorId">
									<%for(Author a : authors) { %>	
										<option value="<%=a.getAuthorId()%>"><%=a.getAuthorName()%></option>
									<% } %>
								</select><br/>	
								<select multiple name="genreId">
									<%for(Genre g : genres) { %>	
										<option value="<%=g.getGenreId()%>"><%=g.getGenreName()%></option>
									<% } %>
								</select><br/>	
								<select name="publisherId">
									<%for(Publisher p : publishers) { %>	
										<option value="<%=p.getPublisherId()%>"><%=p.getPublisherName()%></option>
									<% } %>
								</select><br/>	
							<input type="hidden" name="bId" id="bId"/>
							<td><button class="btn btn-success"onClick="javascript:editBook();">Submit Changes</button></td>
						</tr>
				</form>
            </div>
        </div>
    </div>
</div>

</div>