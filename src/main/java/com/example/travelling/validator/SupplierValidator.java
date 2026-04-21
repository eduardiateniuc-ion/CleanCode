package com.example.travelling.validator;


import com.example.travelling.model.Supplier;
import com.example.travelling.utils.EmailValidatorUtil;
import com.example.travelling.utils.PhoneNumberValidatorUtil;

public class SupplierValidator implements Validator<Supplier> {

    @Override
    public boolean isValid(Supplier supplier) {
        if (supplier == null) {
            return false;
        }

        return isNotBlank(supplier.getCountry())
                && isNotBlank(supplier.getCompanyName())
                && isContactInfoValid(supplier)
                && isStatisticsValid(supplier);
    }



    private boolean isContactInfoValid(Supplier supplier) {
        return EmailValidatorUtil.isEmailValid(supplier.getEmail())
                && PhoneNumberValidatorUtil.isPhoneNumberValid(supplier.getPhoneNumber());
    }

    private boolean isStatisticsValid(Supplier supplier) {
        int flights = supplier.getFlightsCount();
        int positive = supplier.getPositiveComments();
        int negative = supplier.getNegativeComments();

        if (flights < 0 || positive < 0 || negative < 0) {
            return false;
        }

        return positive + negative <= flights;
    }

    private boolean isNotBlank(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
