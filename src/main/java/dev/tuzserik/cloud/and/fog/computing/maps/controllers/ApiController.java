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
    private final WebClient mapsApiCoordinates =
            WebClient.create("https://atlas.microsoft.com/search/fuzzy/JSON?" +
                    "api-version=1.0&country-set=RU&subscription-key=6E0PkW-O9io3uncolW4Gr5LJ_HaB9K1Jcf67f13kL1g&" +
                    "query=");
    private final WebClient mapsApiTravelTime =
            WebClient.create("https://atlas.microsoft.com/route/directions/JSON?" +
                    "api-version=1.0&subscription-key=6E0PkW-O9io3uncolW4Gr5LJ_HaB9K1Jcf67f13kL1g&" +
                    "query=");

    @GetMapping("/api/time")
    public ResponseEntity<TravelTimeResponse> getTravelTime(@RequestParam String from,
                                                            @RequestParam String to) {
        try {
            CoordinatesApiResponse.Element.CoordinatePair fromCoordinates =
                    mapsApiCoordinates
                            .get()
                            .uri(from)
                            .retrieve()
                            .bodyToMono(CoordinatesApiResponse.class)
                            .block()
                            .results
                            .get(0)
                            .position;

//            CoordinatesApiResponse.Element.CoordinatePair toCoordinates =
//                    mapsApiCoordinates
//                            .get()
//                            .uri(to)
//                            .retrieve()
//                            .bodyToMono(CoordinatesApiResponse.class)
//                            .block()
//                            .results
//                            .get(0)
//                            .position;

            return new ResponseEntity<>(
                    new TravelTimeResponse().setMinutes(
//                            mapsApiTravelTime
//                                    .get()
//                                    .uri(fromCoordinates.toString() + ":" + toCoordinates.toString())
//                                    .retrieve()
//                                    .bodyToMono(TravelTimeApiResponse.class)
//                                    .block()
//                                    .routes
//                                    .get(0)
//                                    .legs
//                                    .get(0)
//                                    .summary
//                                    .travelTimeInSeconds / 60
                            3
                    ),
                    HttpStatus.OK
            );
        }
        catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
