<%@page import="entity.LibraryBranch"%>
<%@page import="java.util.List"%>
<%@page import="service.AdministratorService"%>
<%

	List<LibraryBranch> branches = new AdministratorService().getLibraryBranches();

%>
<%@include file="index.jsp"%>
<script>

	var branchId;
	
	function modal(id) {
		branchId = id;
		$(document).ready(function(){
		    $(".btn").click(function(){
		    	$("#myModal").modal('show');
		    });
		});
	}
	
	function editBranch() {
		document.getElementById("bId").value = branchId;
		document.editFrm.submit();
	}
	
	function updateBranch(id) {
		document.getElementById("branchId").value = id;
		document.updateFrm.submit();
	}

</script>

<div style="margin-top:80px;margin-left:10px;">
<section>
<table class="table">
	<tr>
		<td>Branch Id</td>
		<td>Branch Name</td>
		<td>Branch Address</td>
		<td>Edit</td>
		<td>Update</td>
	</tr>
	<%for(LibraryBranch b : branches) { %>	
	<tr>
		<td><%=b.getBranchId()%></td>
		<td><%=b.getBranchName()%></td>
		<td><%=b.getBranchAddress()%></td>
		<td><button class="btn btn-success"onclick="javascript:modal()">Edit</button></td>
		<td><button class="btn btn-danger" onclick="javascript:updateBranch(<%=b.getBranchId()%>)">Update</button></td>
	</tr>
	<% } %>
</table>
</section>
<form action="updateBranch" method="post" name="updateFrm">
<input type="hidden" name="branchId" id="branchId"/>
</form>
<form action="editBranch" method="post" name="editFrm"></form>
<div id="myModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Edit Branch</h4>
            </div>
            <div class="modal-body">
                <form method="post" action="editBranch">
						<tr>
							<td><input type="text" placeholder="Enter Branch's name" name="bName"/></td>
							<td><input type="text" placeholder="Enter Branch's address" name="bAddress"/></td>
							<input type="hidden" name="bId" id="bId"/>
							<td><button class="btn btn-success"onClick="javascript:editBranch()">Submit Changes</button></td>
						</tr>
				</form>
            </div>
        </div>
    </div>
</div>

</div>