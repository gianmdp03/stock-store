package com.stockstore.stockstore.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Desactiva la protección CSRF (común para APIs REST en desarrollo)
                .csrf(AbstractHttpConfigurer::disable)
                // Permite todas las peticiones sin login
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

        return http.build();
    }
}