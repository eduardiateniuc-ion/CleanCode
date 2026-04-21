package com.example.travelling.repository;


import com.example.travelling.model.Customer;
import com.example.travelling.repository.Repository;
import com.example.travelling.validator.Validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerRepository {

    private final Connection connection;
    private final Validator<Customer> validator;

    public CustomerRepository(Connection connection, Validator<Customer> validator) {
        this.connection = connection;
        this.validator = validator;
    }

    public int findOrSave(Customer customer) {
        Integer id = findIdByEmail(customer.getEmail());
        if (id != null) {
            return id;
        }
        return saveAndReturnId(customer);
    }

    private Integer findIdByEmail(String email) {
        String sql = "SELECT Id FROM Customers WHERE Email = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);

            var rs = ps.executeQuery();
            return rs.next() ? rs.getInt("Id") : null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int saveAndReturnId(Customer customer) {
        if (!validator.isValid(customer)) {
            throw new IllegalArgumentException("Customer not valid");
        }

        String sql = """
            INSERT INTO Customers (FirstName, LastName, Email, Phone)
            VALUES (?, ?, ?, ?);
            SELECT CAST(SCOPE_IDENTITY() AS INT);
            """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getPhoneNumber());

            var rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
