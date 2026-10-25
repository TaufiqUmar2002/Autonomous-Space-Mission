package com.umar.payload.request.external;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProvidersRequest {

    private String providerId;
    private String baseUrl;
    private String vaultCredentialPath;
    private Integer rateLimitPerMinute;
    private Integer defaultCacheTtlSeconds;

}
