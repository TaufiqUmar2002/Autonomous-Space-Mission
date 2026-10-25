package com.umar.payload.response.external;

import java.util.List;

public record N2yoResponse(Info info, List<Pass> passes) {

    public record Info(Integer satid, String satname, Integer passescount) {

    }

    public record Pass(Long startUTC, Long endUTC, Double maxEl) {

    }
}
