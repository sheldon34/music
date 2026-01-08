// package com.example.music.security;
// import org.springframework.stereotype.Component;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.util.StringUtils;
// import org.springframework.web.filter.OncePerRequestFilter;

// import java.io.IOException;
// import java.util.List;
// import java.util.stream.Collectors;
// @Component
// @Slf4j
// public class JWTAuthentificationFilter extends OncePerRequestFilter {
// @Autowired
// private JWTGenerator tokenGenerator;
// @Autowired
// private UserDetailService userDetailService;



//     @Override
//     protected void doFilterInternal(HttpServletRequest request,
//                                     HttpServletResponse response,
//                                     FilterChain filterChain)
//             throws ServletException, IOException {
//         try{
//         String token=getJWTFromRequest(request);
//         if(StringUtils.hasText(token)&&tokenGenerator.validateToken(token)){
// //            String username=tokenGenerator.getUsernameFromJWT((token));

//             Claims claims= Jwts.parser()
//                     .setSigningKey(SecurityConstants.JWT_SECRET)
//                     .parseClaimsJws(token)
//                     .getBody();
//             String username=claims.getSubject();
//             List<String> roles=claims.get("roles", List.class);
//             List<GrantedAuthority> authorities=roles !=null ?
//                     roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()):
//                     List.of();
//             UserDetails userDetails=userDetailService.loadUserByUsername(username);
//             UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails,
//                     null,authorities);
// authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//             SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//         }

//         log.info("Jwt header: {}",request.getHeader("Authorization"));
//         log.info("Jwt username: {}",SecurityContextHolder.getContext().getAuthentication());

// filterChain.doFilter(request,response);}
//         catch (Exception e){
//             log.error("jwt filter error ",e);
//         }
//     }
//     private String getJWTFromRequest(HttpServletRequest request){
//         String bearerToken=request.getHeader("Authorization");
//         if(StringUtils.hasText(bearerToken)&& bearerToken.startsWith("Bearer ")){
//             return bearerToken.substring(7);
//         }
//         return null;
//     }
// }
package com.example.music.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class JWTAuthentificationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTGenerator tokenGenerator;

    @Autowired
    private UserDetailService userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getJWTFromRequest(request);

            if (StringUtils.hasText(token) && tokenGenerator.validateToken(token)) {
                
                // ✅ 1. Get username using the Generator (Clean & Reusable)
                String username = tokenGenerator.getUsernameFromJWT(token);

                // ✅ 2. Load user details from DB
                UserDetails userDetails = userDetailService.loadUserByUsername(username);

                // ✅ 3. Create Auth Token using authorities from the UserDetails
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities() // Uses authorities from the DB entity
                );

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

            // Optional: Log purely for debugging (remove in production if too noisy)
            // log.info("Jwt header: {}", request.getHeader("Authorization"));
            // log.info("Jwt username: {}", SecurityContextHolder.getContext().getAuthentication());

            filterChain.doFilter(request, response);

        } catch (Exception e) {
            log.error("Cannot set user authentication: {}", e.getMessage());
            // It is often safer to let the filter chain continue or return an error response here,
            // depending on how strict your security needs to be.
        }
    }

    private String getJWTFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
