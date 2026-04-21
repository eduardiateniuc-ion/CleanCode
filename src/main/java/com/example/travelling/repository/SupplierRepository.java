package com.example.travelling.repository;

import com.example.travelling.model.Supplier;
import com.example.travelling.validator.Validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SupplierRepository {

    private final Connection connection;
    private final Validator<Supplier> validator;

    public SupplierRepository(Connection connection, Validator<Supplier> validator) {
        this.connection = connection;
        this.validator = validator;
    }

    public int findOrSave(Supplier supplier) {
        Integer id = findIdByCompanyName(supplier.getCompanyName());
        if (id != null) {
            return id;
        }
        return saveAndReturnId(supplier);
    }

    private Integer findIdByCompanyName(String name) {
        String sql = "SELECT Id FROM Suppliers WHERE CompanyName = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            var rs = ps.executeQuery();
            return rs.next() ? rs.getInt("Id") : null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int saveAndReturnId(Supplier s) {
        if (!validator.isValid(s)) {
            throw new IllegalArgumentException("Supplier not valid");
        }

        String sql = """
            INSERT INTO Suppliers
            (CompanyName, Country, Email, Phone, FlightsCount, PositiveComments, NegativeComments)
            VALUES (?, ?, ?, ?, ?, ?, ?);
            SELECT CAST(SCOPE_IDENTITY() AS INT);
            """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, s.getCompanyName());
            ps.setString(2, s.getCountry());
            ps.setString(3, s.getEmail());
            ps.setString(4, s.getPhoneNumber());
            ps.setInt(5, s.getFlightsCount());
            ps.setInt(6, s.getPositiveComments());
            ps.setInt(7, s.getNegativeComments());

            var rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
