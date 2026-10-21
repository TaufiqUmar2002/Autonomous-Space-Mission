package com.umar.hook;

import com.umar.payload.response.external.TleData;

public interface FallbackDataSourceHook {
    TleData getFallback(String noradId);
}
