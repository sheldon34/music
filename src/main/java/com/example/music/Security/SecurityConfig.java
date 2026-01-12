package com.example.music.Security;//package com.example.music.Security;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Stream;
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//    @Value("${okta.oauth2.client-secret}")
//    private  String AuthSecrete;
//    @Value("${okta.oauth2.issuer}")
//    private  String issuer;
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//                .sessionManagement(session->
//                                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(auth->auth
//                        .requestMatchers("/api/auth/**").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/api/merchandise/getAll").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/api/track/getAll").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/api/events/getAll").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/api/events/create").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.POST, "/api/merchandise/upload").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.POST, "/api/track/upload").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.PATCH, "/api/events/update/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.PATCH, "/api/merchandise/update/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.PATCH, "/api/track/update/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/api/events/delete/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/api/merchandise/delete/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/api/track/delete/**").hasRole("ADMIN")
//
//                        .anyRequest().authenticated()
//                                        )
//// 5. OAuth2 Resource Server (Validates JWT)
//                .oauth2ResourceServer(oauth2 -> oauth2
//                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())));
//
//
//        return http.build();
//    }
//    @Bean
//    public JwtAuthenticationConverter jwtAuthenticationConverter() {
//        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
//
//        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
//            // A. Standard Scopes (e.g., SCOPE_read)
//            JwtGrantedAuthoritiesConverter scopeConverter = new JwtGrantedAuthoritiesConverter();
//            Collection<GrantedAuthority> scopes = scopeConverter.convert(jwt);
//
//            // B. Custom Roles from "roles" claim
//            List<String> roles = jwt.getClaimAsStringList("roles");
//
//            if (roles == null) {
//                return scopes; // Return just scopes if no roles found
//            }
//
//            List<SimpleGrantedAuthority> roleAuthorities = roles.stream()
//                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role)) // e.g. ROLE_ADMIN
//                    .toList();
//
//            // C. Combine Scopes + Roles
//            return Stream.concat(scopes.stream(), roleAuthorities.stream())
//                    .toList();
//        });
//
//        return converter;
//    }
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        // Allow your frontend origin (CHANGE THIS to your actual frontend URL)
////        configuration.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:4200",""));
//        configuration.setAllowedMethods(List.of("GET", "POST", "PATCH","PUT", "DELETE", "OPTIONS"));
//        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
//        configuration.setAllowedOriginPatterns(List.of(
//                "http://localhost:3000",
//                "http://localhost:5173",
//                "https://music-f-t.vercel.app/",
//                "https://music-f-a5xu7642c-sheldon34s-projects.vercel.app",
//                "https://*.vercel.app"
//        ));
//        configuration.setAllowCredentials(true); // Needed if you use Authorization header
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//    @Bean
//    public JwtDecoder jwtDecoder() {
//        // JWKS endpoint: e.g. https://{yourOktaDomain}/oauth2/default/v1/keys
//        String jwkSetUri = issuer.endsWith("/") ? issuer + "v1/keys" : issuer + "/v1/keys";
//        return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
//    }
//}


//package com.example.music.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    /**
     * Must match the claim you set in your Auth0 Action:
     * api.accessToken.setCustomClaim("https://music-app/roles", roles)
     */
    @Value("${security.jwt.roles-claim:https://music-app/roles}")
    private String rolesClaim;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/merchandise/getAll").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/track/getAll").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/events/getAll").permitAll()

                        .requestMatchers(HttpMethod.POST, "/api/events/create").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/merchandise/upload").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/track/upload").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PATCH, "/api/events/update/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/merchandise/update/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/track/update/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.DELETE, "/api/events/delete/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/merchandise/delete/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/track/delete/**").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
                )
                .build();
    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter scopes = new JwtGrantedAuthoritiesConverter();
        // scopes.setAuthorityPrefix("SCOPE_"); // default

        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            Collection<GrantedAuthority> scopeAuthorities = scopes.convert(jwt);

            List<String> roles = jwt.getClaimAsStringList(rolesClaim);
            if (roles == null || roles.isEmpty()) {
                return scopeAuthorities;
            }

            List<SimpleGrantedAuthority> roleAuthorities = roles.stream()
                    .filter(r -> r != null && !r.isBlank())
                    .map(r -> "ROLE_" + r.trim().toUpperCase(Locale.ROOT)) // ADMIN -> ROLE_ADMIN
                    .map(SimpleGrantedAuthority::new)
                    .toList();

            return Stream.concat(scopeAuthorities.stream(), roleAuthorities.stream()).toList();
        });

        return converter;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOriginPatterns(List.of(
                "http://localhost:3000",
                "http://localhost:5173",
                "https://music-f-t.vercel.app",
                "https://music-f-a5xu7642c-sheldon34s-projects.vercel.app",
                "https://*.vercel.app"
        ));

        configuration.setAllowedMethods(List.of("GET", "POST", "PATCH", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}