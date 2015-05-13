package fr.univtln.madapm.votemanager.dao;


import fr.univtln.madapm.votemanager.crud.ICRUDService;
import fr.univtln.madapm.votemanager.metier.vote.CCandidate;

/**
 * Created by sebastien on 13/05/15.
 */
public interface ICandidateDAO extends ICRUDService<CCandidate>{
    CCandidate findById(int pId);
}
