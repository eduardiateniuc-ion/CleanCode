package com.example.travelling.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.List;

public class Event {
    private String eventName;
    private String originLocation;
    private Location destination;
    private Supplier supplier;
    private LocalDate startTime;
    private LocalDate endTime;
    private int capacity;
    private List<Customer> customers;

    public Event(String eventName, String originLocation, Location destination, Supplier supplier, LocalDate startTime, LocalDate endTime, int capacity) {
        this.eventName = eventName;
        this.originLocation = originLocation;
        this.destination = destination;
        this.supplier = supplier;
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public int getCapacity() {
        return capacity;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public String getEventName() {
        return eventName;
    }

    public String getOriginLocation() {
        return originLocation;
    }

    public Location getDestination() {
        return destination;
    }

    public Supplier getSupplier() {
        return supplier;
    }
}
