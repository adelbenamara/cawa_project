<%-- 
    Document   : articles
    Created on : May 19, 2023, 7:34:46 PM
    Author     : adel
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des articles</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
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
    <h4>Liste des articles :</h4>

    <c:if test="${not empty articles}">
        <table>
            <tr>
                <th>Désignation</th>
                <th>Prix</th>
                <th>Quantité en stock</th>
                <th>Action</th>
            </tr>

            <c:forEach var="article" items="${articles}">
                <tr>
                    <td>${article.designation}</td>
                    <td>${article.price}</td>
                    <td>${article.stockQuantity}</td>
                    <td>
                        <form method="post" action="supprimer-article">
                            <input type="hidden" name="articleId" value="${article.id}" />
                            <input type="submit" value="Supprimer" class="delete-button" />
                            <i class="fa fa-trash-o" aria-hidden="true"></i>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
</html>
