package com.umar.controller;


import com.umar.dto.SatelliteResponse;
import com.umar.payload.request.mission.NoradIdCorrectionRequest;
import com.umar.payload.request.mission.SatelliteDecommissionRequest;
import com.umar.payload.request.mission.SatelliteUpdateRequest;
import com.umar.serviceinterface.ISatelliteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/satellites")
@RequiredArgsConstructor
public class SatelliteController {

    private final ISatelliteService satelliteService;

    @GetMapping("/{id}")
//    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<SatelliteResponse> getSatelliteById(@PathVariable UUID id) {
        SatelliteResponse response = satelliteService.getSatelliteById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<SatelliteResponse> updateSatellite(@PathVariable UUID id, @Valid @RequestBody SatelliteUpdateRequest request) {
        SatelliteResponse response = satelliteService.updateSatellite(id, request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/decommission")
//    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<SatelliteResponse> decommissionSatellite(@PathVariable UUID id, @Valid @RequestBody SatelliteDecommissionRequest request) {
        SatelliteResponse response = satelliteService.decommissionSatellite(id, request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/norad-id")
//    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<SatelliteResponse> correctNoradId(@PathVariable UUID id, @Valid @RequestBody NoradIdCorrectionRequest request) {
        SatelliteResponse response = satelliteService.correctNoradId(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
//    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<SatelliteResponse> searchByNoradId(@RequestParam Integer noradId) {
        SatelliteResponse response = satelliteService.searchByNoradId(noradId);
        return ResponseEntity.ok(response);
    }
}
