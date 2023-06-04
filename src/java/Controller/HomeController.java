/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ArticleDAO;
import DAO.ClientDAO;
import DAO.FactureDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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


public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         HttpSession session = request.getSession(false);
        int nonDeletedClientCount = 0;
         int nonDeletedArticleCount = 0;
          int nonDeletedFactureCount = 0;
        try {
            ClientDAO clientDAO = new ClientDAO();
            ArticleDAO articleDAO = new ArticleDAO();
            FactureDAO factureDAO = new FactureDAO();
            nonDeletedClientCount= clientDAO.countClients();
            nonDeletedArticleCount= articleDAO.countArticles();
            nonDeletedFactureCount = factureDAO.countFactures();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);}
         session.setAttribute("nbclients", nonDeletedClientCount);
          session.setAttribute("nbArticles", nonDeletedArticleCount);
           session.setAttribute("nbFactures", nonDeletedFactureCount);
         request.setAttribute("pageToInclude", "home.jsp");
        request.getRequestDispatcher("accueil.jsp").forward(request, response);
    }

}
