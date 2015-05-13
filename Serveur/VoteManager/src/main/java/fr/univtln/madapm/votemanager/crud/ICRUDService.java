package fr.univtln.madapm.votemanager.crud;

import java.util.List;
import java.util.Map;

/**
 * Created by sebastien on 13/05/15.
 */
public interface ICRUDService<T> {
    public T create(T pT);
    public T find(Class pType, Object pId);
    public T update(T pT);
    public void delete(Class pType, Object pId);
    public void closeEM();
    public void startTransac();
    public void finishTransac();

    public List findWithNamedQuery(String pQueryName);
    public List findWithNamedQuery(String pQueryName, int pResultLimit);
    public List findWithNamedQuery(String pNamedQueryName, Map pParameters);
    public List findWithNamedQuery(String pNamedQueryName, Map pParameters, int pResultLimit);

}
