package com.example.equipo_c23_94_webapp.services.auth;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.equipo_c23_94_webapp.dto.AuthenticationRes;
import com.example.equipo_c23_94_webapp.dto.req.AuthenticationReq;
import com.example.equipo_c23_94_webapp.entity.Users;
import com.example.equipo_c23_94_webapp.repository.UsersRepository;

@Service
public class AuthenticationService {
   @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    
    public Users register(Users request) {
        var user = new Users();
        user.setLast_name(request.getLast_name());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        // user.setLoan(request.getLoan());
        user.setRole(request.getRole());
        return userRepository.save(user);
    }

    public AuthenticationRes login(AuthenticationReq authenticationRequest) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword());
        authenticationManager.authenticate(authToken);
        Users user = userRepository.findByUsername(authenticationRequest.getUsername()).get();
        String jwt = jwtService.generateToken(user, generateExtraClaims(user));
        Date expirationDate = jwtService.getExpirationDate(jwt);
        long expirationTimeMillis = expirationDate.getTime() - System.currentTimeMillis();
        long expirationTimeMinutes = expirationTimeMillis / 1000;
        return new AuthenticationRes(expirationTimeMinutes, jwt);
    }

    /**
     * Generates a map of extra claims for a given user.
     *
     * @param user the user for whom the extra claims are being generated
     * @return a map containing the extra claims, including the user's name and role
     */
    private Map<String, Object> generateExtraClaims(Users user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getUsername());
        extraClaims.put("role", user.getRole());
        return extraClaims;
    }
}
