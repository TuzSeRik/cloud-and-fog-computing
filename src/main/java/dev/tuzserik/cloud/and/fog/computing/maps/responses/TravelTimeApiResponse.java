package dev.tuzserik.cloud.and.fog.computing.maps.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TravelTimeApiResponse {
    public List<Route> routes;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Route {
        public List<Leg> legs;

        @JsonIgnoreProperties(ignoreUnknown = true)
        public class Leg {
            public Summary summary;

            @JsonIgnoreProperties(ignoreUnknown = true)
            public class Summary {
                public int travelTimeInSeconds;
            }
        }
    }
}
