//package com.umar.vault;
//
//
//import com.umar.model.ProviderCredentialSecret;
//import org.springframework.stereotype.Service;
//import org.springframework.vault.core.VaultTemplate;
//import org.springframework.vault.support.VaultResponseSupport;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.Optional;
//
//@Service
//public class VaultCredentialManager {
//
//    private static final Logger log = LoggerFactory.getLogger(VaultCredentialManager.class);
//    private final VaultTemplate vaultTemplate;
//
//    public VaultCredentialManager(VaultTemplate vaultTemplate) {
//        this.vaultTemplate = vaultTemplate;
//    }
//
//    /**
//     * Dynamically pulls external provider keys directly from Vault using the database lookup path.
//     * Enforces BR-14 and NFR-04: Secrets are kept strictly in memory and pulled at runtime.
//     *
//     * @param vaultPath The path stored in the database (e.g., "secret/data/providers/celestrak")
//     * @return An Optional containing the transient credentials if found
//     */
//    public Optional<ProviderCredentialSecret> fetchCredentials(String vaultPath) {
//        try {
//            // Read from Vault KV (Key-Value) engine mapping directly to our data model
//            VaultResponseSupport<ProviderCredentialSecret> response =
//                    vaultTemplate.read(vaultPath, ProviderCredentialSecret.class);
//
//            if (response != null && response.getData() != null) {
//                return Optional.of(response.getData());
//            }
//
//            log.warn("Vault secret path lookup returned empty data for path: {}", vaultPath);
//        } catch (Exception e) {
//            // Mask detailed stack trace parameters to prevent logging injection vectors
//            log.error("Failed to retrieve runtime authorization tokens from Vault backend path structural indicators.");
//        }
//        return Optional.empty();
//    }
//}
