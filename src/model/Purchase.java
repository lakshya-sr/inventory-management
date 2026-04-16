package model;

import java.util.Date;

public class Purchase {
    private int purchaseId;
    private int supplierId;
    private int productId;
    private int quantity;
    private double price;
    private Date purchaseDate;

    public Purchase() {}

    public Purchase(int purchaseId, int supplierId, int productId,
                    int quantity, double price, Date purchaseDate) {
        this.purchaseId = purchaseId;
        this.supplierId = supplierId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.purchaseDate = purchaseDate;
    }

    public int getPurchaseId() { return purchaseId; }
    public void setPurchaseId(int purchaseId) { this.purchaseId = purchaseId; }

    public int getSupplierId() { return supplierId; }
    public void setSupplierId(int supplierId) { this.supplierId = supplierId; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public Date getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(Date purchaseDate) { this.purchaseDate = purchaseDate; }

    @Override
    public String toString() {
        return "Purchase{" + purchaseId + ", product=" + productId + ", qty=" + quantity + "}";
    }
}