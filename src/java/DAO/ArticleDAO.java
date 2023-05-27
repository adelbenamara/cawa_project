package DAO;

import Model.Article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAO {
     Connection connection =null;
     public ArticleDAO() throws ClassNotFoundException ,java.sql.SQLException {
        try {
           connection = ConnectionFactory.getConnection();
        } catch (SQLException e) {
           System.out.println(" erour :"+e.getMessage());
        }
    }

     
    public List<Article> getAllArticles() throws SQLException {
        List<Article> articles = new ArrayList<>();

        try (
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM articles");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String  ref_article = resultSet.getString("ref_article");
                String designation = resultSet.getString("designation");
                double price = resultSet.getDouble("prix");
                int stockQuantity = resultSet.getInt("quantite_stock");

                Article article = new Article(ref_article, designation, price, stockQuantity);
                articles.add(article);
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des articles : " + e.getMessage());
        }

        return articles;
    }

    public void addArticle(Article article) throws SQLException {
        try (
             PreparedStatement statement = connection.prepareStatement("INSERT INTO articles (ref_article,designation, prix, quantite_stock) VALUES (?, ?, ?, ?)")) {
            statement.setString(1, article.getRef_article());
            statement.setString(2, article.getDesignation());
            statement.setDouble(3, article.getPrice());
            statement.setInt(4, article.getStockQuantity());
            
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'article : " + e.getMessage());
        }
    }

    public void deleteArticle(String ref_article) throws SQLException {
        try ( 
             PreparedStatement statement = connection.prepareStatement("DELETE FROM articles WHERE ref_article = ?")) {

            statement.setString(1, ref_article);

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de l'article : " + e.getMessage());
        }
    }
    
    public Article getArticleByID(String ref_article) throws SQLException {
    Article article = null;

    try (
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM articles WHERE ref_article = ?")) {

        statement.setString(1, ref_article);

        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
               
                String designation = resultSet.getString("designation"); 
                double price = resultSet.getDouble("prix");
                int stockQuantity = resultSet.getInt("quantite_stock");

                article = new Article(ref_article, designation, price, stockQuantity);
            }
        }

    } catch (SQLException e) {
        System.out.println("Erreur lors de la récupération de l'article par désignation : " + e.getMessage());
    }

    return article;
}


//    public Article getArticleById(int articleId) throws SQLException{
//        Article article = null;
//
//        try (
//             PreparedStatement statement = connection.prepareStatement("SELECT * FROM articles WHERE id = ?")) {
//
//            statement.setInt(1, articleId);
//
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    int id = resultSet.getInt("id");
//                    String designation = resultSet.getString("designation");
//                    double price = resultSet.getDouble("prix");
//                    int stockQuantity = resultSet.getInt("quantite_stock");
//
//                    article = new Article(id, designation, price, stockQuantity);
//                }
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Erreur lors de la récupération de l'article : " + e.getMessage());
//        }
//
//        return article;
//    }
    
    
    public void updateArticle(Article article) throws SQLException {
    try (
        PreparedStatement statement = connection.prepareStatement("UPDATE articles SET designation = ?, prix = ?, quantite_stock = ? WHERE ref_article = ?")) {
       
        statement.setString(4, article.getRef_article());
        statement.setString(1, article.getDesignation());
        statement.setDouble(2, article.getPrice());
        statement.setInt(3, article.getStockQuantity());
        

        statement.executeUpdate();

    } catch (SQLException e) {
        System.out.println("Erreur lors de la mise à jour de l'article : " + e.getMessage());
    }
}

}
