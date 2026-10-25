package com.umar.exchange;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GlobalErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        log.info("--------GlobalErrorDecoder--------");
        log.info("Method Key: {}", methodKey);
        log.info("Response: {}", response);
        return new Default().decode(methodKey, response);

    }
}
