package fr.univtln.m1dapm.g3.g3vote.Entite;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by ludo on 06/05/15.
 */
public class CVote {

    private int mIdVote;

    private String mNom;

    private String mDescription;

    private boolean mStatut;

    private List<CRegle> mListRegle;

    private List<CChoix> mListChoix;

    private List <CCandidat> mListCandidat;

    private GregorianCalendar mDebut;

    private GregorianCalendar mFin;

    private Date beginDate;
    private Date endDate;

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    private CResultat mResultat;

    public CVote(int mIdVote, String mNom) {
        this.mIdVote = mIdVote;
        this.mNom = mNom;
    }
    public CVote(int mIdVote, String mNom, boolean mStatut, GregorianCalendar mDebut, GregorianCalendar mFin){
        this(mIdVote,mNom);
        this.mStatut=mStatut;
        this.mDebut=mDebut;
        this.mFin=mFin;
    }

    public CVote(int mIdVote, String mNom, boolean mStatut, Date debut, Date fin){
        this(mIdVote,mNom);
        this.mStatut=mStatut;
        this.beginDate = debut;
        this.endDate = fin;
    }
    public String getmNom() {
        return mNom;
    }

    public void setmNom(String mNom) {
        this.mNom = mNom;
    }

    public boolean getmStatut() {
        return mStatut;
    }

    public void setmStatut(boolean mStatut) {
        this.mStatut = mStatut;
    }

    public GregorianCalendar getmDebut() {
        return mDebut;
    }

    public void setmDebut(GregorianCalendar mDebut) {
        this.mDebut = mDebut;
    }

    public GregorianCalendar getmFin() {
        return mFin;
    }

    public void setmFin(GregorianCalendar mFin) {
        this.mFin = mFin;
    }

    public List<CCandidat> getListCandidat() {
        return mListCandidat;
    }

    public void addCandidat(CCandidat pCandidat) {
        mListCandidat.add(pCandidat);
    }

    public List<CRegle> getRegles() {
        return mListRegle;
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
