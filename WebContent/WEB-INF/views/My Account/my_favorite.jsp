<%@page import="java.util.List"%>
<%@page import="model.ProductModel"%>
<%@page import="model.AuctionModel"%>
<%@page import="java.util.ArrayList"%>
<%@page import="service.ProductServiceImpl"%>
<%@page import="service.ProductService"%>
<%@page import="model.PersonModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>
<%
    PersonModel person = null;
    if(null ==session.getAttribute("loggedinUser")){
    	session.setAttribute("from", "myaccount");
    	RequestDispatcher dispatcher = request.getRequestDispatcher("login");
		dispatcher.forward( request, response);
    }else{
    	person = (PersonModel)session.getAttribute("loggedinUser");
    }
    	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		 <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

		<title>eBid:My Favorite</title>
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
 		
 		<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
		<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
		<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
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
		<jsp:include page="/WEB-INF/views/Page_Segment/myaccountNavBar.jsp"></jsp:include>
		<!-- /NAVIGATION -->

		<div class="container">
	<%
	ProductService favorite = new ProductServiceImpl();
	List<Integer> productidlist = favorite.getAllFavoriteByCID(person.getCid());
	ArrayList<ProductModel> favoriteProduct = new ArrayList<>();
	for(int pid:productidlist){
		ProductModel productObj = favorite.selectByID(pid);
		favoriteProduct.add(productObj);
	}
	int count = 0;
	%>
	
    <hgroup class="mb20">
        <h1>My eBids</h1>
        <h2 class="lead"> Toatal Number of Favorite Products: <strong class="text-danger"><%=favoriteProduct.size() %></strong></h2>
    </hgroup>
    <section class="col-xs-12 col-sm-6 col-md-12">
	<%for(ProductModel product: favoriteProduct){ %>
        <article class="search-result row">
            <div class="col-xs-12 col-sm-12 col-md-3">
                <a href="<%out.print("view?pid="+product.getPid());%>" class="thumbnail"><img src="<%out.print(product.getImage(0)); %>" alt="Picture will avilable soon" /></a>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-2">
                <ul class="meta-search">
                    <li><i class="glyphicon glyphicon-time"></i> 
                    <span id="<%out.print("s"+count);%>">
                    <script>countDown("<%out.print(product.getEndDate());%>","<%out.print("s"+count);%>");</script>
                    </span></li>
                    <li><i class="glyphicon glyphicon-tags"></i> <span><%=product.getCategory() %></span></li>
                </ul>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-7 excerpet">
                <h3><a href="<%out.print("view?pid="+product.getPid());%>" title=""><%=product.getPname() %></a></h3>
              	<strong>Rs.<%=product.getLastBidPrice() %></strong>
                <p><%=product.getDescription() %></p>
            </div>
            <div><button class="btn-danger" onclick="location.href = '<%out.print("delFav?cid="+person.getCid()+"&pid="+product.getPid()+"&from=list");%>';">Remove</button></div>
            <span class="clearfix borda"></span>
        </article>
		<%
		count++;
		} %>

    </section>
</div>


		<!--  FOOTER -->
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