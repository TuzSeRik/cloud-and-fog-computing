package dev.tuzserik.cloud.and.fog.computing.client.controllers;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.Events;
import dev.tuzserik.cloud.and.fog.computing.client.model.Event;
import dev.tuzserik.cloud.and.fog.computing.client.responses.EventsResponse;
import dev.tuzserik.cloud.and.fog.computing.client.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TransferController {
    private final WebClient server = WebClient.create(Constants.SERVER_API_URI);

    @GetMapping("/api/import-events")
    public ResponseEntity<EventsResponse> importEvents(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client)
            throws IOException, NoSuchAlgorithmException {
        Credential credential = new Credential(BearerToken.authorizationHeaderAccessMethod())
                .setAccessToken(client.getAccessToken().getTokenValue());

        Calendar calendar = new Calendar.Builder(
                new NetHttpTransport(),
                GsonFactory.getDefaultInstance(),
                credential)
                    .setApplicationName("cloud-and-fog-computing-project")
                    .build();

        Events events = calendar.events().list("primary")
                .setTimeMin(new DateTime(ZonedDateTime.now().toString()))
                .setTimeMax(new DateTime(LocalDateTime.now().plusYears(1).toString()))
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();

        List<com.google.api.services.calendar.model.Event> proceedingEvents = events.getItems();
        List<Event> proceededEvents = new ArrayList<>();
        String userId = new String(MessageDigest.getInstance("MD5")
                .digest(client.getAccessToken().getTokenValue().getBytes())).toUpperCase();

        for (com.google.api.services.calendar.model.Event event : proceedingEvents) {
            Event proceededEvent = new Event();
            proceededEvent.setUserId(userId);
            proceededEvent.setSummary(event.getSummary());
            proceededEvent.setDescription(event.getDescription());
            proceededEvent.setStartDateTime(
                    event.getOriginalStartTime() == null
                            ? null
                            : event.getOriginalStartTime().getDateTime());
            proceededEvent.setEndDateTime(
                    event.getEnd() == null
                            ? null
                            : event.getEnd().getDateTime());
            if (event.getAttendees() == null)
                event.setAttendees(new ArrayList<>());
            proceededEvent.setAttendees(event.getAttendees()
                    .stream().map(EventAttendee::getEmail).collect(Collectors.toList()));
            proceededEvent.setLocation(event.getLocation());

            proceededEvents.add(proceededEvent);
        }

        try {
            return new ResponseEntity<>(
                    new EventsResponse().setEvents(
                        server
                                .post()
                                .uri("events")
                                .bodyValue(proceededEvents)
                                .retrieve()
                                .bodyToMono(EventsResponse.class)
                                .block()
                                .getEvents()
                    ),
                    HttpStatus.OK
            );
        }
        catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
