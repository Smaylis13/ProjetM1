package fr.univtln.madapm.votemanager.dao;

import fr.univtln.madapm.votemanager.crud.CCRUDServiceBean;
import fr.univtln.madapm.votemanager.metier.vote.CCandidate;

/**
 * Created by sebastien on 13/05/15.
 */
public class CCandidateDAO extends CCRUDServiceBean<CCandidate> implements ICandidateDAO {

    @Override
    public CCandidate findById(int pId) {
        return find(CCandidate.class, pId);
    }

    public void deleteCandidate(int pId){
        delete(CCandidate.class, pId);
    }

    public CCandidate updateCandidate(CCandidate pCandidate){
        return update(pCandidate);
    }

    public CCandidate createCandidate(CCandidate pCandidate){
        return create(pCandidate);
    }
}
