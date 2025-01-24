package com.example.equipo_c23_94_webapp.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityFilter {

        @Autowired
        private AuthenticationProvider authenticationProvider;

        @Autowired
        private JwtAuthenticationFilter jwtAuthenticationFilter;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

                http
                                .cors(withDefaults())
                                .csrf(csrfConfig -> csrfConfig.disable())
                                .sessionManagement(
                                                sessionMangConfig -> sessionMangConfig
                                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authenticationProvider(authenticationProvider)
                                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                                .authorizeHttpRequests(authConfig -> {
                                        authConfig.requestMatchers(HttpMethod.POST, "/api/v1/login").permitAll();
                                        authConfig.requestMatchers(HttpMethod.POST, "/api/v1/register").permitAll();
                                        authConfig.anyRequest().denyAll();

                                });

                return http.build();

        }
}
