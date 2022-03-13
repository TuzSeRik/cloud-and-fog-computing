package dev.tuzserik.cloud.and.fog.computing.server.repositories;

import dev.tuzserik.cloud.and.fog.computing.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
