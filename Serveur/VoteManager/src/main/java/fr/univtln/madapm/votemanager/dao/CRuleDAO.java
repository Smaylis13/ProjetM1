package fr.univtln.madapm.votemanager.dao;

import fr.univtln.madapm.votemanager.crud.CCRUDServiceBean;
import fr.univtln.madapm.votemanager.metier.vote.CRule;

/**
 * Created by sebastien on 18/05/15.
 */
public class CRuleDAO extends CCRUDServiceBean<CRule> implements IRuleDAO {
    @Override
    public CRule findById(int pId) {
        return find(CRule.class, pId);
    }

    @Override
    public void deleteRule(int pId) {
        delete(CRule.class, pId);
    }

    @Override
    public CRule updateRule(CRule pRule) {
        return update(pRule);
    }

    @Override
    public CRule createRule(CRule pRule) {
        return create(pRule);
    }
}
