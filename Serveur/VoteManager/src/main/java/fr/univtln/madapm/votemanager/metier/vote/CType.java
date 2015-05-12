package fr.univtln.madapm.votemanager.metier.vote;

/**
 * Created by civars169 on 12/05/15.
 * copyright Christian
 */
public class CType {

    private int midType;
    private String mNom;
    private String mDescription;

    public CType(int pidType, String pNom, String pDescription) {
        this.midType = pidType;
        this.mNom = pNom;
        this.mDescription = pDescription;
    }

    public int getidType() {
        return midType;
    }

    public void setidType(int pidType) {
        this.midType = pidType;
    }

    public String getNom() {
        return mNom;
    }

    public void setNom(String pNom) {
        this.mNom = pNom;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String pDescription) {
        this.mDescription = pDescription;
    }

    @Override
    public String toString() {
        return "CType{" +
                "midType=" + midType +
                ", mNom='" + mNom + '\'' +
                ", mDescription='" + mDescription + '\'' +
                '}';
    }
}
