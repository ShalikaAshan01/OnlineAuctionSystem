<%@page import="model.PersonModel"%>
<%
PersonModel person = null;
if(null==session.getAttribute("loggedinUser")){
		session.setAttribute("from", "addNewProduct");
    	RequestDispatcher rd = request.getRequestDispatcher("login");
    	rd.forward(request, response);
}else{
	person =(PersonModel) session.getAttribute("loggedinUser");
}
%>
<%response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>eBid: Add New Product</title>
<!-- for-mobile-apps -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- //for-mobile-apps -->
<!-- //custom-theme -->
<link href="style/css/signup.css" rel="stylesheet" type="text/css" media="all" />
<!-- js -->
<script type="text/javascript" src="style/js/jquery-2.1.4.min.js"></script>
<!-- //js -->
<!-- font-awesome-icons -->
<!-- //font-awesome-icons -->
<link href='//fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900' rel='stylesheet' type='text/css'>
<script> function isNumberKey(evt)
{
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57))
       return false;

    return true;
 }</script>
</head>
<body>
<!-- banner -->
	<div class="center-container">
		<div class="main">
			<h1 class="w3layouts_head">Add New Product to eBid</h1>
				<div class="w3layouts_main_grid">
					<form action="addProduct" method="post" class="w3_form_post" id="add">

					<div class="w3_agileits_main_grid w3l_main_grid">
					<span class="agileits_grid">
					<label>Product Name </label>
					<input type="text" name="name" required>
					</span>
					</div>
					
					<div class="w3_agileits_main_grid w3l_main_grid">
					<span class="agileits_grid">
					<label>Category </label>
					<select name="category" required>
					<option value="computers">Computers</option>
					<option value="Accessories">Accessories</option>
					<option value="others">Others</option>
					</select>
					</span>
					</div>
					
					<div class="w3_agileits_main_grid w3l_main_grid">
					<span class="agileits_grid">
					<label>Bid Period</label>
					<input type="text" name="bidPeriod" onkeypress="return isNumberKey(event)" required>
					</span>
					</div>
					
					<div class="w3_agileits_main_grid w3l_main_grid">
					<span class="agileits_grid">
					<label>Condition </label>
					<select name="condition" required>
					<option value="new">New</option>
					<option value="preowned">PreOwned</option>
					</select>
					</span>
					</div>
					
					<div class="w3_agileits_main_grid w3l_main_grid">
					<span class="agileits_grid">
					<label>Starting Price </label>
					<input type="text" name="price" onkeypress="return isNumberKey(event)" required>
					</span>
					</div>
					
					<div class="w3_agileits_main_grid w3l_main_grid">
					<span class="agileits_grid">
					<label>Brand</label>
					<input type="text" name="brand" required>
					</span>
					</div>
					
					<div class="w3_agileits_main_grid w3l_main_grid">
					<span class="agileits_grid">
					<label>Model</label>
					<input type="text" name="model" required>
					</span>
					</div>					
					
					<div class="w3_agileits_main_grid w3l_main_grid">
					<span class="agileits_grid">
					<label>Description</label>
					<textarea rows="4" cols="50" name="description" form="add" id="dis" required></textarea>
					</span>
					<input type="hidden" name="seller" value="<%=person.getCid()%>">
					<div style="color:white;float:right;" id="count"></div>
					</div>					
					<script type="text/javascript">
						  document.getElementById('dis').onkeyup = function () {
						  document.getElementById('count').innerHTML = "Characters left: " + (255 - this.value.length);
						};
					</script>
						
					<div class="w3_main_grid">
						<div class="w3_main_grid_right">
							<input type="submit" value="Add Product">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
<!-- //footer -->
</body>
</html>