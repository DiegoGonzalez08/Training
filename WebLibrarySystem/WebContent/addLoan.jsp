<%@page import="entity.Genre"%>
<%@page import="entity.Author"%>
<%@page import="entity.Publisher"%>
<%@page import="java.util.List"%>
<%@page import="service.AdministratorService"%>
<%
	AdministratorService admin = new AdministratorService();
	List<Author> authors = admin.getAllAuthors();
	List<Genre> genres = admin.getAllGenres();
	List<Publisher> publishers = admin.getAllPublishers();
	
%>

<%@include file="index.jsp"%>

<div style="margin-top:80px;margin-left:10px;">
<section>
<form method="post" action="addBook">
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
	<input type="submit"/>

</form>
</section>
</div>