package com.umar.payload.response.external;

import java.util.List;

public record SpaceWeatherResponse(SolarConditions solar, GeomagneticConditions geomagnetic, RadiationConditions radiation, String source, boolean stale) {

    public record SolarConditions(Double xrayFlux, String xrayEnergy, String solarFlareClass, String observedAt) {

    }

    public record GeomagneticConditions(List<GeomagneticReading> readings) {

    }

    public record GeomagneticReading(String timeTag, Double kp, Integer aRunning, Integer stationCount) {

    }

    public record RadiationConditions(List<RadiationReading> readings) {

    }

    public record RadiationReading(String timeTag, Integer satellite, String energy, Double flux, String unit) {

    }
}
