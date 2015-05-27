<%@include file="index.jsp"%>
<script>
	
	var pubId;

	function deletePublisher(id) {
		//document.location.href = "deleteAuthor?authorId="+id;
		
		document.getElementById("publisherId").value = id;
		document.deleteFrm.submit();
	}
	
	function modal(id) {
		pubId = id;
		$(document).ready(function(){
		    $(".btn").click(function(){
		    	$("#myModal").modal('show');
		    });
		});
	}
	
	function editPublisher() {
		document.getElementById("pubId").value = pubId;
		document.editFrm.submit();
	}
	
	

</script>

<div style="margin-top:80px;margin-left:10px;">
<section>
${result}<br/> 
<table class="table" id="publisherTable">
	<tr>
		<td>Publisher Id</td>
		<td>Publisher Name</td>
		<td>Publisher Address</td>
		<td>Publisher Phone</td>
		<td>Edit</td>
		<td>Delete</td>
	</tr>
</table>

<script>
	$(document).ready(function() {
		$.ajax({
			url : 'getAllPublishers',
			dataType: 'json',
		    success: function (data) { 
		    	$.each(data, function(index, element) {
	            	$('#publisherTable').append(
	            			"<tr><td>"+element.publisherId+"</td><td>"+element.publisherName+"</td>"+
	            			"<tr><td>"+element.publisherAddress+"</td><td>"+element.publisherPhone+"</td>"+
	            			"<td><button class='btn btn-success' onclick='javascript:editPublisher("+element.publisherId+")'>Edit</button></td>"+
	            			"<td><button class='btn btn-danger' onclick='javascript:deletePublisher("+element.publisherId+");'>Delete</button></td></tr>");
	        	});
			}
		});
	});
</script>

<form action="deletePublisher" method="post" name="deleteFrm">
	<input type="hidden" name="publisherId" id="publisherId"/>
</form>
<form action="editPublisher" method="post" name="editFrm">
</form>

</section>

<div id="myModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Edit Publisher</h4>
            </div>
            <div class="modal-body">
                <form method="post" action="editPublisher">
						<tr>
							<td><input type="text" placeholder="Enter Publisher's name" name="pubName"/></td>
							<td><input type="text" placeholder="Enter Publisher's address" name="pubAddress"/></td>
							<td><input type="text" placeholder="Enter Publisher's phone" name="pubPhone"/></td>
							<input type="hidden" name="pubId" id="pubId"/>
							<td><button class="btn btn-success"onClick="javascript:editPublisher()">Submit Changes</button></td>
						</tr>
				</form>
            </div>
        </div>
    </div>
</div>

</div>