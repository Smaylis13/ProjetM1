package fr.univtln.madapm.votemanager.dao;

import fr.univtln.madapm.votemanager.crud.CCRUDServiceBean;
import fr.univtln.madapm.votemanager.metier.user.CUser;
import fr.univtln.madapm.votemanager.metier.vote.CVote;

/**
 * Created by Ookami on 15/05/2015.
 */
public class CVoteDAO extends CCRUDServiceBean<CVote> implements IVoteDAO {
    @Override
    public CVote findById(int pId) {
        return find(CVote.class, pId);
    }

    @Override
    public void deleteVote(int pId) {
        delete(CVote.class, pId);
    }

    @Override
    public CVote updateVote(CVote pVote) {
        return update(pVote);
    }

    @Override
    public CVote createVote(CVote pVote) {
        return create(pVote);
    }
}
