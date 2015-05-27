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
	
	function check( html ) {
        return $( $.parseHTML(html) ).text();
    }
	
	function editAuthor() {
		document.getElementById("jackId").value = aId;
		var text =  check(document.getElementById("authorName").value);
		document.getElementById("authorName").value = text;
		alert(text);
		document.editFrm.submit();
	}

</script>

<div style="margin-top:80px;margin-left:10px;">
<section>
${result}<br/> 
<table class="table" id="authorTable">
	<tr>
		<td>Author Id</td>
		<td>Author Name</td>
		<td>Edit</td>
		<td>Delete</td>
	</tr>
</table>

<script>
	$(document).ready(function() {
		$.ajax({
			url : 'getAllAuthors',
			dataType: 'json',
		    success: function (data) { 
		    	$.each(data, function(index, element) {
	            	$('#authorTable').append(
	            			"<tr><td>"+element.authorId+"</td><td>"+element.authorName+"</td>"+
	            			"<td><button class='btn btn-success'>Edit</button></td>"+
	            			"<td><button class='btn btn-danger' onclick='javascript:deleteAuthor("+element.authorId+");'>Delete</button></td></tr>");
	        	});
			}
		});
	});
</script>

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
							<td><input type="text" placeholder="Enter Author's name" name="authorName" id="authorName"/></td>
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