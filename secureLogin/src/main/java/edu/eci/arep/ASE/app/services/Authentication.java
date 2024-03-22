package edu.eci.arep.ASE.app.services;

import edu.eci.arep.ASE.app.domain.Login;

import java.io.IOException;

import edu.eci.arep.ASE.app.apiExternal.HttpClient;


public class Authentication {

    private String url;
    private HttpClient httpClient;
    private DataTransformation dataTransformation;

    public Authentication(String url, HttpClient httpClient){
        this.url = url;
        this.httpClient = httpClient;
        dataTransformation = new DataTransformation();
    }

    public boolean isAuthenticated(Login login) {
        return this.httpClient.call(this.url, login).equals("true");
    }

    public boolean isAuthenticated(String login) throws IOException{
        return this.isAuthenticated(dataTransformation.stringToLogin(login));
    }
    
}
