package com.example.equipo_c23_94_webapp.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.equipo_c23_94_webapp.entity.Permission;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) // Habilita las anotaciones @PreAuthorize
public class SecurityFilter {

        @Autowired
        private AuthenticationProvider authenticationProvider;

        @Autowired
        private JwtAuthenticationFilter jwtAuthenticationFilter;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

                http
                                .cors(Customizer.withDefaults())
                                .csrf(csrfConfig -> csrfConfig.disable())
                                .sessionManagement(
                                                sessionMangConfig -> sessionMangConfig
                                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authenticationProvider(authenticationProvider)
                                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                                .authorizeHttpRequests(authConfig -> {
                                        authConfig.requestMatchers(HttpMethod.POST, "/api/v1/login").permitAll();
                                        authConfig.requestMatchers(HttpMethod.POST, "/api/v1/register").permitAll();


                                        authConfig.requestMatchers(HttpMethod.GET, "/api/v1/authors")
                                        .hasAuthority(Permission.READ_ALL_AUTHORS.name());
                                        authConfig.requestMatchers(HttpMethod.GET, "/api/v1/authors/{id}")
                                        .hasAuthority(Permission.READ_ONE_AUTHOR.name());
                                        authConfig.requestMatchers(HttpMethod.POST, "/api/v1/authors")
                                        .hasAuthority(Permission.SAVE_ONE_AUTHOR.name());
                                        authConfig.requestMatchers(HttpMethod.PUT, "/api/v1/authors/{id}")
                                        .hasAuthority(Permission.UPDATE_ONE_AUTHOR.name());
                                        authConfig.requestMatchers(HttpMethod.DELETE, "/api/v1/authors/{id}")
                                        .hasAuthority(Permission.DELETE_ONE_AUTHOR.name());
                                       
                                        authConfig.requestMatchers(HttpMethod.GET, "/api/v1/books")
                                        .hasAuthority(Permission.READ_ALL_BOOKS.name());
                                        authConfig.requestMatchers(HttpMethod.GET, "/api/v1/books/{id}")
                                        .hasAuthority(Permission.READ_ONE_BOOK.name());
                                        authConfig.requestMatchers(HttpMethod.POST, "/api/v1/books")
                                        .hasAuthority(Permission.SAVE_ONE_BOOK.name());
                                        authConfig.requestMatchers(HttpMethod.PUT, "/api/v1/books/{id}")
                                        .hasAuthority(Permission.UPDATE_ONE_BOOK.name());
                                        authConfig.requestMatchers(HttpMethod.DELETE, "/api/v1/books/{id}")
                                        .hasAuthority(Permission.DELETE_ONE_BOOK.name());

                                        authConfig.requestMatchers(HttpMethod.GET, "/api/v1/categories")
                                        .hasAuthority(Permission.READ_ALL_CATEGORIES.name());
                                        authConfig.requestMatchers(HttpMethod.GET, "/api/v1/categories/{id}")
                                        .hasAuthority(Permission.READ_ONE_CATEGORY.name());
                                        authConfig.requestMatchers(HttpMethod.POST, "/api/v1/categories")
                                        .hasAuthority(Permission.SAVE_ONE_CATEGORY.name());
                                        authConfig.requestMatchers(HttpMethod.PUT, "/api/v1/categories/{id}")
                                        .hasAuthority(Permission.UPDATE_ONE_CATEGORY.name());
                                        authConfig.requestMatchers(HttpMethod.DELETE, "/api/v1/categories/{id}")
                                        .hasAuthority(Permission.DELETE_ONE_CATEGORY.name());

                                        authConfig.requestMatchers(HttpMethod.GET, "/api/v1/loans")
                                        .hasAuthority(Permission.READ_ALL_LOANS.name());
                                        authConfig.requestMatchers(HttpMethod.GET, "/api/v1/loans/{id}")
                                        .hasAuthority(Permission.READ_ONE_LOAN.name());
                                        authConfig.requestMatchers(HttpMethod.POST, "/api/v1/loans")
                                        .hasAuthority(Permission.SAVE_ONE_LOAN.name());
                                        authConfig.requestMatchers(HttpMethod.PUT, "/api/v1/loans/{id}")
                                        .hasAuthority(Permission.UPDATE_ONE_LOAN.name());
                                        authConfig.requestMatchers(HttpMethod.DELETE, "/api/v1/loans/{id}")
                                        .hasAuthority(Permission.DELETE_ONE_LOAN.name());

                                        authConfig.requestMatchers(HttpMethod.GET, "/api/v1/publishers")
                                        .hasAuthority(Permission.READ_ALL_PUBLISHERS.name());
                                        authConfig.requestMatchers(HttpMethod.GET, "/api/v1/publishers/{id}")
                                        .hasAuthority(Permission.READ_ONE_PUBLISHER.name());
                                        authConfig.requestMatchers(HttpMethod.POST, "/api/v1/publishers")
                                        .hasAuthority(Permission.SAVE_ONE_PUBLISHER.name());
                                        authConfig.requestMatchers(HttpMethod.PUT, "/api/v1/publishers/{id}")
                                        .hasAuthority(Permission.UPDATE_ONE_PUBLISHER.name());
                                        authConfig.requestMatchers(HttpMethod.DELETE, "/api/v1/publishers/{id}")
                                        .hasAuthority(Permission.DELETE_ONE_PUBLISHER.name());

                                        authConfig.requestMatchers(HttpMethod.GET, "/api/v1/reviews")
                                        .hasAuthority(Permission.READ_ALL_REVIEWS.name());
                                        authConfig.requestMatchers(HttpMethod.GET, "/api/v1/reviews/{id}")
                                        .hasAuthority(Permission.READ_ONE_REVIEW.name());
                                        authConfig.requestMatchers(HttpMethod.POST, "/api/v1/reviews")
                                        .hasAuthority(Permission.SAVE_ONE_REVIEW.name());
                                        authConfig.requestMatchers(HttpMethod.PUT, "/api/v1/reviews/{id}")
                                        .hasAuthority(Permission.UPDATE_ONE_REVIEW.name());
                                        authConfig.requestMatchers(HttpMethod.DELETE, "/api/v1/reviews/{id}")
                                        .hasAuthority(Permission.DELETE_ONE_REVIEW.name());

                                        authConfig.requestMatchers(HttpMethod.GET, "/api/v1/users")
                                        .hasAuthority(Permission.READ_ALL_USERS.name());
                                        authConfig.requestMatchers(HttpMethod.GET, "/api/v1/users/{id}")
                                        .hasAuthority(Permission.READ_ONE_USER.name());
                                        authConfig.requestMatchers(HttpMethod.POST, "/api/v1/users")
                                        .hasAuthority(Permission.SAVE_ONE_USER.name());
                                        authConfig.requestMatchers(HttpMethod.PUT, "/api/v1/users/{id}")
                                        .hasAuthority(Permission.UPDATE_ONE_USER.name());
                                        authConfig.requestMatchers(HttpMethod.DELETE, "/api/v1/users/{id}")
                                        .hasAuthority(Permission.DELETE_ONE_USER.name());

                                        authConfig.anyRequest().denyAll();

                                });

                return http.build();

        }
}
