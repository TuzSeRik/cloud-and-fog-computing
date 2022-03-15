package dev.tuzserik.cloud.and.fog.computing.server.services;

import dev.tuzserik.cloud.and.fog.computing.server.model.Event;
import dev.tuzserik.cloud.and.fog.computing.server.repositories.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service @AllArgsConstructor
public class EventService {
    private EventRepository eventRepository;

    public List<Event> saveEvents(List<Event> events) {
        for (Event event : events)
            event.setEventId(UUID.randomUUID());

        return eventRepository.saveAll(events);
    }

    public Event saveEvent(Event event) {
        event.setEventId(UUID.randomUUID());

        return eventRepository.save(event);
    }

    public List<Event> findAllByUserId(String userId) {
        return eventRepository.findAllByUserId(userId);
    }
}
