package com.example.music.Security;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service // FIX 1: Added so Controller can find it
public class AuthOService {

    @Value("${okta.oauth2.issuer}") // FIX 2: Added '$'
    private String issuer;

    @Value("${okta.oauth2.client-id}")
    private String clientId;

    @Value("${okta.oauth2.client-secret}")
    private String clientSecret;

    private final RestTemplate restTemplate = new RestTemplate();

    // Caching fields to prevent spamming the token endpoint
    private String cachedAccessToken;
    private Instant tokenExpiry;

    /**
     * Helper to ensure URLs join correctly regardless of trailing slashes
     */
    private String getApiUrl(String path) {
        String base = issuer.endsWith("/") ? issuer : issuer + "/";
        return base + path;
    }

    private synchronized String getManagementToken() {
        // FIX 3: Simple Caching. If we have a valid token, reuse it.
        if (cachedAccessToken != null && tokenExpiry != null && Instant.now().isBefore(tokenExpiry)) {
            return cachedAccessToken;
        }

        // Assuming Auth0 structure based on your endpoints
        Map<String, String> body = Map.of(
                "client_id", clientId,
                "client_secret", clientSecret,
                "audience", getApiUrl("api/v2/"), // Auth0 Audience usually requires trailing slash
                "grant_type", "client_credentials"
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(
                    getApiUrl("oauth/token"),
                    entity,
                    Map.class
            );

            Map responseBody = response.getBody();
            if (responseBody != null) {
                this.cachedAccessToken = (String) responseBody.get("access_token");
                // Set expiry to slightly less than actual (e.g., 86400 seconds) to be safe
                Integer expiresIn = (Integer) responseBody.getOrDefault("expires_in", 3600);
                this.tokenExpiry = Instant.now().plusSeconds(expiresIn - 60);
                return this.cachedAccessToken;
            }
        } catch (Exception e) {
            System.err.println("Error fetching management token: " + e.getMessage());
            throw new RuntimeException("Failed to authenticate with Identity Provider");
        }
        return null;
    }

    public void deleteUser(String userId) {
        String token = getManagementToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        restTemplate.exchange(
                getApiUrl("api/v2/users/" + userId),
                HttpMethod.DELETE,
                entity,
                Void.class
        );
    }

    public Map<String, Object> updateUser(String userId, Map<String, Object> updates) {
        String token = getManagementToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(updates, headers);

        // FIX 4: Auth0/Okta usually requires PATCH for updates, not POST
        ResponseEntity<Map> response = restTemplate.exchange(
                getApiUrl("api/v2/users/" + userId),
                HttpMethod.PATCH,
                entity,
                Map.class
        );
        return response.getBody();
    }

    public Map<String, Object> getUserByEmail(String email) {
        String token = getManagementToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        // Uses ParameterizedTypeReference to avoid raw List.class warnings
        ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                getApiUrl("api/v2/users-by-email?email=" + email),
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {}
        );

        if (response.getBody() != null && !response.getBody().isEmpty()) {
            return response.getBody().get(0);
        }
        return null;
    }
}