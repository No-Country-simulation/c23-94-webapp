
package com.example.equipo_c23_94_webapp.controller;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.equipo_c23_94_webapp.dto.req.AuthenticationReq;
import com.example.equipo_c23_94_webapp.entity.Users;
import com.example.equipo_c23_94_webapp.exceptions.ResponseHelper;
import com.example.equipo_c23_94_webapp.repository.UsersRepository;
import com.example.equipo_c23_94_webapp.services.auth.AuthenticationService;

@RestController
@CrossOrigin(origins = { "*", "https://localhost/","http://localhost:3000", "http://localhost:8100",
        "https://jmghcf68-8100.use2.devtunnels.ms/" }, methods = { RequestMethod.POST,
                RequestMethod.PUT }, allowedHeaders = { "Authorization", "Content-Type" })
@RequestMapping("/api/v1")
public class AuthController {

    @Autowired
    private UsersRepository userService;

    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("/register") // Register a new user
    public ResponseEntity<ResponseHelper<Object>> register(@Valid @RequestBody Users user) {
        try {
            // if (userService.findByEmail(user.getEmail()) != null) {
            //     return createErrorResponse("Email already exists", HttpStatus.BAD_REQUEST);
            // }

            if (userService.findByUsername(user.getUsername()).isPresent()) {
                return createErrorResponse("Username already exists", HttpStatus.BAD_REQUEST);
            }

            Users createdUser = authenticationService.register(user);
            return createSuccessResponse("Success", createdUser, HttpStatus.OK);
        } catch (Exception e) {
            return createErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login") // Login a user
    public ResponseEntity<ResponseHelper<Object>> authenticate(@RequestBody AuthenticationReq request) {
        try {
            return createSuccessResponse("Success login!", authenticationService.login(request), HttpStatus.OK);
        } catch (Exception e) {
            return createErrorResponse("Credentials are incorrect: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private ResponseEntity<ResponseHelper<Object>> createSuccessResponse(String message, Object data, HttpStatus status) {
        return new ResponseEntity<>(new ResponseHelper<>(message, data), new HttpHeaders(), status);
    }

    private ResponseEntity<ResponseHelper<Object>> createErrorResponse(String message, HttpStatus status) {
        return new ResponseEntity<>(new ResponseHelper<>("Error", status, null, message), new HttpHeaders(), status);
    }

}