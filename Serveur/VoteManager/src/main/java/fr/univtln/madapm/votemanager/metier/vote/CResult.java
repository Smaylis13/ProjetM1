package fr.univtln.madapm.votemanager.metier.vote;

import fr.univtln.madapm.votemanager.metier.CMap;

/**
 * Created by civars169 on 12/05/15.
 * copyright Christian
 */
public class CResult {

    private int mIdResultat;
    private String mResultat; //Résultat du vote
    private CVote mVote;
    private CMap<CCandidate, String> mScore; //Contient les scores individuels des candidats

    public CResult(int pIdResultat, String pResultat, CVote pVote, CMap<CCandidate, String> pScore) {
        this.mIdResultat = pIdResultat;
        this.mResultat = pResultat;
        this.mVote = pVote;
        this.mScore = pScore;
    }

    public int getIdResultat() {
        return mIdResultat;
    }

    public void setIdResultat(int pIdResultat) {
        this.mIdResultat = pIdResultat;
    }

    public String getResultat() {
        return mResultat;
    }

    public void setResultat(String pResultat) {
        this.mResultat = pResultat;
    }

    public CVote getVote() {
        return mVote;
    }

    public void setVote(CVote pVote) {
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
                "mIdResultat=" + mIdResultat +
                ", mresultat='" + mResultat + '\'' +
                ", mvote=" + mVote + '\'' +
                ", mscore=" + mScore +
                '}';
    }
}
