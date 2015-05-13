package fr.univtln.madapm.votemanager.metier.vote;

/**
 * Created by civars169 on 05/05/15.
 * copyright Christian
 */

/**
 * Un candidat est un choix lors d'un vote.
 * Exemple, banane et cerise pour voter pour son fruit préféré.
 */
public class CCandidate {

    private int mIdCandidat;
    private String mNomCandidat;
    private String mDescriptionCandidat;

    public CCandidate(int pIdCandidat, String pNomCandidat, String pDescriptionCandidat) {
        this.mIdCandidat = pIdCandidat;
        this.mNomCandidat = pNomCandidat;
        this.mDescriptionCandidat = pDescriptionCandidat;
    }

    public CCandidate(String pNomCandidat) {
        this.mNomCandidat = pNomCandidat;
    }

    public CCandidate() {
    }

    public int getIdCandidat() {
        return mIdCandidat;
    }

    public void setIdCandidat(int pIdCandidat) {
        this.mIdCandidat = pIdCandidat;
    }

    public String getNomCandidat() {
        return mNomCandidat;
    }

    public void setNomCandidat(String pNomCandidat) {
        this.mNomCandidat = pNomCandidat;
    }

    public String getDescriptionCandidat() {
        return mDescriptionCandidat;
    }

    public void setDescriptionCandidat(String pDescriptionCandidat) {
        this.mDescriptionCandidat = pDescriptionCandidat;
    }

    @Override
    public boolean equals(Object pObject) {
        if (this == pObject) return true;
        if (pObject == null || getClass() != pObject.getClass()) return false;

        CCandidate cCandidat = (CCandidate) pObject;

        if (mIdCandidat != cCandidat.mIdCandidat) return false;
        if (mDescriptionCandidat != null ? !mDescriptionCandidat.equals(cCandidat.mDescriptionCandidat)
                : cCandidat.mDescriptionCandidat != null)
            return false;
        if (!mNomCandidat.equals(cCandidat.mNomCandidat)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mIdCandidat;
        result = 31 * result + mNomCandidat.hashCode();
        result = 31 * result + (mDescriptionCandidat != null ? mDescriptionCandidat.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CCandidat{" +
                "mIdCandidat=" + mIdCandidat +
                ", mNomCandidat='" + mNomCandidat + '\'' +
                ", mDescriptionCandidat='" + mDescriptionCandidat + '\'' +
                '}';
    }
}
