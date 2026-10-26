package com.umar.repository;

import com.umar.entity.Satellite;
import com.umar.enums.missionRegistry.OrbitType;
import com.umar.enums.missionRegistry.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SatelliteRepository extends JpaRepository<Satellite, UUID> {

    Optional<Satellite> findByNoradId(Integer noradId);

    Page<Satellite> findByMissionId(UUID missionId, Pageable pageable);

    Page<Satellite> findByStatus(Status status, Pageable pageable);

    @Query("SELECT s FROM Satellite s WHERE s.status = :status AND (:orbitType IS NULL OR s.orbitType = :orbitType)")
    Page<Satellite> findByStatusAndOrbitType(@Param("status") Status status, @Param("orbitType") OrbitType orbitType, Pageable pageable);
}
