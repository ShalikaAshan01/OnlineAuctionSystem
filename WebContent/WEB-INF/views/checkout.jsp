<%@page import="model.ProductModel"%>
<%@page import="service.ProductServiceImpl"%>
<%@page import="service.ProductService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.PersonModel"%>
<%response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html"
	  xmlns="http://www.w3.org/1999/html">
	<head>
		<link rel="icon" type="image/png" href="favicon.png">


		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		 <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

		<title>Ebid:Buy a Product</title>

 		<!-- Google font -->
 		<link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

 		<!-- Bootstrap -->
 		<link type="text/css" rel="stylesheet" href="style/css/bootstrap.min.css"/>

 		<!-- Slick -->
 		<link type="text/css" rel="stylesheet" href="style/css/slick.css"/>
 		<link type="text/css" rel="stylesheet" href="style/css/slick-theme.css"/>

 		<!-- nouislider -->
 		<link type="text/css" rel="stylesheet" href="style/css/nouislider.min.css"/>

 		<!-- Font Awesome Icon -->
 		<link rel="stylesheet" href="style/css/font-awesome.min.css">

 		<!-- Custom stlylesheet -->
 		<link type="text/css" rel="stylesheet" href="style/css/style.css"/>
		<script src="style/js/chkValidation2.js"></script>


		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

		<![endif]-->

    </head>
	<body>
	<%
    PersonModel person = null;
    if(null ==session.getAttribute("loggedinUser")){
    	RequestDispatcher dispatcher = request.getRequestDispatcher("login");
		dispatcher.forward( request, response);
    }else{
    	person = (PersonModel)session.getAttribute("loggedinUser");
    }
    	%>
	<!-- HEADER -->
		<header>
			<!-- TOP HEADER -->
			<jsp:include page="/WEB-INF/views/Page_Segment/topheader.jsp"></jsp:include>
			<!-- /TOP HEADER -->

			<!-- MAIN HEADER -->
			<jsp:include page="/WEB-INF/views/Page_Segment/mainheader.jsp"></jsp:include>
			<!-- /MAIN HEADER -->
		</header>
		<!-- /HEADER -->

		<!-- NAVIGATION -->
		<jsp:include page="/WEB-INF/views/Page_Segment/navBar.jsp"></jsp:include>
		<!-- /NAVIGATION -->
		
		<!-- SECTION -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">

					<div class="col-md-7">
						<!-- Billing Details -->
						<div class="billing-details">
							<div class="section-title">
								<h3 class="title">Delivery Information</h3>
							</div>
							<div class="form-group">
								<input class="input" type="text" name="first-name" placeholder="First Name" disabled value="<%=person.getFname() %>">
							</div>
							<div class="form-group">
								<input class="input" type="text" name="last-name" placeholder="Last Name" disabled value="<%=person.getLname() %>">
							</div>
							<div class="form-group">
								<input class="input" type="text" name="address" placeholder="Address" disabled value="<%out.print(person.getAddrl1()+","+person.getAddrl2());%>">
							</div>
							<div class="form-group">
								<input class="input" type="text" name="city" placeholder="City" disabled value="<%=person.getAddrl3()%>">
							</div>
							<div class="form-group">
								<input class="input" type="tel" name="tel" placeholder="Telephone" disabled value="<%=person.getPhone()%>">
							</div>
						</div>

					</div>

					<!-- Order Details -->
					<div class="col-md-5 order-details">
						<div class="section-title text-center">
							<h3 class="title">My product</h3>
						</div>
						<%ProductService buyProduct = new ProductServiceImpl();
						  String id = request.getParameter("pid");
						  int pid = 0;
						  try{
							  pid = Integer.parseInt(id);
						  }catch(NumberFormatException|NullPointerException e){
							  response.sendRedirect("index.jsp");
						  }
						  ProductModel product = buyProduct.selectByID(pid);
						%>
						<div class="order-summary">
							<div class="order-col">
								<div><strong>PRODUCT</strong></div>
								<div><strong>TOTAL</strong></div>
							</div>
							<div class="order-products">
								<div class="order-col">
									<div><label><%=product.getPname() %></label></div>
									<div><%=product.getLastBidPrice() %></div>
								</div>

							</div>
							<div class="order-col">
								<div><label>Shiping</label></div>
								<div><strong>FREE</strong></div>
							</div>
							<div class="order-col">
								<div><strong>TOTAL</strong></div>
								<div><%=product.getLastBidPrice() %> </div>
							</div>
						</div>
					<br/>
						<div class="payment-method">


								<h3>Enter Card details...</h3></br>

								<form name="payment" action="payment" method="post" onsubmit="return formValidate()">
								
									<input type="hidden" name="pid" value="<%=product.getPid()%>">
									<input type="hidden" name="cid" value="<%=person.getCid()%>">
									<input type="hidden" name="price" value="<%=product.getLastBidPrice()%>">
									<label>Card Number &emsp;&emsp;&emsp;&nbsp;&nbsp; <input type="text" name="number" id="number"></label>
									<label>Exp date &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;<input type="text" name="month" id="month" style="width: 60px" placeholder="MM">
										<input type="text" name="year" id="year" style="width: 60px" placeholder="YY"></label>
									<label>Enter security code &emsp;<input type="text" name="code" id="code" style="width: 60px"></label>


								</br>

							<div class="input-checkbox">
							<input type="checkbox" id="terms">
							<label for="terms">
								<span></span>
								I've read and accept the <a href="#">terms & conditions</a>
							</label>


						</div>

							<input type="submit"  name="pay" class="primary-btn order-submit" value="Pay for Your product">


				</form>
						</div>
					<!-- End of the form-->

					<!-- Order Details -->
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->
		<!-- FOOTER -->
		<jsp:include page="/WEB-INF/views/Page_Segment/footer.jsp"></jsp:include>
		<!-- /FOOTER -->

		<!-- jQuery Plugins -->
		<script src="style/js/jquery.min.js"></script>
		<script src="style/js/bootstrap.min.js"></script>
		<script src="style/js/slick.min.js"></script>
		<script src="style/js/nouislider.min.js"></script>
		<script src="style/js/jquery.zoom.min.js"></script>
		<script src="style/js/main.js"></script>

	</body>
</html>
