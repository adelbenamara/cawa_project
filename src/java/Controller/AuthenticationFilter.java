/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter(urlPatterns = {"/home", "/clients"})
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if needed
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // Check if the user is authenticated
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            // User is not authenticated, redirect to the login page
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            // User is authenticated, proceed with the request
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
       //To change body of generated methods, choose Tools | Templates.
    }

   
}
