package com.umar.controller;

import com.umar.payload.response.external.CurrentWeatherResponse;
import com.umar.payload.response.external.TleData;
import com.umar.service.IExternalIntegrationService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    public ResponseEntity<CurrentWeatherResponse> getCurrentSpaceWeather(){
        CurrentWeatherResponse response = integrationService.getCurrentWeather();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/space-weather/history")
    public ResponseEntity<Void> getHistorySpaceWeather(@RequestParam("startDate")LocalDateTime startDate,@RequestParam("endDate") LocalDateTime endDate){
        return null;
    }

    @GetMapping("/near-earth-objects")
    public ResponseEntity<Void> getNearEarthObjects(){
        return null;
    }

    @GetMapping("/satellite-tracking/{noradId}/passes")
    public ResponseEntity<Void> getSatellitePasses(@PathVariable Integer noradId){
        return null;
    }

    @PostMapping("/providers")
    public ResponseEntity<Void> createProvider(){
        return null;
    }

    @GetMapping("/providers")
    public ResponseEntity<Void> getProviders(){
        return null;
    }

    @GetMapping("/providers/{providerId}/health")
    public ResponseEntity<Void> getProviderHealth(@PathVariable UUID providerId){
        return null;
    }

    @PutMapping("/providers/{providerId}/circuit-breaker")
    public ResponseEntity<Void> updateProviderCircuitBreaker(@PathVariable UUID providerId){
        return null;
    }

    @GetMapping("/cache/stats")
    public ResponseEntity<Void> getCacheStats(){
        return null;
    }









}
