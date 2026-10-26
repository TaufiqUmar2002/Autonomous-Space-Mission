package com.umar.entity;

import com.umar.enums.missionRegistry.OrbitType;
import com.umar.enums.missionRegistry.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name =  "SPACE_SATELLITE")
@EntityListeners(AuditingEntityListener.class)
public class Satellite {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String satelliteName;

    @Column(name = "norad_id")
    private Integer noradId;

    @Enumerated(EnumType.STRING)
    private OrbitType orbitType;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "decommissioned_at")
    private LocalDateTime decommissionedAt;

    @Column(name = "decommissioned_by")
    private String decommissionedBy;

    @Column(name = "decommission_reason")
    private String decommissionReason;
}
