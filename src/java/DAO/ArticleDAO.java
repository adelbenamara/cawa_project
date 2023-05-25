package DAO;

import Model.Article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAO {

    public List<Article> getAllArticles() throws SQLException, ClassNotFoundException {
        List<Article> articles = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM articles");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String designation = resultSet.getString("designation");
                double price = resultSet.getDouble("prix");
                int stockQuantity = resultSet.getInt("quantite_stock");

                Article article = new Article(id, designation, price, stockQuantity);
                articles.add(article);
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des articles : " + e.getMessage());
        }

        return articles;
    }

    public void addArticle(Article article) throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO articles (designation, prix, quantite_stock) VALUES (?, ?, ?)")) {

            statement.setString(1, article.getDesignation());
            statement.setDouble(2, article.getPrice());
            statement.setInt(3, article.getStockQuantity());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'article : " + e.getMessage());
        }
    }

    public void deleteArticle(int articleId) throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM articles WHERE id = ?")) {

            statement.setInt(1, articleId);

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de l'article : " + e.getMessage());
        }
    }

    public Article getArticleById(int articleId) throws SQLException, ClassNotFoundException {
        Article article = null;

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM articles WHERE id = ?")) {

            statement.setInt(1, articleId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String designation = resultSet.getString("designation");
                    double price = resultSet.getDouble("prix");
                    int stockQuantity = resultSet.getInt("quantite_stock");

                    article = new Article(id, designation, price, stockQuantity);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de l'article : " + e.getMessage());
        }

        return article;
    }
}
