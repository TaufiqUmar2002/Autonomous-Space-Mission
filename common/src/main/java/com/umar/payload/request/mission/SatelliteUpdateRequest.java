package com.umar.payload.request.mission;

import com.umar.enums.missionRegistry.OrbitType;
import com.umar.enums.missionRegistry.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SatelliteUpdateRequest {

    @NotBlank(message = " Satellite name is required")
    private String satelliteName;
    private OrbitType orbitType;
    private Status status;
}
