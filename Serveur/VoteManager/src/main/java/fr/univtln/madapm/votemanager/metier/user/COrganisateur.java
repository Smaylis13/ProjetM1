package fr.univtln.madapm.votemanager.metier.user;

/**
 * Created by civars169 on 05/05/15.
 * copyright Christian
 */
public class COrganisateur extends CUser {

    private CGroupe mGroupe; //défini les contacts d'un organisateur en vue d'un vote.

    public COrganisateur(int pId, String pEmail, String pPassword, CGroupe pGroupe) {
        super(pId, pEmail, pPassword);
        this.mGroupe = pGroupe;
    }

    public CGroupe getGroupe() {
        return mGroupe;
    }

    public void setGroupe(CGroupe pGroupe) {
        this.mGroupe = pGroupe;
    }

    @Override
    public String toString() {
        return super.toString() + "COrganisateur{" +
                "mGroupe=" + mGroupe +
                '}';
    }
}
