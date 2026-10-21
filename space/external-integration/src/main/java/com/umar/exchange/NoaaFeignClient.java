package com.umar.exchange;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "NOAA",configuration = FeignClientInterceptor.class,  url = "${noaa.api.url:https://api.weather.gov}")
public interface NoaaFeignClient {
}
