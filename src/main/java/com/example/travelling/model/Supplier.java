package com.example.travelling.model;

import java.util.regex.Pattern;

public class Supplier {

    private final String companyName;
    private final String country;
    private final String email;
    private final String phoneNumber;
    private final int flightsCount;
    private final int positiveComments;
    private final int negativeComments;

    public Supplier(String companyName,
                    String country,
                    String email,
                    String phoneNumber,
                    int flightsCount,
                    int positiveComments,
                    int negativeComments) {

        this.companyName = companyName;
        this.country = country;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.flightsCount = flightsCount;
        this.positiveComments = positiveComments;
        this.negativeComments = negativeComments;
    }


    public String getCompanyName() {
        return companyName;
    }

    public String getCountry() {
        return country;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getNegativeComments() {
        return negativeComments;
    }

    public int getPositiveComments() {
        return positiveComments;
    }

    public int getFlightsCount() {
        return flightsCount;
    }

}