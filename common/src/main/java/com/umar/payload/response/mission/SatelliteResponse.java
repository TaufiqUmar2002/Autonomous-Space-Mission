package com.umar.dto;

import com.umar.enums.missionRegistry.OrbitType;
import com.umar.enums.missionRegistry.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SatelliteResponse {

    private UUID id;
    private String satelliteName;
    private Integer noradId;
    private OrbitType orbitType;
    private Status status;
    private UUID missionId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime decommissionedAt;


}
