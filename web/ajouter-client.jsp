<%-- 
    Document   : ajouter-client
    Created on : May 19, 2023, 2:01:54 PM
    Author     : adel
--%>
  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  
    <form method="post" action="ajouter-client"style="  margin-top: 10%;margin-left: 20%;">
          <c:if test="${not empty errorMessage}">
        <p style="color: red;   text-align: start">${errorMessage}</p></c:if>
            <div class="row" style="margin-bottom: 15px;">
                <div class="col-lg-3"><label class="col-form-label col-form-label col-form-label" style="display: grid;position: static;font-family: Inter, sans-serif;font-weight: bold;text-align: center;font-size: 16px;">Nom complet</label></div>
                <div class="col"><input type="text" style="align-items: center;padding: 10px 10px 10px 15px;border-radius: 16px;font-family: Inter, sans-serif;font-size: 14px;background: #cccccc;border-width: 0px;" required="" minlength="4" maxlength="30" placeholder="Nom complet" autofocus="" name="nom"></div>
            </div>
            <div class="row" style="margin-bottom: 15px;">
                <div class="col-lg-3"><label class="col-form-label col-form-label col-form-label" style="display: grid;position: static;font-family: Inter, sans-serif;font-weight: bold;text-align: center;font-size: 16px;margin-right: -1px;">Telephone</label></div>
                <div class="col"><input type="tel" style="align-items: center;padding: 10px 10px 10px 15px;border-radius: 16px;font-family: Inter, sans-serif;font-size: 14px;background: #cccccc;border-width: 0px;" required="" placeholder="Telephone" name="telephone" autofocus="" minlength="10" maxlength="20" pattern="[0-9]+"></div>
            </div>
            <div class="row" style="margin-bottom: 15px;">
                <div class="col-lg-3"><label class="col-form-label col-form-label col-form-label" style="display: grid;position: static;font-family: Inter, sans-serif;font-weight: bold;text-align: center;font-size: 16px;">Email</label></div>
                <div class="col"><input type="email" style="padding: 10px 10px 10px 15px;border-radius: 16px;font-family: Inter, sans-serif;font-size: 14px;background: #cccccc;border-width: 0px;" required="" min="1" max="99999" step="0.5" placeholder="Email" name="email" autofocus=""></div>
            </div>
            <div class="row">
        <div class="col-lg-5" style="margin-top: 16px;"><input class="btn border  btn-primary d-grid float-end" type="submit" style="width: 120px;font-family: Inter, sans-serif;font-size: 16px;background: #00c993;border-radius: 20px;position: relative;display: grid;font-weight: bold;color: var(--bs-black);border: 0px none var(--bs-purple);padding-top: 7px;padding-right: 20px;padding-bottom: 8px;margin-right: 9px;margin-left: 10px;margin-bottom: 9px;margin-top: 10px;padding-left: 14px;text-align: center;--bs-body-color: #000000;" value="Ajouter " />
            </div>
       
    </form>
 <script src="assets/bootstrap/js/bootstrap.min.js"></script>