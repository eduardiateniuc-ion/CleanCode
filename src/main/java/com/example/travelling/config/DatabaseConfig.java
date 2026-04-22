package com.example.travelling.config;

public record DatabaseConfig(String jdbcUrl, String dbUser, String dbPassword) {

    public static DatabaseConfig fromEnvironment() {
        return new DatabaseConfig(
                readRequiredEnv("DB_URL"),
                readRequiredEnv("DB_USER"),
                readRequiredEnv("DB_PASSWORD")
        );
    }

    private static String readRequiredEnv(String key) {
        String value = System.getenv(key);
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Missing required environment variable: " + key);
        }
        return value;
    }
}
