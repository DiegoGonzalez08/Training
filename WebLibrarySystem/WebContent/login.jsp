<%@include file="index.jsp"%>

<div style="margin-top:80px;margin-left:10px;">
<section>
${result}<br/> 
<form method="post" action="checkCard">
	<input type="text" placeholder="Enter Card Number" name="cardNo"/>
	<input type="submit"/>
</form>
</section>
</div>