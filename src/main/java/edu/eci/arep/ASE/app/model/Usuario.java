package edu.eci.arep.ASE.app.model;

public class Usuario {

    private String username;
    private String contrasena;


    public Usuario(String username, String contrasena){
        this.username = username;
        this.contrasena = contrasena;   
    }

    public String getUsername(){
        return username;
    }

    public String getContrasena(){
        return contrasena;
    }
    
}
