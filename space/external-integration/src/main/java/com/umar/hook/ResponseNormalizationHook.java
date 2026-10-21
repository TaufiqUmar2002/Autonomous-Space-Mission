package com.umar.hook;

import com.umar.payload.response.external.TleData;

public interface ResponseNormalizationHook {
    TleData parse(String rawFormat, String noradId);
}
