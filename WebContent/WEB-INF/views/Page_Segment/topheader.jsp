<div id="top-header">
	<div class="container">
		<ul class="header-links pull-left">
			<li><a href="#"><i class="fa fa-phone"></i> +94114102189</a></li>
			<li><a href="mailto:store@ebid.com" target="_top"><i class="fa fa-envelope-o"></i> store@ebid.com</a></li>
			<li><a href="#"><i class="fa fa-map-marker"></i> New Kandy Rd,Malabe</a></li>
		</ul>
		<ul class="header-links pull-right">
			<li><a href="#">LKR</a></li>
			<li>
		<%if(null==session.getAttribute("loggedinUser")){
    	out.print("<a href='Login'><i class='fa fa-user-o'></i> Log in</a>");
    	}
		else{
			out.print("<a href='myaccount'><i class='fa fa-user-o'></i> My Account</a>");
		}
		%>
		</li>
		</ul>
	</div>
</div>