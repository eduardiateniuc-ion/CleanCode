package com.example.travelling.config;

public record DatabaseConfig(String jdbcUrl, String dbUser, String dbPassword) {

    public static DatabaseConfig fromEnvironment() {
        String jdbcUrl = readRequiredEnv("DB_URL");

        // Для Windows Authentication могут быть null
        String dbUser = System.getenv("DB_USER");
        String dbPassword = System.getenv("DB_PASSWORD");

        return new DatabaseConfig(jdbcUrl, dbUser, dbPassword);
    }

    private static String readRequiredEnv(String key) {
        String value = System.getenv(key);
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    "Missing required environment variable: " + key
            );
        }
        return value;
    }
}
