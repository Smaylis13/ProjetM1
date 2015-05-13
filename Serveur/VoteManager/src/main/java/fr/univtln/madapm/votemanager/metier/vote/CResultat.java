package fr.univtln.madapm.votemanager.metier.vote;

import fr.univtln.madapm.votemanager.metier.CMap;

/**
 * Created by civars169 on 12/05/15.
 * copyright Christian
 */
public class CResultat {

    private int midresultat;
    private String mresultat; //Résultat du vote
    private CVote mvote;
    private CMap<CCandidat, String> mscore; //Contien les scores individuelles des candidats

    public CResultat(int pidresultat, String presultat, CVote pvote, CMap<CCandidat, String> pscore) {
        this.midresultat = pidresultat;
        this.mresultat = presultat;
        this.mvote = pvote;
        this.mscore = pscore;
    }

    public int getMidresultat() {
        return midresultat;
    }

    public void setMidresultat(int pidresultat) {
        this.midresultat = pidresultat;
    }

    public String getMresultat() {
        return mresultat;
    }

    public void setMresultat(String presultat) {
        this.mresultat = presultat;
    }

    public CVote getMvote() {
        return mvote;
    }

    public void setMvote(CVote pvote) {
        this.mvote = pvote;
    }

    public CMap<CCandidat, String> getCandidat() {
        return mscore;
    }

    public void setCandidat(CMap<CCandidat, String> pscore) {
        this.mscore = pscore;
    }

    @Override
    public String toString() {
        return "CResultat{" +
                "midresultat=" + midresultat +
                ", mresultat='" + mresultat + '\'' +
                ", mvote=" + mvote + '\'' +
                ", mscore=" + mscore +
                '}';
    }
}
