package fr.univtln.madapm.votemanager.dao;

import fr.univtln.madapm.votemanager.crud.CCRUDServiceBean;
import fr.univtln.madapm.votemanager.metier.user.CUser;

/**
 * Created by Ookami on 13/05/2015.
 */
public class CUserDAO extends CCRUDServiceBean<CUser> implements IUserDAO{
    @Override
    public CUser findByID(int pId) {
        return find(CUser.class,pId);
    }
}
