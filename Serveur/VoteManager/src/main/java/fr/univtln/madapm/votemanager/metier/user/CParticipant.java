package fr.univtln.madapm.votemanager.metier.user;

import fr.univtln.madapm.votemanager.metier.vote.CVote;

/**
 * Created by civars169 on 05/05/15.
 * copyright Christian
 */
public class CParticipant extends CUser {

    private CVote mVote;
    private String mMonVote = "Abstention";

    public CParticipant(int pId, String pEmail, String pPassword) {
        super(pId, pEmail, pPassword);
    }

    public CParticipant(int pId, String pEmail, String pPassword, CVote pVote, String pMonVote) {
        super(pId, pEmail, pPassword);
        this.mVote = pVote;
        this.mMonVote = pMonVote;
    }

    public CVote getVote() {
        return mVote;
    }

    public void setVote(CVote pVote) {
        this.mVote = pVote;
    }

    public String getMonVote() {
        return mMonVote;
    }

    public void setMonVote(String pMonVote) {
        this.mMonVote = pMonVote;
    }

    @Override
    public String toString() {
        return super.toString() + "CParticipant{" +
                "mVote='" + mVote + '\'' +
                "mMonVote='" + mMonVote + '\'' +
                '}';
    }
}
