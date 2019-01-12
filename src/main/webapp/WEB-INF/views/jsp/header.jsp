<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>


</head>

<body>

<div class="super_container">


<!-- Header -->
	
	<header class="header">

		<!-- Top Bar -->

		<div class="top_bar">
			<div class="container">
				<div class="row">
					<div class="col d-flex flex-row">
						<div class="top_bar_contact_item"><div class="top_bar_icon"><img src="images/phone.png" alt=""></div>+359888068005</div>
						<div class="top_bar_contact_item"><div class="top_bar_icon"><img src="images/mail.png" alt=""></div><a href="">technomarket@gmail.com</a></div>
						<div class="top_bar_content ml-auto">
							<div class="top_bar_menu">
						
							</div>


								<!-- Admin -->
								
										<c:if test="${sessionScope.isAdmin}">
											<div>
												<a href="allUsers">All users</a>
												<a href="newItem">Add item</a>
											</div>
										</c:if>
								

								<div class="top_bar_user">
								<div class="user_icon"><img src="images/user.svg" alt=""></div>
								<c:choose> 
									<c:when	test = "${sessionScope.id == null}">
										<div><a href="register">Register</a></div>
										<div><a href="login">Sign in</a></div>
									</c:when>
									<c:otherwise>
										<div><a href="">${sessionScope.firstName}</a></div>
										<div><a href="http://localhost:8080/FinalProject/logout">Log out</a></div>
									</c:otherwise>
								</c:choose>
								
							</div>
						</div>
					</div>
				</div>
			</div>		
		</div>
		<div class="header_main">
			<div class="container">
				<div class="row">

					<!-- Logo -->
					<div class="col-lg-2 col-sm-3 col-3 order-1">
						<div class="logo_container" style="float:right" >
							<div class="logo"><a href="http://localhost:8080/FinalProject/index">Technomarket</a></div>
						</div>
					</div>

					<!-- Search -->
					<div class="col-lg-6 col-12 order-lg-2 order-3 text-lg-left text-right">
						<div class="header_search" >
							<div class="header_search_content">
								<div class="header_search_form_container">
									<form id = "searchForm" onsubmit="searchFunction()" class="header_search_form clearfix">
										<input type="search" name ="txtSearch" required="required" class="header_search_input" placeholder="Search for products...">
										<div class="custom_dropdown">
											<div class="custom_dropdown_list">
												<span id ="searchCategory" class="custom_dropdown_placeholder clc"></span>
												<ul class="custom_list clc">
													
												</ul>
											</div>
										</div>
										<button value="" id="btnSearch"  type="submit" class="header_search_button trans_300" value="Submit" ><img src="images/search.png" alt=""></button>
									</form>
								</div>
							</div>
						</div>
					</div>

					<!-- Wishlist -->
					<div class="col-lg-4 col-9 order-lg-3 order-2 text-lg-left text-right">
						<div class="wishlist_cart d-flex flex-row align-items-center justify-content-end">
							<div class="wishlist d-flex flex-row align-items-center justify-content-end">
								<div class="wishlist_icon"><img src="images/heart.png" alt=""></div>
								<div class="wishlist_content">
									<div class="wishlist_text"><a href="http://localhost:8080/FinalProject/wishlist">Wishlist</a></div>
									<div class="wishlist_count">${sessionScope.wishlistNumber}</div>
								</div>
							</div>

							<!-- Cart -->
							<div class="cart">
								<div class="cart_container d-flex flex-row align-items-center justify-content-end">
									<div class="cart_icon">
										<img src="images/cart.png" alt="">
										<div class="cart_count">${sessionScope.userItems}<span></span></div>
									</div>
									<div class="cart_content">
										<div class="cart_text"><a href="http://localhost:8080/FinalProject/cart">Cart</a></div>
										<div class="cart_price">${sessionScope.totalPrice}</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<!-- Main Navigation -->

		<nav class="main_nav">
			<div class="container" >
				<div class="row">
					<div class="col">
						
						<div class="main_nav_content d-flex flex-row">

							<!-- Categories Menu 
							<div class="cat_menu_container">
								<div class="cat_menu_title d-flex flex-row align-items-center justify-content-start">
									<div class="cat_burger"><span></span><span></span><span></span></div>
									<div class="cat_menu_text">Категории</div>
								</div>
								<ul class="cat_menu">
									<li><a href="#">Computers & Laptops <i class="fas fa-chevron-right ml-auto"></i></a></li>
									<li><a href="#">Cameras & Photos<i class="fas fa-chevron-right"></i></a></li>
									<li class="hassubs">
										<a href="#">Hardware<i class="fas fa-chevron-right"></i></a>
										<ul>
											<li class="hassubs">
												<a href="#">Menu Item<i class="fas fa-chevron-right"></i></a>
												<ul>
													<li><a href="#">Menu Item<i class="fas fa-chevron-right"></i></a></li>
													<li><a href="#">Menu Item<i class="fas fa-chevron-right"></i></a></li>
													<li><a href="#">Menu Item<i class="fas fa-chevron-right"></i></a></li>
													<li><a href="#">Menu Item<i class="fas fa-chevron-right"></i></a></li>
												</ul>
											</li>
											<li><a href="#">Menu Item<i class="fas fa-chevron-right"></i></a></li>
											<li><a href="#">Menu Item<i class="fas fa-chevron-right"></i></a></li>
											<li><a href="#">Menu Item<i class="fas fa-chevron-right"></i></a></li>
										</ul>
									</li>
									<li><a href="#">Smartphones & Tablets<i class="fas fa-chevron-right"></i></a></li>
									<li><a href="#">TV & Audio<i class="fas fa-chevron-right"></i></a></li>
									<li><a href="#">Gadgets<i class="fas fa-chevron-right"></i></a></li>
									<li><a href="#">Car Electronics<i class="fas fa-chevron-right"></i></a></li>
									<li><a href="#">Video Games & Consoles<i class="fas fa-chevron-right"></i></a></li>
									<li><a href="#">Accessories<i class="fas fa-chevron-right"></i></a></li>
								</ul>
							</div>
							<!-- Main Nav Menu -->

			<!--		<div class="main_nav_menu ml-auto">   -->		
								<ul class="standard_dropdown main_nav_dropdown" >
									<li class="hassubs" >
										<a href="#">TV and audio<i class="fas fa-chevron-down"></i></a>
										<ul>
											<li>
												<a href="#">TV<i class="fas fa-chevron-down"></i></a>
												<ul>
													<li><a href="http://localhost:8080/FinalProject/tv">All TVs<i class="fas fa-chevron-down"></i></a></li>
													<li><a href="http://localhost:8080/FinalProject/tvbrand?brandId=2">LG<i class="fas fa-chevron-down"></i></a></li>
													<li><a href="http://localhost:8080/FinalProject/tvbrand?brandId=4">NEO<i class="fas fa-chevron-down"></i></a></li>
													<li><a href="http://localhost:8080/FinalProject/tvbrand?brandId=6">Philips<i class="fas fa-chevron-down"></i></a></li>
													<li><a href="http://localhost:8080/FinalProject/tvbrand?brandId=3">Samsung<i class="fas fa-chevron-down"></i></a></li>
												</ul>
											</li>
											<li><a href="#">TV Accessories<i class="fas fa-chevron-down"></i></a></li>
											<li><a href="#">Audio Systems<i class="fas fa-chevron-down"></i></a></li>
											<li><a href="#">Audio Accessories<i class="fas fa-chevron-down"></i></a></li>
										</ul>
									</li>
									<li class="hassubs">
										<a href="#">Computers and perifery<i class="fas fa-chevron-down"></i></a>
										<ul>
											<li>
												<a href="#">Laptops<i class="fas fa-chevron-down"></i></a>
												<ul>
													<li><a href="http://localhost:8080/FinalProject/laptop"">All Laptops<i class="fas fa-chevron-down"></i></a></li>
													<li><a href="http://localhost:8080/FinalProject/laptopbrand?brandId=8">Acer<i class="fas fa-chevron-down"></i></a></li>
													<li><a href="http://localhost:8080/FinalProject/laptopbrand?brandId=9">HP<i class="fas fa-chevron-down"></i></a></li>
												</ul>
											</li>
											<li><a href="#">Computers<i class="fas fa-chevron-down"></i></a></li>
											<li><a href="#">Peripheral devices<i class="fas fa-chevron-down"></i></a></li>
											<li><a href="#">Printers<i class="fas fa-chevron-down"></i></a></li>
										</ul>
									</li>
									
									<li class="hassubs">
										<a href="#">Phones and tablets<i class="fas fa-chevron-down"></i></a>
										<ul>
											<li>
												<a href="">Smartphones<i class="fas fa-chevron-down"></i></a>
												<ul>
													<li><a href="http://localhost:8080/FinalProject/phone">All phones<i class="fas fa-chevron-down"></i></a></li>
													<li><a href="#">Samsung<i class="fas fa-chevron-down"></i></a></li>
												</ul>
											</li>
											<li><a href="#">Tablets<i class="fas fa-chevron-down"></i></a></li>
											<li><a href="#">E-book<i class="fas fa-chevron-down"></i></a></li>
										</ul>
									</li>
									<li class="hassubs">
										<a href="#">Electric appliances<i class="fas fa-chevron-down"></i></a>
										
									</li>
									
									
									<li class="hassubs">
										<a href="#">Photo and video<i class="fas fa-chevron-down"></i></a>

									</li>
									<li><a href="">Contact<i class="fas fa-chevron-down"></i></a></li>
								</ul>
				<!--  		</div>   -->		

							<!-- Menu Trigger -->

							<div class="menu_trigger_container ml-auto">
								<div class="menu_trigger d-flex flex-row align-items-center justify-content-end">
									<div class="menu_burger">
										<div class="menu_trigger_text">menu</div>
										<div class="cat_burger menu_burger_inner"><span></span><span></span><span></span></div>
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</nav>
		
		<!-- Menu -->

		<div class="page_menu">
			<div class="container">
				<div class="row">
					<div class="col">
						
						<div class="page_menu_content">
							
							<div class="page_menu_search">
								<form action="#">
									<input type="search" required="required" class="page_menu_search_input" placeholder="Search for products...">
								</form>
							</div>
							<ul class="page_menu_nav">
								<li class="page_menu_item has-children">
									<a href="#">Language<i class="fa fa-angle-down"></i></a>
									<ul class="page_menu_selection">
										<li><a href="#">English<i class="fa fa-angle-down"></i></a></li>
										<li><a href="#">Italian<i class="fa fa-angle-down"></i></a></li>
										<li><a href="#">Spanish<i class="fa fa-angle-down"></i></a></li>
										<li><a href="#">Japanese<i class="fa fa-angle-down"></i></a></li>
									</ul>
								</li>
								<li class="page_menu_item has-children">
									<a href="#">Currency<i class="fa fa-angle-down"></i></a>
									<ul class="page_menu_selection">
										<li><a href="#">US Dollar<i class="fa fa-angle-down"></i></a></li>
										<li><a href="#">EUR Euro<i class="fa fa-angle-down"></i></a></li>
										<li><a href="#">GBP British Pound<i class="fa fa-angle-down"></i></a></li>
										<li><a href="#">JPY Japanese Yen<i class="fa fa-angle-down"></i></a></li>
									</ul>
								</li>
								<li class="page_menu_item">
									<a href="#">Home<i class="fa fa-angle-down"></i></a>
								</li>
								<li class="page_menu_item has-children">
									<a href="#">Super Deals<i class="fa fa-angle-down"></i></a>
									<ul class="page_menu_selection">
										<li><a href="#">Super Deals<i class="fa fa-angle-down"></i></a></li>
										<li class="page_menu_item has-children">
											<a href="#">Menu Item<i class="fa fa-angle-down"></i></a>
											<ul class="page_menu_selection">
												<li><a href="#">Menu Item<i class="fa fa-angle-down"></i></a></li>
												<li><a href="#">Menu Item<i class="fa fa-angle-down"></i></a></li>
												<li><a href="#">Menu Item<i class="fa fa-angle-down"></i></a></li>
												<li><a href="#">Menu Item<i class="fa fa-angle-down"></i></a></li>
											</ul>
										</li>
										<li><a href="#">Menu Item<i class="fa fa-angle-down"></i></a></li>
										<li><a href="#">Menu Item<i class="fa fa-angle-down"></i></a></li>
										<li><a href="#">Menu Item<i class="fa fa-angle-down"></i></a></li>
									</ul>
								</li>
								<li class="page_menu_item has-children">
									<a href="#">Featured Brands<i class="fa fa-angle-down"></i></a>
									<ul class="page_menu_selection">
										<li><a href="#">Featured Brands<i class="fa fa-angle-down"></i></a></li>
										<li><a href="#">Menu Item<i class="fa fa-angle-down"></i></a></li>
										<li><a href="#">Menu Item<i class="fa fa-angle-down"></i></a></li>
										<li><a href="#">Menu Item<i class="fa fa-angle-down"></i></a></li>
									</ul>
								</li>
								<li class="page_menu_item has-children">
									<a href="#">Trending Styles<i class="fa fa-angle-down"></i></a>
									<ul class="page_menu_selection">
										<li><a href="#">Trending Styles<i class="fa fa-angle-down"></i></a></li>
										<li><a href="#">Menu Item<i class="fa fa-angle-down"></i></a></li>
										<li><a href="#">Menu Item<i class="fa fa-angle-down"></i></a></li>
										<li><a href="#">Menu Item<i class="fa fa-angle-down"></i></a></li>
									</ul>
								</li>
								<li class="page_menu_item"><a href="blog.html">blog<i class="fa fa-angle-down"></i></a></li>
								<li class="page_menu_item"><a href="contact.html">contact<i class="fa fa-angle-down"></i></a></li>
							</ul>
							
							<div class="menu_contact">
								<div class="menu_contact_item"><div class="menu_contact_icon"><img src="images/phone_white.png" alt=""></div>+38 068 005 3570</div>
								<div class="menu_contact_item"><div class="menu_contact_icon"><img src="images/mail_white.png" alt=""></div><a href="mailto:fastsales@gmail.com">fastsales@gmail.com</a></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>