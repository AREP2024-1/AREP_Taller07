package edu.eci.arep.ASE.app.service;

import org.bson.Document;

import edu.eci.arep.ASE.app.domain.Login;
import edu.eci.arep.ASE.app.persistence.GestionLogin;

public class Authentication {

    private GestionLogin gestionLogin;

    public Authentication(GestionLogin gestionLogin){
        this.gestionLogin = gestionLogin;
    }

    public boolean isAuthenticated(Login login) {
        Document document = this.gestionLogin.search(login.getUsername());
        return document != null && document.getString("password").equals(login.getPassword());
    }
    
}
