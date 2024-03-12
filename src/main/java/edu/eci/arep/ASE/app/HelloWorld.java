package edu.eci.arep.ASE.app;

import static spark.Spark.*; 

public class HelloWorld {

    public static void main(String[] args) {
        secure("certificates/ecikeystore.p12", "123456", null, null); 
        port(getPort());
        get("/hello", (req, res) -> "Hello World");
    }
    
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000; //returns default port if heroku-port isn't set (i.e. on localhost)
    } 

       
    
}
