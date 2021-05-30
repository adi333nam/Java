<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE>
<html lang="en">

<head>
    <title>Failure</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <link rel="stylesheet" href="confirmation.css" />

</head>
<style>

</style>

<body>
    <nav class="navbar navbar-expand-sm navbar-dark bg-blue">
        <a class="navbar-brand" href=#>
            <span class="material-icons return-order">
                assignment_return
            </span>
            Return Order Portal</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#toggle">
            <span class="navbar-toggler-icon"></span></button>

    </nav>

    <div class="container register">
        <div class="row">
            <div class="col-md-3 register-left">
                <img src="icon1.png"
                alt="" />
                                <h3>Return Order Portal</h3>
                <p>We always try to satisfy our customer and their need is our first priority.</p>
                
            </div>
            <div class="col-md-9 register-right">

                <div class="tab-content" id="myTabContent">



                    <table class="errorTable">
                       <caption></caption>
                       <tr>
                       <th id="td"></th>
                       </tr>
                    
                        <tr>
                            <td class="errorTd">
                                <br>
                                <h1 class="errorH1">
                                    FAILED TO CONNECT </h1>
                            </td>
                        </tr>
                        <tr>
                            <td class="errorTd">
                                <p class="errorP">
                                The url that you are looking for is not correct
                                </p>
                                <p class="errorP">
                                You can try with following url <a href="/login">Login</a>
                                </p>
                            </td>
                        </tr>
               
                    </table>
                      




                </div>

            </div>
                    <!--footer-->
        <div class="row mt-5">
            <footer class="bg-blue">
                <div class="row px-3">
                    <h6 class="ml-4 ml-sm-5 mb-2">Copyright &copy; 2021. All rights reserved.</h6>
            </footer>
        </div>

</body>

</html>