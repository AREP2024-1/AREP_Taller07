package edu.eci.arep.ASE.app.controller;

import static spark.Spark.post;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.eci.arep.ASE.app.service.Authentication;
import edu.eci.arep.ASE.app.service.SaveUser;
import edu.eci.arep.ASE.app.domain.Login;

public class MySparkController implements MyController {
    
    private Authentication authentication;
    private SaveUser saveUser;

    public MySparkController(Authentication authentication, SaveUser saveUser){
        this.authentication = authentication;
        this.saveUser = saveUser;
    }

    @Override
    public void inicializar() {
        post("/login", (req, res)->{
            try{
                String body = req.body();
                ObjectMapper mapper = new ObjectMapper();
                Login readMapper = mapper.readValue(body, Login.class);
                return authentication.isAuthenticated(readMapper);
            }catch(Exception e){
                return false;
            }
        });

        post("/user", (req, res)->{
            try{
                String body = req.body();
                ObjectMapper mapper = new ObjectMapper();
                Login readMapper = mapper.readValue(body, Login.class);
                saveUser.save(readMapper);
                return "El usuario se ha creado con exito";
            }catch(Exception e){
                return "Usuario no creado";
            }
        });
    }

}
