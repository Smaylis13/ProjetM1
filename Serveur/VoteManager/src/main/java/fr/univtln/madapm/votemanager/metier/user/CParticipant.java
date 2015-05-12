package fr.univtln.madapm.votemanager.metier.user;

/**
 * Created by civars169 on 05/05/15.
 * copyright Christian
 */
public class CParticipant extends CUser {

    private String mVote = "Abstention";

    public CParticipant(int pId, String pEmail, String pPassword) {
        super(pId, pEmail, pPassword);
    }

    public CParticipant(int pId, String pEmail, String pPassword, String pVote) {
        super(pId, pEmail, pPassword);
        this.mVote = pVote;
    }

    public String getVote() {
        return mVote;
    }

    public void setVote(String pVote) {
        this.mVote = pVote;
    }

    @Override
    public String toString() {
        return super.toString() + "CParticipant{" +
                "mVote='" + mVote + '\'' +
                '}';
    }
}
