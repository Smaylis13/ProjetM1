package fr.univtln.madapm.votemanager.dao;

import fr.univtln.madapm.votemanager.crud.ICRUDService;
import fr.univtln.madapm.votemanager.metier.user.CGroup;

/**
 * Created by sebastien on 18/05/15.
 */
public interface IGroupDAO extends ICRUDService<CGroup> {
    public CGroup findById(int pId);
    public void deleteGroup(int pId);
    public CGroup updateGroup(CGroup pGroup);
    public CGroup createGroup(CGroup pGroup);
}
