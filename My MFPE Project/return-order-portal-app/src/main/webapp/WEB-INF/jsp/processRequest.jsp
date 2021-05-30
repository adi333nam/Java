<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">
<head>
<title>Process Request Details</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet" href="process-request.css" />
<script src="process-request.js"></script>
<script type="text/javascript">
	function checkHighPriority(that) {
		if (that.value == "INTEGRAL") {

			document.getElementById("ifYes").style.display = "block";
		} else {
		                document.getElementById("isPriorityRequest").value=false;
		
			document.getElementById("ifYes").style.display = "none";
		}
	}
</script>
</head>
<body>

	<!--navbar-->
	<nav class="navbar navbar-expand-sm navbar-dark bg-blue">
		<a class="navbar-brand" href=#> <span
			class="material-icons return-order"> assignment_return </span> Return
			Order Portal
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#toggle">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="toggle">

			<ul class="navbar-nav ml-auto ">
				<li class="navbar-item"><a class="nav-link active"><span
						class="material-icons user-icon"> account_circle </span> ${userName}</a></li>
				<li class="navbar-item"><a class="nav-link active" href="/userLogout">
				<span class="material-icons user-icon">logout</span> Logout</a></li>
			</ul>
		</div>
	</nav>



	<form action="/requestProcessing" method="POST" onsubmit="validateProcessRequest()">
 		<div class="container register">
			<div class="row">
				<div class="col-md-3 register-left">
					<img
						src="icon1.png"
						alt="" />
					<h3>Welcome</h3>
					<p>You are few steps away from returning your order</p>
				</div>
				<div class="col-md-9 register-right">
					<div class="tab-content" id="myTabContent">
						<div class="tab-pane fade show active" id="home" role="tabpanel"
							aria-labelledby="home-tab">
							<h3 class="register-heading">Return Order Details</h3>
							<div class="row register-form">
								<div class="col-md-6">
									<div class="form-group">
										<label>Name</label><span>*</span> <input type="text"
											class="form-control" id="userName"
											name="userName"
											placeholder="Enter Username"
											onchange="return validateUserName()"
											oninput="return validateUserName()"
											oninvalid="return validateUserName()" required>
										<div class="valid-feedback" id="successUserName"></div>
										<div class="invalid-feedback" id="errorUserName"></div>
									</div>
									<div class="form-group">
										<label>Credit Card Number</label><span>*</span> <input
											type="text" class="form-control" id="creditCardNumber"
											name="creditCardNumber"
											placeholder="Enter Credit Card Number"
											onchange="return validateCreditCardNumber()"
											oninput="return validateCreditCardNumber()"
											oninvalid="return validateCreditCardNumber()" required>
										<div class="valid-feedback" id="successCreditCardNumber"></div>
										<div class="invalid-feedback" id="errorCreditCardNumber"></div>
									</div>

									<div class="form-group">
										<label>Component Name</label><span>*</span> <input type="text"
											class="form-control" id="componentName" name="componentName"
											placeholder="Enter Component Name"
											onchange="return validateComponentName()"
											oninput="return validateComponentName()"
											oninvalid="return validateComponentName()" required>
										<div class="valid-feedback" id="successComponentName"></div>
										<div class="invalid-feedback" id="errorComponentName"></div>
									</div>
									<div class="form-group">
										<div id="ifYes" style="display: none;">
											<label for="isPriorityRequest"> </label> <input
												type="checkbox" value="true" id="isPriorityRequest"
												name="isPriorityRequest"> <strong>High Priority Order</strong>

										</div>
										         <div id="ifNo">
                                                <input type="hidden" name="isPriorityRequest" value="false"/> 
                                            </div>
										
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label>Contact Number</label><span>*</span> <input type="text"
											class="form-control" id="contactNumber"
											name="contactNumber"
											placeholder="Enter Contact Number"
											onchange="return validateContactNumber()"
											oninput="return validateContactNumber()"
											oninvalid="return validateContactNumber()" required>
										<div class="valid-feedback" id="successContactNumber"></div>
										<div class="invalid-feedback" id="errorContactNumber"></div>
									</div>
									<div class="form-group">
										<label for="componentType">Select Component Type</label><span>
											*</span> <select class="form-control" id="componentType"
											name="componentType" required="required"
											onchange="checkHighPriority(this);"
											onchange="return validateComponentType()"
											oninput="return validateComponentType()"
											oninvalid="return validateComponentType()" required>
											<option></option>
											<option id="accessory" value="ACCESSORY">Accessory
												Component</option>
											<option id="integral" value="INTEGRAL">Integral
												Component</option>
										</select>
										<div class="valid-feedback" id="successComponentType"></div>
										<div class="invalid-feedback" id="errorComponentType"></div>
									</div>
									<div class="form-group">
										<label>Quantity</label><span>*</span> <input type="text"
											class="form-control" id="quantity" name="quantity"
											placeholder="Enter The Component's Quantity"
											onchange="return validateQuantity()"
											oninput="return validateQuantity()"
											oninvalid="return validateQuantity()" required>
										<div class="valid-feedback" id="successQuantity"></div>
										<div class="invalid-feedback" id="errorQuantity"></div>
									</div>
									<button type="submit" class="btnRegister">Confirm
										Return</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</form>


	<!--footer-->
	<div class="row mt-5">
		<footer class="bg-blue">
			<div class="row px-3">
				<h6 class="ml-4 ml-sm-5 mb-2">Copyright &copy; 2021. All rights
					reserved.</h6>
		</footer>
	</div>
</body>
</html>