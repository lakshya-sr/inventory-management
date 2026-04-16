package dao;

import model.Purchase;
import java.sql.*;

public class PurchaseDAO {

    public void insert(Connection conn, Purchase p) throws Exception {
        String sql = "INSERT INTO Purchase(supplier_id, product_id, quantity, price, purchase_date) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, p.getSupplierId());
            ps.setInt(2, p.getProductId());
            ps.setInt(3, p.getQuantity());
            ps.setDouble(4, p.getPrice());
            ps.setDate(5, new java.sql.Date(p.getPurchaseDate().getTime()));

            ps.executeUpdate();
        }
    }
}