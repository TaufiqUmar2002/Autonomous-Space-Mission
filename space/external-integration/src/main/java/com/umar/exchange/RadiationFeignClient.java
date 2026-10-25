package com.umar.exchange;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@FeignClient(
        name = "radiationClient",
        url = "${noaa.base-url}",
        configuration = FeignClientInterceptor.class
)
public interface RadiationFeignClient {

    @GetMapping("/json/goes/primary/integral-protons-1-day.json")
    List<Map<String, Object>> getProtonReadings();
}
