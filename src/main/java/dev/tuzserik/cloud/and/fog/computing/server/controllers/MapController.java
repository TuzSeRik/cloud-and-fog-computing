package dev.tuzserik.cloud.and.fog.computing.server.controllers;

import dev.tuzserik.cloud.and.fog.computing.server.model.Event;
import dev.tuzserik.cloud.and.fog.computing.server.responses.TimeResponse;
import dev.tuzserik.cloud.and.fog.computing.server.services.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Comparator;

@RestController
public class MapController {
    private EventService eventService;

    @GetMapping("/api/times")
    public ResponseEntity<TimeResponse> getTimes(@RequestParam String userId,
                                                 @RequestParam String startTime,
                                                 @RequestParam String endTime,
                                                 @RequestParam String location) {
//        String filteredLocation = eventService.findAllByUserId(userId).stream()
//                .filter(event -> (event.getEndDateTime().getValue() >= Long.parseLong(startTime)
//                        && event.getStartDateTime().getValue() <= Long.parseLong(endTime)))
//                .findFirst().get().getLocation();

        return new ResponseEntity<>(
                new TimeResponse().setTimes(Collections.singletonList("15:20")),
                HttpStatus.OK
        );
    }
}
