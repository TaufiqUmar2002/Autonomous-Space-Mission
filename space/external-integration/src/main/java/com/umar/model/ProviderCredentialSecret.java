package com.umar.model;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Carrier object matching the structural secret engine layout in HashiCorp Vault.
 * Vault maps JSON keys directly into this object at runtime.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProviderCredentialSecret {

    private String apiKey;
    private String apiSecret;
    private String authToken;


}
