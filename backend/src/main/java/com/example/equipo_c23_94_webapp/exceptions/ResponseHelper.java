package com.example.equipo_c23_94_webapp.exceptions;

import org.springframework.http.HttpStatus;

public class ResponseHelper<T> {
    private String status;
    private HttpStatus httpStatus;
    private T data;
    private String message;

    public ResponseHelper(String status, HttpStatus httpStatus, T data, String message) {
        this.status = status;
        this.httpStatus = httpStatus;
        this.data = data;
        this.message = message;
    }

    public ResponseHelper(String message, T data) {
        this.status = "Success";
        this.httpStatus = HttpStatus.OK;
        this.data = data;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}