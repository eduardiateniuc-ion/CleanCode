package com.example.travelling.utils;

import java.util.regex.Pattern;

public class PhoneNumberValidatorUtil {
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\d{9}$");

    public static boolean isPhoneNumberValid(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            return false;
        }
        return PHONE_PATTERN.matcher(phoneNumber).matches();
    }

}
