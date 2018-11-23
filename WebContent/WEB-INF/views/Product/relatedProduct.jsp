		<!-- /SECTION -->

		<!-- Section -->
<%@page import="service.ProductService"%>
<%@page import="service.ProductServiceImpl"%>
<%@page import="model.ProductModel"%>
<%@page import="java.util.ArrayList"%>
<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">

					<div class="col-md-12">
						<div class="section-title text-center">
							<h3 class="title">Related Products</h3>
						</div>
					</div>
					<!-- product  -->
					<%
					
					ProductModel product = new ProductModel();
					if(null!=request.getAttribute("SelectedProduct")){
					product = (ProductModel)request.getAttribute("SelectedProduct");
					}
					
					ProductService relproduct = new ProductServiceImpl(); 
					ArrayList<ProductModel> relateProductArray =  relproduct.showRelatedProduct(product.getPid(),product.getCategory());
					int count = 1;
					for(ProductModel relatedproduct:relateProductArray){
					%>
						<div class="col-md-3 col-xs-6">
						<div class="product">
							<div class="product-img">
								<img src="<%out.print(relatedproduct.getImage(0)); %>" alt="No Preview Available"  style="width: 260px;height: 180px;">
								<div class="product-label">
									<span class="sale" id="<%out.print("span"+count);%>">
									<script type="text/javascript">
									countDown("<%out.print(relatedproduct.getEndDate());%>","<%out.print("span"+count);%>");
									</script>
									</span>
								</div>
							</div>
							<div class="product-body">
								<p class="product-category"><%=relatedproduct.getPcondition() %></p>
								<h3 class="product-name"><a href="<%out.print("view?pid="+relatedproduct.getPid());%>"><%=relatedproduct.getPname() %></a></h3>
								<h4 class="product-price">Rs. <%=relatedproduct.getLastBidPrice() %></h4>
							</div>
						</div>
					</div>
					<%
					count++;
					}
					%>
					
					<!-- /product -->
					
				</div>
				<!-- /row -->
			</div>
		</div>