package dev.tuzserik.cloud.and.fog.computing.server.requests;

import dev.tuzserik.cloud.and.fog.computing.server.model.Event;
import lombok.Data;

@Data
public class EventRequest {
    private Event event;
}
