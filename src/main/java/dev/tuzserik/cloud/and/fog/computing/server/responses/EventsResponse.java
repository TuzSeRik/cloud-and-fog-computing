package dev.tuzserik.cloud.and.fog.computing.server.responses;

import dev.tuzserik.cloud.and.fog.computing.server.model.Event;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EventsResponse {
    List<Event> events = new ArrayList<>();
}
