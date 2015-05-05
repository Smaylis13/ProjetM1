package fr.univtln.madapm.votemanager.metier.user;

/**
 * Created by civars169 on 05/05/15.
 * copyright Christian
 */

import fr.univtln.madapm.votemanager.metier.CMap;
import fr.univtln.madapm.votemanager.metier.user.CUser;

/**
 * Un groupe correspond à une liste de contacts pour un utilisateur.
 */
public class CGroupe {

    private int mIdgroupe;
    private String mNomgroupe;
    private String mDescriptiongroupe;
    private CMap<CParticipant, String> mMapgroupe;

    public CGroupe(int mIdgroupe, String mNomgroupe, String mDescriptiongroupe, CMap<CParticipant, String> mMapgroupe) {
        this.mIdgroupe = mIdgroupe;
        this.mNomgroupe = mNomgroupe;
        this.mDescriptiongroupe = mDescriptiongroupe;
        this.mMapgroupe = mMapgroupe;
    }

    public int getmIdgroupe() {
        return mIdgroupe;
    }

    public void setmIdgroupe(int mIdgroupe) {
        this.mIdgroupe = mIdgroupe;
    }

    public String getmNomgroupe() {
        return mNomgroupe;
    }

    public void setmNomgroupe(String mNomgroupe) {
        this.mNomgroupe = mNomgroupe;
    }

    public String getmDescriptiongroupe() {
        return mDescriptiongroupe;
    }

    public void setmDescriptiongroupe(String mDescriptiongroupe) {
        this.mDescriptiongroupe = mDescriptiongroupe;
    }

    public CMap getmMapgroupe() {
        return mMapgroupe;
    }

    public void setmMapgroupe(CMap<CParticipant, String> mMapgroupe) {
        this.mMapgroupe = mMapgroupe;
    }

    @Override
    public String toString() {
        return "CGroupe{" +
                "mIdgroupe=" + mIdgroupe +
                ", mNomgroupe='" + mNomgroupe + '\'' +
                ", mDescriptiongroupe='" + mDescriptiongroupe + '\'' +
                ", mMapgroupe=" + mMapgroupe +
                '}';
    }
}
