package com.store.pos.config;

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
            // 1. Disable CSRF protection because REST APIs don't use browser session cookies
            .csrf(csrf -> csrf.disable())
            
            // 2. Tell the bodyguard who is allowed inside
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/products/**").permitAll() // Let anyone view/create products
                .anyRequest().permitAll() // Allow everything else for our current testing phase
            );

        return http.build();
    }
}