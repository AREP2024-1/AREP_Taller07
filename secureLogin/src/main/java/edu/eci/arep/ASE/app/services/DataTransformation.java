package edu.eci.arep.ASE.app.services;

import edu.eci.arep.ASE.app.domain.Login;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataTransformation {

    public Login stringToLogin(String data) {
        ObjectMapper mapper = new ObjectMapper();
        Login readMapper = mapper.readValue(login, Login.class);
        return readMapper;
    }
    
}
