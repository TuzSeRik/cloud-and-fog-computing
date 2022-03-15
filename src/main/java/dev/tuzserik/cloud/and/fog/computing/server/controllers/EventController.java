package dev.tuzserik.cloud.and.fog.computing.server.controllers;

import dev.tuzserik.cloud.and.fog.computing.server.requests.EventRequest;
import dev.tuzserik.cloud.and.fog.computing.server.requests.EventsRequest;
import dev.tuzserik.cloud.and.fog.computing.server.responses.EventResponse;
import dev.tuzserik.cloud.and.fog.computing.server.responses.EventsResponse;
import dev.tuzserik.cloud.and.fog.computing.server.services.EventService;
import dev.tuzserik.cloud.and.fog.computing.server.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController @AllArgsConstructor
public class EventController {
    private EventService eventService;
    private UserService userService;

    @PostMapping("/api/events")
    public ResponseEntity<EventsResponse> importEvents(@RequestBody EventsRequest request) {
        userService.saveUser(userService.getUserById(request.getUserId()).setImported(true));

        return new ResponseEntity<>(
                new EventsResponse().setEvents(eventService.saveEvents(request.getEvents())),
                HttpStatus.OK
        );
    }

    @PostMapping("/api/event")
    public ResponseEntity<EventResponse> addEvent(@RequestBody EventRequest request) {
        return new ResponseEntity<>(
                new EventResponse().setEvent(eventService.saveEvent(request.getEvent())),
                HttpStatus.OK
        );
    }
}
