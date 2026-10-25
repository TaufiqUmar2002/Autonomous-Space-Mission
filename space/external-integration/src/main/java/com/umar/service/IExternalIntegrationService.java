package com.umar.service;

import com.umar.payload.response.external.CurrentWeatherResponse;
import com.umar.payload.response.external.SpaceWeatherResponse;
import com.umar.payload.response.external.TleData;

import java.time.LocalDateTime;

public interface IExternalIntegrationService {

    TleData getTleForSatellite(String nordId);

    SpaceWeatherResponse getCurrentWeather();

    void  getWeatherHistory(LocalDateTime startDate, LocalDateTime endDate);

    void getNearEarthObjects();
}
