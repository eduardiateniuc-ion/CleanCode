package com.example.travelling.serviceImpl;

import com.example.travelling.io.CustomerRow;
import com.example.travelling.model.Customer;
import com.example.travelling.model.Event;
import com.example.travelling.model.Location;
import com.example.travelling.model.Supplier;
import com.example.travelling.repository.CustomerRepository;
import com.example.travelling.repository.EventRepository;
import com.example.travelling.repository.LocationRepository;
import com.example.travelling.repository.SupplierRepository;
import com.example.travelling.validator.Validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImportService {

    private final EventRepository eventRepo;
    private final LocationRepository locationRepo;
    private final SupplierRepository supplierRepo;
    private final CustomerRepository customerRepo;
    private final Validator<Event> eventValidator;
    private final Validator<Location> locationValidator;
    private final Validator<Supplier> supplierValidator;
    private final Validator<Customer> customerValidator;

    public ImportService(
            EventRepository eventRepo,
            LocationRepository locationRepo,
            SupplierRepository supplierRepo,
            CustomerRepository customerRepo,
            Validator<Event> eventValidator,
            Validator<Location> locationValidator,
            Validator<Supplier> supplierValidator,
            Validator<Customer> customerValidator
    ) {
        this.eventRepo = eventRepo;
        this.locationRepo = locationRepo;
        this.supplierRepo = supplierRepo;
        this.customerRepo = customerRepo;
        this.eventValidator = eventValidator;
        this.locationValidator = locationValidator;
        this.supplierValidator = supplierValidator;
        this.customerValidator = customerValidator;
    }

    public void importData(
            List<Event> events,
            List<CustomerRow> customerRows
    ) {
        validateImportInput(events, customerRows);

        Map<String, Integer> eventIdByName = new HashMap<>();

        for (Event event : events) {
            int eventId = saveEventWithDependencies(event);

            eventIdByName.put(event.getEventName(), eventId);
        }

        for (CustomerRow row : customerRows) {
            validateCustomerRow(row);

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

    private int saveEventWithDependencies(Event event) {
        validateEventForImport(event);

        int locationId = locationRepo.findOrSave(event.getDestination());
        int supplierId = supplierRepo.findOrSave(event.getSupplier());
        return eventRepo.save(event, locationId, supplierId);
    }

    private void validateImportInput(List<Event> events, List<CustomerRow> customerRows) {
        if (events == null || customerRows == null) {
            throw new IllegalArgumentException("Import collections must not be null");
        }
    }

    private void validateEventForImport(Event event) {
        if (!eventValidator.isValid(event)) {
            throw new IllegalArgumentException("Event not valid");
        }
        if (!locationValidator.isValid(event.getDestination())) {
            throw new IllegalArgumentException("Location not valid");
        }
        if (!supplierValidator.isValid(event.getSupplier())) {
            throw new IllegalArgumentException("Supplier not valid");
        }
    }

    private void validateCustomerRow(CustomerRow row) {
        if (row == null) {
            throw new IllegalArgumentException("Customer row not valid");
        }
        if (row.eventName() == null || row.eventName().isBlank()) {
            throw new IllegalArgumentException("Customer row has empty event name");
        }
        if (!customerValidator.isValid(row.customer())) {
            throw new IllegalArgumentException("Customer not valid");
        }
    }

}
