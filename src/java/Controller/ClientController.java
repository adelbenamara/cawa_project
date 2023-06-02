/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ClientDAO;
import Model.Client;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
public class ClientController extends HttpServlet {
    private ClientDAO clientDAO;

    public void init() {
        try {
           
            clientDAO = new ClientDAO();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getServletPath();
             
            switch (action) {
                case "/clients":
                    getAllClients(request, response);
                    break;
                case "/ajouter-client":
                    showAddClientForm(request, response);
                    break;
                default:
                    response.sendRedirect("accueil.jsp");
                    break;
            }
        } catch (ClassNotFoundException ex) {
          System.out.println(" erour :"+ex.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getServletPath();
            
            switch (action) {
                case "/ajouter-client":
                     addClient(request, response);
                break;
                case "/supprimer-client":
                    deleteClient(request, response);
                    break;
                default:
                    response.sendRedirect("accueil.jsp");
                    break;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getAllClients(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        HttpSession session = request.getSession(false);
        List<Client> clients = clientDAO.getAllClients();
        session.setAttribute("clients", clients);
        request.setAttribute("pageToInclude", "clients.jsp");
        request.getRequestDispatcher("accueil.jsp").forward(request, response);
    }

    private void showAddClientForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           request.setAttribute("pageToInclude", "ajouter-client.jsp");
        request.getRequestDispatcher("accueil.jsp").forward(request, response);
    }

 private void addClient(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, ClassNotFoundException {
    String nom = request.getParameter("nom");
    String telephone = request.getParameter("telephone");
    String email = request.getParameter("email");
    
    if (!validateClientFields(nom, telephone, email)) {
        // Afficher un message d'erreur et rediriger vers le formulaire d'ajout de client
        request.setAttribute("errorMessage", "Veuillez vérifier les champs du formulaire.");
             request.setAttribute("pageToInclude", "ajouter-client.jsp");
        request.getRequestDispatcher("accueil.jsp").forward(request, response);
        return;
    }

    Client client = new Client(nom, telephone, email);
    clientDAO.addClient(client);

    response.sendRedirect("clients");
}


    private void deleteClient(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        String id = request.getParameter("clientId");

        clientDAO.deleteClient(id);

        response.sendRedirect("clients");
    }
    private boolean validateClientFields(String nom, String telephone, String email) {
    // Vérification du champ de nom
    if (nom.isEmpty() || !nom.matches("[a-zA-Z]+")) {
        return false;
    }
    
    // Vérification du champ de téléphone
    if (telephone.isEmpty() || !telephone.matches("[0-9]+")) {
        return false;
    }
    
    // Vérification du champ d'email
    if (email.isEmpty() || !email.matches("[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+")) {
        return false;
    }
    
    return true;
}

}
