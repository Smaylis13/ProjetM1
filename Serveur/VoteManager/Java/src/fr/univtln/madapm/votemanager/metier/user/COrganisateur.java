package fr.univtln.madapm.votemanager.metier.user;

/**
 * Created by civars169 on 05/05/15.
 * copyright Christian
 */
public class COrganisateur extends CUser {

    private CGroupe mGroupe; //défini les contacts d'un organisateur en vue d'un vote.

    public COrganisateur(int mId, String mEmail, String mPassword, CGroupe mGroupe) {
        super(mId, mEmail, mPassword);
        this.mGroupe = mGroupe;
    }

    public CGroupe getmGroupe() {
        return mGroupe;
    }

    public void setmGroupe(CGroupe mGroupe) {
        this.mGroupe = mGroupe;
    }

    @Override
    public String toString() {
        return super.toString() + "COrganisateur{" +
                "mGroupe=" + mGroupe +
                '}';
    }
}
