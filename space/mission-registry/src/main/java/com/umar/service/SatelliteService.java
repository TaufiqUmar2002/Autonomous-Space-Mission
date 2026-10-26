package com.umar.service;


import com.umar.dto.SatelliteResponse;
import com.umar.entity.Mission;
import com.umar.entity.Satellite;
import com.umar.enums.missionRegistry.OrbitType;
import com.umar.enums.missionRegistry.Status;
import com.umar.payload.request.mission.NoradIdCorrectionRequest;
import com.umar.payload.request.mission.SatelliteDecommissionRequest;
import com.umar.payload.request.mission.SatelliteRegisterRequest;
import com.umar.payload.request.mission.SatelliteUpdateRequest;
import com.umar.repository.MissionRepository;
import com.umar.repository.SatelliteRepository;
import com.umar.serviceinterface.ISatelliteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SatelliteService implements ISatelliteService {

    private final SatelliteRepository satelliteRepository;
    private final MissionRepository missionRepository;

    @Override
    @Transactional
    public SatelliteResponse registerSatellite(UUID missionId, SatelliteRegisterRequest request) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new RuntimeException("Mission not found"));

        Satellite satellite = Satellite.builder()
                .satelliteName(request.getSatelliteName())
                .noradId(request.getNoradId())
                .orbitType(request.getOrbitType())
                .status(request.getStatus())
                .mission(mission)
                .build();

        Satellite savedSatellite = satelliteRepository.save(satellite);

        return mapToResponse(savedSatellite);
    }

    @Override
    public SatelliteResponse getSatelliteById(UUID satelliteId) {
        Satellite satellite = satelliteRepository.findById(satelliteId)
                .orElseThrow(() -> new RuntimeException("Satellite not found"));

        return mapToResponse(satellite);
    }

    @Override
    public Page<SatelliteResponse> getSatellitesByMission(UUID missionId, Pageable pageable) {
        return satelliteRepository.findByMissionId(missionId, pageable)
                .map(this::mapToResponse);
    }

    @Override
    @Transactional
    public SatelliteResponse updateSatellite(UUID satelliteId, SatelliteUpdateRequest request) {
        Satellite satellite = satelliteRepository.findById(satelliteId)
                .orElseThrow(() -> new RuntimeException("Satellite not found"));

        satellite.setSatelliteName(request.getSatelliteName());
        if (request.getOrbitType() != null) {
            satellite.setOrbitType(request.getOrbitType());
        }
        if (request.getStatus() != null) {
            satellite.setStatus(request.getStatus());
        }

        Satellite updatedSatellite = satelliteRepository.save(satellite);

        return mapToResponse(updatedSatellite);
    }



    @Override
    @Transactional
    public SatelliteResponse decommissionSatellite(UUID satelliteId, SatelliteDecommissionRequest request) {
        Satellite satellite = satelliteRepository.findById(satelliteId)
                .orElseThrow(() -> new RuntimeException("Satellite not found"));

        satellite.setStatus(Status.DECOMMISSIONED);
        satellite.setDecommissionedAt(LocalDateTime.now());
        satellite.setDecommissionedBy("system");
        satellite.setDecommissionReason(request.getReason());

        Satellite decommissionedSatellite = satelliteRepository.save(satellite);

        return mapToResponse(decommissionedSatellite);
    }

    @Override
    @Transactional
    public SatelliteResponse correctNoradId(UUID satelliteId, NoradIdCorrectionRequest request) {
        Satellite satellite = satelliteRepository.findById(satelliteId)
                .orElseThrow(() -> new RuntimeException("Satellite not found"));

        satellite.setNoradId(request.getNewNoradId());

        Satellite updatedSatellite = satelliteRepository.save(satellite);

        return mapToResponse(updatedSatellite);
    }

    @Override
    public SatelliteResponse searchByNoradId(Integer noradId) {
        Satellite satellite = satelliteRepository.findByNoradId(noradId)
                .orElseThrow(() -> new RuntimeException("Satellite not found with NORAD ID: " + noradId));

        return mapToResponse(satellite);
    }

    @Override
    public Page<SatelliteResponse> getActiveSatellites(OrbitType orbitTypeFilter, Pageable pageable) {
        return satelliteRepository.findByStatusAndOrbitType(Status.ACTIVE, orbitTypeFilter, pageable)
                .map(this::mapToResponse);
    }

    private SatelliteResponse mapToResponse(Satellite satellite) {
        return SatelliteResponse.builder()
                .id(satellite.getId())
                .satelliteName(satellite.getSatelliteName())
                .noradId(satellite.getNoradId())
                .orbitType(satellite.getOrbitType())
                .status(satellite.getStatus())
                .missionId(satellite.getMission() != null ? satellite.getMission().getId() : null)
                .createdAt(satellite.getCreatedAt())
                .updatedAt(satellite.getUpdatedAt())
                .decommissionedAt(satellite.getDecommissionedAt())
                .build();
    }
}
