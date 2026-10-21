package com.umar.payload.request.mission;

import com.umar.enums.missionRegistry.Status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MissionCreateRequest {

    @NotBlank(message = " Mission code is required")
    private String missionCode;
    @NotBlank(message = "Mission name is required")
    private String missionName;
    @NotBlank(message = "Owning organization is required")
    private String owningOrganization;
    @NotNull(message = "Status is required")
    private Status status;

}
