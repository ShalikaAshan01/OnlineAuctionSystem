<%@page import="service.ProductService"%>
<%@page import="service.ProductServiceImpl"%>
<%@page import="model.ProductModel"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="style/css/adminstyle.css" />
<title>eBid:Admin-Show All Products</title>
	<link rel="stylesheet" type="text/css" href="style/css/adddel.css">
	
		<style type="text/css">
 		.button2[disabled]{
  		cursor: not-allowed;
		}
		.button1[disabled]{
  		cursor: not-allowed;
		}
		</style>
</head>
<body>
<%response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>
<%if(null==session.getAttribute("admin")){
	RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/Admin/adminLoginView.jsp");
	dispatcher.forward(request, response);
}
%>
<div id = "outer">

	<div id= "header">
		<div id="logo">
			<h1>
				Online Auction System
			</h1>
		</div>
	</div>
	
	<div id="banner">
		<div class="captions">
					<h2><a href="logout">Log out</a></h2>
				</div>
		<img src="style/images/banner.png" alt="banner" width="1180" height="200" />
	</div>
	
		<div id="nav">
		<ul>
			<li class="first">
				<a href="admin">Home</a>
			</li>
			<li><a href="adminshowallproduct">Products</a></li>
			<li><a href="adminshowauctionproduct">Ongoing Auction</a></li>
			<li><a href="adminshowawaitingproduct">Awaiting Confirmation</a></li>
			<li><a href="adminshowsuspendproduct">Suspend Product</a></li>
			</ul><br class="clear" />
	</div>
	
	<div id="main">
<h3 style="color: navy;" align="center"><B>Product-Show all Products</B></h3>

<table id="products">

<tr>
<th>Product ID</th>
<th>Product Name</th>
<th>Category</th>
<th>Added Date</th>
<th>Last Bid Price</th>
<th>Added By</th>
<th>Add</th>
<th>Delete</th>

	</tr>			
						<% ProductService prod = new ProductServiceImpl();
							
							ArrayList<ProductModel> pro = prod.showProducts(""); 
							
							for(ProductModel p1:pro){
															
							
						%>		
								<tr>
									<td><%= p1.getPid() %></td>
									<td><%= p1.getPname() %></td>
									<td><%= p1.getCategory() %></td>
									<td><%= p1.getAddeddate() %></td>
									<td><%= p1.getLastBidPrice()%></td>
									<td><%= p1.getSellerId() %></td>
									<td> <button onclick="location.href='<%out.print("addProductAdmin?pid=" + p1.getPid()); %>';" class="button1"
									<%if(p1.getStatus().equals("auction")){
											out.print("disabled");
									}
										%>
									>
									ADD</button></td>
									<td> <button onclick="location.href='<%out.print("del?pid=" + p1.getPid()); %>';" class="button2" 
									<%if(p1.getStatus().equals("suspend")){
											out.print("disabled");
									}
										%>>DELETE</button></td>
								</tr>
								<%} %>
								
	</table>		

	
	</div>
	<div></div>
	<div id="header">
					<h4>
						Copyright 2018 All rights reserved.fore more information please visit: <a href=""><font style="color: yellow;"></font></a>
					</h4>
	</div>
	
</div>

</body>
</html>