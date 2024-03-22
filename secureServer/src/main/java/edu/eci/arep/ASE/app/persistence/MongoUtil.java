package edu.eci.arep.ASE.app.persistence;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoUtil {
    
    private static final String CONNECTION_STRING = "mongodb://daniela:daniela2004@mongodb:27017";

    public static MongoDatabase getDB(String dataBaseName) {
        MongoClient client = MongoClients.create(CONNECTION_STRING);
        return client.getDatabase(dataBaseName);
    }
    
}
