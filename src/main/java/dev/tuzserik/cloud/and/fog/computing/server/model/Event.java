package dev.tuzserik.cloud.and.fog.computing.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.api.client.util.DateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Data @AllArgsConstructor @NoArgsConstructor @Entity
public class Event {
    private @Id UUID eventId = UUID.randomUUID();
    private String userId;
    private String summary;
    private String description;
    private String startTimeString = ZonedDateTime.now().toLocalDateTime().toString();
    private String endTimeString = ZonedDateTime.now().plusMinutes(30).toLocalDateTime().toString();
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
