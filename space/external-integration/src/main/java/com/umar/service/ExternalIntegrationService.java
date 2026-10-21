package com.umar.service;

import com.umar.exchange.CelesTrakFeignClient;
import com.umar.hook.FallbackDataSourceHook;
import com.umar.hook.ResponseNormalizationHook;
import com.umar.payload.response.external.CurrentWeatherResponse;
import com.umar.payload.response.external.TleData;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class ExternalIntegrationService implements IExternalIntegrationService{


    private  final RedisTemplate<String, String> redisTemplate;
    private  final Logger log = LoggerFactory.getLogger(ExternalIntegrationService.class);
    private  final String CACHE_KEY_PREFIX = "tle:norad:";
    private  final long CACHE_TTL_HOURS = 6;
    private  final CelesTrakFeignClient celesTrakFeignClient;
    private final ResponseNormalizationHook responseNormalizationHook;
    @Autowired(required = false)
    private  FallbackDataSourceHook fallbackDataSourceHook;

    @Override
    @CircuitBreaker(name = "liveTle",fallbackMethod = "liveTleFallback")
    public TleData getTleForSatellite(String nordId) {
        String cacheKey = CACHE_KEY_PREFIX.concat(nordId);
        String tleData = redisTemplate.opsForValue().get(cacheKey);
        if (tleData != null) {
            log.info("Cache hit for NORAD ID: {}", nordId);
            return responseNormalizationHook.parse(tleData,nordId);
        }
        String rawFormat =   celesTrakFeignClient.fetchLiveTle(nordId);
        log.debug("Cache hit for NORAD ID: {}", nordId);
        TleData tleData1 =responseNormalizationHook.parse(rawFormat,nordId);
        tleData1.setFromCache(false);
        tleData1.setStale(false);
        redisTemplate.opsForValue().set(cacheKey, tleData1.toString(),Duration.ofHours(CACHE_TTL_HOURS));
        return tleData1;

    }

    @Override
    @CircuitBreaker(name = "currentWeather",fallbackMethod = "currentWeatherFallback")
    public CurrentWeatherResponse getCurrentWeather() {
        String cacheKey = "current-weather";
        String weatherData = redisTemplate.opsForValue().get(cacheKey);
        if (weatherData != null) {
            log.info("Cache hit for current weather");
            return null;
        }
        return null;
    }

    private TleData liveTleFallback(String nordId,Exception e){
        log.info("--------------------------------------------------");
        log.info("Fallback method called for NORAD ID: {}", nordId);
        log.info("Exception: {}", e.getMessage());
        log.info("--------------------------------------------------");
        String cacheKey = CACHE_KEY_PREFIX.concat(nordId);
        String tleData = redisTemplate.opsForValue().get(cacheKey);
        if (tleData != null) {
            log.info("Cache hit for NORAD  ID: {}", nordId);
            return responseNormalizationHook.parse(tleData,nordId);
        }
        return new TleData("1","test","test1",Boolean.TRUE,Boolean.FALSE);
    }

    private CurrentWeatherResponse currentWeatherFallback(Exception e){
        log.info("--------------------------------------------------");
        log.info("Fallback method called for current weather");
        log.info("Exception: {}", e.getMessage());
        log.info("--------------------------------------------------");
        return null;
    }

}
