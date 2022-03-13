package dev.tuzserik.cloud.and.fog.computing.server.responses;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TimeResponse {
    private List<String> times = new ArrayList<>();
}
