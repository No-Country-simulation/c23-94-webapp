package com.example.equipo_c23_94_webapp.services.auth;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.equipo_c23_94_webapp.entity.Users;
import com.example.equipo_c23_94_webapp.repository.UsersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsersRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authHeader.split(" ")[1];

        try {
            authenticateWithJwt(jwt);
        } catch (Exception e) {
            handleAuthenticationError(response, e);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void authenticateWithJwt(String jwt) throws Exception {
        if (!jwtService.isValidToken(jwt)) {
            throw new Exception("Invalid JWT Token");
        }

        String username = jwtService.extractUsername(jwt);
        Optional<Users> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new Exception("User not found");
        }

        Users user = userOptional.get();
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private void handleAuthenticationError(HttpServletResponse response, Exception e) throws IOException {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", "Error");
        errorResponse.put("message", e.getMessage());
        errorResponse.put("code", HttpStatus.UNAUTHORIZED.value());

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getWriter(), errorResponse);
    }
}
