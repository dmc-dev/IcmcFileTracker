package com.IcmcFileTracker.helpers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


// org.datanucleus.store.appengine.jpa.DatastorePersistenceProvider  old provider in persistence.xml

//org.datanucleus.api.jpa.PersistenceProviderImpl new one using now

public final class EMF {
    private static final EntityManagerFactory emfInstance = Persistence.createEntityManagerFactory("transactions-optional");

    private static final EntityManager emInstance = emfInstance.createEntityManager();
    
    private EMF() {}

    public static EntityManagerFactory get() {
        return emfInstance;
    }
    
    public static EntityManager getEntityManager() {
        return emInstance;
    }
    
}
