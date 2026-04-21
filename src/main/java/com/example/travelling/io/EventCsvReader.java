package com.example.travelling.io;

import com.example.travelling.model.Event;
import com.example.travelling.model.Location;
import com.example.travelling.model.Supplier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventCsvReader implements Reader<Event> {

    private final String filePath;

    public EventCsvReader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Event> read() {
        List<Event> events = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine(); // header
            String line;

            while ((line = reader.readLine()) != null) {
                events.add(parse(line));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return events;
    }

    private Event parse(String line) {
        String[] v = line.split(",");

        Location location = new Location(v[2].trim(), v[3].trim());

        Supplier supplier = new Supplier(
                v[4].trim(),
                v[5].trim(),
                v[6].trim(),
                v[7].trim(),
                Integer.parseInt(v[8].trim()),
                Integer.parseInt(v[9].trim()),
                Integer.parseInt(v[10].trim())
        );

        return new Event(
                v[0].trim(),
                v[1].trim(),
                location,
                supplier,
                LocalDate.parse(v[11].trim()),
                LocalDate.parse(v[12].trim()),
                Integer.parseInt(v[13].trim())
        );
    }
}
