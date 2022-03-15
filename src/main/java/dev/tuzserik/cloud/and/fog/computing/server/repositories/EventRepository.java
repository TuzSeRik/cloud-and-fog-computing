package dev.tuzserik.cloud.and.fog.computing.server.repositories;

import dev.tuzserik.cloud.and.fog.computing.server.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, String> {
    List<Event> findAllByUserId(String userId);
}
