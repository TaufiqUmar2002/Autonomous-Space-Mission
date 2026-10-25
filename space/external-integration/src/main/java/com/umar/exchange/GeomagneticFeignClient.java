package com.umar.exchange;

import com.umar.payload.response.external.KIndexRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "geomagneticClient",configuration = FeignClientInterceptor.class,  url = "${noaa.base-url}")
public interface GeomagneticFeignClient {

    @GetMapping("/products/noaa-planetary-k-index.json")
    List<KIndexRecord> getKpReadings();

}
