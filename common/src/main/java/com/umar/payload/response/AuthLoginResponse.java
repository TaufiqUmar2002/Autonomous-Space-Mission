package com.umar.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AuthLoginResponse {

    private String accessToken;

    private String refreshToken;

    private String idToken;

    private String tokenType;

    private Long expiresIn;

    private String sessionId;

    private Boolean mfaEnabled;

    private String partialSessionToken;

}
