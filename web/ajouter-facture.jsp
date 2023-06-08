<%@page import="Controller.CSRFTokenUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--<head>
    <link rel="stylesheet" href="assets/css/Select-Search.css">
    <link rel="stylesheet" href="assets/css/select2.css">
</head>-->
    <form method="post" action="ajouter-facture" style="  margin-top: 10%;margin-left: 20%;">
           <%-- Generate and add CSRF token to the session --%>
                    <% 
                        String csrfToken = CSRFTokenUtil.generateToken();
                        session.setAttribute("csrfToken", csrfToken);
                    %>
                    <input type="hidden" name="csrfToken" value="<%= csrfToken %>">
           <c:if test="${not empty errorMessage}">
        <p style="color: red;   text-align: start">${errorMessage}</p></c:if> 
            <div class="row" style="margin-bottom: 15px;">
                <div class="col-lg-3"><label class="col-form-label col-form-label" style="display: grid;position: static;font-family: Inter, sans-serif;font-weight: bold;text-align: center;font-size: 16px;">Choiser&nbsp; client</label></div>
                <div class="col"><select class="chosen" required="" style="color: #232323;--bs-body-bg: #cccccc;background: #cccccc;border-radius: 16px;border-width: 0px;border-color: #000000;font-family: Inter, sans-serif;padding: 10px 10px 10px 16px;text-align: left;" autofocus="" name="clientId">
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
                    </select></div>
            </div>
            <div class="row" style="margin-bottom: 15px;">
                <div class="col-lg-3"><label class="col-form-label col-form-label" style="display: grid;position: static;font-family: Inter, sans-serif;font-weight: bold;text-align: center;font-size: 16px;margin-right: -1px;">Date de facture</label></div>
                <div class="col-lg-9"><input style="align-items: center;padding: 10px 10px 10px 15px;border-radius: 16px;font-family: Inter, sans-serif;font-size: 14px;background: #cccccc;border-width: 0px;" required=""  autofocus="" minlength="10" maxlength="20" pattern="[0-9]+" type="date" name="date"></div>
            </div>
            <div class="row" style="margin-bottom: 15px;">
                <div class="col-lg-3"><label class="col-form-label col-form-label" style="display: grid;position: static;font-family: Inter, sans-serif;font-weight: bold;text-align: center;font-size: 16px;">Mode paiement</label></div>
                <div class="col-lg-9"><select class="chosen" required="" style="color: #232323;--bs-body-bg: #cccccc;background: #cccccc;border-radius: 16px;border-width: 0px;border-color: #000000;font-family: Inter, sans-serif;padding: 10px 10px 10px 16px;text-align: left;" autofocus="" name="modePaiement">
<option value="Visa">Visa</option>
<option value="Mastercard">Mastercard</option>
<option value="Discover">Discover</option>
<option value="Maestro">Maestro</option>
<option value="American Express">American Express</option>
<option value="Diners Club">Diners Club</option>
<option value="UnionPay">UnionPay</option>
<option value="PayPal">PayPal</option>
<option value="Alipay">Alipay</option>
<option value="WeChat Pay">WeChat Pay</option>
<option value="Google Pay">Google Pay</option>
<option value="Amazon Pay">Amazon Pay</option>
<option value="BitPay">BitPay</option>
<option value="Payoneer">Payoneer</option>
<option value="Cash App">Cash App</option>
<option value="Samsung Pay">Samsung Pay</option>
<option value="TransferWise">TransferWise</option>
                    </select></div>
            </div>
            <div class="row">
                <div class="col-lg-5" style="margin-top: 16px;"><input class="btn btn-primary d-grid float-end border " type="submit" style="width: 120px;font-family: Inter, sans-serif;font-size: 16px;border-radius: 20px;position: relative;display: grid;font-weight: bold;background: #00c993;border: 0px none var(--bs-purple);padding-top: 7px;padding-right: 20px;padding-bottom: 8px;margin-right: 9px;margin-left: 10px;margin-bottom: 9px;margin-top: 10px;padding-left: 14px;text-align: center;color: #000000;" value="Ajouter "></div>
            </div>
      
    </form>