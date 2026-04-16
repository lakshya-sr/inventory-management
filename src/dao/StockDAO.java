package dao;

import model.Stock;
import java.sql.*;

public class StockDAO {

    public Stock getByProduct(Connection conn, int productId) throws Exception {
        String sql = "SELECT * FROM Stock WHERE product_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Stock(
                        rs.getInt("stock_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity")
                    );
                }
            }
        }
        return null;
    }

    public void updateQuantity(Connection conn, int productId, int quantity) throws Exception {
        String sql = "UPDATE Stock SET quantity=? WHERE product_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, quantity);
            ps.setInt(2, productId);
            ps.executeUpdate();
        }
    }

    public void insert(Connection conn, Stock s) throws Exception {
        String sql = "INSERT INTO Stock(product_id, quantity) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, s.getProductId());
            ps.setInt(2, s.getQuantity());
            ps.executeUpdate();
        }
    }
}