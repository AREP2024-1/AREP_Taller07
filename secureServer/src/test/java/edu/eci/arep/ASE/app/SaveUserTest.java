package edu.eci.arep.ASE.app;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import edu.eci.arep.ASE.app.domain.Login;
import edu.eci.arep.ASE.app.persistence.GestionLogin;
import edu.eci.arep.ASE.app.service.Encryption;
import edu.eci.arep.ASE.app.service.SaveUser;

public class SaveUserTest {

    public static void assertDoesNotThrow(Runnable runnable) {
        try {
            runnable.run();
        } catch (Exception e) {
            fail(e.getMessage());
        }
 
    }
 
    @Test
    public void shouldSave() {
        Login login = new Login("prueba", "1234");
        GestionLogin gestionLogin = mock(GestionLogin.class);
        Encryption encryption = mock(Encryption.class);
        SaveUser saveUser = new SaveUser(gestionLogin, encryption);
       
        assertDoesNotThrow(()->{
            saveUser.save(login);
        }); 
    }       
    
}
