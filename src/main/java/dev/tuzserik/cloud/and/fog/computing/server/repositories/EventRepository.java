package dev.tuzserik.cloud.and.fog.computing.server.repositories;

import dev.tuzserik.cloud.and.fog.computing.server.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, String> {
}
