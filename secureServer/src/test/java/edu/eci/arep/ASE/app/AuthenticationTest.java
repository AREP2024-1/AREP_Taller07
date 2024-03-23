package edu.eci.arep.ASE.app;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import edu.eci.arep.ASE.app.domain.Login;
import edu.eci.arep.ASE.app.persistence.GestionLogin;
import edu.eci.arep.ASE.app.service.Authentication;
import edu.eci.arep.ASE.app.service.Encryption;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthenticationTest {

    private Authentication authentication;
    private Encryption encryption;
    private GestionLogin gestionLogin;
 
    @Before
    public void setUp(){
        gestionLogin = mock(GestionLogin.class);
        encryption = mock(Encryption.class);
        authentication = new Authentication(gestionLogin, encryption);
    }
 
    @Test
    public void shouldBeAuthenticated(){
        Login login = new Login("prueba", "1234");
        when(encryption.hashString(login.getPassword())).thenReturn("1234");
        when(gestionLogin.search(login.getUsername())).thenReturn(new Document("_id", login.getUsername())
                                                .append("password", login.getPassword()));
        boolean res = authentication.isAuthenticated(login);
        assertTrue(res);                                    
 
    }
 
    @Test
    public void shouldNotBeAuthenticatedWithNullDocument(){
        Login login = new Login("", "1234");
        when(encryption.hashString(login.getPassword())).thenReturn("1234");
        when(gestionLogin.search(login.getUsername())).thenReturn(null);
        boolean res = authentication.isAuthenticated(login);
        assertFalse(res);                                    
 
    }
 
    @Test
    public void shouldNotBeAuthenticatedWithIncorrectPassword(){
        Login login = new Login("prueba", "1234");
        Login login2 = new Login("prueba", "pass");
 
        when(encryption.hashString(login.getPassword())).thenReturn("1234");
        when(gestionLogin.search(login.getUsername())).thenReturn(new Document("_id", login.getUsername())
                                                .append("password", login2.getPassword()));
        boolean res = authentication.isAuthenticated(login);
        assertFalse(res);                                    
 
    }
     
}
