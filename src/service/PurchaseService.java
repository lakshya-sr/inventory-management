package service;

import dao.*;
import model.*;
import util.DBConnection;

import java.sql.Connection;

public class PurchaseService {

    private PurchaseDAO purchaseDAO = new PurchaseDAO();
    private StockDAO stockDAO = new StockDAO();

    public void createPurchase(Purchase p) throws Exception {
        Connection conn = DBConnection.getConnection();
        try {
            conn.setAutoCommit(false);

            purchaseDAO.insert(conn, p);

            Stock stock = stockDAO.getByProduct(conn, p.getProductId());

            if (stock == null) {
                stockDAO.insert(conn, new Stock(0, p.getProductId(), p.getQuantity()));
            } else {
                int newQty = stock.getQuantity() + p.getQuantity();
                stockDAO.updateQuantity(conn, p.getProductId(), newQty);
            }

            conn.commit();

        } catch (Exception e) {
            conn.rollback();   // 🔥 REQUIRED
            throw e;
        } finally {
            conn.close();
        }
    }
}
