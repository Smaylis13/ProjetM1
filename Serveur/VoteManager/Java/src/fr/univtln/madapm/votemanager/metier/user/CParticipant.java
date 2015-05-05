package fr.univtln.madapm.votemanager.metier.user;

/**
 * Created by civars169 on 05/05/15.
 * copyright Christian
 */
public class CParticipant extends CUser {

    private String mVote = "Abstention";

    public CParticipant(int mId, String mEmail, String mPassword) {
        super(mId, mEmail, mPassword);
    }

    public CParticipant(int mId, String mEmail, String mPassword, String mVote) {
        super(mId, mEmail, mPassword);
        this.mVote = mVote;
    }

    public String getmVote() {
        return mVote;
    }

    public void setmVote(String mVote) {
        this.mVote = mVote;
    }

    @Override
    public String toString() {
        return super.toString() + "CParticipant{" +
                "mVote='" + mVote + '\'' +
                '}';
    }
}
