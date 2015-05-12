package fr.univtln.madapm.votemanager.rest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by civars169 on 12/05/15.
 * copyright Christian
 */
public class CEntityManager {

    EntityManagerFactory emf = Persistence
            .createEntityManagerFactory("votemanager");
    EntityManager em = emf.createEntityManager();

    EntityTransaction transac = em.getTransaction();

    void persist(Object o){
        transac.begin();
        em.persist(o);
        transac.commit();
    }

    void merge(Object o){
        transac.begin();
        em.merge(o);
        transac.commit();
    }

    void remove(Object o){
        transac.begin();
        em.remove(o);
        transac.commit();
    }
}
