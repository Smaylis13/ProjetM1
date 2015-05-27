package fr.univtln.madapm.votemanager.metier.vote;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.univtln.madapm.votemanager.metier.user.CUser;

import javax.persistence.*;

/**
 * Created by civars169 on 05/05/15.
 * copyright Christian
 */
@Entity
@Table(name="participe", uniqueConstraints =@UniqueConstraint(columnNames={"ID_VOTE","ID_UTILISATEUR","ID_CANDIDAT"}))
public class CChoice {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_PARTICIPATION")
    @JsonIgnore
    private int mIdChoice;

    @ManyToOne
    @JoinColumn(name = "ID_VOTE")
    private CVote mVote;

    @ManyToOne
    @JoinColumn(name = "ID_UTILISATEUR")
    private CUser mUser;

    @ManyToOne
    @JoinColumn(name = "ID_CANDIDAT" )
    private CCandidate mCandidate;

    @Column(name="SCORE")
    private int mScore;

    public CChoice(){}

    public CChoice(CVote pVote, CUser pUser, CCandidate pCandidate, int pScore){
        this.mVote = pVote;
        this.mUser = pUser;
        this.mCandidate = pCandidate;
        this.mScore = pScore;
    }

    @Override
    public String toString() {
        return "CChoice{" +
                "mIdChoice=" + mIdChoice +
                ", mVote=" + mVote +
                ", mUser=" + mUser +
                ", mCandidate=" + mCandidate +
                ", mScore=" + mScore +
                '}';
    }
}

