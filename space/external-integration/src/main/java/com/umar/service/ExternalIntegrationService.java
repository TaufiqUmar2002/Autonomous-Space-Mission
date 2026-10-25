package com.umar.service;

import com.umar.exception.SpaceWeatherUnavailableException;
import com.umar.exchange.CelesTrakFeignClient;
import com.umar.hook.FallbackDataSourceHook;
import com.umar.hook.ResponseNormalizationHook;
import com.umar.payload.response.external.CurrentWeatherResponse;
import com.umar.payload.response.external.SpaceWeatherResponse;
import com.umar.payload.response.external.TleData;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class ExternalIntegrationService implements IExternalIntegrationService{


    private final CircuitBreakerRegistry registry;
    private  final RedisTemplate<String, String> redisTemplate;
    private  final Logger log = LoggerFactory.getLogger(ExternalIntegrationService.class);
    private  final String CACHE_KEY_PREFIX = "tle:norad:";
    private  final long CACHE_TTL_HOURS = 6;
    private final StringRedisTemplate redis;
    private final ObjectMapper mapper;
    private static final String CURRENT_KEY = "space-weather:current";
    private static final String LAST_GOOD_KEY = "space-weather:last-good";
    private  final CelesTrakFeignClient celesTrakFeignClient;
    private final ResponseNormalizationHook responseNormalizationHook;
    private static final Duration CACHE_TTL = Duration.ofMinutes(30);
    private static final Duration LAST_GOOD_TTL = Duration.ofDays(7);
    public final NoaaSpaceWeatherProvider provider;
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
    public SpaceWeatherResponse getCurrentWeather() {
        Result<SpaceWeatherResponse.SolarConditions> solar = this.load("solar", SpaceWeatherResponse.SolarConditions.class, provider::fetchSolar);
        Result<SpaceWeatherResponse.GeomagneticConditions> geomagnetic = load("geomagnetic", SpaceWeatherResponse.GeomagneticConditions.class, provider::fetchGeomagnetic);
        Result<SpaceWeatherResponse.RadiationConditions> radiation = load("radiation", SpaceWeatherResponse.RadiationConditions.class, provider::fetchRadiation);
        boolean stale =
                solar.stale()
                        || geomagnetic.stale()
                        || radiation.stale();

        String source = stale
                ? "MIXED"
                : solar.cached()
                && geomagnetic.cached()
                && radiation.cached()
                ? "CACHED"
                : "FRESH";

        return new SpaceWeatherResponse(
                solar.data(),
                geomagnetic.data(),
                radiation.data(),
                source,
                stale
        );
    }

    @Override
    public void getWeatherHistory(LocalDateTime startDate, LocalDateTime endDate) {

    }

    @Override
    public void getNearEarthObjects() {

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

    private <T> Result<T> load(String category, Class<T> type, Supplier<T> fetcher) {
        String currentKey = "space-weather:" + category;
        String lastGoodKey = currentKey + ":last-good";
        T cached = read(currentKey, type);
        if (cached != null) {
            return new Result<>(cached,false, true);
        }
        try {
            T fresh =  registry.circuitBreaker(category).executeSupplier(fetcher);
            writeSafely(currentKey, fresh, CACHE_TTL);
            writeSafely(lastGoodKey, fresh, LAST_GOOD_TTL);
            return new Result<>(fresh, false, false);

        } catch (Exception providerFailure) {
            T lastGood = read(lastGoodKey, type);
            if (lastGood != null) {
                return new Result<>(lastGood, true, false);
            }
            throw new SpaceWeatherUnavailableException("No current or cached " + category + " data is available");
        }
    }

    private <T> T read(String key, Class<T> type) {
        try {
            String json = redis.opsForValue().get(key);
            return json == null ? null : mapper.readValue(json, type);
        } catch (Exception cacheFailure) {
            return null;
        }
    }

    private <T> void writeSafely(
            String key, T value, Duration ttl) {
        try {
            String json = mapper.writeValueAsString(value);
            redis.opsForValue().set(key, json, ttl);
        } catch (Exception cacheFailure) {
            // Log this in production; do not fail a successful fetch.
        }
    }

    private record Result<T>(T data, boolean stale, boolean cached) {

    }

}
