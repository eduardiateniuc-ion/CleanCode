package com.example.travelling.model;

import java.util.regex.Pattern;

public class Customer {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\d{9}$");

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phoneNumber;

    public Customer(String firstName,
                    String lastName,
                    String email,
                    String phoneNumber) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public boolean isEmailValid() {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public boolean isPhoneNumberValid() {
        return PHONE_PATTERN.matcher(phoneNumber).matches();
    }


    public boolean isValid() {
        return isNameValid(firstName)
                && isNameValid(lastName)
                && isEmailValid()
                && isPhoneNumberValid();
    }

    private boolean isNameValid(String name) {
        return name != null && !name.trim().isEmpty();
    }



}