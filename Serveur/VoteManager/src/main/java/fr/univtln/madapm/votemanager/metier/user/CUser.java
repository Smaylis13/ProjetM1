package fr.univtln.madapm.votemanager.metier.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.univtln.madapm.votemanager.metier.vote.CChoice;
import fr.univtln.madapm.votemanager.metier.vote.CDeleguation;
import fr.univtln.madapm.votemanager.metier.vote.CVote;

import javax.persistence.*;
import java.util.List;

/**
 * Created by civars169 on 05/05/15.
 * copyright Christian
 */

/**
 * Défini les utilisauteurs qui peuvent être organisateur et participant.
 */

@Entity
@Table(name="utilisateur",uniqueConstraints =@UniqueConstraint(columnNames={"MAIL"}))
@NamedQueries({
        @NamedQuery(name = "CUser.findAll", query = "SELECT c FROM CUser c where c.mEmail= :emailUser")
})
public class CUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_UTILISATEUR")
    @JsonIgnore
    public int mId;
    @Column(name="MAIL", nullable = false,unique = true)
    private String mEmail;
    @Column(name="MOT_DE_PASSE",nullable = false)
    private String mPassword;
    @Column(name="NOM",nullable = false)
    private String mName;
    @Column(name="PRENOM",nullable = false)
    private String mFirstName;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="appartient", joinColumns = {@JoinColumn(name="ID_UTILISATEUR",nullable = false,updatable = false)},
            inverseJoinColumns = {@JoinColumn(name="ID_GROUPE",nullable = false,updatable = false)})
    private List<CGroup> mListGroups;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="a_contact", joinColumns = {@JoinColumn(name="ID_UTILISATEUR",nullable = false,updatable = false)},
            inverseJoinColumns = {@JoinColumn(name="ID_CONTACT",nullable = false,updatable = false)})
    private List<CUser> mListContacts;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy="mOrganisateur")
    private List<CVote> mOrganisedVotes=null;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy="mUser")
    private List<CChoice> mParticipations;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy="mUser")
    private List<CDeleguation> mMadeDeleguations;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy="mRepresentativeUser")
    private List<CDeleguation> mReceivedDeleguations;

    public CUser(){
    }

    public CUser(int pId, String pEmail, String pPassword) {
        this.mId = pId;
        this.mEmail = pEmail;
        this.mPassword = pPassword;
    }

    public int getmId() {
        return mId;
    }

  /*  public void setId(int pId) {
        this.mId = pId;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CUser user = (CUser) o;

        if(this.getmEmail()!=user.getmEmail()) return false;

        if(this.getmId()!=user.getmId()) return false;

        if(this.getmName()!=user.getmName()) return false;

        if(this.getmFirstName()!=user.getmFirstName()) return false;

        if(this.getmPassword()!=user.getmPassword()) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return this.getmEmail()!=null?this.getmEmail().hashCode():0;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String pEmail) {
        this.mEmail = pEmail;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String pPassword) {
        this.mPassword = pPassword;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String pName) {
        this.mName = pName;
    }

    public String getmFirstName() {
        return mFirstName;
    }

    public void setmFirstName(String pFirstName) {
        this.mFirstName = pFirstName;
    }

    /* public List<CVote> getmOrganisedVotes() {
        return mOrganisedVotes;
    }*/

    @Override
    public String toString() {
        return "CUser{" +
                "mId=" + mId +
                ", mEmail='" + mEmail + '\'' +
                ", mPassword='" + mPassword + '\'' +
                ", mName='" + mName + '\'' +
                ", mFirstName='" + mFirstName + '\'' +
                '}';
    }
}
