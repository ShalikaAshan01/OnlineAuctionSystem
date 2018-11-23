<%@page import="model.PersonModel"%>
<%if(null!=session.getAttribute("loggedinUser")){
    	RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/LogoutView.jsp");
    	rd.forward(request, response);
}
%>
<%response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>eBid: Create new Account</title>
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
</head>
<body>
<!-- banner -->
	<div class="center-container">
		<div class="main">
			<h1 class="w3layouts_head">Create your eBid Account</h1>
				<div class="w3layouts_main_grid">
					<form action="SignUp" method="POST" class="w3_form_post">

						<div class="w3_agileits_main_grid w3l_main_grid">
							<span class="agileits_grid">
								<label>First Name </label>
								<input type="text" name="firstname" 
								value="<%if(null!= request.getAttribute("unregUser")){
										PersonModel model = new PersonModel();
										model = (PersonModel)request.getAttribute("unregUser");
										out.print(model.getFname());
										}%>"
								>
							</span>
							
							<div class="error"><%if(null!= request.getAttribute("FnameError"))
							out.print(request.getAttribute("FnameError")); %></div>
						
						</div>

						<div class="w3_agileits_main_grid w3l_main_grid">
							<span class="agileits_grid">
								<label>Last Name </label>
								<input type="text" name="lastname" 
								value="<%if(null!= request.getAttribute("unregUser")){
										PersonModel model = new PersonModel();
										model = (PersonModel)request.getAttribute("unregUser");
										out.print(model.getLname());
										}%>" >
							</span>
							
							<div class="error"><%if(null!= request.getAttribute("LnameError"))
							out.print(request.getAttribute("LnameError")); %></div>
						</div>

						<div class="w3_agileits_main_grid w3l_main_grid">
							<span class="agileits_grid">
								<label>Email </label>
								<input type="email" name="email"
								
								value="<%if(null!= request.getAttribute("unregUser")){
										PersonModel model = new PersonModel();
										model = (PersonModel)request.getAttribute("unregUser");
										out.print(model.getEmail());
										}%>">
							</span>
							<div class="error"><%if(null!= request.getAttribute("EmailError"))
							out.print(request.getAttribute("EmailError")); %></div>
						</div>

						<div class="w3_agileits_main_grid w3l_main_grid">
							<span class="agileits_grid">
								<label>Phone Number </label>
								<input type="text" name="phoneNumber" 
								value="<%if(null!= request.getAttribute("unregUser")){
										PersonModel model = new PersonModel();
										model = (PersonModel)request.getAttribute("unregUser");
										out.print(model.getPhone());
										}%>"
								 >
								</span>
								
								<div class="error"><%if(null!= request.getAttribute("phoneError"))
								out.print(request.getAttribute("phoneError")); %>
								</div>
						</div>

						<div class="w3_agileits_main_grid w3l_main_grid">
							<span class="agileits_grid">
								<label>Address Line 1 </label>
								<input type="text" name="addressline1" 
								value="<%if(null!= request.getAttribute("unregUser")){
										PersonModel model = new PersonModel();
										model = (PersonModel)request.getAttribute("unregUser");
										out.print(model.getAddrl1());
										}%>"
								 >
								</span>
								<div class="error"><%if(null!= request.getAttribute("AddressError1"))
								out.print(request.getAttribute("AddressError1")); %></div>
						</div>

						<div class="w3_agileits_main_grid w3l_main_grid">
							<span class="agileits_grid">
								<label>Address Line 2</label>
								<input type="text" name="addressline2" 
								value="<%if(null!= request.getAttribute("unregUser")){
										PersonModel model = new PersonModel();
										model = (PersonModel)request.getAttribute("unregUser");
										out.print(model.getAddrl2());
										}%>" >
								</span>
								<div class="error"><%if(null!= request.getAttribute("AddressError2"))
							out.print(request.getAttribute("AddressError2")); %></div>
						</div>

						<div class="w3_agileits_main_grid w3l_main_grid">
							<span class="agileits_grid">
								<label>Address Line 3</label>
								<input type="text" name="addressline3" 
								
								value="<%if(null!= request.getAttribute("unregUser")){
										PersonModel model = new PersonModel();
										model = (PersonModel)request.getAttribute("unregUser");
										out.print(model.getAddrl3());
										}%>" >
								</span>
								<div class="error"><%if(null!= request.getAttribute("AddressError3"))
								out.print(request.getAttribute("AddressError3")); %></div>
						</div>

						<div class="agileits_w3layouts_main_grid w3ls_main_grid">
							<span class="agileinfo_grid">
								<label>Date of Birth </label>
									<input class="date" id="datepicker" name="dob" type="date">
								</span>
								</div>
								<div class="clear"></div>

						<div class="content-w3ls">
							<div style="form-w3ls">
								<div class="content-wthree2">
									<div class="grid-w3layouts1">
										<div class="w3-agile1">
											<label>Gender</label>
											<ul>
												<li>
													<input type="radio" id="a-option" name="gender" value="male">
													<label for="a-option">Male </label>
													<div class="check"></div>
												</li>
												<li>
													<input type="radio" id="b-option" name="gender" value="female">
													<label for="b-option">Female</label>
													<div class="check"><div class="inside"></div></div>
												</li>
											</ul>


										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="w3_agileits_main_grid w3l_main_grid">
							<span class="agileits_grid">
								<label>Password</label>
								<input type="password" name="password" required 
										value="<%if(null!= request.getAttribute("unregUser")){
										PersonModel model = new PersonModel();
										model = (PersonModel)request.getAttribute("unregUser");
										out.print(model.getPassword());
										}%>">
								</span>
						</div>

						<div class="w3_agileits_main_grid w3l_main_grid">
							<span class="agileits_grid">
								<label>Confirm Password</label>
								<input type="password" name="conpassword" required>
								</span>
						</div>

					<div class="w3_main_grid">
						<div class="w3_main_grid_right">
							<input type="submit" value="Sign Up">
						</div>
					</div>
				</form>
					<span class="agileits_grid"><a href="Login">Sign in instead</a></span>
			</div>
		</div>
	</div>
<!-- //footer -->
</body>
</html>