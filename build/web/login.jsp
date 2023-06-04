<%-- 
    Document   : login
    Created on : May 15, 2023, 10:53:27 PM
    Author     : adel
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Untitled</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Inter&amp;display=swap">
    <link rel="stylesheet" href="assets/css/animate.min.css">
    <link rel="stylesheet" href="assets/css/Login-Form-Basic-icons.css">
    <link rel="stylesheet" href="assets/css/Ludens-basic-login.css">
</head>
    <style>
 
.error-message {
            color: red;
            text-align: end;
}
    </style>

<body style="background: #cccccc;">
    <div class="container" style="position: fixed;top: 8%;left: 14%;width: 80%;/*margin-bottom: 10%;*//*right: 52%;*//*bottom: 10%;*//*height: 74%;*/border-radius: 20px;/*padding-bottom: 0px;*//*margin-bottom: -4px;*/">
        <div class="row">
            <div class="col-xxl-4" style="/*height: 100%;*/background: #ffffff;width: 40%;padding-right: 72px;margin-right: -59px;border-radius: 20px;/*padding-bottom: 96px;*//*padding-top: 204px;*/border-top-right-radius: 0px;border-bottom-right-radius: 0px;margin-top: -4px;margin-bottom: 23px;">
                <!-- Start: Login Form Basic -->
                <form method="post" action="Login" class="form"  >
                <section class="py-4 py-xl-5" style="padding-right: 126px;padding-bottom: 10px;/*margin-bottom: -42px;*/margin-right: -22px;/*margin-top: 6px;*/">
                    <div class="container">
                        <div class="row">
                            <div class="col"><label class="col-form-label" style="text-align: center;color: rgb(0,0,0);font-size: 37px;font-weight: bold;display: grid;">Bienvenue</label></div>
                        </div>
                        <div class="row">
                            <div class="col" style="margin-bottom: 8px;"><label class="col-form-label" style="color: rgb(0,0,0);font-family: Inter, sans-serif;font-weight: bold;text-align: center;font-size: 17px;margin-bottom: 0px;">Username :</label></div>
                        </div>
                        <div class="row">
                            <div class="col"><input class="shadow" type="text" data-bss-disabled-mobile="true" data-bss-hover-animate="pulse" style="background: #cccccc;position: static;display: grid;margin-right: 8px;border-radius: 15px;padding-top: 13px;padding-bottom: 11px;margin-top: -12px;box-shadow: 0px 0px 3px 3px var(--bs-red);padding-left: 42px;padding-right: 32px;border-width: 0px;border-color: rgb(0,0,0);color: rgb(0,0,0);font-family: Inter, sans-serif;margin-bottom: 14px;" placeholder="user" autofocus="" required="" name="user"></div>
                        </div>
                        <div class="row">
                            <div class="col"><label class="col-form-label" style="color: rgb(0,0,0);font-family: Inter, sans-serif;font-weight: bold;text-align: center;font-size: 17px;padding-bottom: 23px;margin-bottom: -12px;">Password :</label></div>
                        </div>
                        <div class="row">
                            <div class="col"><input class="shadow" type="password" data-bss-hover-animate="pulse" style="border-radius: 15px;background: #cccccc;display: grid;margin-left: -2px;margin-right: 0px;padding-left: 44px;padding-right: 32px;border: 0px solid rgb(0,0,0);font-family: Inter, sans-serif;color: rgb(0,0,0);margin-top: -4px;margin-bottom: 12px;padding-bottom: 11px;padding-top: 19px;" placeholder="*****" required="" autofocus="" name="pass"></div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col" style="margin-bottom: 0px;margin-right: -10px;margin-left: 2px;padding-left: 6px;"><button class="btn " data-bss-hover-animate="pulse" style="border: none;width: 177px;height: 58px;background-color: #4ddb94;color: rgb(255,255,255);text-align: center;border-radius: 19px;display: inline-block;margin-left: 89px;margin-right: 119px;padding-left: 0px;margin-top: 23px;margin-bottom: 7px;padding-right: 0px;padding-top: 0px;padding-bottom: 0px;" type="submit" data-bs-toggle="submit">Login</button></div>
                    </div>
<c:if test="${not empty errorMessage}">
            <p class="error-message">${errorMessage}</p>
        </c:if>

                </section><!-- End: Login Form Basic --> </form>
            </div>
            <div class="col-xl-5" style="padding-bottom: 40px;margin-bottom: -20px;padding-left: 58px;margin-left: 10px;border-radius: 20px;border-top-left-radius: 0px;border-bottom-left-radius: 0px;"><img src="assets/img/logo.jpg" width="454" height="508" style="height: 100%;/*padding-bottom: 0px;*/margin-right: 6px;padding-right: 14px;padding-left: 0px;margin-left: -12px;border-radius: 20px;border-bottom-left-radius: 0px;border-top-left-radius: 0px;margin-top: -4px;margin-bottom: -4px;"></div>
        </div>
    </div>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/bs-init.js"></script>
</body>

</html>


       
 
