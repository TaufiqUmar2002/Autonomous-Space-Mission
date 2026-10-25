package com.umar.payload.response.external;

public record ProviderHealthResponse(String providerId, String circuitState, String recentFailureReasons, String rateLimitConsumedThisMinute, String lastSuccessfulCallAt) {
}
