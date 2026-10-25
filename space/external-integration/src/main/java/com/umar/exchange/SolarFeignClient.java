package com.umar.exchange;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@FeignClient(name = "solarClient",configuration = FeignClientInterceptor.class,  url = "${noaa.base-url}")
public interface SolarFeignClient  {

    @GetMapping("/json/goes/primary/xrays-1-day.json")
    List<Map<String, Object>> getXrayReadings();

}
