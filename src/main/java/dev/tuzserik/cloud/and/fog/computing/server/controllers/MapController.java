package dev.tuzserik.cloud.and.fog.computing.server.controllers;

import dev.tuzserik.cloud.and.fog.computing.server.responses.TimeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MapController {
    @GetMapping("/api/times")
    public ResponseEntity<TimeResponse> getTimes(@RequestParam String startTime,
                                                 @RequestParam String endTime,
                                                 @RequestParam String location) {
        return null;
    }
}
