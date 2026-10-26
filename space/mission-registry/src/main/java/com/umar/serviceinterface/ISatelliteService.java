package com.umar.serviceinterface;


import com.umar.dto.SatelliteResponse;
import com.umar.enums.missionRegistry.OrbitType;
import com.umar.payload.request.mission.NoradIdCorrectionRequest;
import com.umar.payload.request.mission.SatelliteDecommissionRequest;
import com.umar.payload.request.mission.SatelliteRegisterRequest;
import com.umar.payload.request.mission.SatelliteUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ISatelliteService {

    SatelliteResponse registerSatellite(UUID missionId, SatelliteRegisterRequest request);

    SatelliteResponse getSatelliteById(UUID satelliteId);

    Page<SatelliteResponse> getSatellitesByMission(UUID missionId, Pageable pageable);

    SatelliteResponse updateSatellite(UUID satelliteId, SatelliteUpdateRequest request);

    SatelliteResponse decommissionSatellite(UUID satelliteId, SatelliteDecommissionRequest request);

    SatelliteResponse correctNoradId(UUID satelliteId, NoradIdCorrectionRequest request);

    SatelliteResponse searchByNoradId(Integer noradId);

    Page<SatelliteResponse> getActiveSatellites(OrbitType orbitTypeFilter, Pageable pageable);
}
