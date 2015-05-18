package fr.univtln.madapm.votemanager.dao;

import fr.univtln.madapm.votemanager.crud.CCRUDServiceBean;
import fr.univtln.madapm.votemanager.metier.vote.CResult;

/**
 * Created by sebastien on 18/05/15.
 */
public class CResultDAO extends CCRUDServiceBean<CResult> implements IResultDAO {

    @Override
    public CResult findById(int pId) {
        return find(CResult.class, pId);
    }

    @Override
    public void deleteGroup(int pId) {
        delete(CResult.class, pId);
    }

    @Override
    public CResult updateResult(CResult pResult) {
        return update(pResult);
    }

    @Override
    public CResult createResult(CResult pResult) {
        return create(pResult);
    }
}
