package com.umar.payload.response.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class KIndexRecord {
    @JsonProperty("time_tag")
    private String timeTag;

    @JsonProperty("Kp")
    private Double kp;

    @JsonProperty("a_running")
    private Integer aRunning;

    @JsonProperty("station_count")
    private Integer stationCount;

    // Getters and Setters
}
