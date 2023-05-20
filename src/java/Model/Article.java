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
    private int id;
    private String designation;
    private double price;
    private int stockQuantity;

    public Article(int id, String designation, double price, int stockQuantity) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
