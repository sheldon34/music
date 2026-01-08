// package com.example.music.security;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.stereotype.Component;

// import java.util.Collection;
// import java.util.Date;
// import java.util.List;
// import java.util.stream.Collectors;

// @Slf4j
// @Component
// public class JWTGenerator {
//     public String generateToken(Authentication authentication){

//         String username=authentication.getName();
//         Date currentDate=new Date();
//         Date expiryDate= new Date(currentDate.getTime()+SecurityConstants.JWT_EXPIRATION);

//         Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//      List<String> roles = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
// //



// ///  seting up the token
//     String token= Jwts.builder()
//             .setSubject(username)
//             .claim("roles",roles)
//             .setIssuedAt(currentDate)
//             .setExpiration(expiryDate)
//             // .signWith(SignatureAlgorithm.HS512,SecurityConstants.JWT_SECRET)
//           .signWith(SignatureAlgorithm.HS512, SecurityConstants.JWT_SECRET.getBytes()) // Ensure valid secret

//             .compact();

// return token;


//     }
//     //geting and validating data from the user token

//     public String getUsernameFromJWT(String token){
//         Claims claims= Jwts.parser()
//                 .setSigningKey(SecurityConstants.JWT_SECRET)
//                 .parseClaimsJws(token)
//                 .getBody();
//         return claims.getSubject();
//     }
//     //validating the token
//     public boolean validateToken(String token){
//         try{
//             Jwts.parser().setSigningKey(SecurityConstants.JWT_SECRET).parseClaimsJws(token);
//             return true;
//         }catch (Exception e){
//             throw new AuthenticationCredentialsNotFoundException("jwt was incorrect or exipired");
//         }
//     }
// }
package com.example.music.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys; // Import this
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JWTGenerator {

    // Helper method to ensure the key is generated correctly from your string
    private Key getSignInKey() {
        byte[] keyBytes = SecurityConstants.JWT_SECRET.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expiryDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // FIX: Use getSignInKey() and switch arguments to (Key, Algorithm) or just (Key)
        String token = Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .setIssuedAt(currentDate)
                .setExpiration(expiryDate)
                .signWith(getSignInKey(), SignatureAlgorithm.HS512) 
                .compact();

        return token;
    }

    public String getUsernameFromJWT(String token) {
        // FIX: Use parserBuilder() for version 0.11.5
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            // FIX: Use parserBuilder() here as well
            Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            // It's good practice to log the specific error for debugging
            log.error("JWT Validation failed: {}", e.getMessage());
            throw new AuthenticationCredentialsNotFoundException("JWT was incorrect or expired");
        }
    }
}
