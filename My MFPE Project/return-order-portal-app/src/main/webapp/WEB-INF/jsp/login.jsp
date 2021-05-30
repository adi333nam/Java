<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset='utf-8'>
<meta name='viewport' content='width=device-width, initial-scale=1'>
<title>Login</title>
<link
	href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css'
	rel='stylesheet'>
<link
	href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'
	rel='stylesheet'>
<script type='text/javascript'
	src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
<script type='text/javascript'
	src='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js'></script>
<script type='text/javascript'></script>
<link rel="stylesheet" href="login.css" />
<script src="login.js"></script>

</head>
<body oncontextmenu='return false' class='snippet-body'>
	<div class="container-fluid px-1 px-md-5 px-lg-1 px-xl-5 py-5 mx-auto">
		<div class="card card0 border-0">
		
			<div class="row d-flex">
				<div class="col-lg-6">
					<div class="card1 pb-5">
						<div class="row">
							<img
								src="icon1.png" alt=""
								class="logo">
						</div>
						<div class="row px-3 justify-content-center mt-4 mb-5 border-line">
							<img
								src="https://www.secondnaturebyhand.com/DSN/secondnaturebyhandcom/Content/Images/Return-policy.png"
								class="image" alt="">
						</div>
					</div>
				</div>
				<div class="col-lg-6">
					<div class="card2 card border-0 px-4 py-5">
			
				<div class="row mb-4 px-3">
     			<h5 class="mb-0 mr-4 mt-2"><span style="color:red">${error}</span></h5>
			</div>
					
						<div class="row mb-4 px-3">
							<h2 class="mb-0 mr-4 mt-2">Return Order Management</h2>
						</div>
						<div class="row px-3 mb-4">
							<h4 class="mb-0 mr-4 mt-2">Sign in to your account</h4>
						</div>

						<form action="/processRequest" method='POST'
							onsubmit="validateLogin()">
							<div class="row px-3">
								<label class="mb-1">
									<h6 class="mb-0 text-sm">Username</h6>
								</label> <input class="form-control mb-4" type="text" id="username"
								name="userName"
									name="email" placeholder="Enter Username"
									onchange="return validateUserName()"
									oninput="return validateUserName()"
									oninvalid="return validateUserName()" required>
								<div class="valid-feedback" id="successUserName"></div>
								<div class="invalid-feedback" id="errorUserName"></div>
							</div>
							<div class="row px-3">
								<label class="mb-1">
									<h6 class="mb-0 text-sm">Password</h6>
								</label> <input type="password" class="form-control" id="password"
									name="password" placeholder="Enter Password"
									onchange="return validatePassword()"
									oninput="return validatePassword()"
									oninvalid="return validatePassword()" required>
								<div class="valid-feedback" id="successPassword"></div>
								<div class="invalid-feedback" id="errorPassword"></div>
							</div>
							<div class="row px-3 mb-4"></div>
							<div class="row mb-3 px-3">
								<button type="submit" name="submit" value="submit"
									class="btn btn-blue text-center">Login</button>
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="bg-blue py-4">
				<div class="row px-3">
					<small class="ml-4 ml-sm-5 mb-2">Copyright &copy; 2021. All
						rights reserved.</small>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
