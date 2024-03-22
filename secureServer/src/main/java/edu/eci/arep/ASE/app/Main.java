package edu.eci.arep.ASE.app;

import edu.eci.arep.ASE.app.controller.MyController;
import edu.eci.arep.ASE.app.controller.MySparkController;
import edu.eci.arep.ASE.app.service.Authentication;
import edu.eci.arep.ASE.app.service.SaveUser;
import edu.eci.arep.ASE.app.persistence.GestionLogin;

//import static spark.Spark.staticFileLocation;
import static spark.Spark.secure;
import static spark.Spark.port; 

public class Main {

    public static void main(String[] args) {
        config();
        controller(); 
    }

    public static void config(){
        port(5000);
    }
    
    public static void controller(){
        GestionLogin gestionLogin = GestionLogin.instanciaLoginDAO("login","usuarios");
        Authentication authentication = new Authentication(gestionLogin);
        SaveUser saveUser = new SaveUser(gestionLogin);
        MyController myController = new MySparkController(authentication, saveUser);
        myController.inicializar();
    }
    
}
