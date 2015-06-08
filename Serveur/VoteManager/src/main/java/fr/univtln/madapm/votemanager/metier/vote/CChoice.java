package fr.univtln.madapm.votemanager.metier.vote;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import fr.univtln.madapm.votemanager.dao.CCandidateDAO;
import fr.univtln.madapm.votemanager.dao.CUserDAO;
import fr.univtln.madapm.votemanager.dao.CVoteDAO;
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

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "ID_VOTE")
    private CVote mVote;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "ID_UTILISATEUR")
    private CUser mUser;

    @ManyToOne(cascade = CascadeType.DETACH)
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

    public int getVote() {
        return mVote.getIdVote();
    }

    @JsonSetter("idVote")
    public void setVote(int pIdVote) {
        CVoteDAO lVoteDAO=new CVoteDAO();
        this.mVote = lVoteDAO.findById(pIdVote);
    }

    public int getUser() {
        return mUser.getUserId();
    }

    @JsonSetter("idUser")
    public void setUser(int pIdUser) {
        CUserDAO lUserDAO=new CUserDAO();
        this.mUser = lUserDAO.findByID(pIdUser);
    }

    public int getCandidate() {
        return mCandidate.getIdCandidat();
    }

    @JsonSetter("idCandidate")
    public void setCandidate(int pIdCandidate) {
        CCandidateDAO lCandidateDAO=new CCandidateDAO();
        this.mCandidate = lCandidateDAO.findById(pIdCandidate);
    }


    public int getScore() {
        return mScore;
    }

    @JsonSetter("score")
    public void setScore(int mScore) {
        this.mScore = mScore;
    }
}

