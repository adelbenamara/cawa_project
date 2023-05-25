/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import DAO.FactureDAO;
import DAO.ArticleDAO;
import DAO.ClientDAO;
import Model.Article;
import Model.Facture;
import Model.Client;
import Model.LigneFacture;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

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
        HttpSession session = request.getSession(false);
        if ( action.equals("/ajouter-facture")) {
            List<Client> clients =   clientDAO.getAllClients();
            request.setAttribute("clients", clients);
            request.getRequestDispatcher("ajouter-facture.jsp").forward(request, response);
        
        }else if (action.equals("/factures") ) {  
            
                List<Facture> factures = factureDAO.getAllFactures();
                List<Client> clients =   clientDAO.getAllClients();             
                session.setAttribute("factures", factures);
                session.setAttribute("clients", clients); 
                request.getRequestDispatcher("factures.jsp").forward(request, response);
            
}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action =request.getServletPath();
            
            
            switch (action) {
                
                case "/supprimer-line":
                    deleteLine(request, response);
                    break;
                    
                case "/ajouter-facture":
                   
                        addLine(request, response);
                         break;
                case "/supprimer-facture":
                    deleteFacture(request, response);
                    break;
                case "/ajouter-line":
                    
                    addLineFacture(request, response);
                    
                    break;
                    
                case "/envoyer-facture":
                    
                    finishFacture(request, response);
                    
                    break;    
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   private void addLine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
    String modePaiement = request.getParameter("modePaiement");
    int clientId = Integer.parseInt(request.getParameter("clientId"));
    // Create a new Facture object and store it in session
     HttpSession session = request.getSession(false);
    
    Facture facture = new Facture(modePaiement, clientId);
    
    // Retrieve the ligneFactureList from session or create a new one
    List<Article> articles = articleDAO.getAllArticles();
    List<Client> clients = clientDAO.getAllClients();
     session.setAttribute("facture", facture); 
    request.setAttribute("articles", articles);
     session.setAttribute("clients", clients);
    request.getRequestDispatcher("ajouter-ligne.jsp").forward(request, response);
}
    
private void addLineFacture(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
         String articleRef = request.getParameter("article");
        String quantiteVendue = request.getParameter("quantiteVendue");
        int articleid = Integer.parseInt(articleRef);
        int quantity = Integer.parseInt(quantiteVendue);
        
        // Obtenez le prix de l'article à partir de votre source de données (base de données, etc.)
        Article article = articleDAO.getArticleById(articleid);
        double totalPrice = article.getPrice() * quantity;

        LigneFacture line = new LigneFacture(articleid, quantity, totalPrice);
        
        
        HttpSession session = request.getSession();
        ArrayList<LigneFacture> ligneFactureList = (ArrayList<LigneFacture>) session.getAttribute("ligneFactureList");
        if (ligneFactureList == null) {
            ligneFactureList = new ArrayList<>();
            session.setAttribute("ligneFactureList", ligneFactureList); // Sauvegarder la liste dans la session
        }

        ligneFactureList.add(line);
        session.setAttribute("ligneFactureList", ligneFactureList);
        
        List<Article> articles = articleDAO.getAllArticles();
        session.setAttribute("articles", articles);
        
        request.getRequestDispatcher("ajouter-ligne.jsp").forward(request, response);
    } catch (SQLException | ClassNotFoundException ex) {
        Logger.getLogger(FactureController.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    
    
   private void finishFacture(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
    // Retrieve the facture object from session
    Facture facture = (Facture) request.getSession().getAttribute("facture");
     ArrayList<LigneFacture> ligneFactureList = (ArrayList<LigneFacture>) request.getSession().getAttribute("ligneFactureList");
     facture.setLigneFactureList(ligneFactureList);
    // Insert the facture and line items into the database
    factureDAO.insertFacture(facture);

    // Clear the session
   // request.getSession().invalidate();

    // Redirect to the desired page
    response.sendRedirect(request.getContextPath() + "/factures");
}

private void deleteLine(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    ArrayList<LigneFacture> ligneFactureList = (ArrayList<LigneFacture>) session.getAttribute("ligneFactureList");

    if (ligneFactureList != null) {
        String lineIndex = request.getParameter("lineStatusIndex");
        int index = Integer.parseInt(lineIndex);

        if (index >= 0 && index < ligneFactureList.size()) {
            ligneFactureList.remove(index);
        }
    }

    // Rediriger vers la page d'ajout de ligne de facture
    response.sendRedirect("ajouter-ligne.jsp");
}

     private void deleteFacture(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("numFacture");
         int Id = Integer.parseInt(id);
        factureDAO.deleteFacture(Id);

        response.sendRedirect("factures");
    }
}
