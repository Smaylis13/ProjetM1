package fr.univtln.madapm.votemanager.metier.user;

/**
 * Created by civars169 on 05/05/15.
 * copyright Christian
 */

/**
 * Défini les utilisauteurs qui peuvent être organisateur et participant.
 */
public class CUser {

    private int mId;
    private String mEmail;
    private String mPassword;

    protected CUser(int mId, String mEmail, String mPassword) {
        this.mId = mId;
        this.mEmail = mEmail;
        this.mPassword = mPassword;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    @Override
    public String toString() {
        return "CUser{" +
                "mId=" + mId +
                ", mEmail='" + mEmail + '\'' +
                ", mPassword='" + mPassword + '\'' +
                '}';
    }
}
