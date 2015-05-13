package fr.univtln.madapm.votemanager.metier.vote;

import fr.univtln.madapm.votemanager.metier.CMap;
import fr.univtln.madapm.votemanager.metier.user.COrganizer;
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
    private CResult mResultvote;
    private CType mType;
    private CMap<CRule, String> mRegle;
    private String mStatusvote;

    private COrganizer mOrganisateur;
    private CMap<CParticipant, CChoix> mMapvote = new CMap<>();
    private CMap<CCandidate, String> mMapcandidat = new CMap<>();

    public CVote(int pIdvote, String pNomvote, String pDescriptionvote, String pDatedebut, String pDatefin,
                 CResult pResultvote, CType pType, CMap<CRule, String> pRegle, String pStatusvote,
                 COrganizer pOrganisateur,
                 CMap<CParticipant, CChoix> pMapvote, CMap<CCandidate, String> pMapcandidat) {
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

    public CResult getResultvote() {
        return mResultvote;
    }

    public void setResultvote(CResult pResultvote) {
        this.mResultvote = pResultvote;
    }

    public CType getType() {
        return mType;
    }

    public void setType(CType pType) {
        this.mType = pType;
    }

    public CMap<CRule, String> getmRegle() {
        return mRegle;
    }

    public void setmRegle(CMap<CRule, String> mRegle) {
        this.mRegle = mRegle;
    }

    public String getStatusvote() {
        return mStatusvote;
    }

    public void setStatusvote(String pStatusvote) {
        this.mStatusvote = pStatusvote;
    }

    public COrganizer getOrganisateur() {
        return mOrganisateur;
    }

    public void setOrganisateur(COrganizer pOrganisateur) {
        this.mOrganisateur = pOrganisateur;
    }

    public CMap<CParticipant, CChoix> getMapvote() {
        return mMapvote;
    }

    public void setMapvote(CMap<CParticipant, CChoix> pMapvote) {
        this.mMapvote = pMapvote;
    }

    public CMap<CCandidate, String> getMapcandidat() {
        return mMapcandidat;
    }

    public void setMapcandidat(CMap<CCandidate, String> pMapcandidat) {
        this.mMapcandidat = pMapcandidat;
    }

    /**
     * Ajoute un participant supplémentaire pour le vote
     * @param puser Participant concerné
     * @param pchoix Vote de l'partivipant
     */
    public void addParticipant(CParticipant puser, CChoix pchoix){
        this.mMapvote.put(puser, pchoix);
    }

    /**
     * Supprime un participant à un vote
     * @param puser Participant concerné
     */
    public void deleteParticipant(CParticipant puser){
        puser.setMonVote("Abstention");
        this.mMapvote.remove(puser);
    }

    /**
     * Suprime le vote d'un utilisateur
     * @param puser Participant concerné
     */
    public void deleteVote(CParticipant puser){
        puser.setMonVote("Abstention");
        //this.mMapvote.replace(puser, this.mMapvote.get(puser), "Abstention"); TODO mettre à jour
    }

    /**
     * Vote ou modification d'un vote d'un utilisateur
     * @param puser Participant concerné
     * @param pstring Vote du participant
     */
    public void voteOrReplaceVote(CParticipant puser, String pstring){
        puser.setMonVote(pstring);
        //this.mMapvote.replace(puser, this.mMapvote.get(puser), pstring); TODO mettre à jour
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
