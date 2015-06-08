package fr.univtln.m1dapm.g3.g3vote.Entite;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ludo on 05/05/15.
 */
public class CRule implements Serializable{

    private int mIdRegle;
    private String mRuleName;
    private String mDescription;

    private List<CType> mListTypesAllow;

    public CRule(){}

    public CRule(int pIdRegle,String pName, String pDescription) {
        this.mIdRegle = pIdRegle;
        this.mRuleName=pName;
        this.mDescription = pDescription;
    }

    public CRule(String pName, String pDescription) {
        this.mRuleName=pName;
        this.mDescription = pDescription;
    }

    public void setDescription(String pDescription){
        this.mDescription=pDescription;
    }

    public String getRuleName() {
        return mRuleName;
    }

    public void setRuleName(String mRuleName) {
        this.mRuleName = mRuleName;
    }

    public String getDescription() {
        return mDescription;
    }

}
