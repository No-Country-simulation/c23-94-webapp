package com.example.equipo_c23_94_webapp.controller;


import com.example.equipo_c23_94_webapp.exceptions.DuplicatedException;
import com.example.equipo_c23_94_webapp.exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGlobalException(Exception exception, HttpServletRequest request) {
        ZoneId zoneId = ZoneId.of("America/Buenos_Aires");
        LocalDateTime timestamp = LocalDateTime.now(zoneId);

        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("method", request.getMethod());
        response.put("message", "Error Interno del Servidor");
        response.put("errorDetails", exception.getMessage());
        response.put("timestamp", timestamp);
        response.put("data", null);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFoundException(NotFoundException exception, HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.NOT_FOUND.value());
        response.put("method", request.getMethod());
        response.put("message", "Elemento no encontrado");
        response.put("errorDetails", exception.getMessage());
        response.put("timestamp", LocalDateTime.now(ZoneId.of("America/Buenos_Aires")));
        response.put("data", null);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(DuplicatedException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicatedException(DuplicatedException exception, HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.CONFLICT.value());
        response.put("method", request.getMethod());
        response.put("message", "Elemento duplicado");
        response.put("errorDetails", exception.getMessage());
        response.put("timestamp", LocalDateTime.now(ZoneId.of("America/Buenos_Aires")));
        response.put("data", null);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }


}


