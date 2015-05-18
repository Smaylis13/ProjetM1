package fr.univtln.madapm.votemanager.dao;

import fr.univtln.madapm.votemanager.metier.user.CUser;

/**
 * Created by Ookami on 13/05/2015.
 */
public interface IUserDAO {

    public CUser findByID(int pId);
}
