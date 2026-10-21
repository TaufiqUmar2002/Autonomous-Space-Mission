package com.umar.entity;

import com.umar.enums.authIdentity.Status;
import com.umar.enums.external.CircuitOverride;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "SPACE_PROVIDER_CONFIGURATION")
@EntityListeners(AuditingEntityListener.class)
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "base_url", nullable = false)
    private String baseUrl;

    @Column(name = "vault_credential_path", nullable = false)
    private String vaultCredentialPath;

    @Column(name = "rate_limit_per_minute", nullable = false)
    private Integer rateLimitPerMinute;

    @Column(name = "default_cache_ttl_seconds", nullable = false)
    private Integer defaultCacheTtlSeconds;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(name = "circuit_override", length = 20)
    private CircuitOverride  circuitOverride;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();


}
