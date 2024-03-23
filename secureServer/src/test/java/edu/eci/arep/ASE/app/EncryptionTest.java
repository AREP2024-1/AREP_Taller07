package edu.eci.arep.ASE.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import edu.eci.arep.ASE.app.service.Encryption;

public class EncryptionTest {

    @Test
    public void shouldBeHashString(){
        String password = "password1234";
        String expectedPassEncrypted = "b9c950640e1b3740e98acb93e669c65766f6670dd1609ba91ff41052ba48c6f3";
        Encryption encryption = new Encryption();
        String encrypt = encryption.hashString(password);
        assertEquals(expectedPassEncrypted, encrypt);
    }
 
    @Test
    public void shouldNotBeHashString(){
        String contrasena = "password1234";
        String expectedPassEncrypted = "21b28a8518e2bd8c9e8c433e431dece8f363028bf23cee7a3d7e700136cfebdb";
        Encryption encryption = new Encryption();
        String encrypt = encryption.hashString(contrasena);
        assertNotEquals(expectedPassEncrypted, encrypt);
    }
    
}
