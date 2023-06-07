  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <form method="post" action="ajouter-article"><div class="container" style="  margin-top: 10%;
    margin-left: 20%;"</div>
      <c:if test="${not empty errorMessage}">
        <p style="color: red;   text-align: start">${errorMessage}</p>
    </c:if>
    <div class="row" style="margin-bottom: 15px;">
        <div class="col-lg-2"><label class="col-form-label" style="display: grid;position: static;font-family: Inter, sans-serif;font-weight: bold;text-align: center;font-size: 16px;">Ref-article </label></div>
        <div class="col"><input name="ref_article" type="text" style="/*display: flex;*//*flex-direction: row;*/align-items: center;padding: 10px 10px 10px 15px;/*gap: 10px;*//*width: 420px;*//*height: 72px;*/border-radius: 16px;/*order: 0;*//*flex-grow: 0;*/font-family: Inter, sans-serif;font-size: 14px;background: #cccccc;border-width: 0px;" required minlength="4" maxlength="40" autofocus placeholder="Référence article" /></div>
    </div>
    <div class="row" style="margin-bottom: 15px;">
        <div class="col-lg-2"><label class="col-form-label" style="display: grid;position: static;font-family: Inter, sans-serif;font-weight: bold;text-align: center;font-size: 16px;margin-right: -1px;">Nom article </label></div>
        <div class="col"><input type="text"  name="designation" style="/*display: flex;*//*flex-direction: row;*/align-items: center;padding: 10px 10px 10px 15px;/*gap: 10px;*//*width: 420px;*//*height: 72px;*/border-radius: 16px;/*order: 0;*//*flex-grow: 0;*/font-family: Inter, sans-serif;font-size: 14px;background: #cccccc;border-width: 0px;" required placeholder="designation" autofocus minlength="4" maxlength="40" /></div>
    </div>
    <div class="row" style="margin-bottom: 15px;">
        <div class="col-lg-2"><label class="col-form-label" style="display: grid;position: static;font-family: Inter, sans-serif;font-weight: bold;text-align: center;font-size: 16px;">Prix-vente</label></div>
        <div class="col"><input type="number" name="price" style="padding: 10px 10px 10px 15px;/*gap: 10px;*//*width: 420px;*//*height: 72px;*/border-radius: 16px;/*order: 0;*//*flex-grow: 0;*/font-family: Inter, sans-serif;font-size: 14px;background: #cccccc;border-width: 0px;" required min="1" max="99999" step="0.5" placeholder="Prix" /></div>
    </div>
    <div class="row">
        <div class="col-lg-2"><label class="col-form-label" style="display: grid;position: static;font-family: Inter, sans-serif;font-weight: bold;text-align: center;font-size: 16px;">Quantité </label></div>
        <div class="col"><input type="number" name="stockQuantity" style="padding: 10px 10px 10px 15px;/*gap: 10px;*//*width: 420px;*//*height: 72px;*/border-radius: 16px;/*order: 0;*//*flex-grow: 0;*/font-family: Inter, sans-serif;font-size: 14px;background: #cccccc;border-width: 0px;" min="1" max="9999999" step="1" placeholder="Quantite" required /></div>
    </div>
        <div class="col-lg-5" style="margin-top: 16px;"><input class="btn border  btn-primary d-grid float-end" type="submit" style="width: 120px;font-family: Inter, sans-serif;font-size: 16px;background: #00c993;border-radius: 20px;position: relative;display: grid;font-weight: bold;color: var(--bs-black);border: 0px none var(--bs-purple);padding-top: 7px;padding-right: 20px;padding-bottom: 8px;margin-right: 9px;margin-left: 10px;margin-bottom: 9px;margin-top: 10px;padding-left: 14px;text-align: center;--bs-body-color: #000000;" value="Ajouter " />
        </div>
      
    </div>
     </form>
