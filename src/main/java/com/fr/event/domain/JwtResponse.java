package com.fr.event.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private final String role;

    public JwtResponse(String jwttoken, String role) {
        this.jwttoken = jwttoken;
        this.role = role;
    }

    public String getToken() {
        return this.jwttoken;
    }
}
