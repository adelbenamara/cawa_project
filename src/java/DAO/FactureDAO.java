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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Facture;
import java.util.Date;

public class FactureDAO {
    private Connection connection;

    public FactureDAO() throws ClassNotFoundException {
        try {
            connection = ConnectionFactory.getConnection();
        } catch (SQLException e) {
           System.out.println(" erour :"+e.getMessage());
        }
    }

    public List<Facture> getAllFactures() {
        List<Facture> factures = new ArrayList<>();

        try {
            String query = "SELECT * FROM factures";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                ResultSet resultSet = statement.executeQuery();
                
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    Date dateFacture = resultSet.getDate("date_facture");
                    String modePaiement = resultSet.getString("mode_paiement");
                    int id_client = resultSet.getInt("id_client");
                    
                    Facture facture = new Facture(id, dateFacture, modePaiement, id_client);
                    
                    factures.add(facture);
                }   }
        } catch (SQLException e) {
            System.out.println(" erour :"+e.getMessage());
        }

        return factures;
    }

    public void addFacture(Facture facture) {
        try {
            String query = "INSERT INTO factures (date_facture, mode_paiement, id_client) VALUES (CURDATE(), ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, facture.getModePaiement());
                statement.setInt(2, facture.getIdClient());
                
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(" erour :"+e.getMessage());
        }
    }
    public void addFactureLine(int factureId, int articleId, int quantity) {
    try {
        String query = "INSERT INTO facture_lines (facture_id, article_id, quantity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, factureId);
            statement.setInt(2, articleId);
            statement.setInt(3, quantity);
            statement.executeUpdate();
        }
    } catch (SQLException e) {
       System.out.println(" erour :"+e.getMessage());
    }
}


    public void deleteFacture(int id) {
        try {
            String query = "DELETE FROM factures WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);    
                statement.executeUpdate();
            }
        } catch (SQLException e) {
             System.out.println(" erour :"+e.getMessage());
        }
    }
}
