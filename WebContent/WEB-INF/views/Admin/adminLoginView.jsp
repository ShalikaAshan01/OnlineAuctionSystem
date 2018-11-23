<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin login</title>
<link rel="stylesheet" type="text/css" href="style/css/adminstyle.css"/>
</head>
<body>

<div id="outer">
	
	<div id="header">
		<div id="logo">
			<h1>
				Online Auction System
			</h1>
		</div>
	</div>
	
	<div id="banner">
		<img src="style/images/banner.png" alt="banner" width="1180" height="200" />
	</div>
	
	<div>
	
	</div>
	
	<div id="main">
		
		<h3 style="color: navy;" align="center"><B>Administrator Login</B></h3>
		
		<div id="box">
			<form method="post" action="AdminLogin" >
				<table>
					<tr><td style="color:navy;"><B> Login Form</B></td></tr>
					<tr><td><br>
					<%if(null !=request.getAttribute("errorString"))
						out.print(request.getAttribute("errorString"));		
					%>
					</td></tr>
					<tr><td>User Name:</td><td><input type="text" name="userName" /></td></tr>
								<tr><td><br></td></tr>
					<tr><td>Password:</td><td><input type="password" name="password" /></td></tr>	
							<tr><td><br></td></tr>
							<tr><td><br></td></tr>
					<tr><td>      </td><td>
											<input type="submit" value="Submit">
									</td></tr>
					
				</table>
			</form>
			<br>
		</div>
		
	</div>
	
	<div id="header">
		<h4>
			Copyright 2018 All rights reserved.for more information please visit: <a href=""><font style="color: yellow;"></font></a>
		</h4>
	</div>	
</div>


</body>
</html>