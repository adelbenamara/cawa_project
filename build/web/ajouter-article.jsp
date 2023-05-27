<%-- 
    Document   : ajouter-article
    Created on : May 19, 2023, 7:21:02 PM
    Author     : adel
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter un article</title>
    <style>
        form {
            width: 600px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f9f9f9;
        }

        label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
        }

        input[type="text"],
        input[type="number"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        input[type="submit"] {
            background-color: #4caf50;
            width: 20%;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <jsp:include page="accueil.jsp" />

    <form method="post" action="ajouter-article">
         <label for="ref_article">Référence article:</label>
         <input type="text" id="ref_article" name="ref_article" required><br><br>
        <label for="designation">Nom article:</label>
        <input type="text" id="designation" name="designation" required><br><br>
        <label for="price">Prix:</label>
        <input type="number" id="price" name="price" step="0.5" min="1" required><br><br>
        <label for="stockQuantity">Quantité en stock:</label>
        <input type="number" id="stockQuantity" name="stockQuantity" min="1" required><br><br>
        <input type="submit" value="Ajouter">
    </form>
</body>
</html>
