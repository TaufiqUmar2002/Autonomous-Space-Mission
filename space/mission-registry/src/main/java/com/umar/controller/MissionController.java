package com.umar.controller;

import com.umar.dto.AuditLogEvent;

import com.umar.dto.SatelliteResponse;
import com.umar.payload.request.mission.MissionCreateRequest;
import com.umar.payload.request.mission.SatelliteRegisterRequest;
import com.umar.payload.response.mission.MissionResponse;
import com.umar.serviceinterface.IMissionService;
import com.umar.serviceinterface.ISatelliteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/missions")
@RequiredArgsConstructor
public class MissionController {

    private final IMissionService missionService;
    private final ISatelliteService satelliteService;

    @PostMapping
//    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<MissionResponse> createMission(@Valid @RequestBody MissionCreateRequest request) {
        MissionResponse response = missionService.createMission(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/{id}/satellites")
//    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<SatelliteResponse> registerSatellite(
            @PathVariable UUID id,
            @Valid @RequestBody SatelliteRegisterRequest request) {
        SatelliteResponse response = satelliteService.registerSatellite(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
//    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Page<MissionResponse>> getAllMissions(Pageable pageable) {
//        Page<MissionResponse> response = missionService.getAllMissions(pageable);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}/satellites")
//    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Page<SatelliteResponse>> getSatellitesByMission(
            @PathVariable UUID id,
            Pageable pageable) {
        Page<SatelliteResponse> response = satelliteService.getSatellitesByMission(id, pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/audit-log")
//    @PreAuhorize("hasAnyRole('ADMINISTRATOR', 'COMPLIANCE_OFFICER')")
    public ResponseEntity<List<AuditLogEvent>> getMissionAuditLog(@PathVariable UUID id) {
        List<AuditLogEvent> events = missionService.getMissionAuditLog(id);
        return ResponseEntity.ok(events);
    }
}
