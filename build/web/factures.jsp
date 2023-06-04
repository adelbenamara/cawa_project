<%-- 
    Document   : factures
    Created on : May 21, 2023, 3:37:55 PM
    Author     : adel
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        .view-button {
            background-color: #4CAF50;
            color: #fff;
            border: none;
            padding: 5px 10px;
            border-radius: 4px;
            cursor: pointer;
        }
    </style>
    <c:if test="${not empty factures}">
       
        <table>
            <tr>
                <th>num Facture</th>
                <th>client</th>
                <th>mode Paiement</th>
                <th>date Facture</th>
                <th>Action</th>
                <th>Action</th>
            </tr>

            <c:forEach var="facture" items="${factures}">
                <tr>
                    <td>${facture.numFacture}</td>
               <c:if test="${not empty clients}">
                    <c:forEach var="client" items="${clients}">
                      <c:if test="${client.id eq facture.idClient}">
                               <td>${client.nom}</td>
                                </c:if>
                        </c:forEach>
                          </c:if>     
                    <td>${facture.modePaiement}</td>
                    <td>${facture.dateFacture}</td>
                    <td>
                        <form method="post" action="supprimer-facture" >
                            <input type="hidden" name="numFacture" value="${facture.numFacture}" />
                            <input type="submit" value="Supprimer" class="delete-button" />
                            <i class="fa fa-trash-o" aria-hidden="true"></i>
                        </form>
                    </td>
                    <td>
                        <form method="post" action="details-facture">
                            <input type="hidden" name="numFacture" value="${facture.numFacture}" />
                            <input type="submit" value="Voir les détails" class="view-button" />
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>