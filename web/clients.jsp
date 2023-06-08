<%-- 
    Document   : clients
    Created on : May 19, 2023, 2:26:56 PM
    Author     : adel
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <style>
        table {
            margin-top: 1%;
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

    <c:if test="${not empty clients}">
        <table>
            <tr>
                <th>Nom</th>
                <th>Téléphone</th>
                <th>Email</th>
                <th>Action</th>
            </tr>

            <c:forEach var="client" items="${clients}">
                <tr>
                    <td>${client.nom}</td>
                    <td>${client.telephone}</td>
                    <td>${client.email}</td>
                    <td>
                        <form method="post" action="supprimer-client">
                            <input type="hidden" name="clientId" value="${client.id}" />
                            <input type="submit" value="Supprimer" class="delete-button" />
                            <i class="fa fa-trash-o" aria-hidden="true"></i>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>