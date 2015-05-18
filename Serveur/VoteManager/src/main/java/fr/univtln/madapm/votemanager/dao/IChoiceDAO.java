package fr.univtln.madapm.votemanager.dao;

import fr.univtln.madapm.votemanager.crud.ICRUDService;
import fr.univtln.madapm.votemanager.metier.vote.CChoice;

/**
 * Created by sebastien on 18/05/15.
 */
public interface IChoiceDAO extends ICRUDService<CChoice> {
    public CChoice findById(int pId);
    public void deleteChoice(int pId);
    public CChoice updateChoice(CChoice pChoice);
    public CChoice createChoice(CChoice pChoice);
}
