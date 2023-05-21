/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author adel
 */
import java.util.Date;
import java.util.List;

public class Facture {
    private int numFacture;
    private Date dateFacture;
    private float totalHT;
    private float totalTTC;
    private String modePaiement;
    private int  idClient;
    private List<LigneFacture> ligneFactureList;

    
       public Facture( String modePaiement,int  id_client) {
        this.modePaiement = modePaiement;
        this.idClient= id_client;
       
    }
       
   
    
    
    public Facture(int id, Date dateFacture, String modePaiement, int id_client) {
        this.numFacture = id;
        this.dateFacture = dateFacture;
        this.modePaiement = modePaiement;
        this.idClient= id_client;
    }

    public int getNumFacture() {
        return numFacture;
    }

    public void setNumFacture(int numFacture) {
        this.numFacture = numFacture;
    }

    public Date getDateFacture() {
        return dateFacture;
    }

    public void setDateFacture(Date dateFacture) {
        this.dateFacture = dateFacture;
    }

    public float getTotalHT() {
        return totalHT;
    }

    public void setTotalHT(float totalHT) {
        this.totalHT = totalHT;
    }

    public float getTotalTTC() {
        return totalTTC;
    }

    public void setTotalTTC(float totalTTC) {
        this.totalTTC = totalTTC;
    }

    public String getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(String modePaiement) {
        this.modePaiement = modePaiement;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int client) {
        this.idClient = client;
    }

    public List<LigneFacture> getLigneFactureList() {
        return ligneFactureList;
    }

    public void setLigneFactureList(List<LigneFacture> ligneFactureList) {
        this.ligneFactureList = ligneFactureList;
    }
}
