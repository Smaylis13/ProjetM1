package fr.univtln.madapm.votemanager.dao;

import fr.univtln.madapm.votemanager.crud.ICRUDService;
import fr.univtln.madapm.votemanager.metier.vote.CRule;

/**
 * Created by sebastien on 18/05/15.
 */
public interface IRuleDAO extends ICRUDService<CRule> {
    public CRule findById(int pId);
    public void deleteRule(int pId);
    public CRule updateRule(CRule pRule);
    public CRule createRule(CRule pRule);
}
