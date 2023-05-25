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
import java.util.logging.Level;
import java.util.logging.Logger;

public class FactureDAO {
    private Connection connection;

    public FactureDAO() throws ClassNotFoundException {
        try {
            connection = ConnectionFactory.getConnection();
            
        } catch (SQLException e) {
           System.out.println(" erour :"+e.getMessage());
        }
    }
    
    public Facture getFactureByNumFacture(int numFacture) {
    Facture facture = null;

    try {
        String factureQuery = "SELECT * FROM factures WHERE id = ?";
        try (PreparedStatement factureStatement = connection.prepareStatement(factureQuery)) {
            factureStatement.setInt(1, numFacture);
            ResultSet factureResultSet = factureStatement.executeQuery();

            if (factureResultSet.next()) {
                int id = factureResultSet.getInt("id");
                Date dateFacture = factureResultSet.getDate("date_facture");
                String modePaiement = factureResultSet.getString("mode_paiement");
                int id_client = factureResultSet.getInt("id_client");

                facture = new Facture(id, dateFacture, modePaiement, id_client);
            }
        }

        if (facture != null) {
            String ligneFactureQuery = "SELECT * FROM lignes_facture WHERE id_facture = ?";
            try (PreparedStatement ligneFactureStatement = connection.prepareStatement(ligneFactureQuery)) {
                ligneFactureStatement.setInt(1, facture.getNumFacture());
                ResultSet ligneFactureResultSet = ligneFactureStatement.executeQuery();
                  List<LigneFacture> lignesFacture = new ArrayList<>();
                while (ligneFactureResultSet.next()) {
                    int ligneId = ligneFactureResultSet.getInt("id");
                    int articleRef = ligneFactureResultSet.getInt("id_article");
                    int quantity = ligneFactureResultSet.getInt("quantite");
                    ArticleDAO articleDAO = new ArticleDAO();
                    Article article = articleDAO.getArticleById(articleRef);
                    double totalPrice = quantity * article.getPrice();
                    LigneFacture ligneFacture = new LigneFacture(ligneId, articleRef, quantity, totalPrice);
                    lignesFacture.add(ligneFacture);
                }
                facture.setLigneFactureList(lignesFacture);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FactureDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }

    return facture;
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

public void insertFacture(Facture facture) throws SQLException, ClassNotFoundException {
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

        // Insert the line items and update stock quantity
        String ligneFactureQuery = "INSERT INTO lignes_facture (id_facture, id_article, quantite) VALUES (?, ?, ?)";
        statement = connection.prepareStatement(ligneFactureQuery);

        for (LigneFacture ligneFacture : facture.getLigneFactureList()) {
            statement.setInt(1, factureId);
            statement.setInt(2, ligneFacture.getArticleRef());
            statement.setInt(3, ligneFacture.getQuantity());
            statement.executeUpdate();

            // Update the stock quantity of the article
            ArticleDAO articleDAO = new ArticleDAO();
            Article article = articleDAO.getArticleById(ligneFacture.getArticleRef());
            if (article != null) {
                int updatedStockQuantity = article.getStockQuantity() - ligneFacture.getQuantity();
                article.setStockQuantity(updatedStockQuantity);
                articleDAO.updateArticle(article);
            }
        }
    } catch (SQLException e) {
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
