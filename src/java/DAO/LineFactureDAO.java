/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.LigneFacture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LineFactureDAO {
    private Connection connection;

    public LineFactureDAO() throws ClassNotFoundException {
        try {
            connection = ConnectionFactory.getConnection();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void addLineFacture(LigneFacture ligneFacture) {
        try {
            String query = "INSERT INTO line_facture (num_facture, article_ref, quantity, price) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, ligneFacture.getNumFacture());
                statement.setString(2, ligneFacture.getArticleRef());
                statement.setInt(3, ligneFacture.getQuantity());
                statement.setDouble(4, ligneFacture.getPrice());
                
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
