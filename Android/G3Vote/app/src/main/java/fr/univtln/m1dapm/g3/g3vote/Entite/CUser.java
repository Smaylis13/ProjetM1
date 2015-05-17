package fr.univtln.m1dapm.g3.g3vote.Entite;

/**
 * Created by ludo on 05/05/15.
 */
public class CUser {

    private String mFirstName;//prenom
    private String mLastName;//nom
    private String mMail;
    private String mPassword;

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        this.mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        this.mLastName = lastName;
    }

    public String getMail() {
        return mMail;
    }

    public void setMail(String mail) {
        this.mMail = mail;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }

    public CUser(){

    }

    public CUser (String firstName, String lastName, String mail, String password ){
        super();
        this.mFirstName = firstName;
        this.mLastName = lastName;
        this.mMail = mail;
        this.mPassword = password;
    }
}
