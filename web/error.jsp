<%-- 
    Document   : error
    Created on : May 28, 2023, 10:12:07 AM
    Author     : adel
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
        }

        .container {
            max-width: 500px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: #ff5c5c;
        }

        p {
            text-align: center;
            margin-top: 20px;
            color: #555;
        }
    </style>
    <div class="container">
        <h1>Error</h1>
        <c:if test="${not empty error_message}">
            <p>${error_message}</p>
        </c:if>
    </div>
