package com.cts.authorization.model;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {

    private final String jwttoken;

    public AuthenticationResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }
}