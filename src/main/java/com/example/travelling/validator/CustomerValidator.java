package com.example.travelling.validator;

import com.example.travelling.model.Customer;
import com.example.travelling.model.Supplier;
import com.example.travelling.utils.EmailValidatorUtil;
import com.example.travelling.utils.PhoneNumberValidatorUtil;

public class CustomerValidator implements Validator<Customer> {

    @Override
    public boolean isValid(Customer customer) {

        return isNotBlank(customer.getFirstName()) &&
                isNotBlank(customer.getLastName()) &&
                EmailValidatorUtil.isEmailValid(customer.getEmail()) &&
                PhoneNumberValidatorUtil.isPhoneNumberValid(customer.getPhoneNumber());

    }

    private boolean isNotBlank(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
