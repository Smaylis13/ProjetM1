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
    private CResultat mResultvote;
    private CType mType;
    private CMap<CRegle, String> mRegle;
    private String mStatusvote;

    private COrganisateur mOrganisateur;
    private CMap<CParticipant, String> mMapvote = new CMap<CParticipant,String>();
    private CMap<CCandidat, String> mMapcandidat = new CMap<CCandidat, String>();

    public CVote(int pIdvote, String pNomvote, String pDescriptionvote, String pDatedebut, String pDatefin,
                 CResultat pResultvote, CType pType, CMap<CRegle, String> pRegle, String pStatusvote,
                 COrganisateur pOrganisateur,
                 CMap<CParticipant, String> pMapvote, CMap<CCandidat, String> pMapcandidat) {
        this.mIdvote = pIdvote;
        this.mNomvote = pNomvote;
        this.mDescriptionvote = pDescriptionvote;
        this.mDatedebut = pDatedebut;
        this.mDatefin = pDatefin;
        this.mResultvote = pResultvote;
        this.mType = pType;
        this.mRegle = pRegle;
        this.mStatusvote = pStatusvote;
        this.mOrganisateur = pOrganisateur;
        this.mMapvote = pMapvote;
        this.mMapcandidat = pMapcandidat;
    }

    public int getIdvote() {
        return mIdvote;
    }

    public void setIdvote(int pIdvote) {
        this.mIdvote = pIdvote;
    }

    public String getNomvote() {
        return mNomvote;
    }

    public void setNomvote(String pNomvote) {
        this.mNomvote = pNomvote;
    }

    public String getDescriptionvote() {
        return mDescriptionvote;
    }

    public void setDescriptionvote(String pDescriptionvote) {
        this.mDescriptionvote = pDescriptionvote;
    }

    public String getDatedebut() {
        return mDatedebut;
    }

    public void setDatedebut(String pDatedebut) {
        this.mDatedebut = pDatedebut;
    }

    public String getDatefin() {
        return mDatefin;
    }

    public void setDatefin(String pDatefin) {
        this.mDatefin = pDatefin;
    }

    public CResultat getResultvote() {
        return mResultvote;
    }

    public void setResultvote(CResultat pResultvote) {
        this.mResultvote = pResultvote;
    }

    public CType getType() {
        return mType;
    }

    public void setType(CType pType) {
        this.mType = pType;
    }

    public CMap<CRegle, String> getmRegle() {
        return mRegle;
    }

    public void setmRegle(CMap<CRegle, String> mRegle) {
        this.mRegle = mRegle;
    }

    public String getStatusvote() {
        return mStatusvote;
    }

    public void setStatusvote(String pStatusvote) {
        this.mStatusvote = pStatusvote;
    }

    public COrganisateur getOrganisateur() {
        return mOrganisateur;
    }

    public void setOrganisateur(COrganisateur pOrganisateur) {
        this.mOrganisateur = pOrganisateur;
    }

    public CMap<CParticipant, String> getMapvote() {
        return mMapvote;
    }

    public void setMapvote(CMap<CParticipant, String> pMapvote) {
        this.mMapvote = pMapvote;
    }

    public CMap<CCandidat, String> getMapcandidat() {
        return mMapcandidat;
    }

    public void setMapcandidat(CMap<CCandidat, String> pMapcandidat) {
        this.mMapcandidat = pMapcandidat;
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
        puser.setMonVote("Abstention");
        this.mMapvote.remove(puser);
    }

    /**
     * Suprime le vote d'un utilisateur
     * @param puser
     */
    public void deleteVote(CParticipant puser){
        puser.setMonVote("Abstention");
        this.mMapvote.replace(puser, this.mMapvote.get(puser), "Abstention");
    }

    /**
     * Vote ou modification d'un vote d'un utilisateur
     * @param puser
     * @param pstring
     */
    public void voteOrReplaceVote(CParticipant puser, String pstring){
        puser.setMonVote(pstring);
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
                ", mType='" + mType + '\'' +
                ", mRegle='" + mRegle + '\'' +
                ", mStatusvote='" + mStatusvote + '\'' +
                ", mOrganisateur=" + mOrganisateur +
                ", mMapvote=" + mMapvote +
                ", mMapcandidat=" + mMapcandidat +
                '}';
    }
}
