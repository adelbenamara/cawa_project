/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import DAO.LineFactureDAO;
import Model.Facture;
import Model.LigneFacture;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

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
        

       
    }


}
