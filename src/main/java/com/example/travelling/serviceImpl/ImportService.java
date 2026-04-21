package com.example.travelling.serviceImpl;

import com.example.travelling.io.CustomerRow;
import com.example.travelling.model.Customer;
import com.example.travelling.model.Event;
import com.example.travelling.repository.CustomerRepository;
import com.example.travelling.repository.EventRepository;
import com.example.travelling.repository.LocationRepository;
import com.example.travelling.repository.SupplierRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImportService {

    private final EventRepository eventRepo;
    private final LocationRepository locationRepo;
    private final SupplierRepository supplierRepo;
    private final CustomerRepository customerRepo;

    public ImportService(
            EventRepository eventRepo,
            LocationRepository locationRepo,
            SupplierRepository supplierRepo,
            CustomerRepository customerRepo
    ) {
        this.eventRepo = eventRepo;
        this.locationRepo = locationRepo;
        this.supplierRepo = supplierRepo;
        this.customerRepo = customerRepo;
    }

    public void importData(
            List<Event> events,
            List<CustomerRow> customerRows
    ) {

        Map<String, Integer> eventIdByName = new HashMap<>();

        for (Event event : events) {
            int locationId = locationRepo.findOrSave(event.getDestination());
            int supplierId = supplierRepo.findOrSave(event.getSupplier());
            int eventId = eventRepo.save(event, locationId, supplierId);

            eventIdByName.put(event.getEventName(), eventId);
        }

        for (CustomerRow row : customerRows) {

            Integer eventId = eventIdByName.get(row.eventName());

            if (eventId == null) {
                throw new IllegalStateException(
                        "Event not found: " + row.eventName()
                );
            }

            Customer customer = row.customer();
            int customerId = customerRepo.findOrSave(customer);

            eventRepo.linkCustomer(eventId, customerId);
        }
    }

}
