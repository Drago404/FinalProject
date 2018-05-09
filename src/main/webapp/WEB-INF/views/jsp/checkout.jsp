<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>


<meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title  -->
    <title> Checkout</title>

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
                        <div class="checkout_details_area mt-50 clearfix">

                            <div class="cart-page-heading">
                                <h5>Please enter the information of your Order</h5>

                            </div>

                            <form action="./checkout" method="post">
                                <div class="row">
                                    <div class="col-md-6 mb-3">
                                        <label for="first_name">First Name <span>*</span></label>
                                        <input type="text" class="form-control" name="firstName" value="${sessionScope.firstName}" required>
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <label for="last_name">Last Name <span>*</span></label>
                                        <input type="text" class="form-control" name="lastName" value="${sessionScope.lastName}" required>
                                    </div>
                                    <div class="col-12 mb-3">
                                        <label for="phone_number">E-mail <span>*</span></label>
                                        <input type="text" class="form-control" name="email"  value="${sessionScope.email}">
                                    </div>
                                    <div class="col-12 mb-3">
                                        <label for="phone_number">Phone No <span>*</span></label>
                                        <input type="text" class="form-control" name="phoneNumber"  value="" required>
                                    </div>
                                    <div class="col-12 mb-3">
                                        <label for="first_name">City <span>*</span></label>
                                        <input type="text" class="form-control" name="city" value="" required>
                                    </div>
                                    <div class="col-12 mb-3">
                                        <label for="last_name">Street <span>*</span></label>
                                        <input type="text" class="form-control" name="street" value="" required>
                                    </div>
                                     <div class="col-md-6 mb-3">
                                        <label for="first_name">Post Code <span>*</span></label>
                                        <input type="number" class="form-control" name="postCode" value="" required>
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <label for="last_name">Street Number </label>
                                        <input type="number" class="form-control" name="streetNumber" value="" >
                                    </div>
                                     <div class="col-md-6 mb-3">
                                        <label for="last_name">Block </label>
                                        <input type="text" class="form-control" name="block" value="" >
                                    </div>
                                     <div class="col-md-6 mb-3">
                                        <label for="last_name">Entrance </label>
                                        <input type="text" class="form-control" name="enterance" value="" >
                                    </div>
                                     <div class="col-md-6 mb-3">
                                        <label for="last_name">Floor </label>
                                        <input type="number" class="form-control" name="floor" value="" >
                                    </div>
                                     <div class="col-md-6 mb-3">
                                        <label for="last_name">Apartment </label>
                                        <input type="text" class="form-control" name="apartment" value="" >
                                    </div>
                                    <div class="col-12 mb-3">
                                        <label for="company">Additional information</label>
                                        <input type="text" class="form-control" name="description" value="">
                                    </div>
                                   
                                   
                                   <button class="btn karl-checkout-btn">Place Order</button>
                                    
                                </div>
                            </form>
                        </div>
                    </div>
                    
                    
                    <div class="col-12 col-md-6 col-lg-5 ml-lg-auto">
                        <div class="order-details-confirmation">

                            <div class="cart-page-heading">
                                <h5>Your Order</h5>
                                <p>The Details</p>
                            </div>

                            <ul class="order-details-form mb-4">
                                <li><span>Number of products</span> <span>${sessionScope.userItems}</span></li>
                                <li><span>Shipping</span> <span>Free</span></li>
                                <li><span>Total price</span> <span>${sessionScope.totalPrice} lv.</span></li>
                            </ul>


                            <div id="accordion" role="tablist" class="mb-4">
                               
                                <div class="card">
                                    <div class="card-header" role="tab" id="headingTwo">
                                        <h6 class="mb-0">
                                            <a class="collapsed" data-toggle="collapse" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo"><i class="fa fa-circle-o mr-3"></i>cash on delievery</a>
                                        </h6>
                                    </div>
                                    <div id="collapseTwo" class="collapse" role="tabpanel" aria-labelledby="headingTwo" data-parent="#accordion">
                                        <div class="card-body">
                                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo quis in veritatis officia inventore, tempore provident dignissimos.</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="card">
                                    <div class="card-header" role="tab" id="headingThree">
                                        <h6 class="mb-0">
                                            <a class="collapsed" data-toggle="collapse" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree"><i class="fa fa-circle-o mr-3"></i>credit card</a>
                                        </h6>
                                    </div>
                                    <div id="collapseThree" class="collapse" role="tabpanel" aria-labelledby="headingThree" data-parent="#accordion">
                                        <div class="card-body">
                                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Esse quo sint repudiandae suscipit ab soluta delectus voluptate, vero vitae</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
<!--
                            <a href="#" class="btn karl-checkout-btn">Place Order</a> -->
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
<script src="js/search_product.js"></script>
<script type="text/javascript">
	  function quantityInput(){
		  return document.getElementById('quantity_input').value;
	  }
	</script>

</body>
</html>