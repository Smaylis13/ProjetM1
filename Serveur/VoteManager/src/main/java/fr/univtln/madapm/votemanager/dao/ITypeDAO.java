package fr.univtln.madapm.votemanager.dao;

import fr.univtln.madapm.votemanager.crud.ICRUDService;
import fr.univtln.madapm.votemanager.metier.vote.CType;

/**
 * Created by sebastien on 18/05/15.
 */
public interface ITypeDAO extends ICRUDService<CType> {
    public CType findById(int pId);
    public void deleteType(int pId);
    public CType updateType(CType pType);
    public CType createType(CType pType);
}
