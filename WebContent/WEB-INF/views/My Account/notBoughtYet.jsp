<%@page import="service.ProductService"%>
<%@page import="service.ProductServiceImpl"%>
<%@page import="model.ProductModel"%>
<%@page import="model.PersonModel" %>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>
<%
    PersonModel person = null;
    if(null ==session.getAttribute("loggedinUser")){
    	session.setAttribute("from", "notBought");
    	RequestDispatcher dispatcher = request.getRequestDispatcher("login");
		dispatcher.forward( request, response);
    }else{
    	person = (PersonModel)session.getAttribute("loggedinUser");
    }
    	%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>eBid:Show My Products-Customer not Bought Yet</title>
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
	<link rel="stylesheet" type="text/css" href="style/css/adddel.css">
	<script type="text/javascript" src="style/js/calculateTime.js"></script>
	 		<style type="text/css">
 		.button2[disabled]{
  			cursor: not-allowed;
		}
		</style>

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
<table id="products">

<tr>
<th>Product ID</th>
<th>Product Name</th>
<th>Category</th>
<th>Added Date</th>
<th>Time Left</th>
<th>Last Bid Price</th>
<th>Win By</th>
<th>Send to Customer</th>

	</tr>			
						<% ProductService prod = new ProductServiceImpl();
							
							ArrayList<ProductModel> pro = prod.notBoughtyetProductBySellerId(person.getCid()); 
							if(pro.size()==0)
							{
								RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/Messages/productNotFound.jsp");
								rd.forward(request, response);
							}
							for(ProductModel p1:pro){
															
							
						%>		
								<tr>
									<td><%= p1.getPid() %></td>
									<td><a href="<%out.print("view?pid="+p1.getPid());%>"><%= p1.getPname() %></a></td>
									<td><%= p1.getCategory() %></td>
									<td><%= p1.getAddeddate() %></td>
									<td>
									<span id="<%out.print("s"+p1.getPid());%>" style="color: red">
                    				<script>countDown("<%out.print(p1.getEndDate());%>","<%out.print("s"+p1.getPid());%>");</script>
                    				</span>
									</td>
									<td><%= p1.getLastBidPrice()%></td>
									<td><%
									if(p1.getWinner()==0){
										out.print("unknown");
									}else{
										out.print(p1.getWinner());
									}
									%></td>
									<td> <button onclick="location.href='<%out.print("updateWinner?pid=" + p1.getPid()); %>';" class="button2" 
									<%if(p1.getWinner()!=0){
									out.print("disabled");	
									}
										%>
									>Send Customer to Buy</button></td>
								</tr>
								<%} %>
								
	</table>		
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