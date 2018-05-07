<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>


<meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title  -->
    <title> Add new product</title>

    <!-- Favicon  -->
    <link rel="icon" href="img/core-img/favicon.ico">

   <!-- -->
 <!-- Core Style CSS -->
    <link rel="stylesheet" type="text/css" href="styles/core-style.css">
    <link rel="stylesheet" type="text/css" href="styles/style.css">

    <!-- Responsive CSS -->
    <link rel="stylesheet" type="text/css" href="css/responsive.css" >
  
   
<link rel="stylesheet" type="text/css" href="styles/bootstrap4/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="plugins/fontawesome-free-5.0.1/css/fontawesome-all.css"  >
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.carousel.css">
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/animate.css">
<link rel="stylesheet" type="text/css" href="plugins/jquery-ui-1.12.1.custom/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="styles/shop_styles.css">
<link rel="stylesheet" type="text/css" href="styles/shop_responsive.css">

  

</head>


<body>
    
    <jsp:include page="header.jsp"></jsp:include>

    <div id="wrapper">

  

        <!-- ****** Checkout Area Start ****** -->
        <div class="checkout_area section_padding_100">
            <div class="container">
                <div class="row">

                    <div class="col-12 col-md-6">
                    <form method="post" enctype="multipart/form-data">
                        <div class="checkout_details_area mt-50 clearfix">

							<div class="cart-page-heading">
								<h5>Please enter the information of the new product</h5>

							</div>
							
							
							
								<div class="row">
									<div class="col-md-6 mb-3">
										<label for="first_name">Name <span>*</span></label> <input
											type="text" class="form-control" name="name" value=""
											required>
									</div>

									<div class="col-md-6">
										<label >Brand <span>*</span></label> <FONT SIZE="4"> 
											 <select name="brand" style="width: 100px; display: inline-block;">

												<option value="NONE">--- Select ---</option> 
												<option value="Sony">Sony</option>
												<option value="Acer">Acer</option>
												<option value="Philips">Philips</option>
												<option value="Neo">Neo</option>
												<option value="Samsung">Samsung</option>
										</select>


										</FONT>

									</div>

									<div class="col-12 mb-3">
										<label for="phone_number">Price <span>*</span></label> <input
											type="text" class="form-control" name="price" value="">
									</div>
									<div class="col-12 mb-3">
										<label for="phone_number">Quantity <span>*</span></label> <input
											type="text" class="form-control" name="quantity" value="">
									</div>


								</div>
								
									<div class="col-12 mb-3">
										<label >Category <span>*</span></label> <FONT
											 SIZE="4"> <select name="category" style="width: 100px; display: inline-block;">
	
												<option value="NONE">--- Select ---</option>
												<option value="TV">TV</option>
												<option value="laptop">laptop</option>
											
										</select>


										</FONT>

									</div>

								<div class="col-12 mb-3">
									<label for="phone_number">Description <span>*</span></label> <input
										type="text" class="form-control" name="description" value="">
								</div>

								<div class="col-12 mb-3">
									<label for="last_name">Picture <span>*</span></label> <input
										type="file" class="file" name="file" value="" required
										accept="image/jpg, image/jpeg, image/png">

								</div>



								<button onclick="addProduct" class="btn karl-checkout-btn">Add
									product</button>
						</div>
                           </form>
                        </div>
                    </div>
                    
                </div>
            </div>
        </div>
        
        <!-- ****** Checkout Area End ****** -->

        <!-- ****** Footer Area Start ****** -->
        <footer class="footer_area">
            <div class="container">
                <div class="row">
                    <!-- Single Footer Area Start -->
                    <div class="col-12 col-md-6 col-lg-3">
                        <div class="single_footer_area">
                            <div class="footer-logo">
                                <img src="img/core-img/logo.png" alt="">
                            </div>
                            <div class="copywrite_text d-flex align-items-center">
                                <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
                            </div>
                        </div>
                    </div>
                    <!-- Single Footer Area Start -->
                    <div class="col-12 col-sm-6 col-md-3 col-lg-2">
                        <div class="single_footer_area">
                            <ul class="footer_widget_menu">
                                <li><a href="#">About</a></li>
                                <li><a href="#">Blog</a></li>
                                <li><a href="#">Faq</a></li>
                                <li><a href="#">Returns</a></li>
                                <li><a href="#">Contact</a></li>
                            </ul>
                        </div>
                    </div>
                    <!-- Single Footer Area Start -->
                    <div class="col-12 col-sm-6 col-md-3 col-lg-2">
                        <div class="single_footer_area">
                            <ul class="footer_widget_menu">
                                <li><a href="#">My Account</a></li>
                                <li><a href="#">Shipping</a></li>
                                <li><a href="#">Our Policies</a></li>
                                <li><a href="#">Afiliates</a></li>
                            </ul>
                        </div>
                    </div>
                    <!-- Single Footer Area Start -->
                    <div class="col-12 col-lg-5">
                        <div class="single_footer_area">
                            <div class="footer_heading mb-30">
                                <h6>Subscribe to our newsletter</h6>
                            </div>
                            <div class="subscribtion_form">
                                <form action="#" method="post">
                                    <input type="email" name="mail" class="mail" placeholder="Your email here">
                                    <button type="submit" class="submit">Subscribe</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="line"></div>

                <!-- Footer Bottom Area Start -->
                <div class="footer_bottom_area">
                    <div class="row">
                        <div class="col-12">
                            <div class="footer_social_area text-center">
                                <a href="#"><i class="fa fa-pinterest" aria-hidden="true"></i></a>
                                <a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a>
                                <a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a>
                                <a href="#"><i class="fa fa-linkedin" aria-hidden="true"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
        <!-- ****** Footer Area End ****** -->
    </div>
    <!-- /.wrapper end -->

    <!-- jQuery (Necessary for All JavaScript Plugins) -->
    <script src="js/jquery/jquery-2.2.4.min.js"></script>
    <!-- Popper js -->
    <script src="js/popper.min.js"></script>
    <!-- Bootstrap js -->
    <script src="js/bootstrap.min.js"></script>
    <!-- Plugins js -->
    <script src="js/plugins.js"></script>
    <!-- Active js -->
    <script src="js/active.js"></script>
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
<script type="text/javascript">
	function searchFunction(){
    	var action_src = "http://localhost:8080/FinalProject/search/" + document.getElementsByName("txtSearch")[0].value;
    	var form = document.getElementById('searchForm');
    	form.action = action_src ;	
	}

	  function quantityInput(){
		  return document.getElementById('quantity_input').value;
	  }
	</script>

</body>
</html>