<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter une facture</title>
    <style>
        
         h5{    text-align: center}
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
        .custom-select {
          width: 100%;
            border: 1px solid #ccc;
               border-radius: 5px;
           background-color: #f9f9f9;
                font-size: 16px;
              color: #555;
                     }

.custom-select option {
    padding: 10px;
}  
    </style>
</head>
<body>
    <jsp:include page="accueil.jsp" />

    <h5>Ajouter une facture</h5>

    <form method="post" action="ajouter-facture">
        <label for="clientId">Client :</label>
      
           <select id="clientId" name="clientId" required class="custom-select">
    <c:choose>
        <c:when test="${not empty clients}">
            <option value="">Sélectionner un client</option>
            <c:forEach var="client" items="${clients}">
                <option value="${client.id}">${client.nom}</option>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <option value="">Aucun client disponible</option>
        </c:otherwise>
    </c:choose>
               </select><br><br>
        <label for="modePaiement">Mode Paiement:</label>
        <input type="text" id="modePaiement" name="modePaiement" required><br><br>
        <input type="submit" value="Ajouter">
    </form>  
</body>
</html>
