package dev.tuzserik.cloud.and.fog.computing.server.services;

import dev.tuzserik.cloud.and.fog.computing.server.model.Event;
import dev.tuzserik.cloud.and.fog.computing.server.repositories.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @AllArgsConstructor
public class EventService {
    private EventRepository eventRepository;

    public List<Event> saveEvents(List<Event> events) {
        return eventRepository.saveAll(events);
    }

    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }
}
