package com.umar.service;

import com.umar.exchange.GeomagneticFeignClient;
import com.umar.exchange.RadiationFeignClient;
import com.umar.exchange.SolarFeignClient;
import com.umar.payload.response.external.KIndexRecord;
import com.umar.payload.response.external.SpaceWeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class NoaaSpaceWeatherProvider {

    private final SolarFeignClient solarClient;
    private final GeomagneticFeignClient geomagneticClient;
    private final RadiationFeignClient radiationClient;



    public SpaceWeatherResponse.SolarConditions fetchSolar() {
        List<Map<String, Object>> rows = solarClient.getXrayReadings();

        Map<String, Object> latest = rows == null
                ? null
                : rows.stream()
                .filter(r -> "0.1-0.8nm".equalsIgnoreCase(
                        Objects.toString(r.get("energy"), "")))
                .filter(r -> r.get("flux") instanceof Number)
                .max(Comparator.comparing(
                        r -> Objects.toString(r.get("time_tag"), "")))
                .orElse(null);

        if (latest == null) {
            throw new IllegalStateException("No valid NOAA X-ray readings");
        }
        double flux = ((Number) latest.get("flux")).doubleValue();
        return new SpaceWeatherResponse.SolarConditions(flux, Objects.toString(latest.get("energy"), null), classifyFlare(flux), Objects.toString(latest.get("time_tag"), null));
    }

    public SpaceWeatherResponse.GeomagneticConditions fetchGeomagnetic() {
        List<KIndexRecord> rows = geomagneticClient.getKpReadings();
        if (rows == null || rows.size() < 2) {
            throw new IllegalStateException("No NOAA Kp readings");
        }
        List<SpaceWeatherResponse.GeomagneticReading> readings = rows.stream()
                .skip(1)
                .map(kIndexRecord->
                    new SpaceWeatherResponse.GeomagneticReading(
                            kIndexRecord.getTimeTag(),
                            kIndexRecord.getKp(),
                            kIndexRecord.getARunning(),
                            kIndexRecord.getStationCount()
                ))
                .filter(r -> r.timeTag() != null
                        && r.kp() != null
                        && r.aRunning() != null
                        && r.stationCount() != null)
                .limit(4)
                .toList();

        if (readings.isEmpty()) {
            throw new IllegalStateException("NOAA returned no valid Kp readings");
        }
        return new SpaceWeatherResponse.GeomagneticConditions(readings);
    }

    public SpaceWeatherResponse.RadiationConditions fetchRadiation() {
        List<Map<String, Object>> rows = radiationClient.getProtonReadings();
        if (rows == null) {
            throw new IllegalStateException("No NOAA proton readings");
        }

        List<SpaceWeatherResponse.RadiationReading> readings = rows.stream()
                .filter(r -> r.get("flux") instanceof Number)
                .filter(r -> r.get("energy") != null)
                .map(r -> new SpaceWeatherResponse.RadiationReading(
                        Objects.toString(r.get("time_tag"), null),
                        parseInteger(r.get("satellite")),
                        Objects.toString(r.get("energy"), null),
                        ((Number) r.get("flux")).doubleValue(),
                        "pfu"
                ))
                .filter(r -> r.timeTag() != null)
                .toList();

        if (readings.isEmpty()) {
            throw new IllegalStateException("NOAA returned no valid proton readings");
        }
        return new SpaceWeatherResponse.RadiationConditions(readings);
    }

    private String classifyFlare(double flux) {
        if (flux >= 1e-4) return "X";
        if (flux >= 1e-5) return "M";
        if (flux >= 1e-6) return "C";
        if (flux >= 1e-7) return "B";
        return "A";
    }

    private Double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (RuntimeException ex) {
            return null;
        }
    }

    private Integer parseInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (RuntimeException ex) {
            return null;
        }
    }

    private Integer parseInteger(Object value) {
        try {
            return value instanceof Number number
                    ? number.intValue()
                    : Integer.parseInt(value.toString());
        } catch (RuntimeException ex) {
            return null;
        }
    }
}
