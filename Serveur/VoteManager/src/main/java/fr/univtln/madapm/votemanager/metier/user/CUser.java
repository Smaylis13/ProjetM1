package fr.univtln.madapm.votemanager.metier.user;

/**
 * Created by civars169 on 05/05/15.
 * copyright Christian
 */

/**
 * Défini les utilisauteurs qui peuvent être organisateur et participant.
 */
public class CUser {

    protected int mId;
    protected String mEmail;
    protected String mPassword;

    protected CUser(int pId, String pEmail, String pPassword) {
        this.mId = pId;
        this.mEmail = pEmail;
        this.mPassword = pPassword;
    }

    public int getId() {
        return mId;
    }

    public void setId(int pId) {
        this.mId = pId;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String pEmail) {
        this.mEmail = pEmail;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String pPassword) {
        this.mPassword = pPassword;
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
