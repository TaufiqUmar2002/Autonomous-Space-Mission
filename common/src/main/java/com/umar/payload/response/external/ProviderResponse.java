package com.umar.payload.response.external;




public record ProviderResponse(
        String providerId,
        String baseUrl,
        String circuitState,
        Integer recentSuccessRate,
        String rateLimitRemainingThisMinute
) {
}
