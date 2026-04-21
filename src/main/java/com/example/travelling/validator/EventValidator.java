package com.example.travelling.validator;

import com.example.travelling.model.Event;
import com.example.travelling.model.Location;

public class EventValidator implements Validator<Event> {

    @Override
    public boolean isValid(Event event) {
        if (event == null) {
            return false;
        }
        return isNotBlank(event.getEventName())
                && isNotBlank(event.getOriginLocation())
                && isDestinationValid(event.getDestination())
                && isDatesValid(event);
    }


    private boolean isDestinationValid(Location destination) {
        if (destination == null) {
            return false;
        }
        return isNotBlank(destination.getCountry())
                && isNotBlank(destination.getCity());
    }

    private boolean isDatesValid(Event event) {
        if (event.getStartTime() == null || event.getEndTime() == null) {
            return false;
        }
        return !event.getEndTime().isBefore(event.getStartTime());
    }

    private boolean isNotBlank(String value) {
        return value != null && !value.trim().isEmpty();
    }
}