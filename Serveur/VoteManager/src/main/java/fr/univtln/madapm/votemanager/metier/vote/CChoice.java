package fr.univtln.madapm.votemanager.metier.vote;

import fr.univtln.madapm.votemanager.metier.user.CUser;

import javax.persistence.*;

/**
 * Created by civars169 on 05/05/15.
 * copyright Christian
 */
@Entity
@Table(name="participe")
public class CChoice {

    @EmbeddedId
    private CKeyChoice mIdChoix;

    @ManyToOne
    @MapsId("mIdVote")
    @JoinColumn(name = "ID_VOTE")
    private CVote mVote;

    @ManyToOne
    @MapsId("mIdUser")
    @JoinColumn(name = "ID_UTILISATEUR")
    private CUser mUser;

    @ManyToOne
    @MapsId("mIdCandidate")
    @JoinColumn(name = "ID_CANDIDAT" )
    private CCandidate mCandidate;

    @Column(name="SCORE")
    private int mScore;

    public CChoice(){}

    public CChoice(CKeyChoice pIdChoix, CVote pVote, CUser pUser, CCandidate pCandidate, int pScore){
        this.mIdChoix = pIdChoix;
        this.mVote = pVote;
        this.mUser = pUser;
        this.mCandidate = pCandidate;
        this.mScore = pScore;
    }

    @Override
    public String toString() {
        return "CChoice{" +
                "mIdChoix=" + mIdChoix +
                ", mVote=" + mVote +
                ", mUser=" + mUser +
                ", mCandidate=" + mCandidate +
                ", mScore=" + mScore +
                '}';
    }
}

