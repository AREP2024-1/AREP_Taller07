package edu.eci.arep.ASE.app.controller;

import static spark.Spark.post;

import edu.eci.arep.ASE.app.services.Authentication;

import java.io.IOException;

public class MySparkController implements MyController {
    
    private Authentication authentication;

    public MySparkController(Authentication authentication){
        this.authentication = authentication;
    }

    @Override
    public void inicializar() {
        post("/login", (req, res)->{
            try{
                String body = req.body();
                return authentication.isAuthenticated(body);
            }catch(IOException e){
                e.printStackTrace();
                return "500 server error";

            }
            
        });
    }

}
