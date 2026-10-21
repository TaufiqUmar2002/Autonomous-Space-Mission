package com.umar.service;

import com.umar.payload.response.external.CurrentWeatherResponse;
import com.umar.payload.response.external.TleData;

public interface IExternalIntegrationService {

    TleData getTleForSatellite(String nordId);

    CurrentWeatherResponse getCurrentWeather();
}
