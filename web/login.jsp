<%-- 
    Document   : login
    Created on : May 15, 2023, 10:53:27 PM
    Author     : adel
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <style>
        body {
/*            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            height: 100vh;
            */
position: fixed;
width: 100%;
height: 100%;
left: 0px;
top: 0px;

/* SecondaryColor */
background: #CCCCCC;
        }

        .login-container {
position: absolute;
left: 10%;
right: 12%;
top: 8%;
bottom: 10%;
background: #FFFFFF;
box-shadow: 0px 4px 40px rgba(0, 0, 0, 0.15);
border-radius: 30px;
        }

.hbn {
/* Bienvenue */

font-family: 'Inter';
font-style: normal;
font-weight: 700;
font-size: 40px;
line-height: 97px;
text-align: right;
color: #000000;
float: left;
padding-left: 2%;
color: #000000;


/* Inside auto layout */

        }
        .user_lable{/* style3 */
font-family: 'Inter';
font-style: normal;
font-weight: 700;
font-size: 25px;
line-height: 30px;
text-align: right;

color: #000000;
}
.img{
    
position: static;
height: 100%;
float: right;
border-radius: 10px;
    
    
}



.user_block{
            
display: flex;
flex-direction: column;
justify-content: center;
align-items: flex-start;
padding: 0px;
gap: 9px;

position: absolute;
width: 420px;
height: 131px;
left: 87px;
top: 212px;

        }
.pass_block{
width: 121px;
height: 30px;

/* style3 */
font-family: 'Inter';
font-style: normal;
font-weight: 700;
font-size: 25px;
line-height: 30px;
text-align: right;

color: #000000;


/* Inside auto layout */
flex: none;
order: 0;
flex-grow: 0;
}
.form{
  position: relative;
  top: 35%;
  left: -19%;
  float: left;
}
.error-message {
            color: red;
            margin-top: 10px;
            text-align: center;
}
    </style>
</head>
<body>
    <div class="login-container">
        <img class="img" src="images/logo.jpg"  />
        <div class="hbn">Bienvenue</div>
        <form method="post" action="Login" class="form"  >
              <label class="user_lable">Username:</label>
            <input type="text" id="username" name="user" required>
           
              <div >
            <label  class="pass_block" for="password">Password:</label>
            <input type="password" id="password" name="pass" required><br><br>
              </div>
            
            <input type="submit" value="Submit">
        </form>



<c:if test="${not empty errorMessage}">
            <p class="error-message">${errorMessage}</p>
        </c:if>
    </div>
</body>
</html>
