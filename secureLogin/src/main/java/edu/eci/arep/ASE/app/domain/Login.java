package edu.eci.arep.ASE.app.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
public class Login {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonCreator
    public Login(@JsonProperty("username") String username, 
                   @JsonProperty("password") String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
}
