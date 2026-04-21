package com.example.travelling.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {
/*
    private final Customer customer1 =
            new Customer("Eduard", "Iateniuc", "eduard@mail.ru", "068393402");

    private final Customer customer2 =
            new Customer("Ion", "Popescu", "ion@mail.com", "079123456");

    @Test
    @DisplayName("Plane load is calculated correctly with customers")
    void planeLoadCalculatedCorrectly() {
        Event event = new Event("Summer Trip", "Chisinau", new Location("Italy", "Rome"), LocalDate.of(2024, 6, 1), LocalDate.of(2024, 6, 10), 50, List.of(customer1, customer2));

        assertEquals(4, event.planeLoad());
    }

    @Test
    @DisplayName("Plane load is zero when no customers")
    void planeLoadIsZeroWhenNoCustomers() {
        Event event = new Event("Empty Trip", "Chisinau", new Location("Italy", "Rome"), LocalDate.of(2024, 6, 1), LocalDate.of(2024, 6, 10), 50, Collections.emptyList());

        assertEquals(0, event.planeLoad());
    }

    @Test
    @DisplayName("Plane load throws exception when capacity is zero")
    void planeLoadThrowsExceptionWhenCapacityZero() {
        Event event = new Event("Invalid Trip", "Chisinau", new Location("Italy", "Rome"), LocalDate.of(2024, 6, 1), LocalDate.of(2024, 6, 10), 0, List.of(customer1));

        assertThrows(ArithmeticException.class, event::planeLoad);
    }

    @Test
    @DisplayName("Travel time is calculated correctly")
    void shouldCalculateTravelTime() {
        Event event = new Event("Summer Trip", "Chisinau", new Location("Italy", "Rome"), LocalDate.of(2024, 6, 1), LocalDate.of(2024, 6, 10), 50, List.of(customer1));

        assertEquals(9, event.countTravelTime());
    }

    @Test
    @DisplayName("Travel time is zero when start and end dates are equal")
    void travelTimeIsZeroWhenSameDate() {
        Event event = new Event("One Day Trip", "Chisinau", new Location("Italy", "Rome"), LocalDate.of(2024, 6, 1), LocalDate.of(2024, 6, 1), 50, List.of(customer1));

        assertEquals(0, event.countTravelTime());
    }

    @Test
    @DisplayName("Travel time is negative when end date is before start date")
    void travelTimeIsNegativeWhenEndBeforeStart() {
        Event event = new Event("Invalid Dates", "Chisinau", new Location("Italy", "Rome"), LocalDate.of(2024, 6, 10), LocalDate.of(2024, 6, 1), 50, List.of(customer1));

        assertTrue(event.countTravelTime() < 0);
    }


    @Test
    @DisplayName("Event is valid when all fields are correct")
    void isValidReturnsTrueWhenAllFieldsAreValid() {
        Event event = new Event(
                "Summer Trip",
                "Chisinau",
                new Location("Italy", "Rome"),
                LocalDate.of(2024, 6, 1),
                LocalDate.of(2024, 6, 10),
                50,
                List.of(customer1)
        );

        assertTrue(event.isValid());
    }

    @Test
    @DisplayName("Event is invalid when eventName is null")
    void isValidReturnsFalseWhenEventNameIsNull() {
        Event event = new Event(
                null,
                "Chisinau",
                new Location("Italy", "Rome"),
                LocalDate.now(),
                LocalDate.now().plusDays(5),
                50,
                List.of(customer1)
        );

        assertFalse(event.isValid());
    }

    @Test
    @DisplayName("Event is invalid when eventName is empty")
    void isValidReturnsFalseWhenEventNameIsEmpty() {
        Event event = new Event(
                "",
                "Chisinau",
                new Location("Italy", "Rome"),
                LocalDate.now(),
                LocalDate.now().plusDays(5),
                50,
                List.of(customer1)
        );

        assertFalse(event.isValid());
    }

    @Test
    @DisplayName("Event is invalid when eventName contains only spaces")
    void isValidReturnsFalseWhenEventNameIsBlank() {
        Event event = new Event(
                "   ",
                "Chisinau",
                new Location("Italy", "Rome"),
                LocalDate.now(),
                LocalDate.now().plusDays(5),
                50,
                List.of(customer1)
        );

        assertFalse(event.isValid());
    }

    @Test
    @DisplayName("Event is invalid when originLocation is null")
    void isValidReturnsFalseWhenOriginLocationIsNull() {
        Event event = new Event(
                "Summer Trip",
                null,
                new Location("Italy", "Rome"),
                LocalDate.now(),
                LocalDate.now().plusDays(5),
                50,
                List.of(customer1)
        );

        assertFalse(event.isValid());
    }

    @Test
    @DisplayName("Event is invalid when originLocation is empty")
    void isValidReturnsFalseWhenOriginLocationIsEmpty() {
        Event event = new Event(
                "Summer Trip",
                "",
                new Location("Italy", "Rome"),
                LocalDate.now(),
                LocalDate.now().plusDays(5),
                50,
                List.of(customer1)
        );

        assertFalse(event.isValid());
    }

    @Test
    @DisplayName("Event is invalid when destination is invalid")
    void isValidReturnsFalseWhenDestinationIsInvalid() {
        // Предполагаем, что Location.isValid() вернёт false
        Location invalidLocation = new Location(null, "");

        Event event = new Event(
                "Summer Trip",
                "Chisinau",
                invalidLocation,
                LocalDate.now(),
                LocalDate.now().plusDays(5),
                50,
                List.of(customer1)
        );

        assertFalse(event.isValid());
    }
*/
}
