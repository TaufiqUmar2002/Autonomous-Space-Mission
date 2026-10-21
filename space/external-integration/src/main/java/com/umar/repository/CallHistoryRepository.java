package com.umar.repository;

import com.umar.entity.CallHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface CallHistoryRepository extends JpaRepository<CallHistory, UUID> {

    @Query("SELECT ch FROM CallHistory ch WHERE ch.provider.id = :providerId AND ch.timestamp >= :startTime ORDER BY ch.timestamp DESC")
    List<CallHistory> findRecentCallsByProvider(
            @Param("providerId") String providerId,
            @Param("startTime") LocalDateTime startTime
    );
}
