package edu.eci.arep.ASE.app.controller;

import static spark.Spark.get;

import edu.eci.arep.ASE.app.services.Authentication;

public class MySparkController implements MyController {
    
    private Authentication authentication;

    public MySparkController(Authentication authentication){
        this.authentication = authentication;
    }

    @Override
    public void inicializar() {
        get("/login", (req, res)->{
            String body = req.body();
            return authentication.isAuthenticated(body);
        });
    }

}
