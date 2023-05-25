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
import Model.LigneFacture;
import java.sql.Statement;
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

//


public void insertFacture(Facture facture) throws SQLException {
    PreparedStatement statement = null;
    try {
      // Insert the facture details
String factureQuery = "INSERT INTO factures (date_facture, mode_paiement, id_client) VALUES (CURRENT_DATE, ?, ?)";
statement = connection.prepareStatement(factureQuery, Statement.RETURN_GENERATED_KEYS); // Specify RETURN_GENERATED_KEYS
statement.setString(1, facture.getModePaiement());
statement.setInt(2, facture.getIdClient());
statement.executeUpdate();

// Get the auto-generated facture ID
ResultSet generatedKeys = statement.getGeneratedKeys();
        int factureId = -1;
        if (generatedKeys.next()) {
            factureId = generatedKeys.getInt(1);
        }
        // Insert the line items
        String ligneFactureQuery = "INSERT INTO lignes_facture (id_facture, id_article, quantite) VALUES (?, ?, ?)";
        statement = connection.prepareStatement(ligneFactureQuery);
        for (LigneFacture ligneFacture : facture.getLigneFactureList()) {
            statement.setInt(1, factureId);
            statement.setInt(2, ligneFacture.getArticleRef());
            statement.setInt(3, ligneFacture.getQuantity());
            statement.executeUpdate();
        }
        
//    } finally {
//        // Close the database resources
//        if (statement != null) {
//            statement.close();
//        }
//        if (connection != null) {
//            connection.close();
//        }
//    }
}catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
}
public void deleteFacture(int id) {
    try {
        // Delete the line items associated with the facture
        String deleteLineItemsQuery = "DELETE FROM lignes_facture WHERE id_facture = ?";
        try (PreparedStatement lineItemsStatement = connection.prepareStatement(deleteLineItemsQuery)) {
            lineItemsStatement.setInt(1, id);
            lineItemsStatement.executeUpdate();
        }

        // Delete the facture itself
        String deleteFactureQuery = "DELETE FROM factures WHERE id = ?";
        try (PreparedStatement factureStatement = connection.prepareStatement(deleteFactureQuery)) {
            factureStatement.setInt(1, id);
            factureStatement.executeUpdate();
        }
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
}

}
