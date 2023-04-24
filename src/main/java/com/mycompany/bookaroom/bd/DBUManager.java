package com.mycompany.bookaroom.bd;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DBUManager {
    private static DBUManager instance;
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;


    private DBUManager() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("BookARoomPU");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public static synchronized DBUManager getInstance() {
        if (instance == null) {
            instance = new DBUManager();
        }
        return instance;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public void closeEntityManager() {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
        if (entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
