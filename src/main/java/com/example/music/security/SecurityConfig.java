//package com.example.music.security;
//
//
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Slf4j
//@Configuration
//@EnableWebSecurity
//@AllArgsConstructor
//
//public class SecurityConfig {
//
//    private  JwtAuthEntryPoint authEntryPoint;
//
//    //@Bean
////    public  AuthenticationSuccessHandler authenticationSuccessHandler(){
////        return new authenticationSucessHandler();
//// }
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http)throws  Exception{
//        http
//                .cors(cors->{})
//                .csrf(csrf->csrf.disable())
//                .exceptionHandling(exception->exception
//                                .authenticationEntryPoint(authEntryPoint)
//                        )
//                .sessionManagement(session->session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
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
//                        .anyRequest().authenticated())
//               // .formLogin(form->form
//                       // .loginPage("/api/auth/login")
//                        //.successHandler(authenticationSuccessHandler())
//                      //  .permitAll())
//                .addFilterBefore(jwtAuthentificationFilter(), UsernamePasswordAuthenticationFilter.class);
//
//     return http.build();
//
//    }
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)throws Exception{
//        return   authenticationConfiguration.getAuthenticationManager();
//    }
//
//    @Bean
//    public JWTAuthentificationFilter jwtAuthentificationFilter(){
//        return new JWTAuthentificationFilter();
//    }
//
//
//
//
//}
