<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.model.Item"%>
    
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html SYSTEM "about:legacy-compat">
<html>
<head>

<title>${item.name}</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="OneTech shop project">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="styles/bootstrap4/bootstrap.min.css">
<link href="plugins/fontawesome-free-5.0.1/css/fontawesome-all.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.carousel.css">
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/animate.css">
<link rel="stylesheet" type="text/css" href="styles/product_styles.css">
<link rel="stylesheet" type="text/css" href="styles/product_responsive.css">

</head>
<body>

<div class="super_container">
	
	<!-- Header + TopBar -->
	
	<jsp:include page="header.jsp"></jsp:include>
	

	<!-- Single Product -->



	<div class="single_product">
		<div class="container">
			<div class="row">
				<div> <c:set var="id" value='${item.id}' />  </div>
			<c:choose> 
				<c:when	test="${sessionScope.isAdmin}">
									
									
				<div class="col-lg-5 order-lg-2 order-1">
					<div class="image_selected"><img src= "${item.pictureUrl}" alt=""></div>
					<form action="./changePic/${item.id}" method="post" enctype="multipart/form-data" >
						<div> 
							<input	type="file" class="file" name="file" value="" required	accept="image/jpg, image/jpeg, image/png">
						</div>

						<button onclick="editPicture" class="button cart_button">Change picture</button>
					</form>
				</div>

				<!-- Description -->
				<div class="col-lg-5 order-3">
					<div class="product_description">
					
					<form method="post" id="form1" >
					
						<div class="newsletter_title">Name</div>
						
						<textarea name = "item_name" id = "item_name" rows="1" cols="50">${item.name}</textarea>
						
					<!--  	<div class="rating_r rating_r_4 product_rating"><i></i><i></i><i></i><i></i><i></i></div>   -->
					
						<div class="newsletter_title">Description</div>
						<textarea name = "item_description" rows="10" cols="50">${item.description}</textarea>
						
						
						
						<div class="order_info d-flex flex-row">
							
							
							<div class="newsletter_title">Price</div>
									<input type="number" name = "item_price" value ="${item.price}" >
								
							</div>
						<div class="order_info d-flex flex-row">
							<div class="newsletter_title">Quantity</div>
									<input type="number"  name = "item_quantity" value = "${item.quantity}"></div>
						<div class="button_container">
													<!-- "redirectOne()" -->
								<button onclick="editItem" class="button cart_button">Change description</button>
								</div>
						
						</form>
					</div>
				</div>
									
									
									</c:when>
									<c:otherwise>
									
									<!-- Selected Image -->
				<div class="col-lg-5 order-lg-2 order-1">
					<div class="image_selected"><img src= "${item.pictureUrl}" alt=""></div>
				</div>

				<!-- Description -->
				<div class="col-lg-5 order-3">
					<div class="product_description">
						<div class="product_category">Laptops</div>
						<div class="product_name">${item.name}</div>
					<!--  	<div class="rating_r rating_r_4 product_rating"><i></i><i></i><i></i><i></i><i></i></div>   -->


						<div class="product_text"><p>${item.description}</p></div>
						<div class="order_info d-flex flex-row">
							<form action="#">
								<div class="clearfix" style="z-index: 1000;">

									<!-- Product Quantity -->
									<div class="product_quantity clearfix">
										<span>Quantity: </span>
										<input id="quantity_input" type="text" pattern="[1-9]" value="1">
										<div class="quantity_buttons">
											<div id="quantity_inc_button" class="quantity_inc quantity_control"><i class="fas fa-chevron-up"></i></div>
											<div id="quantity_dec_button" class="quantity_dec quantity_control"><i class="fas fa-chevron-down"></i></div>
										</div>
									</div>

									<!-- Product Color 
									<ul class="product_color">
										<li>
											<span>Color: </span>
											<div class="color_mark_container"><div id="selected_color" class="color_mark"></div></div>
											<div class="color_dropdown_button"><i class="fas fa-chevron-down"></i></div>

											<ul class="color_list">
												<li><div class="color_mark" style="background: #999999;"></div></li>
												<li><div class="color_mark" style="background: #b19c83;"></div></li>
												<li><div class="color_mark" style="background: #000000;"></div></li>
											</ul>
										</li>
									</ul>
									-->
								</div>
								<div> <c:set var="id" value='${item.id}' />  </div>

								<div class="product_price">${item.price} лв</div>
								<div class="button_container">
								
								<c:choose> 
									<c:when	test = "${sessionScope.id == null}">
										<button type="button" onclick="window.location.href='/FinalProject/login';" class="button cart_button">Add to Cart</button>
									</c:when>
									<c:otherwise>  				<!-- "redirectOne()" -->
										<button type="button" onclick="window.location.href='/FinalProject/addItem?itemId=' + '${item.id}' +'&itemQuantity=' + quantityInput() ;" class="button cart_button">Add to Cart</button>
									</c:otherwise>
								</c:choose>
								<button type="button" onclick="window.location.href='/FinalProject/addWishlist?itemId=' + '${item.id}';" class="button cart_button">Add to wishlist</button>
								<!-- 	<div class="product_fav" onclick="window.location.href='/FinalProject/addWishlist?itemId= + '${item.id}';"><i class="fas fa-heart"></i></div>
								 -->
								
								
								</div>
								
								
							</form>
						</div>
					</div>
				</div>
									</c:otherwise>
								</c:choose>

				<!-- Images 
				<div class="col-lg-2 order-lg-1 order-2">
					<ul class="image_list">
						<li data-image="images/single_4.jpg"><img src="images/single_4.jpg" alt=""></li>
						<li data-image="images/single_2.jpg"><img src="images/single_2.jpg" alt=""></li>
						<li data-image="images/single_3.jpg"><img src="images/single_3.jpg" alt=""></li>
					</ul>
				</div>
-->
				

			</div>
		</div>
	</div>

	<!-- Recently Viewed -->

	

	<!-- Brands -->

	<div class="brands">
		<div class="container">
			<div class="row">
				<div class="col">
					<div class="brands_slider_container">
						
						<!-- Brands Slider -->

						<div class="owl-carousel owl-theme brands_slider">
							
							<div class="owl-item"><div class="brands_item d-flex flex-column justify-content-center"><img src="images/brands_1.jpg" alt=""></div></div>
							<div class="owl-item"><div class="brands_item d-flex flex-column justify-content-center"><img src="images/brands_2.jpg" alt=""></div></div>
							<div class="owl-item"><div class="brands_item d-flex flex-column justify-content-center"><img src="images/brands_3.jpg" alt=""></div></div>
							<div class="owl-item"><div class="brands_item d-flex flex-column justify-content-center"><img src="images/brands_4.jpg" alt=""></div></div>
							<div class="owl-item"><div class="brands_item d-flex flex-column justify-content-center"><img src="images/brands_5.jpg" alt=""></div></div>
							<div class="owl-item"><div class="brands_item d-flex flex-column justify-content-center"><img src="images/brands_6.jpg" alt=""></div></div>
							<div class="owl-item"><div class="brands_item d-flex flex-column justify-content-center"><img src="images/brands_7.jpg" alt=""></div></div>
							<div class="owl-item"><div class="brands_item d-flex flex-column justify-content-center"><img src="images/brands_8.jpg" alt=""></div></div>

						</div>
						
						<!-- Brands Slider Navigation -->
						<div class="brands_nav brands_prev"><i class="fas fa-chevron-left"></i></div>
						<div class="brands_nav brands_next"><i class="fas fa-chevron-right"></i></div>

					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Newsletter -->

	<div class="newsletter">
		<div class="container">
			<div class="row">
				<div class="col">
					<div class="newsletter_container d-flex flex-lg-row flex-column align-items-lg-center align-items-center justify-content-lg-start justify-content-center">
						<div class="newsletter_title_container">
							<div class="newsletter_icon"><img src="images/send.png" alt=""></div>
							<div class="newsletter_title">Sign up for Newsletter</div>
							<div class="newsletter_text"><p>...and receive %20 coupon for first shopping.</p></div>
						</div>
						<div class="newsletter_content clearfix">
							<form action="#" class="newsletter_form">
								<input type="email" class="newsletter_input" required="required" placeholder="Enter your email address">
								<button class="newsletter_button">Subscribe</button>
							</form>
							<div class="newsletter_unsubscribe_link"><a href="#">unsubscribe</a></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Footer -->

	<footer class="footer">
		<div class="container">
			<div class="row">

				<div class="col-lg-3 footer_col">
					<div class="footer_column footer_contact">
						<div class="logo_container">
							<div class="logo"><a href="#">OneTech</a></div>
						</div>
						<div class="footer_title">Got Question? Call Us 24/7</div>
						<div class="footer_phone">+38 068 005 3570</div>
						<div class="footer_contact_text">
							<p>17 Princess Road, London</p>
							<p>Grester London NW18JR, UK</p>
						</div>
						<div class="footer_social">
							<ul>
								<li><a href="#"><i class="fab fa-facebook-f"></i></a></li>
								<li><a href="#"><i class="fab fa-twitter"></i></a></li>
								<li><a href="#"><i class="fab fa-youtube"></i></a></li>
								<li><a href="#"><i class="fab fa-google"></i></a></li>
								<li><a href="#"><i class="fab fa-vimeo-v"></i></a></li>
							</ul>
						</div>
					</div>
				</div>

				<div class="col-lg-2 offset-lg-2">
					<div class="footer_column">
						<div class="footer_title">Find it Fast</div>
						<ul class="footer_list">
							<li><a href="#">Computers & Laptops</a></li>
							<li><a href="#">Cameras & Photos</a></li>
							<li><a href="#">Hardware</a></li>
							<li><a href="#">Smartphones & Tablets</a></li>
							<li><a href="#">TV & Audio</a></li>
						</ul>
						<div class="footer_subtitle">Gadgets</div>
						<ul class="footer_list">
							<li><a href="#">Car Electronics</a></li>
						</ul>
					</div>
				</div>

				<div class="col-lg-2">
					<div class="footer_column">
						<ul class="footer_list footer_list_2">
							<li><a href="#">Video Games & Consoles</a></li>
							<li><a href="#">Accessories</a></li>
							<li><a href="#">Cameras & Photos</a></li>
							<li><a href="#">Hardware</a></li>
							<li><a href="#">Computers & Laptops</a></li>
						</ul>
					</div>
				</div>

				<div class="col-lg-2">
					<div class="footer_column">
						<div class="footer_title">Customer Care</div>
						<ul class="footer_list">
							<li><a href="#">My Account</a></li>
							<li><a href="#">Order Tracking</a></li>
							<li><a href="#">Wish List</a></li>
							<li><a href="#">Customer Services</a></li>
							<li><a href="#">Returns / Exchange</a></li>
							<li><a href="#">FAQs</a></li>
							<li><a href="#">Product Support</a></li>
						</ul>
					</div>
				</div>

			</div>
		</div>
	</footer>

	<!-- Copyright -->

	<div class="copyright">
		<div class="container">
			<div class="row">
				<div class="col">
					
					<div class="copyright_container d-flex flex-sm-row flex-column align-items-center justify-content-start">
						<div class="copyright_content"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
</div>
						<div class="logos ml-sm-auto">
							<ul class="logos_list">
								<li><a href="#"><img src="images/logos_1.png" alt=""></a></li>
								<li><a href="#"><img src="images/logos_2.png" alt=""></a></li>
								<li><a href="#"><img src="images/logos_3.png" alt=""></a></li>
								<li><a href="#"><img src="images/logos_4.png" alt=""></a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="js/jquery-3.3.1.min.js"></script>
<script src="styles/bootstrap4/popper.js"></script>
<script src="styles/bootstrap4/bootstrap.min.js"></script>
<script src="plugins/greensock/TweenMax.min.js"></script>
<script src="plugins/greensock/TimelineMax.min.js"></script>
<script src="plugins/scrollmagic/ScrollMagic.min.js"></script>
<script src="plugins/greensock/animation.gsap.min.js"></script>
<script src="plugins/greensock/ScrollToPlugin.min.js"></script>
<script src="plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
<script src="plugins/easing/easing.js"></script>
<script src="js/product_custom.js"></script>
<script src="js/search_product.js"></script>



</body>
</html>