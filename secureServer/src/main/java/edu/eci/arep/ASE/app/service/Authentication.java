package edu.eci.arep.ASE.app.service;

import org.bson.Document;

import edu.eci.arep.ASE.app.domain.Login;
import edu.eci.arep.ASE.app.persistence.GestionLogin;

public class Authentication {

    private GestionLogin gestionLogin;
    private Encryption encryption;

    public Authentication(GestionLogin gestionLogin, Encryption encryption) {
        this.gestionLogin = gestionLogin;
        this.encryption = encryption;
    }

    public boolean isAuthenticated(Login login) {
        Document document = this.gestionLogin.search(login.getUsername());
        String passwordEncripted = this.encryption.hashString(login.getPassword());
        return document != null && document.getString("password").equals(passwordEncripted);
    }
    
}
