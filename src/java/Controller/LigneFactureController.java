/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import DAO.LineFactureDAO;
import Model.LigneFacture;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LigneFactureController extends HttpServlet {
    private LineFactureDAO lineFactureDAO;
    
    @Override
    public void init() throws ServletException {
        try {
            super.init();
            lineFactureDAO = new LineFactureDAO();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LigneFactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if (action != null && action.equals("/ajouter-ligne-facture")) {
            addLineFacture(request, response);
        }
    }

    private void addLineFacture(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int numFacture = Integer.parseInt(request.getParameter("numFacture"));
        String articleRef = request.getParameter("articleRef");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));

        // Create a new LineFacture object
        LigneFacture lineFacture = new LigneFacture(numFacture, articleRef, quantity, price);

        // Add the line of the facture to the database
        lineFactureDAO.addLineFacture(lineFacture);

        // Redirect to a confirmation page or any other desired destination
        response.sendRedirect("confirmation.jsp");
    }
}
