package fr.univtln.madapm.votemanager.metier.vote;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import fr.univtln.madapm.votemanager.dao.CCandidateDAO;
import fr.univtln.madapm.votemanager.dao.CVoteDAO;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by civars169 on 12/05/15.
 * copyright Christian
 */
@Entity
@Table(name="resultat")
public class CResult implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_RESULTAT")
    @JsonIgnore
    private int mIdResultat;

    @Column(name="ORDRE")
    private int mOrder; //Résultat du vote

    @JsonIgnore
    @JoinColumn(name="ID_VOTE")
    @ManyToOne(fetch= FetchType.LAZY, cascade ={CascadeType.MERGE, CascadeType.PERSIST},optional=false)
    private CVote mVote;

    @JsonIgnore
    @JoinColumn(name="ID_CANDIDAT")
    @OneToOne(fetch= FetchType.LAZY, cascade ={CascadeType.MERGE, CascadeType.PERSIST},optional=false)
    private CCandidate mCandidate;

    @Transient
    @JsonIgnore
    private int mPercent;

    public CResult(){}
    public CResult(int pIdResultat, int pOrder, CVote pVote, CCandidate pCandidate) {
        this.mIdResultat = pIdResultat;
        this.mOrder = pOrder;
        this.mVote = pVote;
        this.mCandidate = pCandidate;
    }


    /*public int getResultat() {
        return mOrder;
    }

    public void setResultat(int pOrder) {
        this.mOrder = pOrder;
    }*/

    @JsonGetter("vote")
    public int getVote() {
        return mVote.getIdVote();
    }

    @JsonSetter("vote")
    public void setVote(int pVoteId) {
        CVoteDAO lVDAO=new CVoteDAO();
        this.mVote = lVDAO.findById(pVoteId);
    }

    @JsonGetter("candidat")
    public int getCandidat() {
        return mCandidate.getIdCandidat();
    }

    @JsonSetter("candidat")
    public void setCandidat(int pCandId) {
        CCandidateDAO lCandidateDao=new CCandidateDAO();
        this.mCandidate = lCandidateDao.findById(pCandId);
    }

    @JsonGetter("order")
    public int getOrder() {
        return mOrder;
    }

    @JsonSetter("order")
    public void setOrder(int pOrder) {
        this.mOrder = pOrder;
    }

    @Override
    public String toString() {
        return "CResultat{" +
                "mIdResultat=" + mIdResultat +
                ", mOrder='" + mOrder + '\'' +
                ", mCandidate=" + mCandidate.getIdCandidat() +
                '}';
    }
}
