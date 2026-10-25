package com.umar.payload.response.external;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProvidersResponse {
    private String providerId;
    private String baseUrl;
    private Integer rateLimitPerMinute;
    private String status;
}
