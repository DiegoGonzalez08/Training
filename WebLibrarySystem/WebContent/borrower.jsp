<%@include file="index.jsp"%>
<div style="margin-top:80px;margin-left:10px;">
<section>
<%-- <% if(request.getAttribute("result") != null){ %>
	<%=request.getAttribute("result")%><br/>
<% } %>
 --%>
${result}<br/> 
<a href="addBorrower.jsp">Add Borrower</a><br/>
<a href="listBorrowers.jsp">List Borrowers</a>
</section>
</div>
<!-- this is a comment -->
