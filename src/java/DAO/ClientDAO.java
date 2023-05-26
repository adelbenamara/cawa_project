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
import Model.Client;

public class ClientDAO {
      private Connection connection;

      public ClientDAO() throws ClassNotFoundException {
        try {
            connection = ConnectionFactory.getConnection();
        } catch (SQLException e) {
           System.out.println(" erour :"+e.getMessage());
        }
    }
    
    
    public Client getClientByID(int id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Client client = null;

        try {
            String query = "SELECT * FROM clients WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nom = resultSet.getString("nom");
                String telephone = resultSet.getString("telephone");
                String email = resultSet.getString("email");

                client = new Client(id, nom, telephone, email);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            // Close the resultSet, statement, and connection (similar to other methods)
        }

        return client;
    }
    
    public List<Client> getAllClients()   {
        List<Client> clients = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT * FROM clients";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                 String telephone = resultSet.getString("telephone");
                String email = resultSet.getString("email");
         
                Client client = new Client(id, nom,telephone, email);
                clients.add(client);
            }
        } catch (SQLException e) {
           System.out.println(" erour :"+e.getMessage());
//        } finally {
//            if (resultSet != null) {
//                try {
//                    resultSet.close();
//                } catch (SQLException e) {
//                   System.out.println(" erour :"+e.getMessage());
//                }
//            }
//            if (statement != null) {
//                try {
//                    statement.close();
//                } catch (SQLException e) {
//                  System.out.println(" erour :"+e.getMessage());
//                }
//            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                  System.out.println(" erour :"+e.getMessage());
//                }
//            }
        }

        return clients;
    }

    public void addClient(Client client)  {
       
        PreparedStatement statement = null;

        try {
          
            String query = "INSERT INTO clients (nom,  telephone,email) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, client.getNom());
            statement.setString(2, client.getTelephone());
            statement.setString(3, client.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
             System.out.println(" erour :"+e.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                     System.out.println(" erour :"+e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(" erour :"+e.getMessage());
                }
            }
        }
    }

    public void deleteClient(String clientId)  {
       
        PreparedStatement statement = null;

        try {
           
           int Id = Integer.parseInt(clientId);
            String query = "DELETE FROM clients WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, Id);
            statement.executeUpdate();
        } catch (SQLException e) {
           System.out.println(" erour :"+e.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                   System.out.println(" erour :"+e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                 System.out.println(" erour :"+e.getMessage());
                }
            }
        }
    }
}
