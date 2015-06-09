package fr.univtln.madapm.votemanager.metier.vote;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by civars169 on 05/05/15.
 * copyright Christian
 */

/**
 * Un candidat est un choix lors d'un vote.
 * Exemple, banane et cerise pour voter pour son fruit préféré.
 */
@Entity
@Table(name="candidat")
public class CCandidate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_CANDIDAT")
    @JsonIgnore
    private int mIdCandidat;
    @Column(name="NOM_CANDIDAT")
    private String mNomCandidat;
    @Column(name="DESCRIPTION_CANDIDAT")
    private String mDescriptionCandidat;


    @JoinColumn(name = "ID_VOTE")
    @ManyToOne(fetch=FetchType.LAZY,optional = false,cascade ={CascadeType.MERGE, CascadeType.PERSIST})
    private CVote mVote;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy="mCandidate")
    private List<CChoice> mChoices;

    public CCandidate(int pIdCandidat, String pNomCandidat, String pDescriptionCandidat, CVote pVote) {
        this.mIdCandidat = pIdCandidat;
        this.mNomCandidat = pNomCandidat;
        this.mDescriptionCandidat = pDescriptionCandidat;
        this.mVote = pVote;
    }

    public CCandidate(String pNomCandidat) {
        this.mNomCandidat = pNomCandidat;
    }

    public CCandidate() {
    }


    @JsonGetter("id")
    public int getIdCandidat() {
        return mIdCandidat;
    }

    @JsonIgnore
    public void setIdCandidat(int mIdCandidat) {
        this.mIdCandidat = mIdCandidat;
    }

    public String getNomCandidat() {
        return mNomCandidat;
    }

    public void setNomCandidat(String pNomCandidat) {
        this.mNomCandidat = pNomCandidat;
    }

    public String getDescriptionCandidat() {
        return mDescriptionCandidat;
    }

    public void setDescriptionCandidat(String pDescriptionCandidat) {
        this.mDescriptionCandidat = pDescriptionCandidat;
    }

    public void addVote(CVote pVote){
        this.mVote=pVote;
    }

    public int getVote() {
        return mVote.getIdVote();
    }

    @Override
    public boolean equals(Object pObject) {
        if (this == pObject) return true;
        if (pObject == null || getClass() != pObject.getClass()) return false;

        CCandidate cCandidat = (CCandidate) pObject;

        if (mIdCandidat != cCandidat.mIdCandidat) return false;
        if (mDescriptionCandidat != null ? !mDescriptionCandidat.equals(cCandidat.mDescriptionCandidat)
                : cCandidat.mDescriptionCandidat != null)
            return false;
        if (!mNomCandidat.equals(cCandidat.mNomCandidat)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mIdCandidat;
        result = 31 * result + mNomCandidat.hashCode();
        result = 31 * result + (mDescriptionCandidat != null ? mDescriptionCandidat.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CCandidate{" +
                "mIdCandidat=" + mIdCandidat +
                ", mNomCandidat='" + mNomCandidat + '\'' +
                ", mDescriptionCandidat='" + mDescriptionCandidat + '\'' +
                '}';
    }
}
