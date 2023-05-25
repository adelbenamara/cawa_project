/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author adel
 */

public class LoginController extends HttpServlet {

 @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
    String action = request.getServletPath();
     
        if (action.equals("/logout")) {
            // Logout the user by invalidating the session
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            // Redirect the user to the login page
            response.sendRedirect(request.getContextPath()+"/login");
        } else {
  request.getRequestDispatcher("login.jsp").forward(request, response);
        }
}

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
  // Get the username and password from the form
  String username = request.getParameter("user");
  String password = request.getParameter("pass");

  if(username.equals("admin") && password.equals("admin")){
  // If the username and password are correct, create a new session for the user
  HttpSession session = request.getSession();
  session.setAttribute("username", username);

  // Redirect the user to the home page
  response.sendRedirect(request.getContextPath() + "/home");
  }else{
        request.setAttribute("errorMessage", "Identifiants invalides");
            request.getRequestDispatcher("login.jsp").forward(request, response);
  }
}

}