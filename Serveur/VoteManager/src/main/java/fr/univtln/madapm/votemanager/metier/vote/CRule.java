package fr.univtln.madapm.votemanager.metier.vote;

/**
 * Created by civars169 on 12/05/15.
 * copyright Christian
 */
public class CRule {

    private int mIdRegle;
    private String mDescription;

    public CRule(int pIdRegle, String pDescription) {
        this.mIdRegle = pIdRegle;
        this.mDescription = pDescription;
    }

    public int getIdRegle() {
        return mIdRegle;
    }

    public void setIdRegle(int pIdRegle) {
        this.mIdRegle = pIdRegle;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String pDescription) {
        this.mDescription = pDescription;
    }

    @Override
    public String toString() {
        return "CRegle{" +
                "midRegle=" + mIdRegle +
                ", mdescription='" + mDescription + '\'' +
                '}';
    }
}
