package com.example.music.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))                                .sessionManagement(session->
                                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                        .authorizeHttpRequests(auth->auth
                                                .requestMatchers("/api/events/upload/**").hasRole("ADMIN")
                                                .requestMatchers("/api/events/update/**").hasRole("ADMIN")
                                                        .requestMatchers("/api/merchandise/upload/**").hasRole("ADMIN")
                                                        .requestMatchers("/api/merchandise/update/**").hasRole("ADMIN")
                                                        .requestMatchers("/api/track/upload/**").hasRole("ADMIN")
                                                        .requestMatchers("/api/track/update/**").hasRole("ADMIN")
                                                        .requestMatchers("/api/events/delete/**").hasRole("ADMIN")
                                                        .requestMatchers("/api/merchandise/delete/**").hasRole("ADMIN")
                                                        .requestMatchers("/api/track/delete/**").hasRole("ADMIN")
                                                .anyRequest().permitAll()
                                        )
// 5. OAuth2 Resource Server (Validates JWT)
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())));


        return http.build();
    }
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            // A. Standard Scopes (e.g., SCOPE_read)
            JwtGrantedAuthoritiesConverter scopeConverter = new JwtGrantedAuthoritiesConverter();
            Collection<GrantedAuthority> scopes = scopeConverter.convert(jwt);

            // B. Custom Roles from "roles" claim
            List<String> roles = jwt.getClaimAsStringList("roles");

            if (roles == null) {
                return scopes; // Return just scopes if no roles found
            }

            List<SimpleGrantedAuthority> roleAuthorities = roles.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role)) // e.g. ROLE_ADMIN
                    .toList();

            // C. Combine Scopes + Roles
            return Stream.concat(scopes.stream(), roleAuthorities.stream())
                    .toList();
        });

        return converter;
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Allow your frontend origin (CHANGE THIS to your actual frontend URL)
        configuration.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:4200"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true); // Needed if you use Authorization header

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

