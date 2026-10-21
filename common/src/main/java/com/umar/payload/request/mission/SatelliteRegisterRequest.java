package com.umar.payload.request.mission;

import com.umar.enums.missionRegistry.OrbitType;
import com.umar.enums.missionRegistry.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SatelliteRegisterRequest {

    @NotBlank(message = " Satellite name is required")
    private String satelliteName;

    @NotNull(message = "NORAD ID is required")
    @Positive(message = "NORAD ID must be positive")
    private Integer noradId;

    @NotNull(message = "Orbit type is required")
    private OrbitType orbitType;

    @NotNull(message = "Status is required")
    private Status status;
}
