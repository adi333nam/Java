<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Process Response</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <link rel="stylesheet" href="confirmation.css" />



</head>

<body>
    <form action="/payment" method="post">

        <nav class="navbar navbar-expand-sm navbar-dark bg-blue">
            <a class="navbar-brand" href=#>
                <span class="material-icons return-order">
                    assignment_return
                </span>
                Return Order Portal</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#toggle">
                <span class="navbar-toggler-icon"></span></button>
    
            <div class="collapse navbar-collapse" id="toggle">
    
                <ul class="navbar-nav ml-auto ">
                    <li class="navbar-item"><a class="nav-link active"><span
                        class="material-icons user-icon"> account_circle </span> ${userName}</a></li>
                <li class="navbar-item"><a class="nav-link active" href="/userLogout">
                <span class="material-icons user-icon">logout</span> Logout</a></li>
            </ul>
            </div>
        </nav>
    



        <div class="container register">
            <div class="row">
                <div class="col-md-3 register-left">
                    	<img
						src="icon1.png"
						alt="" />
                    <h3>Packaging and Delivery Details</h3>
                    <p>We will deliver your product as soon as possible.</p>

                </div>
                <div class="col-md-9 register-right">

                    <div class="tab-content" id="myTabContent">


                        <table class="errorTable">
                        <caption></caption>
                        <tr>
                        <th id="th"></th>
                        </tr>
                            <tr>
                                <td class="errorTd">
                                    <br>
                                    <h1 class="confirmationH1">
                                        Packaging and Delivery Details</h1>
                                </td>
                            </tr>
                            <tr>
                                <td class="errorTd">
                                    <p class="errorP">
                                </td>
                            </tr>
                            <tr>
                                <td><input type="hidden" name="requestId" value="${processResponse .requestId}"
                                        readonly /> 
                                        <input type="hidden" name="userId"
                                        value="${processResponse .userId}" readonly />
                                             <input type="hidden" name="userName"
                                        value="${userName}" readonly />
                                    <h5>Request Id : ${processResponse .requestId}</h5>
                                </td>
                            </tr>

                            <tr>
                                <td><input type="hidden" name="processingCharge"
                                        value="${processResponse .processingCharge}" readonly />

                                    <h5>Processing Charge : ${processResponse .processingCharge}</h5>

                                </td>
                            </tr>
                            <tr>
                                <td><input type="hidden" name="packagingAndDeliveryCharge"
                                        value="${processResponse .packagingAndDeliveryCharge}" readonly />

                                    <h5>Packaging and Delivery Charge : ${processResponse .packagingAndDeliveryCharge}
                                    </h5>

                                </td>
                            </tr>

                            <tr>
                                <td><input type="hidden" name="dateOfDelivery"
                                        value="${processResponse .dateOfDelivery}" readonly />

                                    <h5>Date Of Delivery : ${processResponse .dateOfDelivery}</h5>
                                </td>
                            </tr>
                            <tr>
                                
                                <td><input class="btnRegister" type="submit" name="submit" value="Proceed Payment" />
                                </td>
                            </tr>
                        </table>




                    </div>

                </div>
            </div>

        </div>
</form>
        <!--footer-->
        <div class="row mt-5">
            <footer class="bg-blue">
                <div class="row px-3">
                    <h6 class="ml-4 ml-sm-5 mb-2">Copyright &copy; 2021. All rights reserved.</h6>
            </footer>
        </div>
    
</body>

</html>
