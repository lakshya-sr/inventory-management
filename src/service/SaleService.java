package service;

import dao.*;
import model.*;
import util.DBConnection;

import java.sql.Connection;

public class SaleService {

    private SaleDAO saleDAO = new SaleDAO();
    private StockDAO stockDAO = new StockDAO();

    public void createSale(Sale s) throws Exception {
        Connection conn = DBConnection.getConnection();
        try {
            conn.setAutoCommit(false);

            Stock stock = stockDAO.getByProduct(conn, s.getProductId());

            if (stock == null || stock.getQuantity() < s.getQuantity()) {
                throw new Exception("Insufficient stock!");
            }

            saleDAO.insert(conn, s);

            int newQty = stock.getQuantity() - s.getQuantity();
            stockDAO.updateQuantity(conn, s.getProductId(), newQty);

            conn.commit();

        } catch (Exception e) {
            conn.rollback();   // 🔥 REQUIRED
            throw e;
        } finally {
            conn.close();
        }
    }
}
