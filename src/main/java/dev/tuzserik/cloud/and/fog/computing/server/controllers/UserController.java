package dev.tuzserik.cloud.and.fog.computing.server.controllers;

import dev.tuzserik.cloud.and.fog.computing.server.model.User;
import dev.tuzserik.cloud.and.fog.computing.server.responses.StatusResponse;
import dev.tuzserik.cloud.and.fog.computing.server.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController @AllArgsConstructor
public class UserController {
    UserService userService;

    @GetMapping("/api/user")
    public ResponseEntity<StatusResponse> showUserStatus(@RequestParam String id) {
        User user = userService.getUserById(id);

        return new ResponseEntity<>(
                new StatusResponse().setId(user.getId()).setImported(user.isImported()),
                HttpStatus.OK
        );
    }
}
