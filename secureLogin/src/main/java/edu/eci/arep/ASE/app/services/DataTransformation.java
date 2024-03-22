package edu.eci.arep.ASE.app.services;

import edu.eci.arep.ASE.app.domain.Login;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DataTransformation {

    public Login stringToLogin(String data) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        Login readMapper = mapper.readValue(data, Login.class);
        return readMapper;
    }
    
}
