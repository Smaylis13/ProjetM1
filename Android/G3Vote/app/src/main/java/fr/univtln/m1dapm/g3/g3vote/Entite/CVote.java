package fr.univtln.m1dapm.g3.g3vote.Entite;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ludo on 06/05/15.
 */
public class CVote implements Serializable,Parcelable {


    private int mIdVote;

    private String mVoteName;

    private String mDescriptionVote;

    private boolean mStatusVote;



    private Date mDateDebut;
    private Date mDateFin;

    private int mIdOrganisateur;

    private List<CResult> mResultVote=null;

    private List<CType> mTypes;

    private List<CRule> mRules;

    private List<CChoice> mChoices;

    private List<CCandidate> mCandidates;

    private List<CDeleguation> mDeleguations;

    @JsonIgnore
    private boolean mVoted;

    public CVote(){};

    public CVote(String pVoteName, String pDescriptionVote, boolean pStatusVote, Date pDateDebut, Date pDateFin, int pOrganisateur, List<CResult> pResultVote, List<CType> pType, List<CRule> pRules, List<CCandidate> pCandidate, List<CDeleguation> pDeleguations) {
        this.mVoteName = pVoteName;
        this.mDescriptionVote = pDescriptionVote;
        this.mStatusVote = pStatusVote;
        this.mDateDebut = pDateDebut;
        this.mDateFin = pDateFin;
        this.mIdOrganisateur = pOrganisateur;
        this.mResultVote = pResultVote;
        this.mTypes = pType;
        this.mRules = pRules;
        this.mCandidates = pCandidate;
        this.mDeleguations = pDeleguations;
    }

    public CVote(int pIdVote, String pNom) {
        this.mIdVote = pIdVote;
        this.mVoteName = pNom;
    }

    public CVote(int pIdVote, String pNom, boolean pStatut, Date pDebut, Date pFin){
        this(pIdVote,pNom);
        this.mStatusVote =pStatut;
        this.mDateDebut = pDebut;
        this.mDateFin = pFin;
    }

    public CVote(int pIdVote, String pNom, boolean pStatut,boolean mVoted, Date pDebut, Date pFin){
        this(pIdVote,pNom);
        this.mStatusVote =pStatut;
        this.mDateDebut = pDebut;
        this.mDateFin = pFin;
        this.mVoted=mVoted;
    }
    public void setIdVote(int pId){this.mIdVote=pId;}

    public int getIdVote(){return this.mIdVote;}

    public void setVoted(boolean pVoted){
        this.mVoted=pVoted;
    }

    public String getDescriptionVote() {
        return mDescriptionVote;
    }

    public void setDescriptionVote(String mDescriptionVote) {
        this.mDescriptionVote = mDescriptionVote;
    }

    public int getOrganisateur(){ return mIdOrganisateur;}

    public void setOrganisateur(int pId){
        this.mIdOrganisateur=pId;
    }

    public Date getDateDebut() {
        return mDateDebut;
    }

    public void setDateDebut(Date beginDate) {
        this.mDateDebut = beginDate;
    }

    public Date getDateFin() {
        return mDateFin;
    }

    public void setDateFin(Date endDate) {
        this.mDateFin = endDate;
    }

    public String getVoteName() {
        return mVoteName;
    }

    public void setVoteName(String mVoteName) {
        this.mVoteName = mVoteName;
    }

    public boolean getStatusVote() {
        return mStatusVote;
    }

    public void setStatusVote(boolean mStatusVote) {
        this.mStatusVote = mStatusVote;
    }

    public void setCandidates(List<CCandidate> pCandidates){
        this.mCandidates=pCandidates;
    }

    public List<CCandidate> getCandidates() {
        return mCandidates;
    }

    public void addCandidat(CCandidate pCandidat) {
        mCandidates.add(pCandidat);
    }

    public List<CRule> getRegles() {
        return mRules;
    }

    public void setRegles(List<CRule> pRules){
        this.mRules=pRules;
    }

    public List<CType> getTypes() {
        return mTypes;
    }

    public void setTypes(List<CType> pTypes) {
        this.mTypes = pTypes;
    }

    public static ArrayList<CVote> getAListOfVote() {
        ArrayList<CVote> listVote = new ArrayList<CVote>();//GregorianCalendar (int year, int month, int day)

        // Create an instance of SimpleDateFormat used for formatting
// the string representation of date (month/day/year)
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

// Get the date today using Calendar object.
        Date d1 = new Date(2015-1900,05,12);
        Date d2 = new Date(2015-1900,05,22);
        Date d3 = new Date(2015-1900,05,13);
        Date d4 = new Date(2015-1900,05,23);
        Date d5 = new Date(2015-1900,05,14);
        Date d6 = new Date(2015-1900,05,24);
        Date d7 = new Date(2015-1900,05,15);
        Date d8 = new Date(2015-1900,05,25);
        Date d9 = new Date(2015-1900,05,16);
        Date d10 = new Date(2015-1900,05,26);
// Using DateFormat format method we can create a string
// representation of a date with the defined format.


        listVote.add(new CVote(1,"elireToto",true,true,d1,d2));
        listVote.add(new CVote(2,"elireTata",true,true,d3,d4));
        listVote.add(new CVote(3,"elireTiti",false,true,d5,d6));
        listVote.add(new CVote(4,"elireTete",true,false,d7,d8));
        listVote.add(new CVote(5,"elireTutu",false,false,d9,d10));
        return listVote;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    @Override
    public String toString() {
        return "CVote{" +
                "mIdVote=" + mIdVote +
                ", mVoteName='" + mVoteName + '\'' +
                ", mStatusVote=" + mStatusVote +
                ", mDescriptionVote='" + mDescriptionVote + '\'' +
                ", mDateDebut=" + mDateDebut +
                ", mDateFin=" + mDateFin +
                ", mResultVote=" + mResultVote +
                ", mType=" + mTypes +
                ", mRules=" + mRules +
                ", mChoices=" + mChoices +
                ", mCandidate=" + mCandidates +
                ", mDeleguations=" + mDeleguations +
                ", mOrganisateur=" + mIdOrganisateur +
                ", mVoted="+mVoted+
                '}';
    }

    public boolean isVoted(){
        return mVoted;
    }
}
