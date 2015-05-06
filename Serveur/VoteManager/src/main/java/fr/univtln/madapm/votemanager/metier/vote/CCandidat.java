package fr.univtln.madapm.votemanager.metier.vote;

/**
 * Created by civars169 on 05/05/15.
 * copyright Christian
 */

/**
 * Un candidat est un choix lors d'un vote.
 * Example, banane et cerrise pour voter pour son fruit préféré.
 */
public class CCandidat {

    private int mIdcandidat;
    private String mNomcandidat;
    private String mDescriptioncandidat;

    public CCandidat(int mIdcandidat, String mNomcandidat, String mDescriptioncandidat) {
        this.mIdcandidat = mIdcandidat;
        this.mNomcandidat = mNomcandidat;
        this.mDescriptioncandidat = mDescriptioncandidat;
    }

    public int getmIdcandidat() {
        return mIdcandidat;
    }

    public void setmIdcandidat(int mIdcandidat) {
        this.mIdcandidat = mIdcandidat;
    }

    public String getmNomcandidat() {
        return mNomcandidat;
    }

    public void setmNomcandidat(String mNomcandidat) {
        this.mNomcandidat = mNomcandidat;
    }

    public String getmDescriptioncandidat() {
        return mDescriptioncandidat;
    }

    public void setmDescriptioncandidat(String mDescriptioncandidat) {
        this.mDescriptioncandidat = mDescriptioncandidat;
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
