/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import DAO.FactureDAO;
import DAO.ArticleDAO;
import Model.Facture;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FactureController extends HttpServlet {
    private FactureDAO factureDAO;
    private ArticleDAO articleDAO;

    @Override
    public void init() throws ServletException {
        try {
            super.init();
            factureDAO = new FactureDAO();
            articleDAO = new ArticleDAO();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        
        if (action != null && action.equals("/ajouter-facture")) {
            
        request.getRequestDispatcher("ajouter-facture.jsp").forward(request, response);   
        
        }else if (action != null  && action.equals("factures") ) {
            System.out.println("dkhal /factures");
        List<Facture> factures = factureDAO.getAllFactures();
        request.setAttribute("/factures", factures);
        request.getRequestDispatcher("factures.jsp").forward(request, response);}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if (action != null && action.equals("/ajouter-facture")) {
            addFacture(request, response);
        } else if (action != null && action.equals("addFactureLine")) {
            addFactureLine(request, response);
        } 
    }

    private void addFacture(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dateFacture = request.getParameter("dateFacture");
        String modePaiement = request.getParameter("modePaiement");
        int clientId = Integer.parseInt(request.getParameter("clientId"));

        // Create a new Facture object
        Facture facture = new Facture(modePaiement, clientId);

        // Add the facture to the database
        factureDAO.addFacture(facture);

        // Redirect to the factures page
        response.sendRedirect(request.getContextPath() + "/factures");
    }

    private void addFactureLine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int factureId = Integer.parseInt(request.getParameter("factureId"));
        int articleId = Integer.parseInt(request.getParameter("articleId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

     
        // Add the facture line to the database
        factureDAO.addFactureLine(factureId, articleId, quantity);

        // Redirect to the factures page
        response.sendRedirect(request.getContextPath() + "/factures");
    }
}
