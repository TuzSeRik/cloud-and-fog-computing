package dev.tuzserik.cloud.and.fog.computing.server.responses;

import lombok.Data;

@Data
public class StatusResponse {
    private String id;
    private boolean isImported;
}
