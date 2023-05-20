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
public class LigneFacture {
    private Article article;
    private int quantiteVendue;
    private double prixVente;

    public LigneFacture(Article article, int quantiteVendue, double prixVente) {
        this.article = article;
        this.quantiteVendue = quantiteVendue;
        this.prixVente = prixVente;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getQuantiteVendue() {
        return quantiteVendue;
    }

    public void setQuantiteVendue(int quantiteVendue) {
        this.quantiteVendue = quantiteVendue;
    }

    public double getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
    }

    public double getMontantTotal() {
        return quantiteVendue * prixVente;
    }
}