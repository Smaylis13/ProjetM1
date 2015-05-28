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

    public int getmIdChoice() {
        return mIdChoice;
    }

    public void setmIdChoice(int mIdChoice) {
        this.mIdChoice = mIdChoice;
    }

    public CVote getmVote() {
        return mVote;
    }

    public void setmVote(CVote mVote) {
        this.mVote = mVote;
    }

    public void setmUser(CUser mUser) {
        this.mUser = mUser;
    }

    public CCandidate getmCandidate() {
        return mCandidate;
    }

    public void setmCandidate(CCandidate mCandidate) {
        this.mCandidate = mCandidate;
    }

    public int getmScore() {
        return mScore;
    }

    public void setmScore(int mScore) {
        this.mScore = mScore;
    }

    public CUser getmUser() {
        return mUser;
    }
}

