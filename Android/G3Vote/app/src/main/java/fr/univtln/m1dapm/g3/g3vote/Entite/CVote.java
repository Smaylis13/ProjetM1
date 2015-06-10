package fr.univtln.m1dapm.g3.g3vote.Entite;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by ludo on 06/05/15.
 */
public class CVote implements Serializable {


    private int mIdVote;

    private String mVoteName;

    private String mDescriptionVote;

    private boolean mStatusVote;



    private Timestamp mDateDebut;
    private Timestamp mDateFin;

    private int mIdOrganisateur;

    private List<CResult> mResultVote=null;

    private CType mTypes;

    private List<CRule> mRules;

    private List<CChoice> mChoices;

    private List<CCandidate> mCandidates;

    private List<CDeleguation> mDeleguations;

    private List<CUser> mParticipants;

    @JsonIgnore
    private boolean mVoted;

    public CVote(){};

    public CVote(int pIdVote, String pVoteName, String pDescriptionVote, boolean pStatusVote, Timestamp pDateDebut, Timestamp pDateFin, int pOrganisateur, List<CResult> pResultVote,CType pType, List<CRule> pRules, List<CCandidate> pCandidate, List<CDeleguation> pDeleguations, List<CUser> pParticipants) {
        this.mIdVote=pIdVote;
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
        this.mParticipants=pParticipants;
    }

    public CVote(String pVoteName, String pDescriptionVote, boolean pStatusVote, Timestamp pDateDebut, Timestamp pDateFin, int pOrganisateur, List<CResult> pResultVote,CType pType, List<CRule> pRules, List<CCandidate> pCandidate, List<CDeleguation> pDeleguations, List<CUser> pParticipants) {
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
        this.mParticipants=pParticipants;
    }

    public CVote(int pIdVote, String pNom) {
        this.mIdVote = pIdVote;
        this.mVoteName = pNom;
    }

    public CVote(int pIdVote, String pNom, boolean pStatut, Timestamp pDebut, Timestamp pFin){
        this(pIdVote,pNom);
        this.mStatusVote =pStatut;
        this.mDateDebut = pDebut;
        this.mDateFin = pFin;
    }

    public CVote(int pIdVote, String pNom, boolean pStatut,boolean mVoted, Timestamp pDebut, Timestamp pFin){
        this(pIdVote,pNom);
        this.mStatusVote =pStatut;
        this.mDateDebut = pDebut;
        this.mDateFin = pFin;
        this.mVoted=mVoted;
    }

    @JsonSetter("idVote")
    public void setIdVote(int pId){this.mIdVote=pId;}

    @JsonIgnore
    public int getIdVote(){return this.mIdVote;}

    @JsonSetter("voted")
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

    public void setDateDebut(Timestamp beginDate) {
        this.mDateDebut = beginDate;
    }

    public Date getDateFin() {
        return mDateFin;
    }

    public void setDateFin(Timestamp endDate) {
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

    @JsonIgnore
    public CType getTypes() {
        return mTypes;
    }

    @JsonGetter("types")
    public int getIdType(){
        return mTypes.getIdType();
    }

    @JsonSetter("types")
    public void setTypes(CType pTypes) {
        this.mTypes = pTypes;
    }

    public List<Integer> getParticipants() {
        List<Integer> lIdUsers=new ArrayList<>();
        for(CUser lUser:this.mParticipants){
            lIdUsers.add(lUser.getUserId());
        }

        return lIdUsers;
    }


    public void setParticipants(List<CUser> pParticipants) {
        this.mParticipants = pParticipants;
    }

    public List<CResult> getResultVote() {
        return mResultVote;
    }

    public void setResultVote(List<CResult> mResultVote) {
        this.mResultVote = mResultVote;
    }

    public static ArrayList<CVote> getAListOfVote() {
        ArrayList<CVote> listVote = new ArrayList<>();//GregorianCalendar (int year, int month, int day)

        // Create an instance of SimpleDateFormat used for formatting
// the string representation of date (month/day/year)
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.FRANCE);

// Get the date today using Calendar object.
        Timestamp d1 = new Timestamp(System.currentTimeMillis());
        Timestamp d2 = new Timestamp(System.currentTimeMillis());
        Timestamp d3 = new Timestamp(System.currentTimeMillis());
        Timestamp d4 = new Timestamp(System.currentTimeMillis());
        Timestamp d5 = new Timestamp(System.currentTimeMillis());
        Timestamp d6 = new Timestamp(System.currentTimeMillis());
        Timestamp d7 = new Timestamp(System.currentTimeMillis());
        Timestamp d8 = new Timestamp(System.currentTimeMillis());
        Timestamp d9 = new Timestamp(System.currentTimeMillis());
        Timestamp d10 = new Timestamp(System.currentTimeMillis());

// Using DateFormat format method we can create a string
// representation of a date with the defined format.


        listVote.add(new CVote(1, "elireToto", true, true, d1, d2));
        listVote.add(new CVote(2, "elireTata", true, true, d3, d4));
        listVote.add(new CVote(3,"elireTiti",false,true,d5,d6));
        listVote.add(new CVote(4,"elireTete",true,false,d7,d8));
        listVote.add(new CVote(5, "elireTutu", false, false, d9, d10));
        return listVote;
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
                ", mParticipants=" + mParticipants +
                ", mVoted="+mVoted+
                '}';
    }



    @JsonIgnore
    public boolean isVoted(){
        return mVoted;
    }

}
