package com.example.travelling.validator;

import com.example.travelling.model.Location;

public class LocationValidator implements Validator<Location> {
    @Override
    public boolean isValid(Location location) {
        if (location == null) {
            return false;
        }
        return isNotBlank(location.getCity()) && isNotBlank(location.getCountry());
    }
    private boolean isNotBlank(String value) {
        return value != null && !value.trim().isEmpty();
    }

}
