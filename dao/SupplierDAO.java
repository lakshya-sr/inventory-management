package dao;

import model.Supplier;
import java.sql.*;
import java.util.*;

public class SupplierDAO {

    public void insert(Connection conn, Supplier s) throws Exception {
        String sql = "INSERT INTO Supplier(name, contact_info) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getContactInfo());
            ps.executeUpdate();
        }
    }

    public List<Supplier> getAll(Connection conn) throws Exception {
        List<Supplier> list = new ArrayList<>();
        String sql = "SELECT * FROM Supplier";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Supplier(
                    rs.getInt("supplier_id"),
                    rs.getString("name"),
                    rs.getString("contact_info")
                ));
            }
        }
        return list;
    }
}