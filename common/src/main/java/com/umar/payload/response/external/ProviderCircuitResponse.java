package com.umar.payload.response.external;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProviderCircuitResponse {

    private String providerId;
    private String circuitState;
    private String overriddenBy;
}
