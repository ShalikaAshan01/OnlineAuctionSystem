<%@page import="java.util.List"%>
<%@page import="model.ProductModel"%>
<%@page import="java.util.ArrayList"%>
<%@page import="service.ProductServiceImpl"%>
<%@page import="service.ProductService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
	String keyword=null;
	if(null != request.getAttribute("keyword")){
		keyword = (String)request.getAttribute("keyword");
	}
	
	ProductService productSearch = new ProductServiceImpl();
	ArrayList<ProductModel> products = productSearch.searchResult(keyword);
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8" content="">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		 <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

		<title>eBid:Search</title>
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
 		<script type="text/javascript" src="style/js/calculateTime.js"></script>
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
		<jsp:include page="/WEB-INF/views/Page_Segment/navBar.jsp"></jsp:include>
		<!-- /NAVIGATION -->
		<!-- BREADCRUMB -->
		<div id="breadcrumb" class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<div class="col-md-12">
						<ul class="breadcrumb-tree">
							<li><a href="index.jsp">Home</a></li>
							<li><a href="#">All Categories</a></li>
							<li class="active"><%out.print(keyword+" ("+products.size() +" Results)"); %></li>
						</ul>
					</div>
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /BREADCRUMB -->
		<div id="aside" class="col-md-3">
		<!-- aside Widget -->
				<div class="aside">
					<h3 class="aside-title">Top Bidding</h3>
					<%
					List<Integer> top = productSearch.getTopBiddingProduct();
					for(int pid:top){
						ProductModel topproduct = productSearch.selectByID(pid);
						if(topproduct.getWinner()!=0){
							continue;
						}
					%>
					  <div class="product-widget">
						<div class="product-img">
						<img src="<%out.print(topproduct.getImage(0)); %>" alt="No image found">
						</div>
						<div class="product-body">
						 <p class="product-category"  id="<%out.print("p"+topproduct.getPid());%>">
						 <script >countDown("<%out.print(topproduct.getEndDate());%>","<%out.print("p"+topproduct.getPid());%>")</script>
						 </p>
						 <h3 class="product-name"><a href="<%out.print("view?pid="+topproduct.getPid());%>"><%=topproduct.getPname() %></a></h3>
						 <h4 class="product-price">Rs.<%=topproduct.getLastBidPrice() %></h4>
						</div>
					</div>
					<%} %>
				</div>
			</div>
		<div class="container" >
		<div id="store" class="col-md-9">
		<div class="row">
		<%for(ProductModel product:products){ %>
		<!-- product -->
		<div class="col-md-4 col-xs-6">
			<div class="product">
				<div class="product-img"><br/>
				<img src="<%out.print(product.getImage(0)); %>" alt="No Preview Available" style="width: 210px;height: 150px">
				<div class="product-label">
				<span class="sale" id="<%out.print("s"+product.getPid());%>">
				<script >
                    countDown("<%out.print(product.getEndDate());%>","<%out.print("s"+product.getPid());%>")</script>
				</span>
				</div>
				</div>
					<div class="product-body">
						<p class="product-category"><%=product.getCategory() %></p>
						<h3 class="product-name">
							<a href="<%out.print("view?pid="+product.getPid());%>"><%=product.getPname() %></a>
						</h3>
						<h4 class="product-price">
							Rs.<%=product.getLastBidPrice() %>
						</h4>
					</div>
				</div>
			</div>
			<!-- /product -->
			<%
		}
			%>
		</div>
		</div>
	</div>
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