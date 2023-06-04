<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>accueil</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i&amp;display=swap">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Inter&amp;display=swap">
    <link rel="stylesheet" href="assets/fonts/fontawesome-all.min.css">
    <link rel="stylesheet" href="assets/css/Navbar-Centered-Links-icons.css">
    <link rel="stylesheet" href="assets/css/sidebar-1.css">
    <link rel="stylesheet" href="assets/css/Sidebar-navbar.css">
    <link rel="stylesheet" href="assets/css/sidebar-style4.css">
    <link rel="stylesheet" href="assets/css/Sidebar.css">
</head>
<body id="page-top" style="background: #cccccc;">
    <div id="wrapper" style="background: #CCCCCC;">
        <!-- Start: Navbar Centered Links -->
        <nav class="navbar navbar-light navbar-expand-md py-3" style="position: fixed;/*--bs-body-bg: #ffffff;*/background: #ffffff;border-radius: 20px;width: 90%;margin-left: 5%;top: 2%;">
            <div class="container"><a class="navbar-brand d-flex align-items-center" href="home"><img  src="assets/img/logo_in.png" width="62" height="64" style="border-radius: 20px;/*float: left;*/position: fixed;"></a><button data-bs-toggle="collapse" class="navbar-toggler" data-bs-target="#navcol-3"><span class="visually-hidden">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navcol-3">
                    <ul class="navbar-nav mx-auto">
                        <li class="nav-item"></li>
                        <li class="nav-item"></li>
                        <li class="nav-item"></li>
                    </ul><a href="clients" style="color: var(--bs-navbar-active-color);font-size: 15px;font-weight: bold;text-decoration: none;padding-right: 0px;padding-left: 21px;margin-bottom: 1px;font-family: Inter, sans-serif;margin-left: 5%;margin-right: 5%;text-align: center;"> Mes client</a>
                    <a href="articles" style="color: var(--bs-navbar-active-color);font-size: 15px;font-weight: bold;text-decoration: none;padding-right: 0px;padding-left: 21px;margin-bottom: 1px;font-family: Inter, sans-serif;margin-right: 5%;margin-left: 5%;text-align: center;"> Les articles</a>
                    <a  href="factures" style="color: var(--bs-navbar-active-color);font-size: 15px;font-weight: bold;text-decoration: none;margin-bottom: 1px;font-family: Inter, sans-serif;padding-left: 26px;padding-right: 53px;margin-right: 5%;margin-left: 5%;text-align: center;"> Les factures</a>
                    <a  class="btn btn-primary" href="logout" style="background: rgba(233, 13, 13, 0.81);;margin-left: -14px;margin-right: -5px;padding-right: 14px;padding-left: 14px;border-radius: 20.6px;border-width: 0px;font-family: Inter, sans-serif;font-weight: bold;text-align: center;">Se déconnecter</a>
                </div>
            </div>
        </nav><!-- End: Navbar Centered Links -->
    </div>
    <nav style="width: 15%;height: 80%;/*position: absolute;*/left: 5%;/*top: 25%;*/position: fixed;bottom: 2%;background-color: #ffffff;border-radius: 20px;margin-right: 4%;">
        <!-- Start: 2 Rows 1+2 Columns -->
        <div class="container">
            <div class="row">
                <div class="col-md-6" style="margin-left: -21px;margin-right: -15px;padding-right: 11px;"><i class="far fa-sun" style="font-size: 18px;margin-top: 21px;margin-left: 27px;margin-bottom: 9px;padding-right: 0px;margin-right: 15px;"></i></div>
                <div class="col-md-6" style="margin-left: 18px;margin-right: 18px;padding-left: 0px;"><label class="col-form-label" style="margin-top: 12px;font-family: Inter, sans-serif;color: var(--bs-body-color);font-weight: bold;font-size: 15px;padding-right: 0px;padding-left: 0px;margin-bottom: 1px;margin-left: 4px;margin-right: 5px;">Outils</label></div>
            </div>
        </div><!-- End: 2 Rows 1+2 Columns -->
        <div class="row">
            <div class="col"><a href="ajouter-client" style="text-align: center;position: relative;display: grid;font-family: Inter, sans-serif;font-weight: bold;color: var(--bs-black);border-radius: 20px;padding-bottom: 0px;margin-right: 0px;margin-left: 0px;padding-top: 0px;margin-top: 17px;margin-bottom: 17px;">Ajouter un client</a></div>
        </div>
        <div class="row">
            <div class="col"><a href="ajouter-article" style="text-align: center;position: relative;display: grid;font-family: Inter, sans-serif;font-weight: bold;color: var(--bs-black);border-radius: 20px;padding-bottom: 0px;margin-right: 0px;margin-left: 0px;padding-top: 0px;margin-top: 17px;margin-bottom: 17px;">Ajouter un article</a></div>
        </div>
        <div class="row">
            <div class="col"><a href="ajouter-facture"style="text-align: center;position: relative;display: grid;font-family: Inter, sans-serif;font-weight: bold;color: var(--bs-black);border-radius: 20px;padding-bottom: 0px;margin-right: 0px;margin-left: 0px;padding-top: 0px;margin-top: 17px;margin-bottom: 17px;">Ajouter une facture</a></div>
        </div>
    </nav>
    <div class="container" style="position: fixed;width: 70%;float: right;bottom: 2%;right: 0;/*margin-top: 31%;*/height: 80%;/*padding-right: 53px;*/border-radius: 20px;/*border-style: solid;*//*border-color: rgb(255,255,255);*//*--bs-body-bg: #fff;*/background: #ffffff;margin-right: 5%;">
    <c:if test="${not empty pageToInclude}">
    <jsp:include page="${pageToInclude}" />
     </c:if>
    </div>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/theme.js"></script>
</body>

</html>

       