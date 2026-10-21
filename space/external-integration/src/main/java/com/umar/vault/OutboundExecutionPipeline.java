//package com.umar.vault;
//
//
//
//import com.umar.model.ProviderCredentialSecret;
//import org.bouncycastle.jcajce.provider.config.ProviderConfiguration;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//@Service
//public class OutboundExecutionPipeline {
//
//    private final VaultCredentialManager vaultManager;
//    private final RestTemplate restTemplate;
//
//    public OutboundExecutionPipeline(VaultCredentialManager vaultManager, RestTemplate restTemplate) {
//        this.vaultManager = vaultManager;
//        this.restTemplate = restTemplate;
//    }
//
//    public String executeExternalCall(ProviderConfiguration config, String subPath) {
//        // 1. Resolve Vault token references dynamically at runtime invocation points
////        ProviderCredentialSecret credentials = vaultManager.fetchCredentials(config.getVaultCredentialPath())
////                .orElseThrow(() -> new IllegalStateException("Authorization token missing from Vault backend layer."));
////
////        // 2. Inject transient credentials cleanly into HTTP Header scopes
////        HttpHeaders headers = new HttpHeaders();
////        headers.set("X-API-KEY", credentials.getApiKey());
////        headers.setBearerAuth(credentials.getAuthToken());
////
////        HttpEntity<Void> entity = new HttpEntity<>(headers);
////        String targetUrl = config.getBaseUrl() + subPath;
//        return null;
//        // 3. Fire request to external provider (Protected by Resilience4j boundaries externally)
////        return restTemplate.exchange(targetUrl, HttpMethod.GET, entity, String.class).getBody();
//    }
//}
