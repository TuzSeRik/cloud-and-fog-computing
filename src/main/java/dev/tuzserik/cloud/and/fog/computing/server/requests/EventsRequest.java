package dev.tuzserik.cloud.and.fog.computing.server.requests;

import dev.tuzserik.cloud.and.fog.computing.server.model.Event;
import lombok.Data;

import java.util.List;

@Data
public class EventsRequest {
    private String userId;
    private List<Event> events;
}
