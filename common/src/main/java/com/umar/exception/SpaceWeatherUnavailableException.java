package com.umar.exception;

public class SpaceWeatherUnavailableException extends RuntimeException {
    public SpaceWeatherUnavailableException(String message) {
        super(message);
    }
}
