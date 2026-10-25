package com.umar.service;

import com.umar.payload.request.external.ProvidersRequest;
import com.umar.payload.response.external.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface IExternalIntegrationService {

    TleData getTleForSatellite(String nordId);

    SpaceWeatherResponse getCurrentWeather();

    void  getWeatherHistory(LocalDateTime startDate, LocalDateTime endDate);

    ExternalPassResponse getNearEarthObjects(Double observerLatitude, Double observerLongitude, Integer days);

    ProvidersResponse createProvider(ProvidersRequest request);

    List<ProviderResponse> getProviders();

    ProviderHealthResponse getProviderHealth(UUID providerId);

    ProviderCircuitResponse updateProviderCircuitBreaker(String providerId, String desiredState, String reason);

    List<CacheResponse> getCacheStats(String providerId, String periodHours);

    TrackingPasses getSatellitePasses(Integer noradId, Double observerLatitude, Double observerLongitude, Integer days);

}
