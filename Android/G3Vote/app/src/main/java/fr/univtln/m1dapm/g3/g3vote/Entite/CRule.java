package fr.univtln.m1dapm.g3.g3vote.Entite;

import java.util.List;

/**
 * Created by ludo on 05/05/15.
 */
public class CRule {

    private int mIdRegle;
    private String mDescription;


    private List<CType> mListTypesAllow;

    public CRule(){}

    public CRule(String pDescription){
        this.mDescription=pDescription;
    }

    public CRule(int pIdRegle, String pDescription) {
        this.mIdRegle = pIdRegle;
        this.mDescription = pDescription;
    }

    public void setDescription(String pDescription){
        this.mDescription=pDescription;
    }
}
