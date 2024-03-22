package edu.eci.arep.ASE.app.service;

import edu.eci.arep.ASE.app.domain.Login;
import edu.eci.arep.ASE.app.persistence.GestionLogin;

public class SaveUser {

    private GestionLogin gestionLogin;

    public SaveUser(GestionLogin gestionLogin){
        this.gestionLogin = gestionLogin;
    }

    public void save(Login login){
        gestionLogin.save(login.getUsername(), login.getPassword());
    }

    
}
