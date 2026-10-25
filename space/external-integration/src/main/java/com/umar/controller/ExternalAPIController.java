package com.umar.controller;

import com.umar.payload.request.external.ProvidersRequest;
import com.umar.payload.response.external.*;
import com.umar.service.IExternalIntegrationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/external")
public class ExternalAPIController {


    private final IExternalIntegrationService integrationService;

    @GetMapping("/tle/{nordId}")
    public ResponseEntity<TleData> getTwoLineElement(@PathVariable @NotBlank(message = "NORAD ID is required")  String nordId){
        TleData tleData = integrationService.getTleForSatellite(nordId);
        return ResponseEntity.status(HttpStatus.OK).body(tleData);
    }

    @GetMapping("/space-weather/current")
    public ResponseEntity<SpaceWeatherResponse> getCurrentSpaceWeather(){
        SpaceWeatherResponse response = integrationService.getCurrentWeather();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/space-weather/history")
    public ResponseEntity<Void> getHistorySpaceWeather(@RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime from, @RequestParam("to")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime to){
        long days = ChronoUnit.DAYS.between(from,to);
        if(days>365){
            throw new IllegalArgumentException("Date range cannot be more than 365 days");
        }
        this.integrationService.getWeatherHistory(from,to);
        return null;
    }

    @GetMapping("/near-earth-objects")
    public ResponseEntity<ExternalPassResponse> getNearEarthObjects(@RequestParam @DecimalMin("-90.0")@DecimalMax("90.0") Double observerLatitude, @RequestParam @DecimalMin("-180.0")@DecimalMax("180.0") Double observerLongitude, @RequestParam(defaultValue = "3") @Min(1) @Max(10) Integer days){
        ExternalPassResponse response = this.integrationService.getNearEarthObjects(observerLatitude,observerLongitude,days);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/satellite-tracking/{noradId}/passes")
    public ResponseEntity<TrackingPasses> getSatellitePasses(@PathVariable Integer noradId,@RequestParam @DecimalMin("-90.0")@DecimalMax("90.0") Double observerLatitude, @RequestParam @DecimalMin("-180.0")@DecimalMax("180.0") Double observerLongitude, @RequestParam(defaultValue = "3") @Min(1) @Max(10) Integer days){
        TrackingPasses passes = this.integrationService.getSatellitePasses(noradId,observerLongitude,observerLongitude,days);
        return ResponseEntity.status(HttpStatus.OK).body(passes);
    }

    @PostMapping("/providers")
    public ResponseEntity<ProvidersResponse> createProvider(@Valid @RequestBody ProvidersRequest request){
        ProvidersResponse response = this.integrationService.createProvider(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/providers")
    public ResponseEntity<List<ProviderResponse>> getProviders(){
        List<ProviderResponse> responseList = this.integrationService.getProviders();
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    @GetMapping("/providers/{providerId}/health")
    public ResponseEntity<ProviderHealthResponse> getProviderHealth(@PathVariable UUID providerId){
        ProviderHealthResponse response = this.integrationService.getProviderHealth(providerId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/providers/{providerId}/circuit-breaker")
    public ResponseEntity<ProviderCircuitResponse> updateProviderCircuitBreaker(@PathVariable UUID providerId,@RequestParam String desiredState,@RequestParam String reason) {
        ProviderCircuitResponse response = this.integrationService.updateProviderCircuitBreaker(String.valueOf(providerId),desiredState,reason);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/cache/stats")
    public ResponseEntity<List<CacheResponse>> getCacheStats(@RequestParam("providerId") String providedId,@RequestParam("periodHours") String periodHours){
        List<CacheResponse> responseList = this.integrationService.getCacheStats(providedId,periodHours);
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }









}
