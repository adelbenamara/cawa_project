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
       String factureQuery = "SELECT * FROM factures WHERE id = ? AND is_delete = 0";
        try (PreparedStatement factureStatement = connection.prepareStatement(factureQuery)) {
            factureStatement.setInt(1, numFacture);
            ResultSet factureResultSet = factureStatement.executeQuery();

            if (factureResultSet.next()) {
                int id = factureResultSet.getInt("id");
                Date dateFacture = factureResultSet.getDate("date_facture");
                String modePaiement = factureResultSet.getString("mode_paiement");
                int id_client = factureResultSet.getInt("id_client");

                facture = new Facture(id, (java.sql.Date) dateFacture, modePaiement, id_client);
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
                    String articleRef = ligneFactureResultSet.getString("ref_article");
                    int quantity = ligneFactureResultSet.getInt("quantite");
                    ArticleDAO articleDAO = new ArticleDAO();
                    Article article = articleDAO.getArticleByID(articleRef);
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
        String query = "SELECT * FROM factures WHERE is_delete = 0";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date dateFacture = resultSet.getDate("date_facture");
                String modePaiement = resultSet.getString("mode_paiement");
                int id_client = resultSet.getInt("id_client");

                Facture facture = new Facture(id, (java.sql.Date) dateFacture, modePaiement, id_client);

                factures.add(facture);
            }
        }
    } catch (SQLException e) {
        System.out.println("Erreur lors de la récupération des factures : " + e.getMessage());
    }

    return factures;
}

//

public void insertFacture(Facture facture) throws SQLException, ClassNotFoundException {
    PreparedStatement statement = null;
    try {
        // Insert the facture details
        String factureQuery = "INSERT INTO factures (date_facture, mode_paiement, id_client) VALUES (?, ?, ?)";
        statement = connection.prepareStatement(factureQuery, Statement.RETURN_GENERATED_KEYS); // Specify RETURN_GENERATED_KEYS
      statement.setDate(1, new java.sql.Date(facture.getDateFacture().getTime()));
        statement.setString(2, facture.getModePaiement());
        statement.setInt(3, facture.getIdClient());
        statement.executeUpdate();

        // Get the auto-generated facture ID
        ResultSet generatedKeys = statement.getGeneratedKeys();
        int factureId = -1;
        if (generatedKeys.next()) {
            factureId = generatedKeys.getInt(1);
        }

        // Insert the line items and update stock quantity
        String ligneFactureQuery = "INSERT INTO lignes_facture (id_facture, ref_article, quantite) VALUES (?, ?, ?)";
        statement = connection.prepareStatement(ligneFactureQuery);

        for (LigneFacture ligneFacture : facture.getLigneFactureList()) {
            statement.setInt(1, factureId);
            statement.setString(2, ligneFacture.getArticleRef());
            statement.setInt(3, ligneFacture.getQuantity());
            statement.executeUpdate();

            // Update the stock quantity of the article
            ArticleDAO articleDAO = new ArticleDAO();
            Article article = articleDAO.getArticleByID(ligneFacture.getArticleRef());
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
        // Update the is_delete column in factures table
        String updateFactureQuery = "UPDATE factures SET is_delete = 1 WHERE id = ?";
        try (PreparedStatement factureStatement = connection.prepareStatement(updateFactureQuery)) {
            factureStatement.setInt(1, id);
            factureStatement.executeUpdate();
        }

        // Update the is_delete column in lignes_facture table
        String updateLineItemsQuery = "UPDATE lignes_facture SET is_delete = 1 WHERE id_facture = ?";
        try (PreparedStatement lineItemsStatement = connection.prepareStatement(updateLineItemsQuery)) {
            lineItemsStatement.setInt(1, id);
            lineItemsStatement.executeUpdate();
        }
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
}
public int countFactures() {
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    int count = 0;

    try {
        String query = "SELECT COUNT(*) FROM factures WHERE is_delete = 0";
        statement = connection.prepareStatement(query);
        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            count = resultSet.getInt(1);
        }
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    } finally {
        // Close the resultSet, statement, and connection (similar to other methods)
    }

    return count;
}


}
