<%@page import="model.PersonModel"%>
<%@page import="service.ProductService"%>
<%@page import="service.ProductServiceImpl"%>
<%@page import="model.ProductModel"%>

<%
	ProductModel product = new ProductModel();
	/*
	*if Selectedproduct attribute is null forward user to error page,else show result
	*/
	if(null!=request.getAttribute("SelectedProduct")){
	product = (ProductModel)request.getAttribute("SelectedProduct");
	}
	else{
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/Messages/productNotFound.jsp");
		rd.forward(request, response);
	}
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		 <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

		<title>eBid:Seller View Product</title>
		<link rel="icon" type="image/png" href="style/images/icons/favicon.png"/>
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
 		<style type="text/css">
 		.add-to-cart-btn[disabled],.bidPrice[disabled]{
  			cursor: not-allowed;
		 	background-color: #EEEEEE;
		}
		</style>
 		<script type="text/javascript" src="style/js/calculateTime.js">
 		</script>
 		<script>
 		countDown("<%out.print(product.getEndDate());%>","available");
 		</script> 		
<%response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>
    </head>
	<body>
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
		<jsp:include page="/WEB-INF/views/Page_Segment/myProductNavBar.jsp"></jsp:include>
		<!-- /NAVIGATION -->

		<!-- SECTION -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<!-- Product main img -->
					<div class="col-md-5 col-md-push-2">
						<div id="product-main-img">
						<%
						for(int i=0; i<product.getImage().length;i++){ 
						%>
							<div class="product-preview">
								<img src="<%=product.getImage(i) %>" alt="">
							</div>
						<%} %>
						</div>
					</div>
					<!-- /Product main img -->
					
					<!-- Product thumb imgs -->
					<div class="col-md-2  col-md-pull-5">
						<div id="product-imgs">
						<%
						for(int i=0; i<product.getImage().length;i++){ 
						%>
						<div class="product-preview">
								<img src="<%=product.getImage(i) %>" alt="">
							</div>
							<%} %>
						</div>
					</div>
					<!-- /Product thumb imgs -->

					<!-- Product details -->
					<div class="col-md-5">
						<div class="product-details">
							<h2 class="product-name"><%out.print(product.getPname()); %></h2>
							<div>
								<h3 class="product-price">Rs.<%out.print(product.getLastBidPrice()); %></h3>
								<span class="product-available" id="available"></span>
							</div>
							<b>Condition: <i><%out.print(product.getPcondition()); %></i></b>
							<p><%out.print(product.getDescription()); %></p>
							<ul class="product-links">
								<li>Category:</li>
								<li><a href="#"><%out.print(product.getCategory()); %></a></li>
							</ul>
							<br/>
							<b><%
							ProductService bid = new ProductServiceImpl();
							out.print("No of Bids: "+bid.showBidCount(product.getPid())); %></b>
						</div>
					</div>
					<!-- /Product details -->

					<!-- Product tab -->
					<div class="col-md-12">
						<div id="product-tab">
							<!-- product tab nav -->
							<ul class="tab-nav">
								<li class="active"><a data-toggle="tab" href="#tab1">Description</a></li>
							</ul>
						</div>
							<!-- /product tab nav -->

							<!-- product tab content -->
							<div class="tab-content">
								<!-- tab1  -->
								<div id="tab1" class="tab-pane fade in active">
									<div class="row">
										<div class="col-md-12">
											<b>Brand: </b><%out.print(product.getBrand()); %><br/>
											<b>Model</b> <%out.print(product.getModel()); %>
											<p><%out.print(product.getDescription()); %></p>
											</div>
									</div>
								</div>
		</div>
		<!-- /Section -->
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
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
