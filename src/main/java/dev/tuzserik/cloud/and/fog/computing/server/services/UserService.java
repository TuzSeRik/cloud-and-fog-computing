package dev.tuzserik.cloud.and.fog.computing.server.services;

import dev.tuzserik.cloud.and.fog.computing.server.model.User;
import dev.tuzserik.cloud.and.fog.computing.server.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public User getUserById(String id) {
        if (userRepository.existsById(id))
            return userRepository.getById(id);
        else
            return userRepository.save(new User().setId(id));
    }
}
