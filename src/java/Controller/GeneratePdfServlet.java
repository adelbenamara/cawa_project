package Controller;
import DAO.ArticleDAO;
import DAO.ClientDAO;
import Model.Article;
import Model.Client;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import Model.Facture;
import Model.LigneFacture;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

public class GeneratePdfServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve the facture details from the request or session
            HttpSession session = request.getSession(false);
            Facture facture = (Facture) session.getAttribute("facture");
            // Retrieve the ligneFactureList from the request or session
            List<LigneFacture> ligneFactureList = (List<LigneFacture>) session.getAttribute("ligneFactureList");
            ClientDAO clientDAO = new ClientDAO();
            ArticleDAO articleDAO = null;
            try {
                articleDAO = new ArticleDAO();
            } catch (SQLException ex) {
                Logger.getLogger(GeneratePdfServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            Client client = clientDAO.getClientByID(facture.getIdClient());
           
            // Create the PDF document
            Document document = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            
            try {
                PdfWriter.getInstance(document, baos);
                document.open();
                
                // Set up fonts and styles
                Font headingFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, Font.BOLD);
                Font tableHeaderFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
                Font tableCellFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
                
                // Add the facture details to the PDF document
                Paragraph factureHeader = new Paragraph("Facture Information", headingFont);
                factureHeader.setSpacingAfter(10f);
                document.add(factureHeader);
                PdfPTable FactureTable = new PdfPTable(4);
                FactureTable.setWidthPercentage(100);
                FactureTable.setWidths(new float[]{1, 1, 1, 1});
                FactureTable.addCell(new Phrase("Facture Number:", tableHeaderFont));
                FactureTable.addCell(new Phrase("Nom Client", tableHeaderFont));
                FactureTable.addCell(new Phrase("Date de Facture", tableHeaderFont));
                FactureTable.addCell(new Phrase("Mode of Payment", tableHeaderFont));
                FactureTable.addCell(new Phrase( String.valueOf(facture.getNumFacture()) , tableCellFont));
                  FactureTable.addCell(new Phrase( client.getNom() , tableCellFont));
                    FactureTable.addCell(new Phrase( String.valueOf(facture.getDateFacture() ) , tableCellFont));
                      FactureTable.addCell(new Phrase( facture.getModePaiement() , tableCellFont));
           document.add(FactureTable);
                // Add the ligneFactureList details to the PDF document
                Paragraph ligneFactureHeader = new Paragraph("Line Facture Information", headingFont);
                ligneFactureHeader.setSpacingBefore(20f);
                ligneFactureHeader.setSpacingAfter(10f);
                document.add(ligneFactureHeader);
                // Create a table for ligneFactureList
                PdfPTable ligneFactureTable = new PdfPTable(5);
                ligneFactureTable.setWidthPercentage(100);
                ligneFactureTable.setWidths(new float[]{0.8f, 1.7f, 0.5f, 0.5f,0.5f});
                ligneFactureTable.addCell(new Phrase("Référence article", tableHeaderFont));
                ligneFactureTable.addCell(new Phrase("Designation Article", tableHeaderFont));
                ligneFactureTable.addCell(new Phrase("Prix unitaire", tableHeaderFont));
                ligneFactureTable.addCell(new Phrase("Quantité vendue", tableHeaderFont));
                ligneFactureTable.addCell(new Phrase("Total Price", tableHeaderFont));
                
                // Add ligneFactureList details to the table
                for (LigneFacture ligneFacture : ligneFactureList) {
                     Article article = null;
                    try {
                        article = articleDAO.getArticleByID(ligneFacture.getArticleRef());
                    } catch (SQLException ex) {
                        Logger.getLogger(GeneratePdfServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                     ligneFactureTable.addCell(new Phrase( article.getRef_article(), tableCellFont));
                    ligneFactureTable.addCell(new Phrase( article.getDesignation(), tableCellFont)); // Replace with actual article name
                    ligneFactureTable.addCell(new Phrase(String.valueOf( article.getPrice()) ,tableCellFont));
                    ligneFactureTable.addCell(new Phrase(String.valueOf(ligneFacture.getQuantity()), tableCellFont));
                    ligneFactureTable.addCell(new Phrase(String.valueOf(ligneFacture.getTotalPrice()), tableCellFont));
                }
                document.add(ligneFactureTable);
       
                document.add(new Paragraph("Total HT: "+String.valueOf(facture.getTotalHT()), tableHeaderFont));
                document.add(new Paragraph("Total TTC: "+String.valueOf(facture.getTotalTTC()), tableHeaderFont));
                // Add total details to the PDF document
                
                document.close();
                
                // Set response headers for PDF download
                response.setHeader("Content-Disposition", "attachment; filename=\"facture.pdf\"");
                response.setContentType("application/pdf");
                response.setContentLength(baos.size());
                
                // Write the PDF document to the response output stream
                response.getOutputStream().write(baos.toByteArray());
                response.getOutputStream().flush();
            } catch (DocumentException e) {
                System.out.println("erour"+e.getMessage());
                // Handle the exception
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GeneratePdfServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
