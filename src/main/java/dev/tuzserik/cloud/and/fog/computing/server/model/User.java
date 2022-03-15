package dev.tuzserik.cloud.and.fog.computing.server.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class User {
    private @Id String id;
    private String password;
    private boolean isImported = false;
    private String email;
    private String givenName;
    private String lastName;
    private String profilePicture;
}
