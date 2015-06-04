package fr.univtln.madapm.votemanager.crud;

import org.eclipse.persistence.exceptions.DatabaseException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by sebastien on 13/05/15.
 */
public class CCRUDServiceBean<T> implements ICRUDService<T> {
    private EntityManager mEM = CConstantes.EM;
    private EntityTransaction mET = CConstantes.ET;


    @Override
    public T create(T pT){

        System.out.println(pT);
        System.out.println("em"+mEM);
        System.out.println("et"+mET);
        mET.begin();
        System.out.println("ICI1");
        this.mEM.persist(pT);
        System.out.println("ICI2");
        //this.mEM.flush();
        System.out.println("ICI3");
        //this.mEM.refresh(pT);
        System.out.println("ICI4");
        mET.commit();
        System.out.println("ICI5");
        return pT;
    }

    public void detach(T pT){
        this.mEM.detach(pT);
    }


    @Override
    public T find(Class pType, Object pId) {
        return (T) this.mEM.find(pType, pId);
    }

    @Override
    public T update(T pT) {
        mET.begin();
        T lT = this.mEM.merge(pT);
        mET.commit();
        return lT;
    }

    @Override
    public void delete(Class pType, Object pId) {
        mET.begin();
        Object lRef = this.mEM.getReference(pType, pId);
        this.mEM.remove(lRef);
        mET.commit();
    }

    @Override
    public void closeEM() {

    }

    @Override
    public void startTransac() {
        this.mET.begin();
    }

    @Override
    public void rollBackTransac(){
        this.mET.rollback();
    }
    @Override
    public void finishTransac() {
        this.mET.commit();
    }

    @Override
    public List findWithNamedQuery(String pQueryName) {
        return this.mEM.createNamedQuery(pQueryName).getResultList();
    }

    @Override
    public List findWithNamedQuery(String pQueryName, int pResultLimit) {
        return this.mEM.createNamedQuery(pQueryName).setMaxResults(pResultLimit).getResultList();
    }

    @Override
    public List findWithNamedQuery(String pNamedQueryName, Map pParameters) {
        return findWithNamedQuery(pNamedQueryName, pParameters, 0);
    }

    @Override
    public List findWithNamedQuery(String pNamedQueryName, Map pParameters, int pResultLimit) {
        Set<Map.Entry> lRawParameters = pParameters.entrySet();
        Query lQuery = this.mEM.createNamedQuery(pNamedQueryName);
        //System.out.println(lQuery.toString());
        if(pResultLimit > 0)
            lQuery.setMaxResults(pResultLimit);
        for(Map.Entry lEntry : lRawParameters) {
            lQuery.setParameter((String) lEntry.getKey(), lEntry.getValue());
        }
        return lQuery.getResultList();
    }

    public List findByNativeQuery(String mSQL, Class mType){
        return this.mEM.createNativeQuery(mSQL, mType).getResultList();
    }
}