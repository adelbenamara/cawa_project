/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
//import java.io.PrintWriter;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author adel
 */
//@WebServlet(name = "Root", urlPatterns = {"/"})
public class Root extends HttpServlet {

@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            // User is already logged in, redirect to the home page
            response.sendRedirect("home");
        } else {
            // Session doesn't exist or user is not logged in, redirect to the login page
           
            String csrfToken = CSRFTokenUtil.generateToken();
            session = request.getSession(true);
            session.setAttribute("csrfToken", csrfToken);
            response.sendRedirect("login.jsp");
        }
    }
    
    // Other methods (e.g., doPost) can be implemented as needed
}