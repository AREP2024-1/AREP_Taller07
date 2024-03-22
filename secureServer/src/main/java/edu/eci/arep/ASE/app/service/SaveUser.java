package edu.eci.arep.ASE.app.service;

import edu.eci.arep.ASE.app.domain.Login;
import edu.eci.arep.ASE.app.persistence.GestionLogin;

public class SaveUser {

    private GestionLogin gestionLogin;
    private Encryption encryption;

    public SaveUser(GestionLogin gestionLogin, Encryption encryption){
        this.gestionLogin = gestionLogin;
        this.encryption = encryption;
    }

    public void save(Login login){
        String passwordEncripted = this.encryption.hashString(login.getPassword());
        gestionLogin.save(login.getUsername(), passwordEncripted);
    }

    
}
