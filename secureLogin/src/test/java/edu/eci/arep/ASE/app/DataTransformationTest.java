package edu.eci.arep.ASE.app;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import edu.eci.arep.ASE.app.domain.Login;
import edu.eci.arep.ASE.app.services.DataTransformation;

public class DataTransformationTest {

    @Test
    public void shouldTransformStringToLogin() throws IOException{
        DataTransformation dataTransformation = new DataTransformation();
        String dataUser = "{\"username\":\"prueba\",\"password\":\"1234\"}";
        Login login = dataTransformation.stringToLogin(dataUser);
        assertEquals("prueba", login.getUsername());
        assertEquals("1234", login.getPassword());
    }
    
}
