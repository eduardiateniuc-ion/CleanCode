package com.example.travelling.repository;

import com.example.travelling.model.Location;
import com.example.travelling.model.Location;
import com.example.travelling.validator.Validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LocationRepository {

    private final Connection connection;
    private final Validator<Location> validator;

    public LocationRepository(Connection connection, Validator<Location> validator) {
        this.connection = connection;
        this.validator = validator;
    }

    public int findOrSave(Location location) {
        Integer id = findId(location);
        if (id != null) {
            return id;
        }
        return saveAndReturnId(location);
    }

    private Integer findId(Location location) {
        String sql = """
            SELECT Id FROM Locations
            WHERE Country = ? AND City = ?
            """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, location.getCountry());
            ps.setString(2, location.getCity());

            var rs = ps.executeQuery();
            return rs.next() ? rs.getInt("Id") : null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int saveAndReturnId(Location location) {
        if (!validator.isValid(location)) {
            throw new IllegalArgumentException("Location not valid");
        }

        String sql = """
            INSERT INTO Locations (Country, City)
            VALUES (?, ?);
            SELECT CAST(SCOPE_IDENTITY() AS INT);
            """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, location.getCountry());
            ps.setString(2, location.getCity());

            var rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

