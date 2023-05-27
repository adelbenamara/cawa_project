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


public class Article {

  
    private String  ref_article;
    private String designation;
    private double price;
    private int stockQuantity;

    public Article(String  ref_article, String designation, double price, int stockQuantity) {
        this.ref_article=ref_article;
        this.designation = designation;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
      public Article(String designation, double price, int stockQuantity) {
        this.designation = designation;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    // Getters and Setters

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
      public String getRef_article() {
        return ref_article;
    }

    public void setRef_article(String ref_article) {
        this.ref_article = ref_article;
    }
}
