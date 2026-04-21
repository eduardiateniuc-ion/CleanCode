package com.example.travelling.service;

import com.example.travelling.model.Event;

public interface EventService {
    int planeLoad(Event event);
    int countTravelTime(Event event);
}
