package fr.univtln.madapm.votemanager.metier.vote;

import com.fasterxml.jackson.annotation.*;
import fr.univtln.madapm.votemanager.dao.CTypeDAO;
import fr.univtln.madapm.votemanager.dao.CUserDAO;
import fr.univtln.madapm.votemanager.metier.user.CUser;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by civars169 on 05/05/15.
 * copyright Christian
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "CVote.findAllFromUser", query = "SELECT c FROM CVote c where (c.mOrganisateur= :User) or c.mIdVote in :IdVotes"),
        @NamedQuery(name = "CVote.findOrgaByUser", query = "SELECT c FROM CVote c where (c.mOrganisateur= :User)")
})
@Table(name="vote")
public class CVote implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_VOTE")
    @JsonIgnore
    private int mIdVote;
    @Column(name="NOM_VOTE")
    private String mVoteName;
    @Column(name="DESCRIPTION_VOTE")
    private String mDescriptionVote;
    @Column(name="DATE_DEBUT_VOTE")
    private Timestamp mDateDebut;
    @Column(name="DATE_FIN_VOTE")
    private Timestamp mDateFin;

    @Column(name="STATUT_VOTE")
    private boolean mStatusVote;

    @JoinColumn(name = "ID_UTILISATEUR")
    @ManyToOne(fetch= FetchType.LAZY,optional=false,cascade = CascadeType.DETACH)
    private CUser mOrganisateur;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="mVote")
    private List<CResult> mResultVote;

    @JoinColumn(name="ID_TYPE")
    @ManyToOne(fetch=FetchType.LAZY,optional = false,cascade = CascadeType.DETACH)
    private CType mType;

    @JoinTable(name="parametre", joinColumns = {@JoinColumn(name="ID_VOTE",nullable = false,updatable = false)},
            inverseJoinColumns = {@JoinColumn(name="ID_REGLE",nullable = false,updatable = false)})
    @ManyToMany(fetch = FetchType.LAZY,cascade ={CascadeType.MERGE, CascadeType.PERSIST})
    private List<CRule> mRegle;


    @OneToMany(cascade = CascadeType.DETACH, mappedBy="mVote")
    private List<CChoice> mChoices=null;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy="mVote")
    private List<CCandidate> mCandidate;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy="mVote")
    private List<CDeleguation> mDeleguations;

    @JsonIgnore
    @JoinTable(name="invitation", joinColumns = {@JoinColumn(name="ID_VOTE",nullable = false,updatable = false)},
            inverseJoinColumns = {@JoinColumn(name="ID_UTILISATEUR",nullable = false,updatable = false)})
    @ManyToMany(cascade = CascadeType.DETACH)
    private List<CUser> mParticipatingUsers;

    @Transient
    private boolean mVoted;

    public CVote(){}

    public CVote(int pIdvote, String pNomvote, String pDescriptionvote, Timestamp pDatedebut, Timestamp pDatefin,
                 List<CResult> pResultvote, CType pType, List<CRule> pRegle, boolean pStatusvote,
                 CUser pOrganisateur, List<CCandidate> pCandidate) {
        this.mIdVote = pIdvote;
        this.mVoteName = pNomvote;
        this.mDescriptionVote = pDescriptionvote;
        this.mDateDebut = pDatedebut;
        this.mDateFin = pDatefin;
        this.mResultVote = pResultvote;
        this.mType = pType;
        this.mRegle = pRegle;
        this.mStatusVote = pStatusvote;
        this.mOrganisateur = pOrganisateur;
        this.mCandidate = pCandidate;
    }

    public CVote(int pIdvote, String pNomvote, String pDescriptionvote, Timestamp pDatedebut, Timestamp pDatefin,
                 List<CResult> pResultvote, CType pType, List<CRule> pRegle, boolean pStatusvote,
                 CUser pOrganisateur, List<CChoice> pChoices, List<CCandidate> pCandidate) {
        this.mIdVote = pIdvote;
        this.mVoteName = pNomvote;
        this.mDescriptionVote = pDescriptionvote;
        this.mDateDebut = pDatedebut;
        this.mDateFin = pDatefin;
        this.mResultVote = pResultvote;
        this.mType = pType;
        this.mRegle = pRegle;
        this.mStatusVote = pStatusvote;
        this.mOrganisateur = pOrganisateur;
        this.mChoices = pChoices;
        this.mCandidate = pCandidate;
    }

    @JsonGetter("participants")
    public List<CUser> getParticipatingUsers() {
        /*List<Integer> lIdUsers=new ArrayList<>();
        for(CUser lUser:this.mParticipatingUsers){
            lIdUsers.add(lUser.getUserId());
        }

        return lIdUsers;*/
        CUserDAO lUserDAO=new CUserDAO();
        for(CUser lUser:this.mParticipatingUsers){
            lUserDAO.detach(lUser);
            lUser.setPassword("participe");
        }
        return this.mParticipatingUsers;
    }

    @JsonSetter("participants")
    public void setParticipatingUsers(List<Integer> pParticipatingUsersId) {
        this.mParticipatingUsers=new ArrayList<>();
        CUserDAO lUserDAO=new CUserDAO();
        for(int lId:pParticipatingUsersId){
            mParticipatingUsers.add(lUserDAO.findByID(lId));
        }
    }

    public int getIdVote(){return mIdVote;}
    public String getVoteName() {
        return mVoteName;
    }

    public void setVoteName(String pNomvote) {
        this.mVoteName = pNomvote;
    }

    public String getDescriptionVote() {
        return mDescriptionVote;
    }

    public void setDescriptionVote(String pDescriptionvote) {
        this.mDescriptionVote = pDescriptionvote;
    }

    public Timestamp getDateDebut() {
        return mDateDebut;
    }

    public void setDateDebut(Timestamp pDatedebut) {
        this.mDateDebut = pDatedebut;
    }

    public Timestamp getDateFin() {
        return mDateFin;
    }

    public void setDateFin(Timestamp pDatefin) {
        this.mDateFin = pDatefin;
    }

    /*public List<CResult> getResultVote() {
        return mResultVote;
    }

    public void setResultVote(List<CResult> pResultvote) {
        this.mResultVote = pResultvote;
    }*/

    public CType getTypes() {
        return mType;
    }

    public void setTypes(int pIdType) {
        CTypeDAO lTypeDAO=new CTypeDAO();
        this.mType=lTypeDAO.findById(pIdType);
    }

    public List<CRule> getRegles() {
        return mRegle;
    }

    public void setRegles(List<CRule> mRegle) {
        this.mRegle = mRegle;
    }

    public boolean getStatusVote() {
        return mStatusVote;
    }

    public void setStatusVote(boolean pStatusvote) {
        this.mStatusVote = pStatusvote;
    }

    public int getOrganisateur() {
        return mOrganisateur.getUserId();
    }

    public void setOrganisateur(int pIdOrga) {
        CUserDAO lUserDao=new CUserDAO();
        this.mOrganisateur = lUserDao.findByID(pIdOrga);
    }


    public List<CResult> getResultVote() {
        return mResultVote;
    }

    public void setResultVote(List<CResult> pResultVote) {
        this.mResultVote = pResultVote;
    }

    public boolean getVoted(){
        return mVoted;
    }

    public void setterVoted(boolean pVoted){
        this.mVoted=pVoted;
    }


    /* public List<CChoice> getVotes() {
        return mChoices;
    }

    public void setVotes(List<CChoice> pChoices) {
        this.mChoices = pChoices;
    }*/

    @JsonIgnore
    public List<CChoice> getChoices() {
        return mChoices;
    }

    public List<CCandidate> getCandidates() {
        return mCandidate;
    }

    public void setCandidates(List<CCandidate> pCandidate) {
        this.mCandidate = pCandidate;
    }

    /**
     * Ajoute une sélection pour le vote
     *
     * @param pChoix Choix d'un participant
     */
    public void addParticipant(CChoice pChoix){
        this.mChoices.add(pChoix);
    }

    /**
     * Ajoute le choix d'un participant
     *
     * @param pChoice Choix d'un votant
     */
    public void addChoice(CChoice pChoice){
        this.mChoices.add(pChoice);
    }

    @Override
    public String toString() {
        return "CVote{" +
                "mIdvote=" + mIdVote +
                ", mNomvote='" + mVoteName + '\'' +
                ", mDescriptionvote='" + mDescriptionVote + '\'' +
                ", mDatedebut='" + mDateDebut + '\'' +
                ", mDatefin='" + mDateFin + '\'' +
                ", mResultvote='" + mResultVote + '\'' +
                ", mType='" + mType + '\'' +
                ", mRegle='" + mRegle + '\'' +
                ", mStatusvote='" + mStatusVote + '\'' +
                ", mOrganisateur=" + mOrganisateur +
                ", mChoices=" + mChoices +
                ", mCandidate=" + mCandidate +
                '}';
    }
}
