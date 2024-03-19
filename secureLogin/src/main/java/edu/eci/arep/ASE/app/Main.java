package edu.eci.arep.ASE.app;

import edu.eci.arep.ASE.app.controller.MyController;
import edu.eci.arep.ASE.app.controller.MySparkController;
import edu.eci.arep.ASE.app.services.Authentication;

import static spark.Spark.staticFileLocation;
import static spark.Spark.secure;
import static spark.Spark.port; 

public class Main {

    public static void main(String[] args) {
        config();
        controller(); 
    }

    public static void config(){
        staticFileLocation("/public");
        secure("certificates/ecikeystore.p12", "123456", "certificates/myTrustStore.p12", "123456");
        port(80);
    }
    
    public static void controller(){
        Authentication authentication = new Authentication("http://localhost:5000/login", new HttpClient());
        MyController myController = new MySparkController(authentication);
        myController.inicializar();
    }
    
}
