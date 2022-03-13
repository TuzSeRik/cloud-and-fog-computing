package dev.tuzserik.cloud.and.fog.computing.client.requests;

import dev.tuzserik.cloud.and.fog.computing.client.model.Event;
import lombok.Data;

@Data
public class EventRequest {
    private Event event;
}
