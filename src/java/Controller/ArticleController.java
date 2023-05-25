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
        } catch (SQLException ex) {
            Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
                req.setAttribute("articles", articles);
                req.getRequestDispatcher("articles.jsp").forward(req, resp);
                break;
            case "/ajouter-article":
                req.getRequestDispatcher("ajouter-article.jsp").forward(req, resp);
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
    String designation = req.getParameter("designation");
    double price = Double.parseDouble(req.getParameter("price"));
    int stockQuantity = Integer.parseInt(req.getParameter("stockQuantity"));

    // Vérifier si l'article existe déjà dans la base de données
    Article existingArticle = articleDAO.getArticleByDesignation(designation);
    if (existingArticle != null) {
        // L'article existe déjà, augmenter la quantité
        existingArticle.setStockQuantity(existingArticle.getStockQuantity() + stockQuantity);
        existingArticle.setPrice(price);
        articleDAO.updateArticle(existingArticle);
    } else {
        // Créer un nouvel objet Article
        Article article = new Article(designation, price, stockQuantity);

        // Ajouter l'article à la base de données
        articleDAO.addArticle(article);
    }

    // Rediriger vers la page des articles
    resp.sendRedirect(req.getContextPath() + "/articles");
}


    private void deleteArticle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Récupérer l'ID de l'article à supprimer
            int articleId = Integer.parseInt(req.getParameter("articleId"));
            
            // Supprimer l'article de la base de données
            articleDAO.deleteArticle(articleId);
            
            // Rediriger vers la page des articles
            resp.sendRedirect(req.getContextPath() + "/articles");
        } catch (SQLException ex) {
            Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
