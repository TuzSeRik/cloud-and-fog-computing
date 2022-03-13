package dev.tuzserik.cloud.and.fog.computing.client.controllers;

import dev.tuzserik.cloud.and.fog.computing.client.responses.StatusResponse;
import dev.tuzserik.cloud.and.fog.computing.client.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.security.MessageDigest;

@RestController
public class UserController {
    private final WebClient server = WebClient.create(Constants.SERVER_API_URI);

    @GetMapping("/api/user-status")
    public ResponseEntity<StatusResponse> getUserStatus(
            @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client) {
        try {
            return new ResponseEntity<>(
                    server
                        .get()
                        .uri("user-status?id=" +
                                new String(MessageDigest.getInstance("MD5")
                                        .digest(client.getAccessToken().getTokenValue().getBytes()))
                                    .toUpperCase()
                        ).retrieve()
                        .bodyToMono(StatusResponse.class)
                        .block(),
                    HttpStatus.OK
            );
        }
        catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
