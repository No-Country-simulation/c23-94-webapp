package com.example.equipo_c23_94_webapp.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.equipo_c23_94_webapp.repository.UsersRepository;


@Component
@Configuration
public class SecurityBeansInjectos {
     @Autowired
    private UsersRepository userRepository;

    /**
     * Creates and configures an {@link AuthenticationManager} bean.
     *
     * @param authenticationConfiguration the configuration object for
     *                                    authentication
     * @return the configured {@link AuthenticationManager} instance
     * @throws Exception if an error occurs while retrieving the
     *                   {@link AuthenticationManager}
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Creates and configures an {@link AuthenticationProvider} bean.
     * 
     * This method sets up a {@link DaoAuthenticationProvider} with a custom
     * {@link UserDetailsService} and a password encoder. The configured
     * {@link AuthenticationProvider} is then returned for use in the
     * authentication process.
     * 
     * @return an {@link AuthenticationProvider} configured with a
     *         {@link UserDetailsService} and a password encoder.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    /**
     * Provides a PasswordEncoder bean that uses BCrypt hashing algorithm.
     * 
     * @return a BCryptPasswordEncoder instance for encoding passwords.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    /**
     * Provides a UserDetailsService bean that loads user-specific data.
     * 
     * @return a UserDetailsService that retrieves user details based on the
     *         username.
     * @throws RuntimeException if the user is not found.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            return userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));
        };
    }
}
