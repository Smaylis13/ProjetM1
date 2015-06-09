package fr.univtln.m1dapm.g3.g3vote.Entite;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * Created by ludo on 05/05/15.
 */
public class CResult implements Serializable{

    @JsonIgnore
    private int mIdResultat;

    private int mOrder; //Résultat du vote

    private int mVote;

    private int mIdCandidate;

    public CResult(){}
    public CResult(int pIdResultat, int pOrder, int pVote, int pIdCandidate) {
        this.mIdResultat = pIdResultat;
        this.mOrder = pOrder;
        this.mVote = pVote;
        this.mIdCandidate = pIdCandidate;
    }

    public CResult(int pOrder, int pVote, int pIdCandidate) {
        this.mOrder = pOrder;
        this.mVote = pVote;
        this.mIdCandidate = pIdCandidate;
    }



    public int getVote() {
        return mVote;
    }

    public void setVote(int pVoteId) {
        this.mVote=pVoteId;
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

    @Override
    public String toString() {
        return "CResult{" +
                "mIdResultat=" + mIdResultat +
                ", mOrder=" + mOrder +
                ", mIdCandidate=" + mIdCandidate +
                '}';
    }
}
