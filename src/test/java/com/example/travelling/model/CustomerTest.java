package com.example.travelling.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {


    @Test
    @DisplayName("Customer is valid when all fields are correct")
    void customerIsValid_whenAllFieldsAreCorrect() {
        Customer customer = new Customer("Eduard", "Iateniuc", "eduard@mail.ru", "068393402");

        assertTrue(customer.isValid());
    }

    @Test
    void emailIsInvalid_whenWrongFormat() {
        Customer customer = new Customer("Eduard", "Iateniuc", "wrong-email", "068393402");

        assertFalse(customer.isValid());
        assertFalse(customer.isEmailValid());
    }

    @Test
    void phoneIsInvalid_whenWrongFormat() {
        Customer customer = new Customer("Eduard", "Iateniuc", "eduard@mail.ru", "123");

        assertFalse(customer.isValid());
        assertFalse(customer.isPhoneNumberValid());
    }


    @Test
    void customerIsInvalid_whenFirstNameIsNull() {
        Customer customer = new Customer(null, "Iateniuc", "eduard@mail.ru", "068393402");

        assertFalse(customer.isValid());
    }

    @Test
    void customerIsInvalid_whenFirstNameIsBlank() {
        Customer customer = new Customer("   ", "Iateniuc", "eduard@mail.ru", "068393402");

        assertFalse(customer.isValid());
    }


    @Test
    void customerIsInvalid_whenLastNameIsNull() {
        Customer customer = new Customer("Eduard", null, "eduard@mail.ru", "068393402");

        assertFalse(customer.isValid());
    }

    @Test
    void customerIsInvalid_whenLastNameIsBlank() {
        Customer customer = new Customer("Eduard", "   ", "eduard@mail.ru", "068393402");
        assertFalse(customer.isValid());
    }


    @Test
    void emailValidationWorksSeparately() {
        assertTrue(new Customer("A", "B", "a@b.com", "123456789").isEmailValid());
        assertFalse(new Customer("A", "B", "bad", "123456789").isEmailValid());
    }

    @Test
    void phoneValidationWorksSeparately() {
        assertTrue(new Customer("A", "B", "a@b.com", "123456789").isPhoneNumberValid());
        assertFalse(new Customer("A", "B", "a@b.com", "abc").isPhoneNumberValid());
    }
}
