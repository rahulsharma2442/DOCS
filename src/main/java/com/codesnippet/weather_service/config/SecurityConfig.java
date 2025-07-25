package com.codesnippet.weather_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.beans.factory.annotation.Autowired;

import com.codesnippet.weather_service.filters.JwtAuthFilter;
import com.codesnippet.weather_service.service.CustomUserDetailService;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    JwtAuthFilter jwtAuthFilter;

    private final CustomUserDetailService customUserDetailService;
    public SecurityConfig(CustomUserDetailService customUserDetailService) {
        this.customUserDetailService = customUserDetailService;
    }


    // for jwt authentication, we need to configure the security filter chain.
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    System.out.println("🛡️ Configuring security filter chain...");

    http
        .csrf(csrf -> {
            csrf.disable();
            System.out.println("✅ CSRF is disabled");
        })
        .authorizeHttpRequests(auth -> {
            auth
                .requestMatchers(HttpMethod.POST, "/authenticate").permitAll();
            System.out.println("✅ Permitting POST /authenticate");

            auth
                .anyRequest().authenticated();
            System.out.println("🔐 All other requests require authentication");
        });

    http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
    System.out.println("✅ JWT filter added before UsernamePasswordAuthenticationFilter");

    return http.build();
}


    @Bean
   public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // authentication manager bean for the simple authentication. validate user using username and password.
    @Bean
    public AuthenticationManager authenticationManager(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(customUserDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(daoAuthenticationProvider);
    }
}
