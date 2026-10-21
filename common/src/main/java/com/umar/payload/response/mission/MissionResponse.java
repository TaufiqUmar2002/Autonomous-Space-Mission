package com.umar.payload.response.mission;

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
public class MissionResponse {

    private UUID id;
    private String missionCode;
    private String missionName;
    private String owningOrganization;
    private Status status;
    private LocalDateTime createdAt;
}
