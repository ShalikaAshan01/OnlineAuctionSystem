<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%if(null==session.getAttribute("loggedinUser"))
    	response.sendRedirect("loginView.jsp");
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="icon" type="image/png" href="style/images/icons/favicon.png"/>
<link rel="stylesheet" type="text/css" href="style/vendor/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="/style/vendor/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/style/vendor/bootstrap/js/bootstrap.min.js"></script>
<title>eBid: Logout</title>
</head>
<body>

	<div>Are you sure you want to logout ?<br/>
	<form action="Logout" method="post">
	<button type="submit" class="btn">Logout</button>
	<button type="button" class="btn"  onclick="window.location.href='index.jsp'">Cancel</button></form></div>
</body>
</html>