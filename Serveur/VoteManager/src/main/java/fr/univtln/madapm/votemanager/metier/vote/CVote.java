package fr.univtln.madapm.votemanager.metier.vote;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.univtln.madapm.votemanager.metier.user.CUser;

import javax.persistence.*;
import java.util.List;

/**
 * Created by civars169 on 05/05/15.
 * copyright Christian
 */
@Entity
@Table(name="vote")
public class CVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_VOTE")
    @JsonIgnore
    private int mIdVote;
    @Column(name="NOM_VOTE")
    private String mNomVote;
    @Column(name="DESCRIPTION_VOTE")
    private String mDescriptionVote;
    @Column(name="DATE_DEBUT_VOTE")
    private String mDateDebut;
    @Column(name="DATE_FIN_VOTE")
    private String mDateFin;

    @Column(name="STATUT_VOTE")
    private boolean mStatusVote;

    @JoinColumn(name = "ID_UTILISATEUR")
    @ManyToOne(fetch= FetchType.LAZY,optional=false)
    private CUser mOrganisateur;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="mVote")
    private List<CResult> mResultVote;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="certifie", joinColumns = {@JoinColumn(name="ID_VOTE",nullable = false,updatable = false)},
            inverseJoinColumns = {@JoinColumn(name="ID_TYPE",nullable = false,updatable = false)})
    private List<CType> mType;

    @JoinTable(name="parametre", joinColumns = {@JoinColumn(name="ID_VOTE",nullable = false,updatable = false)},
            inverseJoinColumns = {@JoinColumn(name="ID_REGLE",nullable = false,updatable = false)})
    @ManyToMany(fetch = FetchType.LAZY,cascade ={CascadeType.MERGE, CascadeType.PERSIST})
    private List<CRule> mRegle;


   /* @JsonIgnore
    @Transient
    public long mOrganisateurID=0;
*/
    @OneToMany(cascade = CascadeType.ALL, mappedBy="mVote")
    private List<CChoice> mChoices;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="mVote")
    private List<CCandidate> mCandidate;

    public CVote(){}

    public CVote(int pIdvote, String pNomvote, String pDescriptionvote, String pDatedebut, String pDatefin,
                 List<CResult> pResultvote, List<CType> pType, List<CRule> pRegle, boolean pStatusvote,
                 CUser pOrganisateur, List<CCandidate> pCandidate) {
        this.mIdVote = pIdvote;
        this.mNomVote = pNomvote;
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

    public CVote(int pIdvote, String pNomvote, String pDescriptionvote, String pDatedebut, String pDatefin,
                 List<CResult> pResultvote, List<CType> pType, List<CRule> pRegle, boolean pStatusvote,
                 CUser pOrganisateur, List<CChoice> pChoices, List<CCandidate> pCandidate) {
        this.mIdVote = pIdvote;
        this.mNomVote = pNomvote;
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

    public int getIdvote() {
        return mIdVote;
    }

    public void setIdvote(int pIdvote) {
        this.mIdVote = pIdvote;
    }

    public String getNomvote() {
        return mNomVote;
    }

    public void setNomvote(String pNomvote) {
        this.mNomVote = pNomvote;
    }

    public String getDescriptionvote() {
        return mDescriptionVote;
    }

    public void setDescriptionvote(String pDescriptionvote) {
        this.mDescriptionVote = pDescriptionvote;
    }

    public String getDatedebut() {
        return mDateDebut;
    }

    public void setDatedebut(String pDatedebut) {
        this.mDateDebut = pDatedebut;
    }

    public String getDatefin() {
        return mDateFin;
    }

    public void setDatefin(String pDatefin) {
        this.mDateFin = pDatefin;
    }

    public List<CResult> getResultvote() {
        return mResultVote;
    }

    public void setResultvote(List<CResult> pResultvote) {
        this.mResultVote = pResultvote;
    }

    public List<CType> getType() {
        return mType;
    }

    public void setType(List<CType> pType) {
        this.mType = pType;
    }

    public List<CRule> getmRegle() {
        return mRegle;
    }

    public void setmRegle(List<CRule> mRegle) {
        this.mRegle = mRegle;
    }

    public boolean getStatusvote() {
        return mStatusVote;
    }

    public void setStatusvote(boolean pStatusvote) {
        this.mStatusVote = pStatusvote;
    }

    public CUser getOrganisateur() {
        return mOrganisateur;
    }

    public void setOrganisateur(CUser pOrganisateur) {
        this.mOrganisateur = pOrganisateur;
    }

    public List<CChoice> getMapvote() {
        return mChoices;
    }

    public void setMapvote(List<CChoice> pChoices) {
        this.mChoices = pChoices;
    }

    public List<CCandidate> getMapcandidat() {
        return mCandidate;
    }

    public void setMapcandidat(List<CCandidate> pCandidate) {
        this.mCandidate = pCandidate;
    }

    /**
     * Ajoute une sélection pour le vote
     *
     * @param pChoix
     */
    public void addParticipant(CChoice pChoix){
        this.mChoices.add(pChoix);
    }

    /**
     * Ajoute le choix d'un participant
     *
     * @param pUser Participant concerné
     * @param pCandidate Candidat concerné
     * @param pChois chois pour ce candidat
     */
    public void addChoice(CUser pUser, CCandidate pCandidate, int pChois){
        CKeyChoice lKeyChoice = new CKeyChoice();
        CChoice lChoice = new CChoice(lKeyChoice, this, pUser, pCandidate, pChois);
    }

    @Override
    public String toString() {
        return "CVote{" +
                "mIdvote=" + mIdVote +
                ", mNomvote='" + mNomVote + '\'' +
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
