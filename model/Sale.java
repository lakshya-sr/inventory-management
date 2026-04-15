package model;

import java.util.Date;

public class Sale {
    private int saleId;
    private int productId;
    private int quantity;
    private double price;
    private Date saleDate;

    public Sale() {}

    public Sale(int saleId, int productId, int quantity,
                double price, Date saleDate) {
        this.saleId = saleId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.saleDate = saleDate;
    }

    public int getSaleId() { return saleId; }
    public void setSaleId(int saleId) { this.saleId = saleId; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public Date getSaleDate() { return saleDate; }
    public void setSaleDate(Date saleDate) { this.saleDate = saleDate; }

    @Override
    public String toString() {
        return "Sale{" + saleId + ", product=" + productId + ", qty=" + quantity + "}";
    }
}