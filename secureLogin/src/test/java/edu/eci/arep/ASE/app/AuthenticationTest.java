package edu.eci.arep.ASE.app;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import edu.eci.arep.ASE.app.apiExternal.HttpClient;
import edu.eci.arep.ASE.app.domain.Login;
import edu.eci.arep.ASE.app.services.Authentication;

public class AuthenticationTest {

    @Test
    public void shouldBeAuthenticated(){
        HttpClient httpClient = Mockito.mock(HttpClient.class);
        when(httpClient.call(anyString(), any(Login.class))).thenReturn("true");
        Authentication authentication = new Authentication("http://localhost:5000/login", httpClient);
        boolean res = authentication.isAuthenticated(new Login("prueba", "1234"));
        assertTrue(res);
    }
 
    @Test
    public void shouldNotBeAuthenticatedwithEmptyLogin(){
        HttpClient httpClient = Mockito.mock(HttpClient.class);
        when(httpClient.call(anyString(), any(Login.class))).thenReturn("false");
        Authentication authentication = new Authentication("http://localhost:5000/login", httpClient);
        boolean res = authentication.isAuthenticated(new Login("", ""));
        assertFalse(res);
    }
 
    @Test
    public void shouldNotBeAuthenticatedwithEmptyUser(){
        HttpClient httpClient = Mockito.mock(HttpClient.class);
        when(httpClient.call(anyString(), any(Login.class))).thenReturn("false");
        Authentication authentication = new Authentication("http://localhost:5000/login", httpClient);
        boolean res = authentication.isAuthenticated(new Login("", "1234"));
        assertFalse(res);
    }
   
    @Test
    public void shouldNotBeAuthenticatedwithEmptyPassword(){
        HttpClient httpClient = Mockito.mock(HttpClient.class);
        when(httpClient.call(anyString(), any(Login.class))).thenReturn("false");
        Authentication authentication = new Authentication("http://localhost:5000/login", httpClient);
        boolean res = authentication.isAuthenticated(new Login("prueba", ""));
        assertFalse(res);
    }
 
    @Test
    public void shouldNotBeAuthenticatedwithNullLogin(){
        HttpClient httpClient = Mockito.mock(HttpClient.class);
        when(httpClient.call(anyString(), any(Login.class))).thenReturn("false");
        Authentication authentication = new Authentication("http://localhost:5000/login", httpClient);
        boolean res = authentication.isAuthenticated(new Login(null, null));
        assertFalse(res);
    }
 
    @Test
    public void shouldNotBeAuthenticatedwithNullUser(){
        HttpClient httpClient = Mockito.mock(HttpClient.class);
        when(httpClient.call(anyString(), any(Login.class))).thenReturn("false");
        Authentication authentication = new Authentication("http://localhost:5000/login", httpClient);
        boolean res = authentication.isAuthenticated(new Login(null, "1234"));
        assertFalse(res);
    }
   
    @Test
    public void shouldNotBeAuthenticatedwithNullPassword(){
        HttpClient httpClient = Mockito.mock(HttpClient.class);
        when(httpClient.call(anyString(), any(Login.class))).thenReturn("false");
        Authentication authentication = new Authentication("http://localhost:5000/login", httpClient);
        boolean res = authentication.isAuthenticated(new Login("prueba", null));
        assertFalse(res);
    }
    
}
