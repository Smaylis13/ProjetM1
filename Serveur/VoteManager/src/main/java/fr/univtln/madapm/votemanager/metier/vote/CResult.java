package fr.univtln.madapm.votemanager.metier.vote;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by civars169 on 12/05/15.
 * copyright Christian
 */
@Entity
@Table(name="resultat")
public class CResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_RESULTAT")
    @JsonIgnore
    private int mIdResultat;

    @Column(name="ORDRE")
    private int mOrder; //Résultat du vote

    @JoinColumn(name="ID_VOTE")
    @ManyToOne(fetch= FetchType.LAZY, cascade ={CascadeType.MERGE, CascadeType.PERSIST},optional=false)
    private CVote mVote;

    @JoinColumn(name="ID_CANDIDAT")
    @OneToOne(fetch= FetchType.LAZY, cascade ={CascadeType.MERGE, CascadeType.PERSIST},optional=false)
    private CCandidate mCandidate;

    @Transient
    private int mPercent;

    public CResult(){}
    public CResult(int pIdResultat, int pOrder, CVote pVote, CCandidate pCandidate) {
        this.mIdResultat = pIdResultat;
        this.mOrder = pOrder;
        this.mVote = pVote;
        this.mCandidate = pCandidate;
    }


    public int getResultat() {
        return mOrder;
    }

    public void setResultat(int pOrder) {
        this.mOrder = pOrder;
    }

    public CVote getVote() {
        return mVote;
    }

    public void setVote(CVote pVote) {
        this.mVote = pVote;
    }

    public CCandidate getCandidat() {
        return mCandidate;
    }

    public void setCandidat(CCandidate pCandidate) {
        this.mCandidate = pCandidate;
    }

    public int getOrder() {
        return mOrder;
    }

    public void setOrder(int pOrder) {
        this.mOrder = pOrder;
    }

    @Override
    public String toString() {
        return "CResultat{" +
                "mIdResultat=" + mIdResultat +
                ", mOrder='" + mOrder + '\'' +
                ", mvote=" + mVote + '\'' +
                ", mCandidate=" + mCandidate +
                '}';
    }
}
