package com.example.travelling.io;

import com.example.travelling.model.Customer;
import com.example.travelling.model.Event;
import com.example.travelling.model.Location;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerCsvReader {

    private final String filePath;

    public CustomerCsvReader(String filePath) {
        this.filePath = filePath;
    }

    public List<CustomerRow> read() {
        List<CustomerRow> rows = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] v = line.split(",");

                rows.add(new CustomerRow(
                        v[0].trim(),
                        new Customer(
                                v[1].trim(),
                                v[2].trim(),
                                v[3].trim(),
                                v[4].trim()
                        )
                ));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return rows;
    }
}

