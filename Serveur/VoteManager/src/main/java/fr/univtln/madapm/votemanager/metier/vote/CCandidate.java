package fr.univtln.madapm.votemanager.metier.vote;

/**
 * Created by civars169 on 05/05/15.
 * copyright Christian
 */

/**
 * Un candidat est un choix lors d'un vote.
 * Example, banane et cerrise pour voter pour son fruit préféré.
 */
public class CCandidate {

    private int mIdcandidat;
    private String mNomcandidat;
    private String mDescriptioncandidat;

    public CCandidate(int pIdcandidat, String pNomcandidat, String pDescriptioncandidat) {
        this.mIdcandidat = pIdcandidat;
        this.mNomcandidat = pNomcandidat;
        this.mDescriptioncandidat = pDescriptioncandidat;
    }

    public int getIdcandidat() {
        return mIdcandidat;
    }

    public void setIdcandidat(int pIdcandidat) {
        this.mIdcandidat = pIdcandidat;
    }

    public String getNomcandidat() {
        return mNomcandidat;
    }

    public void setNomcandidat(String pNomcandidat) {
        this.mNomcandidat = pNomcandidat;
    }

    public String getDescriptioncandidat() {
        return mDescriptioncandidat;
    }

    public void setDescriptioncandidat(String pDescriptioncandidat) {
        this.mDescriptioncandidat = pDescriptioncandidat;
    }

    @Override
    public String toString() {
        return "CCandidat{" +
                "mIdcandidat=" + mIdcandidat +
                ", mNomcandidat='" + mNomcandidat + '\'' +
                ", mDescriptioncandidat='" + mDescriptioncandidat + '\'' +
                '}';
    }
}
