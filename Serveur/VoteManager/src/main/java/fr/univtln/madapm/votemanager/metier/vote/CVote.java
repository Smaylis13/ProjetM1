package fr.univtln.madapm.votemanager.metier.vote;

import fr.univtln.madapm.votemanager.metier.CMap;
import fr.univtln.madapm.votemanager.metier.user.COrganisateur;
import fr.univtln.madapm.votemanager.metier.user.CParticipant;

/**
 * Created by civars169 on 05/05/15.
 * copyright Christian
 */
public class CVote {

    private int mIdvote;
    private String mNomvote;
    private String mDescriptionvote;
    private String mDatedebut;
    private String mDatefin;
    private String mResultvote;
    private String mStatusvote;

    private COrganisateur mOrganisateur;
    private CMap<CParticipant, String> mMapvote = new CMap<CParticipant,String>();
    private CMap<CCandidat, String> mMapcandidat = new CMap<CCandidat, String>();

    public CVote(int mIdvote, String mNomvote, String mDescriptionvote, String mDatedebut, String mDatefin,
                 String mResultvote, String mStatusvote, COrganisateur mOrganisateur, CMap<CParticipant, String> mMapvote,
                 CMap<CCandidat, String> mMapcandidat) {
        this.mIdvote = mIdvote;
        this.mNomvote = mNomvote;
        this.mDescriptionvote = mDescriptionvote;
        this.mDatedebut = mDatedebut;
        this.mDatefin = mDatefin;
        this.mResultvote = mResultvote;
        this.mStatusvote = mStatusvote;
        this.mOrganisateur = mOrganisateur;
        this.mMapvote = mMapvote;
        this.mMapcandidat = mMapcandidat;
    }

    public int getmIdvote() {
        return mIdvote;
    }

    public void setmIdvote(int mIdvote) {
        this.mIdvote = mIdvote;
    }

    public String getmNomvote() {
        return mNomvote;
    }

    public void setmNomvote(String mNomvote) {
        this.mNomvote = mNomvote;
    }

    public String getmDescriptionvote() {
        return mDescriptionvote;
    }

    public void setmDescriptionvote(String mDescriptionvote) {
        this.mDescriptionvote = mDescriptionvote;
    }

    public String getmDatedebut() {
        return mDatedebut;
    }

    public void setmDatedebut(String mDatedebut) {
        this.mDatedebut = mDatedebut;
    }

    public String getmDatefin() {
        return mDatefin;
    }

    public void setmDatefin(String mDatefin) {
        this.mDatefin = mDatefin;
    }

    public String getmResultvote() {
        return mResultvote;
    }

    public void setmResultvote(String mResultvote) {
        this.mResultvote = mResultvote;
    }

    public String getmStatusvote() {
        return mStatusvote;
    }

    public void setmStatusvote(String mStatusvote) {
        this.mStatusvote = mStatusvote;
    }

    public COrganisateur getmOrganisateur() {
        return mOrganisateur;
    }

    public void setmOrganisateur(COrganisateur mOrganisateur) {
        this.mOrganisateur = mOrganisateur;
    }

    public CMap<CParticipant, String> getmMapvote() {
        return mMapvote;
    }

    public void setmMapvote(CMap<CParticipant, String> mMapvote) {
        this.mMapvote = mMapvote;
    }

    public CMap<CCandidat, String> getmMapcandidat() {
        return mMapcandidat;
    }

    public void setmMapcandidat(CMap<CCandidat, String> mMapcandidat) {
        this.mMapcandidat = mMapcandidat;
    }

    /**
     * Ajoute un participant supplémentaire pour le vote
     * @param puser
     * @param pstring
     */
    public void addParticipant(CParticipant puser, String pstring){
        this.mMapvote.put(puser, pstring);
    }

    /**
     * Supprime un participant à un vote
     * @param puser
     */
    public void deleteParticipant(CParticipant puser){
        puser.setmVote("Abstention");
        this.mMapvote.remove(puser);
    }

    /**
     * Suprime le vote d'un utilisateur
     * @param puser
     */
    public void deleteVote(CParticipant puser){
        puser.setmVote("Abstention");
        this.mMapvote.replace(puser, this.mMapvote.get(puser), "Abstention");
    }

    /**
     * Vote ou modification d'un vote d'un utilisateur
     * @param puser
     * @param pstring
     */
    public void voteOrReplaceVote(CParticipant puser, String pstring){
        puser.setmVote(pstring);
        this.mMapvote.replace(puser, this.mMapvote.get(puser), pstring);
    }

    @Override
    public String toString() {
        return "CVote{" +
                "mIdvote=" + mIdvote +
                ", mNomvote='" + mNomvote + '\'' +
                ", mDescriptionvote='" + mDescriptionvote + '\'' +
                ", mDatedebut='" + mDatedebut + '\'' +
                ", mDatefin='" + mDatefin + '\'' +
                ", mResultvote='" + mResultvote + '\'' +
                ", mStatusvote='" + mStatusvote + '\'' +
                ", mOrganisateur=" + mOrganisateur +
                ", mMapvote=" + mMapvote +
                ", mMapcandidat=" + mMapcandidat +
                '}';
    }
}
