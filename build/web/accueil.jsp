<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <!-- Inclure le CDN de Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <style>
        .left-side {
            position: fixed;
            background-color: #183342;
            height: 100vh;
            padding: 20px;
            width: 20%;
            z-index: 1;
            display: flex; /* Add this line */
            align-items: center; /* Add this line */
            justify-content: center; /* Add this line */
        }
        .list-group-item {
            background-color:   #020e53;
            padding-top: 20px;
            border-radius: 50%;
        }
        .left-side ul.list-group {
            width: 100%; /* Add this line */
            text-align: center; /* Add this line */
        }

        .left-side ul.list-group a {
            text-decoration: none;
            display: block; /* Add this line */
            padding: 10px 0; /* Add this line */
            transition: color 0.3s ease; /* Add this line */
        }

        .left-side ul.list-group a:hover {
            color: #ffc107;
        }

        /* Change the color of the navbar */
        .navbar {
            background-color: #f3f6f7;
            position: static;
            margin-left: 250px;
        }

        /* Change the color of the active nav item */
        .nav-link.active {
            color: #ffc107;
        }

        /* Center the form in the center area */
        .center-area {
            position: relative;
            justify-content: center;
            align-items: center;
            padding-top: 1%;
            margin-left: 22%;           
        }
    </style>
</head>
<body>
    <div class="left-side">
        <ul class="list-group">
            <li class="list-group">
                <a href="ajouter-client" class="text-decoration-none">Ajouter client</a>
            </li>
            <li class="list-group">
                <a href="ajouter-article" class="text-decoration-none">Ajouter article</a>
            </li>
            <li class="list-group">
                <a href="ajouter-facture" class="text-decoration-none">Ajouter facture</a>
            </li>
        </ul>
    </div>

    <nav class="navbar navbar-expand-lg navbar-light">
        <div class="container">
            <div class="navbar-collapse justify-content-center">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link  " href="clients">Clients</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link " href="articles">Articles</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link " href="factures">Factures</a>
                    </li>
                </ul>
            </div>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="logout">Déconnecter</a>
                </li>
            </ul>
        </div>
    </nav>
    <!-- Inclure le CDN de jQuery et de Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<div class="center-area">
  
</body>
</html>
