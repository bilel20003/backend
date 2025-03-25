package com.centre.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Désactiver CSRF pour le moment
            .authorizeRequests()
            .requestMatchers("/api/**").permitAll()  // Remplacer antMatchers() par requestMatchers()
            .anyRequest().authenticated() // Toute autre requête doit être authentifiée
            .and()
            .httpBasic();  // Permet une authentification de base (facultatif)
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Encodage du mot de passe
    }
}
