package dev.tuzserik.cloud.and.fog.computing.server.responses;

import dev.tuzserik.cloud.and.fog.computing.server.model.Event;
import lombok.Data;

@Data
public class EventResponse {
    private Event event;
}
