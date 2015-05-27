package fr.univtln.m1dapm.g3.g3vote.Entite;

/**
 * Created by ludo on 05/05/15.
 */
public class CResult {

    private int mIdResultat;

    private int mOrder; //Résultat du vote

    private CVote mVote;

    private CCandidate mCandidate;

    public CResult(){}
    public CResult(int pIdResultat, int pOrder, CVote pVote, CCandidate pCandidate) {
        this.mIdResultat = pIdResultat;
        this.mOrder = pOrder;
        this.mVote = pVote;
        this.mCandidate = pCandidate;
    }


    public int getResultat() {
        return mOrder;
    }

    public void setResultat(int pOrder) {
        this.mOrder = pOrder;
    }

    public CVote getVote() {
        return mVote;
    }

    public void setVote(CVote pVote) {
        this.mVote = pVote;
    }

    public CCandidate getCandidat() {
        return mCandidate;
    }

    public void setCandidat(CCandidate pCandidate) {
        this.mCandidate = pCandidate;
    }

}
