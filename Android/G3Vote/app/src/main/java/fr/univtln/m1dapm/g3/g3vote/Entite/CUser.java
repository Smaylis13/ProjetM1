package fr.univtln.m1dapm.g3.g3vote.Entite;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ludo on 05/05/15.
 */
public class CUser implements Serializable{
    private int mUserId;
    private String mFirstName;//prenom

    private String mName;//nom
    private String mEmail;
    private String mPassword;

    @JsonIgnore
    private String mIdDevice;

    @JsonGetter("userId")
    public int getUserId() {
        return mUserId;
    }

    @JsonSetter("userId")
    public void setUserId(int pIdUser) {
        this.mUserId = pIdUser;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String pFirstName) {
        this.mFirstName = pFirstName;
    }

    public String getName() {
        return mName;
    }

    public void setName(String pLastName) {
        this.mName = pLastName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mail) {
        this.mEmail = mail;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }

    public CUser(){

    }

    public CUser (int pID, String firstName, String lastName, String mail, String password ){
        super();
        this.mUserId=pID;
        this.mFirstName = firstName;
        this.mName = lastName;
        this.mEmail = mail;
        this.mPassword = password;
    }

    public CUser (String firstName, String lastName, String mail, String password ){
        super();
        this.mFirstName = firstName;
        this.mName = lastName;
        this.mEmail = mail;
        this.mPassword = password;
    }

    public CUser (String pMail, String pIdDevice ){
        super();
        this.mEmail = pMail;
        this.mIdDevice = pIdDevice;
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

    @Override
    public String toString() {
        return "CUser{" +
                "mUserId=" + mUserId +
                ", mFirstName='" + mFirstName + '\'' +
                ", mName='" + mName + '\'' +
                ", mEmail='" + mEmail + '\'' +
                ", mPassword='" + mPassword + '\'' +
                '}';
    }

}
