package com.example.travelling.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.List;

public class Event {
    private String eventName;
    private String originLocation;
    private Location destination;
    private LocalDate startTime;
    private LocalDate endTime;
    private int capacity;
    private List<Customer> customers;

    public Event(String eventName, String originLocation, Location destination, LocalDate startTime, LocalDate endTime, int capacity, List<Customer> customers) {
        this.eventName = eventName;
        this.originLocation = originLocation;
        this.destination = destination;
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;
        this.customers = customers;
    }

    public int planeLoad(){
        return (customers.size()*100)/capacity ;
    }

    public int countTravelTime(){
        Period period = Period.between(startTime, endTime);
        return period.getDays();
    }
}
