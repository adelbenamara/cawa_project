<%--
    Document   : ajouter-ligne
    Created on : May 22, 2023, 12:56:13 AM
    Author     : adel
--%>
<%@page import="Controller.CSRFTokenUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:if test="${not empty errorMessage}">
    <p style="color: red;">${errorMessage}</p>
</c:if>
 <style>
     #container {
         
           overflow: auto;
   
     }
</style>

    
    
<div class="container" style="position: absolute;bottom: 0;right: 0;width: 80%;height: 80%;">
    <c:if test="${not empty facture}">
        <c:forEach items="${clients}" var="client">
            <c:if test="${client.id eq facture.idClient}">
                <div class="row">
                    <div class="col-lg-3">
                        <label class="col-form-label" style="color: rgb(0,0,0);font-weight: bold;font-family: Inter, sans-serif;padding: 5px 5px 5px 5px;font-size: 14px;">Nom de client :</label>
                    </div>
                    <div class="col">
                        <span style="color: rgb(0,0,0);font-family: Inter, sans-serif;text-align: center;border-radius: 16px;border-width: 0px;background: #cccccc;padding: 2px 5px 5px 5px;position: absolute;margin-bottom: 0;">${client.nom}</span>
                    </div>
                </div>
            </c:if>
        </c:forEach>
        <div class="row">
            <div class="col-lg-3">
                <label class="col-form-label" style="color: rgb(0,0,0);font-weight: bold;font-family: Inter, sans-serif;padding: 5px 5px 5px 5px;font-size: 14px;">Date de facture :</label>
            </div>
            <div class="col">
                <span style="color: rgb(0,0,0);font-family: Inter, sans-serif;text-align: center;border-radius: 16px;border-width: 0px;
                      background: #cccccc;padding: 2px 5px 5px 5px;position: absolute;margin-bottom: 0;">
                     <fmt:formatDate value="${facture.dateFacture}" pattern="dd/MM/yyyy" /></span>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-3">
                <label class="col-form-label" style="color: rgb(0,0,0);font-weight: bold;font-family: Inter, sans-serif;padding: 5px 5px 5px 5px;font-size: 14px;">Mode de paiement :</label>
            </div>
            <div class="col">
                <span class="text-center" style="color: rgb(0,0,0);font-family: Inter, sans-serif;text-align: center;border-radius: 16px;border-width: 0px;background: #cccccc;padding: 2px 5px 5px 5px;position: absolute;margin-bottom: 0;"> ${facture.modePaiement}</span>
            </div>
        </div>
    </c:if>
  <div class="row">
    <div class="col-lg-7"><label class="col-form-label" style="color: rgb(0,0,0);font-family: Inter, sans-serif;padding: 12px 5px 5px 5px;font-size: 16px;font-weight: bold;text-align: left;">Ajouter une facture :</label></div>
    <div class="col"> <form method="POST" action="envoyer-facture" ><input class="btn btn-primary" type="submit" style="font-family: Inter, sans-serif;font-size: 16px;background: #00c993;border-width: 0px;border-radius: 15px;margin-top: 11px;" value="terminer" /></form></div>
</div>
    <div class="row" style="background: #ebebeb;border-radius: 15px;width: 80%;margin-bottom: -24px;padding: 0px;padding-bottom: 18px;">
        <div class="col">
           
                <form method="post" action="ajouter-line">
                       <%-- Generate and add CSRF token to the session --%>
                    <% 
                        String csrfToken = CSRFTokenUtil.generateToken();
                        session.setAttribute("csrfToken", csrfToken);
                    %>
                    <input type="hidden" name="csrfToken" value="<%= csrfToken %>">
                    
                        <div class="row" style="margin: 10px;">
                            <div class="col-lg-3">
                                <label class="col-form-label" style="color: rgb(0,0,0);font-weight: bold;font-family: Inter, sans-serif;padding: 5px 5px 5px 5px;font-size: 14px;">Article :</label>
                            </div>
                            <div class="col">
                                 <c:if test="${not empty articles}">
                                <select name="article" style="font-family: Inter, sans-serif;font-size: 16px;padding: 5px 5px 5px 10px;border-width: 0px;border-radius: 15px;">
                                    
                                        <c:forEach items="${articles}" var="article">
                                            <option value="${article.ref_article}">${article.designation}</option>
                                        </c:forEach>
                                </select>
                                </c:if>
                            </div>
                        </div>
                    
                    <div class="row">
                        <div class="col-lg-3">
                            <label class="col-form-label" style="color: rgb(0,0,0);font-weight: bold;font-family: Inter, sans-serif;padding: 5px 5px 5px 5px;font-size: 14px;">Quantité vendue :</label>
                        </div>
                        <div class="col">
                            <input type="number" name="quantiteVendue" style="font-family: Inter, sans-serif;font-size: 16px;padding: 5px 5px 5px 10px;border-width: 0px;border-radius: 15px;" placeholder="Quantité :" max="99999" min="1" step="1">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-3 offset-lg-5">
                            <input class="btn btn-primary" type="submit" style="font-family: Inter, sans-serif;font-size: 16px;background:cadetblue;border-width: 0px;border-radius: 15px;margin-top: 11px;" value="Ajouter">
                        </div>
                    </div>
                    <c:if test="${not empty ligneFactureList}">
                        <input type="hidden" value="${ligneFactureList}" name="ligneFactureList">
                         </c:if>
                </form>
           
        </div>
    </div>
    
    <c:if test="${not empty ligneFactureList}">
    <div class="row justify-content" style="margin-top: 25px;width: 80%;border-radius: 20px;border-width: 0px;padding-left: 0px;margin-left: -14px;margin-right: 6px;">
        <div class="col-xl-10 col-xxl-9" style="padding-left: 0px;width: 100%;">
            <div class="card shadow" style="width: 100%;">
                <div class="card-header py-3" style="background: #f0f0f0;width: 100%;">
                    <p class="text-center" style="font-size: 20px;font-weight: bold;font-family: Inter, sans-serif;color: rgb(0,0,0);">Liste des lignes facture</p>
                </div>
                <div class="card-body" style="background: rgb(255,255,255);width: 100%;">
                    <c:if test="${not empty ligneFactureList}">
                        <table class="table table-hover " style="text-align: center;width: 100%;">
                            <thead>
                                <tr>
                                    <th style="color: rgb(0,0,0);font-family: Inter, sans-serif;">Référence article</th>
                                    <th style="color: rgb(0,0,0);font-family: Inter, sans-serif;">Désignation</th>
                                    <th style="color: rgb(0,0,0);font-family: Inter, sans-serif;">Quantité vendue</th>
                                    <th style="color: rgb(0,0,0);font-family: Inter, sans-serif;">Total Prix</th>
                                    <th style="color: rgb(0,0,0);font-family: Inter, sans-serif;">Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${ligneFactureList}" var="ligneFacture" varStatus="status">
                                    <tr>
                                        <td>${ligneFacture.articleRef}</td>
                                        <td>${ligneFacture.designation}</td>
                                        <td>${ligneFacture.quantity}</td>
                                        <td>${ligneFacture.totalPrice}</td>
                                        <td>
                                            <form method="post" action="supprimer-line">
                                                <input type="hidden" value="${status.index}" name="index">
                                                <input class="btn btn-danger" type="submit" style="font-family: Inter, sans-serif;font-size: 14px;background: rgb(239,61,77);border-width: 0px;border-radius: 15px;" value="Supprimer">
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
    </c:if>
</div>
