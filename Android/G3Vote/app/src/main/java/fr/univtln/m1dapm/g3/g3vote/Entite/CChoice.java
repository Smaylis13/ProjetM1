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

    public int getIdChoice() {
        return mIdChoice;
    }

    public void setIdChoice(int pIdChoice) {
        this.mIdChoice = pIdChoice;
    }

    public CVote getVote() {
        return mVote;
    }

    public void setVote(CVote pVote) {
        this.mVote = pVote;
    }

    public void setUser(CUser pUser) {
        this.mUser = pUser;
    }

    public CCandidate getCandidate() {
        return mCandidate;
    }

    public void setCandidate(CCandidate pCandidate) {
        this.mCandidate = pCandidate;
    }

    public int getScore() {
        return mScore;
    }

    public void setScore(int pScore) {
        this.mScore = pScore;
    }

    public CUser getUser() {
        return mUser;
    }
}

