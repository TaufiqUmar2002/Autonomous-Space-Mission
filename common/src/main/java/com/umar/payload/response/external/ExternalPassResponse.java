package com.umar.payload.response.external;

import java.time.Instant;
import java.util.List;

public record ExternalPassResponse(Integer noradId, List<PassDto> passes, String source) {

    public record PassDto(Instant aosTime, Instant losTime, Double maxElevationDegrees) {

    }
}
