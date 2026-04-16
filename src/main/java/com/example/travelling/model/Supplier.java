package com.example.travelling.model;

import java.util.regex.Pattern;

public class Supplier {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                    Pattern.CASE_INSENSITIVE);

    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^\\d{10}$");

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

    public boolean isValid() {
        return isEmailValid()
                && isPhoneNumberValid()
                && flightsCount >= 0
                && positiveComments >= 0
                && negativeComments >= 0
                && positiveComments + negativeComments <= flightsCount;
    }

    public double getCompanyRating() {
        int totalComments = positiveComments + negativeComments;

        if (totalComments == 0) {
            return 0.0;
        }

        if (totalComments > flightsCount
                || positiveComments > flightsCount
                || negativeComments > flightsCount) {
            throw new IllegalArgumentException("Comments count exceeds flights count");
        }

        double reviewScore = (double) positiveComments / totalComments;

        double experience = Math.min(1.0, (double) flightsCount / 100.0);

        double rating = 5.0 * reviewScore * experience;

        return roundToOneDecimal(rating);
    }

    public String getComfortLevel() {
        double rating = getCompanyRating();

        if (rating >= 4.0) return "Premium";
        if (rating >= 3.0) return "Comfort";
        if (rating >= 1.0) return "So-So...";

        return "Low";
    }

    public boolean isEmailValid() {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public boolean isPhoneNumberValid() {
        return PHONE_PATTERN.matcher(phoneNumber).matches();
    }

    private double roundToOneDecimal(double value) {
        return Math.round(value * 10) / 10.0;
    }
}