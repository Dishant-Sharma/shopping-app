package com.shoppingapp.user.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/auth/v1/register", "/auth/v1/login").permitAll()
//                                .requestMatchers("/auth/**", // allow register/login/logout
//                                        "/api/v1/users/me/**").permitAll()
//                        .anyRequest().authenticated()
                        .anyRequest().permitAll()
                );

        return http.build();
    }

}
