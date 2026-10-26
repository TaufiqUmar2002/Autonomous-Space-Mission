package com.umar.repository;

import com.umar.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MissionRepository extends JpaRepository<Mission, UUID> {

    Page<Mission> findAll(Pageable pageable);

    @Query("SELECT m FROM Mission m WHERE m.missionCode = :missionCode OR m.missionName = :missionName")
    Optional<Mission> findByMissionCodeOrMissionName(String missionCode,String missionName);
}
