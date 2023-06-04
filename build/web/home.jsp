<%-- 
    Document   : home.jsp
    Created on : Jun 4, 2023, 4:00:36 AM
    Author     : adel
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<div class="container" style="top: 20%;/*left: 0%;*/right: 0;bottom: 0;/*margin-right: -9px;*//*margin-top: 144px;*/position: fixed;width: 70%;">
    <div class="row">
        <div class="col"><label class="col-form-label" style="color: var(--bs-black);font-size: 25px;font-weight: bold;text-align: center;">Bienvenue Admin</label></div>
    </div>
    <div class="row" style="margin-top: 4px;margin-bottom: 7px;background: #ffffff;"></div>
     <div class="row" style="margin-top: 4px;margin-bottom: 7px;background: #ffffff;"></div>
    <div class="row">
        <div class="col">
            <p style="display: block;text-align: left;font-weight: bold;font-family: Inter, sans-serif;font-size: 16px;">Bienvenue dans l&#39;application du Gestionnaire Commercial !
                <br />Nous sommes ravis que vous ayez choisi de nous rejoindre <br />
                pour révolutionner la façon dont les entreprises fonctionnent.<br>
                Grâce à notre application, vous pourrez gérer efficacement vos articles<br> vos factures et bien plus encore. Vous pourrez enregistrer et suivre vos articles<br> mettre à jour les prix et les quantités en temps réel<br> et même supprimer les articles obsolètes.
            </p>
        </div>
    </div>
    <br> <br> <br> <br> 
    <div class="row">
      <c:if test="${not empty nbclients}">
        <div class="col">
            <p><strong><span style="color: rgb(0, 0, 0);">Clients :${nbclients} </span></strong></p>
        </div>
      </c:if>
        <c:if test="${not empty nbArticles}">
        <div class="col">
            <p><strong<span style="color: rgb(0, 0, 0);">Articles :${nbArticles} </span></strong></p>
        </div></c:if>
            <c:if test="${not empty nbFactures}">
        <div class="col">
            <p><strong><span style="color: rgb(0, 0, 0);">Facture : ${nbFactures}</span></strong></p>
        </div>
        </c:if>
    </div>
</div>