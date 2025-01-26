package com.example.equipo_c23_94_webapp.Dtos;

public class AuthenticationRes {
    private String jwt;
    private Long expiration;

    public AuthenticationRes(Long expiration, String jwt) {
        this.jwt = jwt;
        this.expiration = expiration;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }
}
