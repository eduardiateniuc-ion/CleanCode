package com.example.travelling.config;

public record CsvImportConfig(String eventsResourcePath, String customersResourcePath) {

    public static CsvImportConfig defaultConfig() {
        return new CsvImportConfig(
                "csv/events.csv",
                "csv/customers.csv"
        );
    }
}
