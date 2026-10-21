package com.umar.hook;

import com.umar.payload.response.external.TleData;
import org.springframework.stereotype.Component;

@Component
public class DefaultTleParser implements ResponseNormalizationHook{

    @Override
    public TleData parse(String rawFormat, String noradId) {
        if(rawFormat==null || rawFormat.isBlank()){
            throw new IllegalArgumentException("Raw format cannot be null or blank");
        }
        String[] lines = rawFormat.split("\\r?\\n");
        String line1 = lines.length > 0 ? lines[0] : "";
        String line2 = lines.length > 1 ? lines[1] : "";
        return new TleData(noradId, line1, line2, false, false);
    }
}
