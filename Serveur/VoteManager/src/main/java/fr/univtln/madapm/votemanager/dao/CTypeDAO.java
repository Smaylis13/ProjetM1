package fr.univtln.madapm.votemanager.dao;

import fr.univtln.madapm.votemanager.crud.CCRUDServiceBean;
import fr.univtln.madapm.votemanager.metier.vote.CType;

/**
 * Created by sebastien on 18/05/15.
 */
public class CTypeDAO extends CCRUDServiceBean<CType> implements ITypeDAO {
    @Override
    public CType findById(int pId) {
        return find(CType.class, pId);
    }

    @Override
    public void deleteType(int pId) {
        delete(CType.class, pId);
    }

    @Override
    public CType updateType(CType pType) {
        return update(pType);
    }

    @Override
    public CType createType(CType pType) {
        return create(pType);
    }
}
