<%-- 
    Document   : ajouter-facture
    Created on : May 19, 2023, 11:16:06 PM
    Author     : adel
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <title>Ajouter une facture</title>
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

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #f5f5f5;
        }

        .delete-button {
            background-color: #f44336;
            color: #fff;
            border: none;
            padding: 5px 10px;
            border-radius: 4px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <jsp:include page="accueil.jsp" />

    <h3>Ajouter une facture</h3>

    <c:if test="${factureAdded}">
        <p style="color: #45a049">La facture a été ajoutée avec succès.</p>
      </c:if>  
        
        <form method="post" action="ajouter-ligne-facture">
            <input type="hidden" name="factureId" value="${factureId}" />
            <label for="articleId">ID Article:</label>
            <input type="text" id="articleId" name="articleId" required><br><br>
            <label for="quantity">Quantité:</label>
            <input type="number" id="quantity" name="quantity" required><br><br>
            <label for="lineTotal">Total ligne:</label>
            <input type="text" id="lineTotal" name="lineTotal" required><br><br>
            <input type="submit" value="Ajouter ligne">
        </form>
</body>
</html>



