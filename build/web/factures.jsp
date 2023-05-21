<%-- 
    Document   : factures
    Created on : May 21, 2023, 3:37:55 PM
    Author     : adel
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Factures</title>
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
    <h4>Liste des Factures :</h4>

    <c:if test="${not empty factures}">
       
        <table>
            <tr>
                <th>num Facture</th>
                <th>id client</th>
                <th>mode Paiement</th>
                <th>date Facture</th>
                <th>Action</th>
            </tr>

            <c:forEach var="facture" items="${factures}">
                <tr>
                    <td>${facture.numFacture}</td>
                    <td>${facture.idClient}</td>
                    <td>${facture.modePaiement}</td>
                    <td>${facture.dateFacture}</td>
                   
                    <td>
                        <form method="post" action="supprimer-facture" >
                            <input type="hidden" name="numFacture" value="${facture.numFacture}" />
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
