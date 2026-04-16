package service;

import dao.SupplierDAO;
import model.Supplier;
import util.DBConnection;

import java.sql.Connection;
import java.util.List;

public class SupplierService {

    private SupplierDAO supplierDAO = new SupplierDAO();

    // Add supplier
    public void addSupplier(Supplier s) throws Exception {
        try (Connection conn = DBConnection.getConnection()) {
            supplierDAO.insert(conn, s);
        }
    }

    // Get all suppliers
    public List<Supplier> getAllSuppliers() throws Exception {
        try (Connection conn = DBConnection.getConnection()) {
            return supplierDAO.getAll(conn);
        }
    }
}