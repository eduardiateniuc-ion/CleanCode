package com.example.travelling.repository;

import com.example.travelling.model.Event;
import com.example.travelling.repository.Repository;
import com.example.travelling.validator.Validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EventRepository {

    private final Connection connection;
    private final Validator<Event> validator;

    public EventRepository(Connection connection, Validator<Event> validator) {
        this.connection = connection;
        this.validator = validator;
    }

    public int save(Event event, int locationId, int supplierId) {
        if (!validator.isValid(event)) {
            throw new IllegalArgumentException("Event not valid");
        }

        String sql = """
            INSERT INTO Events
            (EventName, OriginLocation, DestinationLocationId, SupplierId,
             StartDate, EndDate, Capacity)
            VALUES (?, ?, ?, ?, ?, ?, ?);
            SELECT CAST(SCOPE_IDENTITY() AS INT);
            """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, event.getEventName());
            ps.setString(2, event.getOriginLocation());
            ps.setInt(3, locationId);
            ps.setInt(4, supplierId);
            ps.setDate(5, java.sql.Date.valueOf(event.getStartTime()));
            ps.setDate(6, java.sql.Date.valueOf(event.getEndTime()));
            ps.setInt(7, event.getCapacity());

            var rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void linkCustomer(int eventId, int customerId) {
        String sql = """
            INSERT INTO Event_Customers (EventId, CustomerId)
            VALUES (?, ?)
            """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, eventId);
            ps.setInt(2, customerId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
