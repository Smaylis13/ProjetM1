package fr.univtln.madapm.votemanager.metier.user;

/**
 * Created by civars169 on 05/05/15.
 * copyright Christian
 */
public class COrganizer extends CUser {

    private CGroup mGroupe; //défini les contacts d'un organisateur en vue d'un vote.

    public COrganizer(int pId, String pEmail, String pPassword, CGroup pGroupe) {
        super(pId, pEmail, pPassword);
        this.mGroupe = pGroupe;
    }

    public CGroup getGroupe() {
        return mGroupe;
    }

    public void setGroupe(CGroup pGroupe) {
        this.mGroupe = pGroupe;
    }

    @Override
    public String toString() {
        return super.toString() + "COrganisateur{" +
                "mGroupe=" + mGroupe +
                '}';
    }
}
