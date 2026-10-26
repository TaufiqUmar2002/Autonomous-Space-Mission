package com.umar.service;

import com.umar.dto.AuditLogEvent;
import com.umar.entity.Mission;
import com.umar.payload.error.ApiException;
import com.umar.payload.request.mission.MissionCreateRequest;
import com.umar.payload.response.mission.MissionResponse;
import com.umar.repository.MissionRepository;
import com.umar.serviceinterface.IMissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MissionService implements IMissionService {

    private final MissionRepository missionRepository;


    @Override
    @Transactional
    public MissionResponse createMission(MissionCreateRequest request) {
        Optional<Mission> existingMission = missionRepository.findByMissionCodeOrMissionName(request.getMissionCode(), request.getMissionName());
        if (existingMission.isPresent()) {
            throw new ApiException(HttpStatus.CONFLICT,"DUPLICATE_MISSION_CODE","Mission with code " + request.getMissionCode() + " or name " + request.getMissionName() + " already exists");
        }
        Mission mission = Mission.builder()
                .missionCode(request.getMissionCode())
                .missionName(request.getMissionName())
                .owningOrganization(request.getOwningOrganization())
                .description(request.getDescription())
                .missionStartDate(request.getMissionStartDate())
                .build();
        Mission savedMission = missionRepository.save(mission);
        return mapToResponse(savedMission);
    }



    @Override
    public Page<MissionResponse> getAllMissions(Pageable pageable) {
        return missionRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    @Override
    public List<AuditLogEvent> getMissionAuditLog(UUID missionId) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new RuntimeException("Mission not found"));

        List<AuditLogEvent> events = new ArrayList<>();

        events.add(AuditLogEvent.builder()
                .eventType("MISSION_CREATED")
                .entityType("Mission")
                .entityId(mission.getId().toString())
                .changedBy("system")
                .timestamp(mission.getCreatedAt())
                .details("Mission created")
                .build());

        return events;
    }

    private MissionResponse mapToResponse(Mission mission) {
        return MissionResponse.builder()
                .id(mission.getId())
                .missionCode(mission.getMissionCode())
                .missionName(mission.getMissionName())
                .owningOrganization(mission.getOwningOrganization())
                .status(mission.getStatus())
                .createdAt(mission.getCreatedAt())
                .build();
    }
}
