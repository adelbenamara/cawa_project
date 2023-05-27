<%--
    Document   : ajouter-ligne
    Created on : May 22, 2023, 12:56:13 AM
    Author     : adel
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Ajouter une ligne de facture</title>
<style>
    body {
        font-family: Arial, sans-serif;
    }

    h2 {
        text-align: center;
    }

    form {
        width: 400px;
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
    input[type="number"],
    select {
        width: 100%;
        padding: 10px;
        margin-bottom: 20px;
        border: 1px solid #ccc;
        border-radius: 5px;
        background-color: #f9f9f9;
        color: #555;
    }

    input[type="submit"].add-button {
        background-color: #4caf50;
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    input[type="submit"].add-button:hover {
        background-color: #45a049;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }

    th,
    td {
        padding: 8px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }

    th {
        background-color: #f2f2f2;
    }

    input[type="submit"].delete-button {
        background-color: #ff5c5c;
        color: white;
        padding: 6px 12px;
        border: none;
        border-radius: 3px;
        cursor: pointer;
    }

    input[type="submit"].delete-button:hover {
        background-color: #e63f3f;
    }

    input[type="submit"].finish-button {
        float: right;
        background-color: #2196f3;
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    input[type="submit"].finish-button:hover {
        background-color: #0c7cd5;
    }
</style>

</head>

<body>
    <jsp:include page="accueil.jsp" />
    <c:if test="${not empty facture}">
        <div class="right">
            <c:forEach items="${clients}" var="client">
                <c:if test="${client.id eq facture.idClient}">
                    <h5>Nom Client: ${client.nom}</h5>
                </c:if>
            </c:forEach>
            <h5>Mode Paiement : ${facture.modePaiement}</h5>
            <form method="post" action="envoyer-facture">
                <input type="hidden" name="facture" value="${facture}">
                 <input type="hidden" value="${ligneFactureList}" name="ligneFactureList">
                <input type="submit" class="finish-button" value="Terminer">
            </form>
        </div>
    </c:if>
    <h2>Ajouter une ligne de facture</h2>
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>
    <form method="post" action="ajouter-line">
        <c:if test="${not empty ligneFactureList}">
            <input type="hidden" value="${ligneFactureList}" name="ligneFactureList">
        </c:if>
        <c:if test="${not empty articles}">
            <label for="article">Article :</label>
            <select id="article" name="article" required>
                <c:forEach items="${articles}" var="article">
                    <option value="${article.ref_article}">${article.designation}</option>
                </c:forEach>
            </select>
        </c:if>
        <label for="quantiteVendue">Quantité vendue :</label>
        <input type="number" id="quantiteVendue" min="1" name="quantiteVendue" required>
        <input type="submit" value="Ajouter">
    </form>

    <c:if test="${not empty ligneFactureList}">
        <h2>Table des lignes de facture</h2>
        <table>
            <thead>
                <tr>
                    <th>Article</th>
                    <th>Quantité vendue</th>
                    <th>Total Price</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${ligneFactureList}" var="ligneFacture" varStatus="lineStatus">
                    <tr>
                        <td>
                            <c:forEach items="${articles}" var="article">
                                <c:if test="${article.ref_article eq ligneFacture.articleRef}">
                                    ${article.designation}
                                </c:if>
                            </c:forEach>
                        </td>
                        <td>${ligneFacture.quantity}</td>
                        <td>${ligneFacture.totalPrice}</td>
                        <td>
                            <form method="post" action="supprimer-line">
                                <input type="hidden" name="lineStatusIndex" value="${lineStatus.index}">
                                <input type="submit" class="delete-button" value="Supprimer">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</body>

</html>
