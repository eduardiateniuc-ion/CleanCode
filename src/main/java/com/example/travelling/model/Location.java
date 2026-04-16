package com.example.travelling.model;

public class Location {
    private String country;
    private String city;

    public Location(String country, String city) {
        this.country = country;
        this.city = city;
    }

    public boolean isValid() {
        if (country == null || country.trim().isEmpty()) {
            return false;
        }
        if (city == null || city.trim().isEmpty()) {
            return false;
        }
        return true;
    }
}
