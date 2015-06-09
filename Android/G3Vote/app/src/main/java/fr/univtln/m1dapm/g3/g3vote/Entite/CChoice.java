package fr.univtln.m1dapm.g3.g3vote.Entite;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by ludo on 05/05/15.
 */
public class CChoice implements Parcelable {

    private int mIdChoice;

    //private CVote mVote;
    private int mIdVote;

    //private CUser mUser;
    private int mIdUser;

    //private CCandidate mCandidate;
    private int mIdCandidate;

    private int mScore;

    public CChoice(){}

   /* public CChoice(CVote pVote, CUser pUser, CCandidate pCandidate, int pScore){
        this.mVote = pVote;
        this.mUser = pUser;
        this.mCandidate = pCandidate;
        this.mScore = pScore;
    }*/

    public CChoice(int pIdVote, int pIdUser, int pIdCandidate, int pScore) {
        this.mIdVote = pIdVote;
        this.mIdUser = pIdUser;
        this.mIdCandidate = pIdCandidate;
        this.mScore = pScore;
    }

    @JsonIgnore
    public int getIdChoice() {
        return mIdChoice;
    }

    @JsonIgnore
    public void setIdChoice(int pIdChoice) {
        this.mIdChoice = pIdChoice;
    }

   /* public CVote getVote() {
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
    }*/

    public int getScore() {
        return mScore;
    }

    public void setScore(int pScore) {
        this.mScore = pScore;
    }

    public int getIdVote() {
        return mIdVote;
    }

    public void setIdVote(int mIdVote) {
        this.mIdVote = mIdVote;
    }

    public int getIdUser() {
        return mIdUser;
    }

    public void setIdUser(int mIdUser) {
        this.mIdUser = mIdUser;
    }

    public int getIdCandidate() {
        return mIdCandidate;
    }

    public void setIdCandidate(int mIdCandidate) {
        this.mIdCandidate = mIdCandidate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public CChoice(Parcel p){
        setIdVote(p.readInt());
        setIdUser(p.readInt());
        setIdCandidate(p.readInt());
        //setIdChoice(p.readInt());
        setScore(p.readInt());
    }

    public static final Parcelable.Creator<CChoice> CREATOR = new Creator<CChoice>() {

        public CChoice createFromParcel(Parcel source) {

            return new CChoice(source);
        }

        public CChoice[] newArray(int size) {

            return new CChoice[size];
        }

    };

/* public CUser getUser() {
        return mUser;
    }*/

    /*@Override
    public String toString() {
        return mVote.toString() + mUser.toString() + "\n" + mCandidate.toString() + "Score = " + mScore + "\n";
    }*/
}

