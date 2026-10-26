package com.umar.serviceinterface;

import com.umar.dto.AuditLogEvent;
import com.umar.payload.request.mission.MissionCreateRequest;
import com.umar.payload.response.mission.MissionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IMissionService {

    MissionResponse createMission(MissionCreateRequest request);

    Page<MissionResponse> getAllMissions(Pageable pageable);

    List<AuditLogEvent> getMissionAuditLog(UUID missionId);
}
