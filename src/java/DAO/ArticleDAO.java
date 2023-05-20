/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author adel
 */

import Model.Article;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAO {

    public List<Article> getAllArticles() throws SQLException, ClassNotFoundException {
        List<Article> articles = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM articles";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
              int id = resultSet.getInt("id");
                String designation = resultSet.getString("designation");
                double price = resultSet.getDouble("prix");
                int stockQuantity = resultSet.getInt("quantite_stock");

                Article article = new Article(id, designation, price, stockQuantity);
                articles.add(article);
            }

        } catch (SQLException e) {
            System.out.println("erour ta3 afichage  :"+e.getMessage());
        }

        return articles;
    }

    public void addArticle(Article article) throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO articles (designation, prix, quantite_stock) VALUES (?, ?, ?)")) {

            preparedStatement.setString(1, article.getDesignation());
            preparedStatement.setDouble(2, article.getPrice());
            preparedStatement.setInt(3, article.getStockQuantity());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("erour create article  :"+e.getMessage());
        }
    }

    public void deleteArticle(int articleId) throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM articles WHERE id = ?")) {

            preparedStatement.setInt(1, articleId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
           System.out.println("erour :"+e.getMessage());
        }
    }
}
