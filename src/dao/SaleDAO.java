package dao;

import model.Sale;
import java.sql.*;

public class SaleDAO {

    public void insert(Connection conn, Sale s) throws Exception {
        String sql = "INSERT INTO Sale(product_id, quantity, price, sale_date) " +
                     "VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, s.getProductId());
            ps.setInt(2, s.getQuantity());
            ps.setDouble(3, s.getPrice());
            ps.setDate(4, new java.sql.Date(s.getSaleDate().getTime()));

            ps.executeUpdate();
        }
    }
}