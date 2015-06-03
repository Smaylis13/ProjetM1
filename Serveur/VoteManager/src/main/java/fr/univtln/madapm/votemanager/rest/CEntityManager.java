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

    private EntityManagerFactory mEmf = Persistence
            .createEntityManagerFactory("votemanager");
    private EntityManager mEm = mEmf.createEntityManager();

    private EntityTransaction mTransac = mEm.getTransaction();

    void persist(Object o){
        mTransac.begin();
        mEm.persist(o);
        mTransac.commit();
    }

    void merge(Object o){
        mTransac.begin();
        mEm.merge(o);
        mTransac.commit();
    }

    void remove(Object o){
        mTransac.begin();
        mEm.remove(o);
        mTransac.commit();
    }
}
