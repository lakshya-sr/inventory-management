package dao;

import model.Product;
import java.sql.*;
import java.util.*;

public class ProductDAO {

    public void insert(Connection conn, Product p) throws Exception {
        String sql = "INSERT INTO Product(name, price) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.executeUpdate();
        }
    }

    public Product getById(Connection conn, int id) throws Exception {
        String sql = "SELECT * FROM Product WHERE product_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getDouble("price")
                    );
                }
            }
        }
        return null;
    }

    public List<Product> getAll(Connection conn) throws Exception {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Product(
                    rs.getInt("product_id"),
                    rs.getString("name"),
                    rs.getDouble("price")
                ));
            }
        }
        return list;
    }
}