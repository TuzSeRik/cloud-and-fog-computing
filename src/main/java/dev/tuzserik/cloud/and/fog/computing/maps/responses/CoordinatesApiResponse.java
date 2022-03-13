package dev.tuzserik.cloud.and.fog.computing.maps.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoordinatesApiResponse {
    public List<Element> results;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Element {
        public CoordinatePair position;

        @JsonIgnoreProperties(ignoreUnknown = true)
        public class CoordinatePair {
            public String lat;
            public String lon;

            @Override
            public String toString() {
                return lat + "," + lon;
            }
        }
    }
}
