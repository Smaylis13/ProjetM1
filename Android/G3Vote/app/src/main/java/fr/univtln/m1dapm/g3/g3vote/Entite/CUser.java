package fr.univtln.m1dapm.g3.g3vote.Entite;

import java.util.ArrayList;

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

    //initialise une liste de Users
    public static ArrayList<CUser> getAListOfUser(){

        ArrayList<CUser> listUser = new ArrayList<CUser>();

        listUser.add(new CUser("Neo","theOne","neo.matrix@gmail.com","pass"));
        listUser.add(new CUser("Trinity","theGirl","trinity.matrix@gmail.com","pass"));
        listUser.add(new CUser("Morpheus","theFriend","morpheus.matrix@gmail.com","pass"));
        listUser.add(new CUser("Merovigien","French","merovingien.matrix@gmail.com","pass"));
        listUser.add(new CUser("John","Doe","john.matrix@gmail.com","pass"));

        return listUser;
    }
}
