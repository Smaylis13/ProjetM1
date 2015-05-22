package fr.univtln.m1dapm.g3.g3vote.Entite;

import java.io.Serializable;

/**
 * Created by ludo on 06/05/15.
 */
public class CCandidat implements Serializable{

    private int mIdCandidat;
    private String mNom;
    private String mDescription;

    public int getId() {
        return mIdCandidat;
    }

    public void setId(int mId) {
        this.mIdCandidat = mId;
    }

    public String getNom() {
        return mNom;
    }

    public void setNom(String mNom) {
        this.mNom = mNom;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    @Override
    public String toString() {
        return "CCandidat{" +
                "mId=" + mIdCandidat +
                ", mNom='" + mNom + '\'' +
                ", mDescription='" + mDescription + '\'' +
                '}';
    }


    public CCandidat(int mId, String mNom, String mDescription) {
        this.mIdCandidat = mId;
        this.mNom = mNom;
        this.mDescription = mDescription;
    }

    public CCandidat(int mId, String mNom) {
        this.mIdCandidat = mId;
        this.mNom = mNom;
    }

    public CCandidat(String mNom) {
        this.mNom = mNom;
    }

    public CCandidat() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CCandidat cCandidat = (CCandidat) o;

        if (mIdCandidat != cCandidat.mIdCandidat) return false;
        if (mDescription != null ? !mDescription.equals(cCandidat.mDescription) : cCandidat.mDescription != null)
            return false;
        if (!mNom.equals(cCandidat.mNom)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mIdCandidat;
        result = 31 * result + mNom.hashCode();
        result = 31 * result + (mDescription != null ? mDescription.hashCode() : 0);
        return result;
    }
}
