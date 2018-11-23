<%@page import="service.ProductService"%>
<%@page import="service.ProductServiceImpl"%>
<%@page import="model.PersonModel"%>
<%@page import="service.PersonService"%>
<%@page import="java.util.List"%>
<div id="header">
				<!-- container -->
				<div class="container">
					<!-- row -->
					<div class="row">
						<!-- LOGO -->
						<div class="col-md-3">
							<div class="header-logo">
								<a href="#" class="logo">
									<img src="style/images/logo.png" alt="">
								</a>
							</div>
						</div>
						<!-- /LOGO -->

						<!-- SEARCH BAR -->
						<div class="col-md-6">
							<div class="header-search">
								<form action="search" method="GET">
									<select class="input-select">
										<option value="0">All Categories</option>
									</select>
									<input class="input" name="keyword" placeholder="Search here">
									<button type="submit" class="search-btn">Search</button>
								</form>
							</div>
						</div>
						<!-- /SEARCH BAR -->

						<!-- ACCOUNT -->
						<div class="col-md-3 clearfix">
							<div class="header-ctn">
								<!-- Wishlist -->
								<div>
									<a href="myfavorite">
										<i class="fa fa-heart-o"></i>
										<span>My Favorite</span>
										<div class="qty">
										<%
										if(null != session.getAttribute("loggedinUser")){
											PersonModel person = (PersonModel)session.getAttribute("loggedinUser");
											ProductService product = new ProductServiceImpl();
											List<Integer> qty = product.getAllFavoriteByCID(person.getCid());
											out.print(qty.size());
										}else{
											out.print(0);
										}
										%>
										</div>
									</a>
								</div>
								<!-- Wishlist -->

								<!-- Menu Toogle -->
								<div class="menu-toggle">
									<a href="#">
										<i class="fa fa-bars"></i>
										<span>Menu</span>
									</a>
								</div>
								<!-- /Menu Toogle -->
							</div>
						</div>
						<!-- /ACCOUNT -->
					</div>
					<!-- row -->
				</div>
				<!-- container -->
			</div>