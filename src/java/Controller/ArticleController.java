package Controller;

import DAO.ArticleDAO;
import Model.Article;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArticleController extends HttpServlet {
    
    private ArticleDAO articleDAO;

    @Override
    public void init() throws ServletException {
        try {
            super.init();
            articleDAO = new ArticleDAO();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String action = req.getServletPath();
    switch (action) {
        case "/articles":
    List<Article> articles = null;
    try {
        articles = articleDAO.getAllArticles();
        req.setAttribute("articles", articles);
        req.setAttribute("pageToInclude", "articles.jsp");
        req.getRequestDispatcher("accueil.jsp").forward(req, resp);
    } catch (SQLException ex) {
        req.setAttribute("error_message", "MYSQL SERVER IS OFF");
        req.getRequestDispatcher("error.jsp").forward(req, resp);
    }
    break;

   case "/ajouter-article":
            req.setAttribute("pageToInclude", "ajouter-article.jsp");
            req.getRequestDispatcher("accueil.jsp").forward(req, resp);
            break;
        default:
            resp.sendRedirect("accueil.jsp");
            break;
    }
}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String action = req.getServletPath();
          
            switch (action) {
                case "/ajouter-article":
                    addArticle(req, resp);
                    break;
                case "/supprimer-article":
                    deleteArticle(req, resp);
                    break;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

private void addArticle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, ClassNotFoundException {
    // Récupérer les données du formulaire
    String ref_article =  req.getParameter("ref_article");
    String designation = req.getParameter("designation");
    String priceStr =req.getParameter("price");
    String  stockQuantityStr = req.getParameter("stockQuantity");
    
   String errorMessage = validateArticleFields(ref_article, designation, priceStr, stockQuantityStr);
if (errorMessage != null) {
    // Afficher le message d'erreur spécifique et rediriger vers la page du formulaire d'ajout
    req.setAttribute("errorMessage", errorMessage);
    req.setAttribute("pageToInclude", "ajouter-article.jsp");
    req.getRequestDispatcher("accueil.jsp").forward(req, resp);
    return;
}

     double price = Double.parseDouble(priceStr);
    int stockQuantity = Integer.parseInt(stockQuantityStr);
    // Vérifier si l'article existe déjà dans la base de données
    Article existingArticle = articleDAO.getArticleByID(ref_article);
    if (existingArticle != null) {
        // L'article existe déjà, augmenter la quantité
        existingArticle.setStockQuantity(existingArticle.getStockQuantity() + stockQuantity);
        existingArticle.setPrice(price);
        articleDAO.updateArticle(existingArticle);
    } else {
        // Créer un nouvel objet Article
        Article article = new Article(ref_article, designation, price, stockQuantity);

        // Ajouter l'article à la base de données
        articleDAO.addArticle(article);
    }

    // Rediriger vers la page des articles
    resp.sendRedirect(req.getContextPath() + "/articles");
}


    private void deleteArticle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Récupérer l'ID de l'article à supprimer
            String articleId =req.getParameter("articleId");
            
            // Supprimer l'article de la base de données
            articleDAO.deleteArticle(articleId);
            
            // Rediriger vers la page des articles
            resp.sendRedirect(req.getContextPath() + "/articles");
        } catch (SQLException ex) {
            Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Méthode pour valider les champs du formulaire d'ajout d'article
private String validateArticleFields(String ref_article, String designation, String priceStr, String stockQuantityStr) {
    String errorMessage = null;

    // Vérification de la référence de l'article
    if (ref_article.isEmpty() || !ref_article.matches("[a-zA-Z0-9]+")) {
        errorMessage = "La référence de l'article est invalide.";
    }

   // Vérification de la désignation
    if (designation.isEmpty() || !designation.matches("[a-zA-Z0-9\\s]+")) {
    errorMessage = "La désignation de l'article est invalide.";
}


    // Vérification du champ de prix
    if (priceStr.isEmpty()) {
        errorMessage = "Le champ de prix est requis.";
    } else {
        try {
            double price = Double.parseDouble(priceStr);
            if (price <= 0) {
                errorMessage = "Le prix de l'article doit être supérieur à zéro.";
            }
        } catch (NumberFormatException e) {
            errorMessage = "Le prix de l'article est invalide.";
        }
    }

    // Vérification du champ de stockQuantity
    if (stockQuantityStr.isEmpty()) {
        errorMessage = "Le champ de quantité en stock est requis.";
    } else {
        try {
            int stockQuantity = Integer.parseInt(stockQuantityStr);
            if (stockQuantity < 0) {
                errorMessage = "La quantité en stock de l'article ne peut pas être négative.";
            }
        } catch (NumberFormatException e) {
            errorMessage = "La quantité en stock de l'article est invalide.";
        }
    }

    return errorMessage;
}


    
    
}
