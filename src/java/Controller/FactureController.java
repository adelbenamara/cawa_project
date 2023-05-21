/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import DAO.FactureDAO;
import DAO.ArticleDAO;
import DAO.ClientDAO;
import Model.Facture;
import Model.Client;
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
    private ClientDAO clientDAO;
    @Override
    public void init() throws ServletException {
        try {
            super.init();
            factureDAO = new FactureDAO();
            articleDAO = new ArticleDAO();
            clientDAO = new ClientDAO();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        
        if (action != null && action.equals("/ajouter-facture")) {
            try {
                List<Client> clients =   clientDAO.getAllClients();
                request.setAttribute("clients", clients);   
                request.getRequestDispatcher("ajouter-facture.jsp").forward(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FactureController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }else if (action != null  && action.equals("/factures") ) {     
                List<Facture> factures = factureDAO.getAllFactures();
              
                request.setAttribute("factures", factures);
                request.getRequestDispatcher("factures.jsp").forward(request, response);
            
}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if (action != null && action.equals("/ajouter-facture")) {
            addFacture(request, response);
        } else if (action != null && action.equals("/supprimer-facture")) {
           deleteFacture(request, response);
        } 
    }

    private void addFacture(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
        String modePaiement = request.getParameter("modePaiement");
        int clientId = Integer.parseInt(request.getParameter("clientId"));

        // Create a new Facture object
        Facture facture = new Facture(modePaiement, clientId);
          System.out.println(" create facteur avec succes"); 
       // Add the facture to the database
        factureDAO.addFacture(facture);

        // Redirect to the factures page
        response.sendRedirect(request.getContextPath() + "/factures");
    }

     private void deleteFacture(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("numFacture");
         int Id = Integer.parseInt(id);
        factureDAO.deleteFacture(Id);

        response.sendRedirect("factures");
    }
}
