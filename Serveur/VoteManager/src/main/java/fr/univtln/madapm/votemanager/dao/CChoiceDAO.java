package fr.univtln.madapm.votemanager.dao;

import fr.univtln.madapm.votemanager.crud.CCRUDServiceBean;
import fr.univtln.madapm.votemanager.metier.vote.CChoice;

/**
 * Created by sebastien on 18/05/15.
 */
public class CChoiceDAO extends CCRUDServiceBean<CChoice> implements IChoiceDAO {
    @Override
    public CChoice findById(int pId) {
        return find(CChoice.class, pId);
    }

    @Override
    public void deleteChoice(int pId) {
        delete(CChoice.class, pId);
    }

    @Override
    public CChoice updateChoice(CChoice pChoice) {
        return update(pChoice);
    }

    @Override
    public CChoice createChoice(CChoice pChoice) {
        return create(pChoice);
    }
}
