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

    public CCandidate(String pNomcandidat) {
        this.mNomcandidat = pNomcandidat;
    }

    public CCandidate() {
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
    public boolean equals(Object pobject) {
        if (this == pobject) return true;
        if (pobject == null || getClass() != pobject.getClass()) return false;

        CCandidate cCandidat = (CCandidate) pobject;

        if (mIdcandidat != cCandidat.mIdcandidat) return false;
        if (mDescriptioncandidat != null ? !mDescriptioncandidat.equals(cCandidat.mDescriptioncandidat)
                : cCandidat.mDescriptioncandidat != null)
            return false;
        if (!mNomcandidat.equals(cCandidat.mNomcandidat)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mIdcandidat;
        result = 31 * result + mNomcandidat.hashCode();
        result = 31 * result + (mDescriptioncandidat != null ? mDescriptioncandidat.hashCode() : 0);
        return result;
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
