package dev.tuzserik.cloud.and.fog.computing.client.controllers;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import dev.tuzserik.cloud.and.fog.computing.client.requests.EventRequest;
import dev.tuzserik.cloud.and.fog.computing.client.responses.EventResponse;
import dev.tuzserik.cloud.and.fog.computing.client.responses.TimeResponse;
import dev.tuzserik.cloud.and.fog.computing.client.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class EventController {
    private final WebClient server = WebClient.create(Constants.SERVER_API_URI);

    @GetMapping("/api/times")
    public ResponseEntity<TimeResponse> getTimes(@RequestParam String startTime,
                                                 @RequestParam String endTime,
                                                 @RequestParam String location) {
        try {
            return new ResponseEntity<>(
                    new TimeResponse().setTimes(
                        server
                                .get()
                                .uri("times?" +
                                        "startTime" + startTime +
                                        "endTime" + endTime +
                                        "location" + location
                                ).retrieve()
                                .bodyToMono(TimeResponse.class)
                                .block()
                                .getTimes()
                    ),
                    HttpStatus.OK
            );
        }
        catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/api/event")
    public ResponseEntity<EventResponse> addEvent(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client,
                                                  @RequestBody EventRequest eventRequest) {
        try {
            Credential credential = new Credential(BearerToken.authorizationHeaderAccessMethod())
                    .setAccessToken(client.getAccessToken().getTokenValue());

            Calendar calendar = new Calendar.Builder(
                    new NetHttpTransport(),
                    GsonFactory.getDefaultInstance(),
                    credential)
                    .setApplicationName("cloud-and-fog-computing-project")
                    .build();

            calendar.events().insert("primary",
                    new Event()
                            .setDescription(eventRequest.getEvent().getDescription())
                            .setStart(new EventDateTime().setDateTime(eventRequest.getEvent().getStartDateTime()))
                            .setEnd(new EventDateTime().setDateTime(eventRequest.getEvent().getEndDateTime()))
                            .setLocation(eventRequest.getEvent().getLocation())
            );

            return new ResponseEntity<>(
                    new EventResponse().setEvent(
                        server
                                .post()
                                .uri("event")
                                .bodyValue(eventRequest)
                                .retrieve()
                                .bodyToMono(EventResponse.class)
                                .block()
                                .getEvent()
                    ),
                    HttpStatus.OK
            );
        }
        catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
