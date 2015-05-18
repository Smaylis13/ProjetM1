package fr.univtln.madapm.votemanager.dao;

import fr.univtln.madapm.votemanager.crud.CCRUDServiceBean;
import fr.univtln.madapm.votemanager.metier.user.CGroup;

/**
 * Created by sebastien on 18/05/15.
 */
public class CGroupDAO extends CCRUDServiceBean<CGroup> implements IGroupDAO {

    @Override
    public CGroup findById(int pId) {
        return find(CGroup.class, pId);
    }

    @Override
    public void deleteGroup(int pId) {
        delete(CGroup.class, pId);
    }

    @Override
    public CGroup updateGroup(CGroup pGroup) {
        return update(pGroup);
    }

    @Override
    public CGroup createGroup(CGroup pGroup) {
        return create(pGroup);
    }

}
