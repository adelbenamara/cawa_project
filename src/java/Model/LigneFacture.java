package Model;

import java.io.Serializable;

public class LigneFacture implements Serializable {

   
    
    private int numFacture;
    private String articleRef;
    private String designation ;
    private int quantity;
    private double totalPrice;


 public LigneFacture(int numFacture, String articleRef, int quantity, double totalPrice) {
    this.numFacture = numFacture;
    this.articleRef = articleRef;
    this.quantity = quantity;
    this.totalPrice = totalPrice;
}

public LigneFacture(String articleRef, int quantity, double totalPrice) {
    this.articleRef = articleRef;
    this.quantity = quantity;
    this.totalPrice = totalPrice;
}

public double getTotalPrice() {
    return totalPrice;
}

public void setTotalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
}


    public int getNumFacture() {
        return numFacture;
    }

    public void setNumFacture(int numFacture) {
        this.numFacture = numFacture;
    }

    public String getArticleRef() {
        return articleRef;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    
     public String getDesignation() {
        return designation;
    }


    public void setArticleRef(String articleRef) {
        this.articleRef = articleRef;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

 
}
