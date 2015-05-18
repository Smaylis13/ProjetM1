package fr.univtln.madapm.votemanager.crud;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by sebastien on 13/05/15.
 */
public class CConstantes {

    public static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("mysqlconnection");
    public static final EntityManager EM = EMF.createEntityManager();
    public static final EntityTransaction ET = EM.getTransaction();
}
