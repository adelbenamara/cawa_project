package Model;

public class LigneFacture {
    private int numFacture;
    private int articleRef;
    private int quantity;
    private double totalPrice;


 public LigneFacture(int numFacture, int articleRef, int quantity, double totalPrice) {
    this.numFacture = numFacture;
    this.articleRef = articleRef;
    this.quantity = quantity;
    this.totalPrice = totalPrice;
}

public LigneFacture(int articleRef, int quantity, double totalPrice) {
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

    public int getArticleRef() {
        return articleRef;
    }

    public void setArticleRef(int articleRef) {
        this.articleRef = articleRef;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

 
}
