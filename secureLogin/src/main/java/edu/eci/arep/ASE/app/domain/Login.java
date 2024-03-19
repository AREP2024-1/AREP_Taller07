package edu.eci.arep.ASE.app.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Login {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
}
