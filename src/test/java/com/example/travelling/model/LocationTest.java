package com.example.travelling.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    @Test
    void validLocation_isValid() {
        Location location = new Location("Italy", "Como");
        assertTrue(location.isValid());
    }

    @Test
    void invalidWhenCountryIsNull() {
        Location location = new Location(null, "Rome");
        assertFalse(location.isValid());
    }

    @Test
    void invalidWhenCityIsNull() {
        Location location = new Location("Italy", null);
        assertFalse(location.isValid());
    }

    @Test
    void invalidWhenCountryIsEmpty() {
        Location location = new Location("", "Rome");
        assertFalse(location.isValid());
    }

    @Test
    void invalidWhenCityIsEmpty() {
        Location location = new Location("Italy", "");
        assertFalse(location.isValid());
    }

    @Test
    void invalidWhenCountryIsBlank() {
        Location location = new Location("   ", "Rome");
        assertFalse(location.isValid());
    }

    @Test
    void invalidWhenCityIsBlank() {
        Location location = new Location("Italy", "   ");
        assertFalse(location.isValid());
    }
}