package fr.univtln.madapm.votemanager.metier.vote;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.univtln.madapm.votemanager.metier.CMap;
import fr.univtln.madapm.votemanager.metier.user.COrganizer;
import fr.univtln.madapm.votemanager.metier.user.CParticipant;
import fr.univtln.madapm.votemanager.metier.user.CUser;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;
import java.util.Map;

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
    @ManyToOne(fetch= FetchType.LAZY,optional=false,cascade = CascadeType.PERSIST)
    private CUser mOrganisateur;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="mVote")
    private List<CResult> mResultVote=null;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="certifie", joinColumns = {@JoinColumn(name="ID_VOTE",nullable = false,updatable = false)},
            inverseJoinColumns = {@JoinColumn(name="ID_TYPE",nullable = false,updatable = false)})
    private List<CType> mType;

    @JoinTable(name="parametre", joinColumns = {@JoinColumn(name="ID_VOTE",nullable = false,updatable = false)},
            inverseJoinColumns = {@JoinColumn(name="ID_REGLE",nullable = false,updatable = false)})
    @ManyToMany(fetch = FetchType.LAZY,cascade ={CascadeType.MERGE, CascadeType.PERSIST})
    private List<CRule> mRegle;


    @OneToMany(cascade = CascadeType.ALL, mappedBy="mVote")
    private List<CChoice> mChoices=null;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="mVote")
    private List<CCandidate> mCandidate;

    public CVote(){}

    public CVote(int pIdvote, String pNomvote, String pDescriptionvote, String pDatedebut, String pDatefin,
                 List<CResult> pResultvote, List<CType> pType, List<CRule> pRegle, boolean pStatusvote,
                 CUser pOrganisateur,
                 List<CChoice> pChoices, List<CCandidate> pCandidate) {
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


    public int getIdVote(){return mIdVote;}
    public String getVoteName() {
        return mNomVote;
    }

    public void setVoteName(String pNomvote) {
        this.mNomVote = pNomvote;
    }

    public String getDescriptionVote() {
        return mDescriptionVote;
    }

    public void setDescriptionVote(String pDescriptionvote) {
        this.mDescriptionVote = pDescriptionvote;
    }

    public String getDateDebut() {
        return mDateDebut;
    }

    public void setDateDebut(String pDatedebut) {
        this.mDateDebut = pDatedebut;
    }

    public String getDateFin() {
        return mDateFin;
    }

    public void setDateFin(String pDatefin) {
        this.mDateFin = pDatefin;
    }

    /*public List<CResult> getResultVote() {
        return mResultVote;
    }

    public void setResultVote(List<CResult> pResultvote) {
        this.mResultVote = pResultvote;
    }*/

    public List<CType> getType() {
        return mType;
    }

    public void setType(List<CType> pType) {
        this.mType = pType;
    }

    public List<CRule> getRegle() {
        return mRegle;
    }

    public void setRegle(List<CRule> mRegle) {
        this.mRegle = mRegle;
    }

    public boolean getStatusVote() {
        return mStatusVote;
    }

    public void setStatusVote(boolean pStatusvote) {
        this.mStatusVote = pStatusvote;
    }

    public CUser getOrganisateur() {
        return mOrganisateur;
    }

    public void setOrganisateur(CUser pOrganisateur) {
        this.mOrganisateur = pOrganisateur;
    }

   /* public List<CChoice> getVotes() {
        return mChoices;
    }

    public void setVotes(List<CChoice> pChoices) {
        this.mChoices = pChoices;
    }*/

    public List<CCandidate> getCandidates() {
        return mCandidate;
    }

   /* public void setCandidates(List<CCandidate> pCandidate) {
        this.mCandidate = pCandidate;
    }*/

    /**
     * Ajoute une sélection pour le vote
     *
     * @param pChoix
     */
    public void addParticipant(CChoice pChoix){
        this.mChoices.add(pChoix);
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
