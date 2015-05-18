package fr.univtln.madapm.votemanager.dao;

import fr.univtln.madapm.votemanager.crud.ICRUDService;
import fr.univtln.madapm.votemanager.metier.user.CUser;

/**
 * Created by Ookami on 13/05/2015.
 */
public interface IUserDAO extends ICRUDService<CUser>{

    public CUser findByID(int pId);
    public void deleteUser(int pId);
    public CUser updateUser(CUser pType);
    public CUser createUser(CUser pType);
}
