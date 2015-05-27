package fr.univtln.m1dapm.g3.g3vote.Entite;

/**
 * Created by ludo on 05/05/15.
 */
public class CChoice {

    private int mIdChoice;

    private CVote mVote;

    private CUser mUser;

    private CCandidate mCandidate;

    private int mScore;

    public CChoice(){}

    public CChoice(CVote pVote, CUser pUser, CCandidate pCandidate, int pScore){
        this.mVote = pVote;
        this.mUser = pUser;
        this.mCandidate = pCandidate;
        this.mScore = pScore;
    }
}

