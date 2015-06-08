package fr.univtln.m1dapm.g3.g3vote.Entite;

/**
 * Created by ludo on 05/05/15.
 */
public class CResult{

    private int mIdResultat;

    private int mOrder; //Résultat du vote

    private CVote mVote;

    private int mIdCandidate;

    public CResult(){}
    public CResult(int pIdResultat, int pOrder, CVote pVote, int pIdCandidate) {
        this.mIdResultat = pIdResultat;
        this.mOrder = pOrder;
        this.mVote = pVote;
        this.mIdCandidate = pIdCandidate;
    }

    public CResult(int pOrder, CVote pVote, int pIdCandidate) {
        this.mOrder = pOrder;
        this.mVote = pVote;
        this.mIdCandidate = pIdCandidate;
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

    public int getCandidat() {
        return mIdCandidate;
    }

    public void setCandidat(int pIdCandidate) {
        this.mIdCandidate = pIdCandidate;
    }


    public int getOrder() {
        return mOrder;
    }

    public void setOrder(int pOrder) {
        this.mOrder = pOrder;
    }
}
