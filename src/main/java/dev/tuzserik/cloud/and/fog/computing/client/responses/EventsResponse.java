package dev.tuzserik.cloud.and.fog.computing.client.responses;

import dev.tuzserik.cloud.and.fog.computing.client.model.Event;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EventsResponse {
    List<Event> events = new ArrayList<>();
}
