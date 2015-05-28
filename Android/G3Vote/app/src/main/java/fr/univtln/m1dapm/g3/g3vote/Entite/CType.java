package fr.univtln.m1dapm.g3.g3vote.Entite;

import java.util.List;

/**
 * Created by Ookami on 27/05/2015.
 */
public class CType {
    private int mIdType;
    private String mName;
    private String mDescription;

    private List<CRule> mListRulesAllowed;

    public CType(){}

    public CType(String pName, String pDescription) {
        this.mName = pName;
        this.mDescription = pDescription;
    }

    public CType(int pidType, String pName, String pDescription) {
        this.mIdType = pidType;
        this.mName = pName;
        this.mDescription = pDescription;
    }


    public String getNom() {
        return mName;
    }

    public void setNom(String pName) {
        this.mName = pName;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String pDescription) {
        this.mDescription = pDescription;
    }

}
