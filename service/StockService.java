package service;

import dao.StockDAO;
import model.Stock;
import util.DBConnection;

import java.sql.Connection;

public class StockService {

    private StockDAO stockDAO = new StockDAO();

    public Stock getStock(int productId) throws Exception {
        try (Connection conn = DBConnection.getConnection()) {
            return stockDAO.getByProduct(conn, productId);
        }
    }

    public void adjustStock(int productId, int quantity) throws Exception {
        try (Connection conn = DBConnection.getConnection()) {
            stockDAO.updateQuantity(conn, productId, quantity);
        }
    }
}
