package com.example.travelling.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SupplierTest {
/*
    @Test
    @DisplayName("Supplier is valid when all fields are correct")
    void supplierIsValid() {
        Supplier supplier = new Supplier("AirTest", "DE", "company@mail.com", "0123456789", 100, 60, 40);

        assertTrue(supplier.isValid());
    }

    @Test
    @DisplayName("Supplier is invalid when comments exceed flights")
    void supplierIsInvalid() {
        Supplier supplier = new Supplier("BadAir", "DE", "company@mail.com", "0123456789", 10, 8, 5);

        assertFalse(supplier.isValid());
    }

    @Test
    void isValid_false_whenEmailInvalid() {
        Supplier s = new Supplier("Air", "DE", "bad-email", "0123456789", 10, 5, 5);
        assertFalse(s.isValid());
    }

    @Test
    void isValid_false_whenPhoneInvalid() {
        Supplier s = new Supplier("Air", "DE", "a@b.com", "123", 10, 5, 5);
        assertFalse(s.isValid());
    }

    @Test
    void isValid_false_whenFlightsNegative() {
        Supplier s = new Supplier("Air", "DE", "a@b.com", "0123456789", -1, 0, 0);
        assertFalse(s.isValid());
    }

    @Test
    void isValid_false_whenPositiveCommentsNegative() {
        Supplier s = new Supplier(
                "Air", "DE", "a@b.com", "0123456789", 10, -1, 0);
        assertFalse(s.isValid());
    }

    @Test
    void isValid_false_whenNegativeCommentsNegative() {
        Supplier s = new Supplier("Air", "DE", "a@b.com", "0123456789", 10, 0, -1);
        assertFalse(s.isValid());
    }


    @Test
    @DisplayName("Rating is zero when there are no comments")
    void ratingIsZeroWhenNoComments() {
        Supplier supplier = new Supplier("NewAir", "DE", "company@mail.com", "0123456789", 100, 0, 0);

        assertEquals(0.0, supplier.getCompanyRating());
    }

    @Test
    @DisplayName("Exception is thrown when total comments exceed flights")
    void ratingThrowsExceptionWhenTotalCommentsExceedFlights() {
        Supplier supplier = new Supplier("BadAir", "DE", "company@mail.com", "0123456789", 10, 6, 6);

        assertThrows(IllegalArgumentException.class,
                supplier::getCompanyRating);
    }

    @Test
    @DisplayName("Company rating is calculated correctly")
    void ratingCalculatedCorrectly() {
        Supplier supplier = new Supplier("GoodAir", "DE", "company@mail.com", "0123456789", 1000, 80, 20);

        assertEquals(4.0, supplier.getCompanyRating());
    }

    @Test
    void ratingThrowsException_whenPositiveCommentsExceedFlights() {
        Supplier s = new Supplier(
                "Air", "DE", "a@b.com", "0123456789",
                10, 15, 0
        );

        assertThrows(IllegalArgumentException.class, s::getCompanyRating);
    }

    @Test
    void ratingThrowsException_whenNegativeCommentsExceedFlights() {
        Supplier s = new Supplier(
                "Air", "DE", "a@b.com", "0123456789",
                10, 0, 12
        );

        assertThrows(IllegalArgumentException.class, s::getCompanyRating);
    }

    @Test
    @DisplayName("Comfort level is Premium")
    void comfortLevelPremium() {
        Supplier supplier = new Supplier(
                "PremiumAir",
                "DE",
                "company@mail.com",
                "0123456789",
                1000,
                90,
                10
        );

        assertEquals("Premium", supplier.getComfortLevel());
    }

    @Test
    @DisplayName("Comfort level is Comfort")
    void comfortLevelComfort() {
        Supplier supplier = new Supplier(
                "ComfortAir",
                "DE",
                "company@mail.com",
                "0123456789",
                500,
                60,
                40
        );

        assertEquals("Comfort", supplier.getComfortLevel());
    }

    @Test
    @DisplayName("Comfort level is So-So")
    void comfortLevelSoSo() {
        Supplier supplier = new Supplier(
                "AverageAir",
                "DE",
                "company@mail.com",
                "0123456789",
                200,
                30,
                70
        );

        assertEquals("So-So...", supplier.getComfortLevel());
    }

    @Test
    @DisplayName("Comments count exceeds flights count")
    void comfortLevelLow() {
        Supplier supplier = new Supplier(
                "BadAir",
                "DE",
                "company@mail.com",
                "0123456789",
                50,
                1,
                99
        );

        assertThrows(IllegalArgumentException.class,
                supplier::getCompanyRating);
    }

    @Test
    @DisplayName("Email validation works")
    void emailValidation() {
        Supplier valid = new Supplier("Air", "DE", "valid@mail.com", "0123456789", 10, 5, 5);

        Supplier invalid = new Supplier("Air", "DE", "invalid-email", "0123456789", 10, 5, 5);
        assertTrue(valid.isEmailValid());
        assertFalse(invalid.isEmailValid());
    }

    @Test
    @DisplayName("Phone validation works")
    void phoneValidation() {
        Supplier valid = new Supplier(
                "Air",
                "DE",
                "mail@mail.com",
                "0123456789",
                10,
                5,
                5
        );

        Supplier invalid = new Supplier(
                "Air",
                "DE",
                "mail@mail.com",
                "123",
                10,
                5,
                5
        );

        assertTrue(valid.isPhoneNumberValid());
        assertFalse(invalid.isPhoneNumberValid());
    }

*/
}
