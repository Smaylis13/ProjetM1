package fr.univtln.madapm.votemanager.dao;

import fr.univtln.madapm.votemanager.crud.ICRUDService;
import fr.univtln.madapm.votemanager.metier.vote.CVote;

/**
 * Created by Ookami on 15/05/2015.
 */
public interface IVoteDAO extends ICRUDService<CVote> {
    public CVote findById(int pId);
    public void deleteVote(int pId);
    public CVote updateVote(CVote pVote);
    public CVote createVote(CVote pVote);
}
