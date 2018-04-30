<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Register</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
<!--===============================================================================================-->
</head>
<body>
	
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<form class="login100-form validate-form p-l-55 p-r-55 p-t-178" style="height:800px" action="./register" method="post">
					<span class="login100-form-title">
						Sign up
					</span>

					<div class="wrap-input100 validate-input m-b-16" style="margin:15px" data-validate="Please enter first name">
						<input class="input100" type="text" name="firstName" placeholder="First Name">
						<span class="focus-input100"></span>
					</div>

					<div class="wrap-input100 validate-input" style="margin:15px"  data-validate = "Please enter last name">
						<input class="input100" type="text" name="lastName" placeholder="Last Name">
						<span class="focus-input100"></span>
					</div>
					
					<div class="wrap-input100 validate-input" style="margin:15px" data-validate = "Please enter email">
						<input class="input100" type="text" name="email" placeholder="Email">
						<span class="focus-input100"></span>
					</div>

				<div class="wrap-input100 validate-input" style="margin:15px" data-validate = "Please enter password">
						<input class="input100" type="password" name="pass" placeholder="Password">
						<span class="focus-input100"></span>
					</div>

					
				<div class="wrap-input100 validate-input" style="margin:15px" data-validate = "Please enter confirm password ">
						<input class="input100" type="password" name="password" placeholder="Password again">
						<span class="focus-input100"></span>
					</div>
					
				<div class="wrap-input100 validate-input" style="margin:15px" data-validate = "Please enter date of birth">
						<input class="input100" type="text" name="dateOfBirth" placeholder="Date of birth(YYYY-MM-DD)">
						<span class="focus-input100"></span>
					</div>	
						
				<div class="container-login100-form-btn" style="margin:15px" >
				<button class="login100-form-btn">
					Register
				</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	
<!--===============================================================================================-->
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/bootstrap/js/popper.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/daterangepicker/moment.min.js"></script>
	<script src="vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
	<script src="js/main.js"></script>

</body>
</html>