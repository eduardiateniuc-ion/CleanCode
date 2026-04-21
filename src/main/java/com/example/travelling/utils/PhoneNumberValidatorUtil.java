package com.example.travelling.utils;

import java.util.regex.Pattern;

public class PhoneNumberValidatorUtil {
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\d{9}$");

    public static boolean isPhoneNumberValid(String phoneNumber) {
        return PHONE_PATTERN.matcher(phoneNumber).matches();
    }

}
