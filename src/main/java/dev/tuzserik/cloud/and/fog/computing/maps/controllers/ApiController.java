package dev.tuzserik.cloud.and.fog.computing.maps.controllers;

import dev.tuzserik.cloud.and.fog.computing.maps.responses.CoordinatesApiResponse;
import dev.tuzserik.cloud.and.fog.computing.maps.responses.TravelTimeApiResponse;
import dev.tuzserik.cloud.and.fog.computing.maps.responses.TravelTimeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class ApiController {
    private final WebClient azure = WebClient.create("https://atlas.microsoft.com");

    @GetMapping("/api/time")
    public ResponseEntity<TravelTimeResponse> getTravelTime(@RequestParam String from,
                                                            @RequestParam String to) {
        try {
            CoordinatesApiResponse.Element.CoordinatePair fromCoordinates =
                    azure
                            .get()
                            .uri(builder -> builder.pathSegment("search", "fuzzy", "JSON")
                                    .queryParam("api-version", "1.0")
                                    .queryParam("country-set", "RU")
                                    .queryParam("subscription-key", "6E0PkW-O9io3uncolW4Gr5LJ_HaB9K1Jcf67f13kL1g")
                                    .queryParam("query", from)
                                    .build()
                            )
                            .retrieve()
                            .bodyToMono(CoordinatesApiResponse.class)
                            .block()
                            .results
                            .get(0)
                            .position;

            CoordinatesApiResponse.Element.CoordinatePair toCoordinates =
                    azure
                            .get()
                            .uri(builder -> builder.pathSegment("search", "fuzzy", "JSON")
                                    .queryParam("api-version", "1.0")
                                    .queryParam("country-set", "RU")
                                    .queryParam("subscription-key", "6E0PkW-O9io3uncolW4Gr5LJ_HaB9K1Jcf67f13kL1g")
                                    .queryParam("query", to)
                                    .build()
                            )
                            .retrieve()
                            .bodyToMono(CoordinatesApiResponse.class)
                            .block()
                            .results
                            .get(0)
                            .position;

            return new ResponseEntity<>(
                    new TravelTimeResponse().setMinutes(
                            azure
                                    .get()
                                    .uri(builder -> builder.pathSegment("route", "directions", "JSON")
                                            .queryParam("api-version", "1.0")
                                            .queryParam("country-set", "RU")
                                            .queryParam("subscription-key", "6E0PkW-O9io3uncolW4Gr5LJ_HaB9K1Jcf67f13kL1g")
                                            .queryParam("query", fromCoordinates.toString() + ":" + toCoordinates.toString())
                                            .build()
                                    )
                                    .retrieve()
                                    .bodyToMono(TravelTimeApiResponse.class)
                                    .block()
                                    .routes
                                    .get(0)
                                    .legs
                                    .get(0)
                                    .summary
                                    .travelTimeInSeconds / 60
                    ),
                    HttpStatus.OK
            );
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
