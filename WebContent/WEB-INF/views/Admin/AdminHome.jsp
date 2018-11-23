<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="style/css/adminstyle.css" />
<title>Admin Home</title>
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
				<a href="${ pageContext.request.contextPath }/">Home</a>
			</li>
			<li>
				<a href="${ pageContext.request.contextPath }/adminHome">Administrator Home</a>
			</li>
			<li>
				<a href="${ pageContext.request.contextPath }/bidInfoView">Bid Details</a>
			</li>
			<li>
				<a href="${ pageContext.request.contextPath }/UserProfile">User Profile</a>
			</li>
						
			<li>
				<a href="${ pageContext.request.contextPath }/UpdateUserProfile">Update User Profile</a>
			</li>
			<li><a href="adminshowallproduct">Products</a></li>
			</ul><br class="clear" />
	</div>
	
	<div id="main">

	<table align="right">
			
			<tr align="center"><td style="color: navy;"><B>Notice</B></td></tr>
			<tr><td><div><marquee direction="up" width="300px" truespeed="truespeed" onmouseover="stop()" onmouseout="start()" style="color: red;">Welcome Administrator sir.
															     You Can Visit a lot of information
															    
															      </marquee></div></td></tr></table>
	
	<h3 style="color: navy;" align="center"><B>Admin Home</B></h3>
	
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