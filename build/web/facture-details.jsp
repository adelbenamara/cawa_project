<%-- 
    Document   : facture-details
    Created on : May 25, 2023, 2:07:39 PM
    Author     : adel
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        
        h1 {
            color: #333;
        }
        .pdf{
            
            float: right;
        }
        table {
            border-collapse: collapse;
            width: 100%;
            margin-bottom: 20px;
        }
        
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        
        th {
            background-color: #f2f2f2;
            font-weight: bold;
        }
        
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        
        h2 {
            margin-top: 40px;
            color: #333;
        }
        
        p {
            margin: 10px 0;
        }
    </style>
     <c:if test="${not empty facture }">
    <h4>Facture Information</h4>
    <table>
        <tr>
            <th>Facture Number</th>
            <th>Client</th>
            <th>Date</th>
            <th>Mode of Payment</th>
        </tr>
        <tr>
            <td>${facture.numFacture}</td>
              <c:if test="${not empty clients }">
                            <c:forEach items="${clients}" var="client">
                                <c:if test="${client.id eq facture.idClient}">
                                 <td>   ${client.nom} </td>
                                </c:if>
                            </c:forEach>
                            </c:if>
            <td>${facture.dateFacture}</td>
            <td>${facture.modePaiement}</td>
        </tr>
    </table>
            </c:if>
    <h4>Line Facture Information</h4>
   <c:if test="${not empty ligneFactureList }">
        <table>
            <thead>
                <tr>
                    <th>Référence article</th>
                    <th>designation Article</th>
                    <th>Prix unitaire </th>
                    <th>Quantité vendue</th>
                    <th>Total Price</th>
                   
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${ligneFactureList}" var="ligneFacture" >
                    <tr>
                        
                            <c:if test="${not empty articles }">
                            <c:forEach items="${articles}" var="article">
                                <c:if test="${ article.ref_article eq ligneFacture.articleRef}">
                                  <td>  ${article.ref_article}  </td>
                                  <td>  ${article.designation}  </td>
                                  <td>  ${article.price}  </td>
                                </c:if>
                            </c:forEach>
                            </c:if>
                       
                        <td>${ligneFacture.quantity}</td>
                        <td>${ligneFacture.totalPrice}</td>
                       
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    
    <h4>Total</h4>
    <p>Total HT: ${facture.totalHT}</p>
    <p>Total TTC: ${facture.totalTTC}</p>
    <c:if test="${not empty ligneFactureList }">
         <c:if test="${not empty facture }">
      <form class="pdf" action="generate-pdf" method="post">
         <input type="hidden" name="facture" value="${facture}">
         <input type="hidden" name="ligneFactureList" value="${ligneFactureList}">
        <input type="submit" value="Print PDF">
    </form>
   </c:if> </c:if>