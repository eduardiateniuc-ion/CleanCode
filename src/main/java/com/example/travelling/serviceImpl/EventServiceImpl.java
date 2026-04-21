package com.example.travelling.serviceImpl;

import com.example.travelling.model.Customer;
import com.example.travelling.model.Event;
import com.example.travelling.service.EventService;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class EventServiceImpl implements EventService {

    @Override
    public int planeLoad(Event event){
        List<Customer> customers = event.getCustomers();
        int capacity = event.getCapacity();
        return (customers.size()*100)/capacity ;
    }

    @Override
    public int countTravelTime(Event event){
        Period period = Period.between(event.getStartTime(), event.getEndTime());
        return period.getDays();
    }
}
