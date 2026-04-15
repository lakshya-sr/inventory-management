package service;

import dao.ProductDAO;
import model.Product;
import util.DBConnection;

import java.sql.Connection;
import java.util.List;

public class ProductService {

    private ProductDAO productDAO = new ProductDAO();

    // Add product
    public void addProduct(Product p) throws Exception {
        try (Connection conn = DBConnection.getConnection()) {
            productDAO.insert(conn, p);
        }
    }

    // Get product by ID
    public Product getProduct(int id) throws Exception {
        try (Connection conn = DBConnection.getConnection()) {
            return productDAO.getById(conn, id);
        }
    }

    // Get all products
    public List<Product> getAllProducts() throws Exception {
        try (Connection conn = DBConnection.getConnection()) {
            return productDAO.getAll(conn);
        }
    }
}
