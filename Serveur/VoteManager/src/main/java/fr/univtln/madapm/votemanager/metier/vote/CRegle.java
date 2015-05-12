package fr.univtln.madapm.votemanager.metier.vote;

/**
 * Created by civars169 on 12/05/15.
 * copyright Christian
 */
public class CRegle {

    private int midRegle;
    private String mdescription;

    public CRegle(int pidRegle, String pdescription) {
        this.midRegle = pidRegle;
        this.mdescription = pdescription;
    }

    public int getIdRegle() {
        return midRegle;
    }

    public void setIdRegle(int pidRegle) {
        this.midRegle = pidRegle;
    }

    public String getDescription() {
        return mdescription;
    }

    public void setDescription(String pdescription) {
        this.mdescription = pdescription;
    }

    @Override
    public String toString() {
        return "CRegle{" +
                "midRegle=" + midRegle +
                ", mdescription='" + mdescription + '\'' +
                '}';
    }
}
