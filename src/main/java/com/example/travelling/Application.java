package com.example.travelling;

import com.example.travelling.config.CsvImportConfig;
import com.example.travelling.config.DatabaseConfig;
import com.example.travelling.io.CustomerCsvReader;
import com.example.travelling.io.EventCsvReader;
import com.example.travelling.repository.CustomerRepository;
import com.example.travelling.repository.EventRepository;
import com.example.travelling.repository.LocationRepository;
import com.example.travelling.repository.SupplierRepository;
import com.example.travelling.serviceImpl.ImportService;
import com.example.travelling.validator.CustomerValidator;
import com.example.travelling.validator.EventValidator;
import com.example.travelling.validator.LocationValidator;
import com.example.travelling.validator.SupplierValidator;

import java.sql.Connection;
import java.sql.DriverManager;

public class Application {

    public static void main(String[] args) {
        DatabaseConfig databaseConfig = DatabaseConfig.fromEnvironment();
        CsvImportConfig csvImportConfig = CsvImportConfig.defaultConfig();

        try (Connection connection = DriverManager.getConnection(
                databaseConfig.jdbcUrl(),
                databaseConfig.dbUser(),
                databaseConfig.dbPassword()
        )) {
            connection.setAutoCommit(false);

            ImportService importService = buildImportService(connection);
            EventCsvReader eventReader = new EventCsvReader(csvImportConfig.eventsResourcePath());
            CustomerCsvReader customerReader = new CustomerCsvReader(csvImportConfig.customersResourcePath());

            try {
                importService.importData(eventReader.read(), customerReader.read());
                connection.commit();
                System.out.println("Import completed: data saved to Locations, Suppliers, Events, Customers and Event_Customers.");
            } catch (Exception e) {
                connection.rollback();
                throw e;
            }
        } catch (Exception e) {
            System.err.println("Import failed: " + e.getMessage());
            throw new RuntimeException("Application execution failed", e);
        }
    }

    private static ImportService buildImportService(Connection connection) {
        EventValidator eventValidator = new EventValidator();
        LocationValidator locationValidator = new LocationValidator();
        SupplierValidator supplierValidator = new SupplierValidator();
        CustomerValidator customerValidator = new CustomerValidator();

        return new ImportService(
                new EventRepository(connection, eventValidator),
                new LocationRepository(connection, locationValidator),
                new SupplierRepository(connection, supplierValidator),
                new CustomerRepository(connection, customerValidator),
                eventValidator,
                locationValidator,
                supplierValidator,
                customerValidator
        );
    }
}
