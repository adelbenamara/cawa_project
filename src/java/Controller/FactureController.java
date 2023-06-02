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
        } catch (ClassNotFoundException | SQLException ex) {
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
            request.setAttribute("pageToInclude", "ajouter-facture.jsp");
            request.getRequestDispatcher("accueil.jsp").forward(request, response);
        
        }else if (action.equals("/factures") ) {  
            
                List<Facture> factures = factureDAO.getAllFactures();
                List<Client> clients =   clientDAO.getAllClients();             
                session.setAttribute("factures", factures);
                session.setAttribute("clients", clients); 
                request.setAttribute("pageToInclude", "factures.jsp");
                request.getRequestDispatcher("accueil.jsp").forward(request, response);
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
                case "/details-facture":
                  showFactureDetails(request, response);
                 break;

            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    private void showFactureDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     HttpSession session = request.getSession(false);
        String numFacture = request.getParameter("numFacture");
    int idFacture = Integer.parseInt(numFacture);
    Facture facture = factureDAO.getFactureByNumFacture(idFacture);
    facture.calculateTotalHT();
    facture.calculateTotalTTC(Facture.TVA);
   List<Article> articles = new ArrayList<>();
        try {
            articles = articleDAO.getAllArticles();
        } catch (SQLException ex) {
            Logger.getLogger(FactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
   List<LigneFacture> ligneFactureList = facture.getLigneFactureList();
   List<Client> clients =  clientDAO.getAllClients();
    session.setAttribute("facture", facture);
    session.setAttribute("clients", clients);
    session.setAttribute("ligneFactureList", ligneFactureList);
    session.setAttribute("articles", articles);
                request.setAttribute("pageToInclude", "facture-details.jsp");
                request.getRequestDispatcher("accueil.jsp").forward(request, response);
}

    
private void addLine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
    String modePaiement = request.getParameter("modePaiement");
    String clientIdStr = request.getParameter("clientId");

    if (modePaiement != null && !modePaiement.isEmpty() && clientIdStr != null && !clientIdStr.isEmpty()) {
        try {
            int clientId = Integer.parseInt(clientIdStr);

            // Vérification de la validité du client
            if (!clientDAO.isValidClient(clientId)) {
                // Afficher un message d'erreur et rediriger vers la page d'ajout de facture
                request.setAttribute("errorMessage", "Client invalide");
                  request.setAttribute("pageToInclude", "ajouter-facture.jsp");
                request.getRequestDispatcher("accueil.jsp").forward(request, response);
                return;
            }

            // Create a new Facture object and store it in session
            HttpSession session = request.getSession(false);
            Facture facture = new Facture(modePaiement, clientId);

            // Retrieve the ligneFactureList from session or create a new one
            List<Article> articles = articleDAO.getAllArticles();
            List<Client> clients = clientDAO.getAllClients();
            session.setAttribute("facture", facture);
            session.setAttribute("articles", articles);
            session.setAttribute("clients", clients);
            request.setAttribute("pageToInclude", "ajouter-ligne.jsp");
            request.getRequestDispatcher("accueil.jsp").forward(request, response);
        } catch (NumberFormatException ex) {
            // Afficher un message d'erreur et rediriger vers la page d'ajout de facture
                request.setAttribute("errorMessage", "ID client invalide");            request.getRequestDispatcher("accueil.jsp").forward(request, response);
                request.setAttribute("pageToInclude", "ajouter-facture.jsp");
                request.getRequestDispatcher("accueil.jsp").forward(request, response);
        }
    } else {
        // Afficher un message d'erreur et rediriger vers la page d'ajout de facture
        request.setAttribute("errorMessage", "Veuillez remplir tous les champs");
        request.setAttribute("pageToInclude", "ajouter-facture.jsp");
                request.getRequestDispatcher("accueil.jsp").forward(request, response);
    }
}

   
private void addLineFacture(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String articleRef = request.getParameter("article");
    String quantiteVendue = request.getParameter("quantiteVendue");

    if (articleRef != null && !articleRef.isEmpty() && quantiteVendue != null && !quantiteVendue.isEmpty()) {
        try {
            String articleId =articleRef;
            int quantity = Integer.parseInt(quantiteVendue);

            // Obtain the article from your data source (database, etc.)
            Article article = articleDAO.getArticleByID(articleId);

            // Check if the article exists and the quantities are valid
            if (article != null && quantity > 0 && quantity <= article.getStockQuantity()) {
                double totalPrice = article.getPrice() * quantity;

                LigneFacture line = new LigneFacture(articleId, quantity, totalPrice);
                HttpSession session = request.getSession();
                ArrayList<LigneFacture> ligneFactureList = (ArrayList<LigneFacture>) session.getAttribute("ligneFactureList");
                if (ligneFactureList == null) {
                    ligneFactureList = new ArrayList<>();
                    session.setAttribute("ligneFactureList", ligneFactureList); // Save the list in the session
                }

                ligneFactureList.add(line);
                session.setAttribute("ligneFactureList", ligneFactureList);

                List<Article> articles = articleDAO.getAllArticles();
                session.setAttribute("articles", articles);
                
                 request.setAttribute("pageToInclude", "ajouter-ligne.jsp");
                request.getRequestDispatcher("accueil.jsp").forward(request, response);
            } else {
              
                request.setAttribute("errorMessage", "Invalid article or quantity");
                 request.setAttribute("pageToInclude", "ajouter-ligne.jsp");
                request.getRequestDispatcher("accueil.jsp").forward(request, response);
            }
        } catch (NumberFormatException | SQLException ex) {
            Logger.getLogger(FactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else {
     
                request.setAttribute("errorMessage", "Invalid article or quantity");
                request.setAttribute("pageToInclude", "ajouter-ligne.jsp");
                request.getRequestDispatcher("accueil.jsp").forward(request, response);
    }
}


    
    
private void finishFacture(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
    // Retrieve the facture object from session
    Facture facture = (Facture) request.getSession().getAttribute("facture");
    ArrayList<LigneFacture> ligneFactureList = (ArrayList<LigneFacture>) request.getSession().getAttribute("ligneFactureList");
    facture.setLigneFactureList(ligneFactureList);

    // Insert the facture and line items into the database
    factureDAO.insertFacture(facture);

    // Remove facture and ligneFactureList from session
    request.getSession().removeAttribute("facture");
    request.getSession().removeAttribute("ligneFactureList");

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
                request.setAttribute("pageToInclude", "ajouter-ligne.jsp");
                request.getRequestDispatcher("accueil.jsp").forward(request, response);
}

     private void deleteFacture(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("numFacture");
         int Id = Integer.parseInt(id);
        factureDAO.deleteFacture(Id);

        response.sendRedirect("factures");
    }
}
