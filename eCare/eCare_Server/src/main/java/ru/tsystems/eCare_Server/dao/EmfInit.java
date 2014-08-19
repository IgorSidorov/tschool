package ru.tsystems.eCare_Server.dao;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmfInit {

    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("eCare");

    private static EntityManager em = emf.createEntityManager();

    public static EntityManager getEm() {
        return em;
    }
}
