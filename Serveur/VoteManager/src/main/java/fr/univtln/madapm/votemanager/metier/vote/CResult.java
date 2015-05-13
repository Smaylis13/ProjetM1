package fr.univtln.madapm.votemanager.metier.vote;

import fr.univtln.madapm.votemanager.metier.CMap;

/**
 * Created by civars169 on 12/05/15.
 * copyright Christian
 */
public class CResult {

    private int mIdresultat;
    private String mResultat; //Résultat du vote
    private CVote mVote;
    private CMap<CCandidate, String> mScore; //Contien les scores individuelles des candidats

    public CResult(int pIdresultat, String pResultat, CVote pVote, CMap<CCandidate, String> pScore) {
        this.mIdresultat = pIdresultat;
        this.mResultat = pResultat;
        this.mVote = pVote;
        this.mScore = pScore;
    }

    public int getMidresultat() {
        return mIdresultat;
    }

    public void setMidresultat(int pIdresultat) {
        this.mIdresultat = pIdresultat;
    }

    public String getMresultat() {
        return mResultat;
    }

    public void setMresultat(String pResultat) {
        this.mResultat = pResultat;
    }

    public CVote getMvote() {
        return mVote;
    }

    public void setMvote(CVote pVote) {
        this.mVote = pVote;
    }

    public CMap<CCandidate, String> getCandidat() {
        return mScore;
    }

    public void setCandidat(CMap<CCandidate, String> pScore) {
        this.mScore = pScore;
    }

    @Override
    public String toString() {
        return "CResultat{" +
                "midresultat=" + mIdresultat +
                ", mresultat='" + mResultat + '\'' +
                ", mvote=" + mVote + '\'' +
                ", mscore=" + mScore +
                '}';
    }
}
