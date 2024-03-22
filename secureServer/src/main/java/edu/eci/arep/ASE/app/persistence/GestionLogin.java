package edu.eci.arep.ASE.app.persistence;

import java.util.ArrayList;
import java.util.Collection;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;

public class GestionLogin implements CRUDoperations<Document, String, String>{
    
    private MongoCollection<Document> loginMongo;
    
    public static GestionLogin instanciaLoginDAO(String nombreColletion, String nombreDB){
        MongoDatabase baseDatos = MongoUtil.getDB(nombreDB);
        GestionLogin login = new GestionLogin(nombreColletion, baseDatos);
        return login;
    }

    public GestionLogin(String nombreCollection, MongoDatabase baseDatos){
        this.loginMongo = baseDatos.getCollection(nombreCollection);
    }

    @Override
    public void save(String username, String password){
        Document addLogin = new Document("_id", username).append("password", password);
        loginMongo.insertOne(addLogin);
    }

    @Override
    public Document search(String username){
        return loginMongo.find(eq("_id", username)).first();
    }

    @Override
    public Collection<Document> searchAll(){
        return loginMongo.find().into(new ArrayList<>());
    }

    @Override
    public void delete(String username){
        loginMongo.deleteOne(eq("_id", username));
    }
    
}
