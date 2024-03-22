package edu.eci.arep.ASE.app.persistence;

import java.util.Collection;

public interface CRUDoperations<D,I,T> {

    void save(I id, T infoLogin);
    D search(I id);
    Collection<D> searchAll();
    void delete(I id); 
    
}
