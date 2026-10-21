package com.umar.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Configuration
public class CircuitBreakerListenerConfig {

    private final CircuitBreakerRegistry registry;
    private  final Logger log = LoggerFactory.getLogger(CircuitBreakerListenerConfig.class);

    public CircuitBreakerListenerConfig(CircuitBreakerRegistry registry) {
        this.registry = registry;
    }

    @PostConstruct
    public void setupCircuitListeners() {
        CircuitBreaker circuitBreaker = registry.circuitBreaker("celestrakApi");
        circuitBreaker.getEventPublisher()
                .onStateTransition(event -> {
                    String from =
                            event.getStateTransition()
                                 .getFromState()
                                 .name();
                    String to =
                            event.getStateTransition()
                                 .getToState()
                                 .name();
                    log.warn("CelesTrak CB changed: {} -> {}", from, to);

                    if ("OPEN".equals(to)) {
                        log.error("CelesTrak is DOWN. Circuit opened.");
                        // publish Kafka event
                    }
                    if ("CLOSED".equals(to)) {
                        log.info("CelesTrak recovered. Circuit closed.");
                        // publish Kafka event
                    }
                });
    }
}
