package fr.univtln.madapm.votemanager.metier.user;

/**
 * Created by civars169 on 05/05/15.
 * copyright Christian
 */

import fr.univtln.madapm.votemanager.metier.CMap;

/**
 * Un groupe correspond à une liste de contacts pour un utilisateur.
 */
public class CGroup {

    private int mIdgroupe;
    private String mNomgroupe;
    private String mDescriptiongroupe;
    private CMap<CParticipant, String> mMapgroupe;

    public CGroup(int pIdgroupe, String pNomgroupe, String pDescriptiongroupe, CMap<CParticipant, String> pMapgroupe) {
        this.mIdgroupe = pIdgroupe;
        this.mNomgroupe = pNomgroupe;
        this.mDescriptiongroupe = pDescriptiongroupe;
        this.mMapgroupe = pMapgroupe;
    }

    public int getIdgroupe() {
        return mIdgroupe;
    }

    public void setIdgroupe(int pIdgroupe) {
        this.mIdgroupe = pIdgroupe;
    }

    public String getNomgroupe() {
        return mNomgroupe;
    }

    public void setNomgroupe(String pNomgroupe) {
        this.mNomgroupe = pNomgroupe;
    }

    public String getDescriptiongroupe() {
        return mDescriptiongroupe;
    }

    public void setDescriptiongroupe(String pDescriptiongroupe) {
        this.mDescriptiongroupe = pDescriptiongroupe;
    }

    public CMap getMapgroupe() {
        return mMapgroupe;
    }

    public void setMapgroupe(CMap<CParticipant, String> pMapgroupe) {
        this.mMapgroupe = pMapgroupe;
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
