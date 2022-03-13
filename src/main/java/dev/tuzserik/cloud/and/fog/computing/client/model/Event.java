package dev.tuzserik.cloud.and.fog.computing.client.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.api.client.util.DateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Data @AllArgsConstructor @NoArgsConstructor
public class Event {
    private UUID eventId = UUID.randomUUID();
    private String userId;
    private String summary;
    private String description;
    private String startTimeString = ZonedDateTime.now().toLocalDateTime().toString();
    private String endTimeString = ZonedDateTime.now().plusMinutes(30).toLocalDateTime().toString();
    private List<String> attendees;
    private String location;

    @JsonIgnore
    public DateTime getStartDateTime() {
        return new DateTime(startTimeString);
    }

    @JsonIgnore
    public void setStartDateTime(DateTime dateTime) {
        if (dateTime != null)
            this.startTimeString = dateTime.toString();
        else
            this.startTimeString = ZonedDateTime.now().toLocalDateTime().toString();
    }

    @JsonIgnore
    public DateTime getEndDateTime() {
        return new DateTime(startTimeString);
    }

    @JsonIgnore
    public void setEndDateTime(DateTime dateTime) {
        if (dateTime != null)
            this.endTimeString = dateTime.toString();
        else
            this.endTimeString = ZonedDateTime.now().toLocalDateTime().toString();
    }
}
