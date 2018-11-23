		<nav id="navigation">
			<!-- container -->
			<div class="container">
				<!-- responsive-nav -->
				<div id="responsive-nav">
					<!-- NAV -->
					<ul class="main-nav nav navbar-nav">
						<li><a href="index.jsp">Home</a></li>
						<li><a href="search?keyword=computer">Computers</a></li>
						<li><a href="search?keyword=smartphones">Smartphones</a></li>
						<li><a href="search?keyword=cameras">Cameras</a></li>
						<li><a href="search?keyword=accessories">Accessories</a></li>
						<%if(null !=session.getAttribute("loggedinUser")){ %>
						<li><a href="logout">Logout</a></li>
						<%} %>
					</ul>
					<!-- /NAV -->
				</div>
				<!-- /responsive-nav -->
			</div>
			<!-- /container -->
		</nav>