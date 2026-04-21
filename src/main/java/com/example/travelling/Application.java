package com.example.travelling;

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
        Config config = Config.from(args);

        EventCsvReader eventReader = new EventCsvReader(config.eventsCsvPath());
        CustomerCsvReader customerReader = new CustomerCsvReader(config.customersCsvPath());

        try (Connection connection = DriverManager.getConnection(
                config.jdbcUrl(),
                config.dbUser(),
                config.dbPassword()
        )) {
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

    private record Config(
            String eventsCsvPath,
            String customersCsvPath,
            String jdbcUrl,
            String dbUser,
            String dbPassword
    ) {
        static Config from(String[] args) {
            if (args.length < 2) {
                throw new IllegalArgumentException(
                        "Usage: java ...Application <events-csv-path> <customers-csv-path>"
                );
            }

            String jdbcUrl = readRequiredEnv("DB_URL");
            String dbUser = readRequiredEnv("DB_USER");
            String dbPassword = readRequiredEnv("DB_PASSWORD");

            return new Config(args[0], args[1], jdbcUrl, dbUser, dbPassword);
        }

        private static String readRequiredEnv(String key) {
            String value = System.getenv(key);
            if (value == null || value.isBlank()) {
                throw new IllegalArgumentException("Missing required environment variable: " + key);
            }
            return value;
        }
    }
}
