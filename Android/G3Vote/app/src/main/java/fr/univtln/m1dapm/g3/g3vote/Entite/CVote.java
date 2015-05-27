package fr.univtln.m1dapm.g3.g3vote.Entite;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ludo on 06/05/15.
 */
public class CVote {

    private int mIdVote;

    private String mVoteName;

    private String mDescriptionVote;

    private boolean mStatusVote;


    private List <CCandidate> mListCandidat = new ArrayList<>();

    private Date mDateDebut;
    private Date mDateFin;

    private CUser mOrganisateur;

    private List<CResult> mResultVote=null;

    private List<CType> mType;

    private List<CRule> mRules;

    private List<CChoice> mChoices=null;

    private List<CCandidate> mCandidate;

    private List<CDeleguation> mDeleguations;

    public CVote(String pVoteName, String pDescriptionVote, boolean pStatusVote, List<CCandidate> pListCandidat, Date pDateDebut, Date pDateFin, CUser pOrganisateur, List<CResult> pResultVote, List<CType> pType, List<CRule> pRules, List<CCandidate> pCandidate, List<CDeleguation> pDeleguations) {
        this.mVoteName = pVoteName;
        this.mDescriptionVote = pDescriptionVote;
        this.mStatusVote = pStatusVote;
        this.mListCandidat = pListCandidat;
        this.mDateDebut = pDateDebut;
        this.mDateFin = pDateFin;
        this.mOrganisateur = pOrganisateur;
        this.mResultVote = pResultVote;
        this.mType = pType;
        this.mRules = pRules;
        this.mCandidate = pCandidate;
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

    public Date getBeginDate() {
        return mDateDebut;
    }

    public void setBeginDate(Date beginDate) {
        this.mDateDebut = beginDate;
    }

    public Date getEndDate() {
        return mDateFin;
    }

    public void setEndDate(Date endDate) {
        this.mDateFin = endDate;
    }

    public String getmVoteName() {
        return mVoteName;
    }

    public void setmVoteName(String mVoteName) {
        this.mVoteName = mVoteName;
    }

    public boolean getmStatusVote() {
        return mStatusVote;
    }

    public void setmStatusVote(boolean mStatusVote) {
        this.mStatusVote = mStatusVote;
    }

    public List<CCandidate> getListCandidat() {
        return mListCandidat;
    }

    public void addCandidat(CCandidate pCandidat) {
        mListCandidat.add(pCandidat);
    }

    public List<CRule> getmRules() {
        return mRules;
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


        listVote.add(new CVote(1,"elireToto",true,d1,d2));
        listVote.add(new CVote(2,"elireTata",true,d3,d4));
        listVote.add(new CVote(3,"elireTiti",false,d5,d6));
        listVote.add(new CVote(4,"elireTete",true,d7,d8));
        listVote.add(new CVote(5,"elireTutu",false,d9,d10));
        return listVote;
    }
}
