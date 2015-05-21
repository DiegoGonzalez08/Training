<%@page import="entity.Borrower"%>
<%@page import="java.util.List"%>
<%@page import="service.AdministratorService"%>
<%

	List<Borrower> borrowers = new AdministratorService().getAllBorrowers();

%>
<%@include file="index.jsp"%>
<script>

	var bId;

	function deleteBranch(id) {
		//document.location.href = "deleteAuthor?authorId="+id;
		
		document.getElementById("cardNo").value = id;
		document.deleteFrm.submit();
	}
	
	function modal(id) {
		bId = id;
		$(document).ready(function(){
		    $(".btn").click(function(){
		    	$("#myModal").modal('show');
		    });
		});
	}
	
	function editBorrower() {
		document.getElementById("bId").value = bId;
		document.editFrm.submit();
	}

</script>

<div style="margin-top:80px;margin-left:10px;">
<section>
<table class="table">
	<tr>
		<td>Card Number</td>
		<td>Name</td>
		<td>Address</td>
		<td>Phone</td>
		<td>Edit</td>
		<td>Delete</td>
	</tr>
	<%for(Borrower b : borrowers) { %>	
	<tr>
		<td><%=b.getCardNo()%></td>
		<td><%=b.getName()%></td>
		<td><%=b.getAddress()%></td>
		<td><%=b.getPhone()%></td>
		<td><button class="btn btn-success"onclick="javascript:modal(<%=b.getCardNo()%>)">Edit</button></td>
		<td><button class="btn btn-danger" onclick="javascript:deleteBranch(<%=b.getCardNo()%>);">Delete</button></td>
	</tr>
	<% } %>
</table>
<form action="deleteBorrower" method="post" name="deleteFrm">
	<input type="hidden" name="cardNo" id="cardNo"/>
</form>
<form action="editBorrower" method="post" name="editFrm"></form>
</section>

<div id="myModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Edit Borrower</h4>
            </div>
            <div class="modal-body">
                <form method="post" action="editBorrower">
						<tr>
							<td><input type="text" placeholder="Enter Borrower's name" name="bName"/></td>
							<td><input type="text" placeholder="Enter Borrower's address" name="bAddress"/></td>
							<td><input type="text" placeholder="Enter Borrower's phone" name="bPhone"/></td>
							<input type="hidden" name="bId" id="bId"/>
							<td><button class="btn btn-success"onClick="javascript:editBorrower()">Submit Changes</button></td>
						</tr>
				</form>
            </div>
        </div>
    </div>
</div>

</div>