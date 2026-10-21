package com.umar.payload.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AuthLoginRequest {

    private String authorizationCode;

    private String codeVerifier;

    private String redirectUri;

    private String clientId;

    private String ipAddress;

    private String deviceFingerprint;

}
