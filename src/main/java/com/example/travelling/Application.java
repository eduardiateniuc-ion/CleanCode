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

        CsvImportConfig csv = CsvImportConfig.defaultConfig();
        DatabaseConfig db = DatabaseConfig.fromEnvironment();

        EventCsvReader eventReader =
                new EventCsvReader(csv.eventsResourcePath());
        CustomerCsvReader customerReader =
                new CustomerCsvReader(csv.customersResourcePath());

        try (Connection connection = createConnection(db)) {

            connection.setAutoCommit(false);

            EventValidator eventValidator = new EventValidator();
            LocationValidator locationValidator = new LocationValidator();
            SupplierValidator supplierValidator = new SupplierValidator();
            CustomerValidator customerValidator = new CustomerValidator();

            ImportService importService = new ImportService(
                    new EventRepository(connection, eventValidator),
                    new LocationRepository(connection, locationValidator),
                    new SupplierRepository(connection, supplierValidator),
                    new CustomerRepository(connection, customerValidator),
                    eventValidator,
                    locationValidator,
                    supplierValidator,
                    customerValidator
            );

            try {
                importService.importData(
                        eventReader.read(),
                        customerReader.read()
                );
                connection.commit();
                System.out.println(
                        "Import completed: data saved successfully."
                );
            } catch (Exception e) {
                connection.rollback();
                throw e;
            }

        } catch (Exception e) {
            System.err.println("Import failed: " + e.getMessage());
            throw new RuntimeException("Application execution failed", e);
        }
    }

    private static Connection createConnection(DatabaseConfig db)
            throws Exception {

        if (db.dbUser() == null || db.dbUser().isBlank()) {
            // ✅ Windows Authentication
            return DriverManager.getConnection(db.jdbcUrl());
        }

        // ✅ SQL Authentication
        return DriverManager.getConnection(
                db.jdbcUrl(),
                db.dbUser(),
                db.dbPassword()
        );
    }
}