package fr.univtln.madapm.votemanager.dao;

import fr.univtln.madapm.votemanager.crud.ICRUDService;
import fr.univtln.madapm.votemanager.metier.vote.CResult;

/**
 * Created by sebastien on 18/05/15.
 */
public interface IResultDAO extends ICRUDService<CResult> {
    public CResult findById(int pId);
    public void deleteResult(int pId);
    public CResult updateResult(CResult pResult);
    public CResult createResult(CResult pResult);
}
