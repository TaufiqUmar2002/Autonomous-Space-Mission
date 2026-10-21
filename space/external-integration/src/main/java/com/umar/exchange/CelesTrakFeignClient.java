package com.umar.exchange;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "CELESTRACK",configuration = FeignClientInterceptor.class,  url = "${celestrak.api.url:https://celestrak.org}")
public interface CelesTrakFeignClient {


    @GetMapping("/NORAD/elements/gp.php")
    String fetchLiveTle(@RequestParam("CATNR") String nordId);

}
