package com.umar.payload.response.external;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CacheResponse {

    private String providerId;
    private String dataType;
    private String hitRatePercent;
    private String currentTtlSeconds;
    private String callsAvoidedByCache;

}
