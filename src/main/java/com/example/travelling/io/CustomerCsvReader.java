package com.example.travelling.io;

import com.example.travelling.model.Customer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CustomerCsvReader {

    private final String resourcePath;

    public CustomerCsvReader(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public List<CustomerRow> read() {
        List<CustomerRow> rows = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(openResource(), StandardCharsets.UTF_8))) {
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

    private InputStream openResource() {
        InputStream inputStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(resourcePath);
        if (inputStream == null) {
            throw new IllegalArgumentException("CSV resource not found: " + resourcePath);
        }
        return inputStream;
    }
}

