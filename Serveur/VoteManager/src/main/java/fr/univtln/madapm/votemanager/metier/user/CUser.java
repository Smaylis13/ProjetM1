package fr.univtln.madapm.votemanager.metier.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import fr.univtln.madapm.votemanager.dao.CUserDAO;
import fr.univtln.madapm.votemanager.metier.vote.CChoice;
import fr.univtln.madapm.votemanager.metier.vote.CDeleguation;
import fr.univtln.madapm.votemanager.metier.vote.CVote;

import javax.persistence.*;
import java.util.ArrayList;
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
        @NamedQuery(name = "CUser.findAll", query = "SELECT c FROM CUser c where c.mEmail= :emailUser"),
        @NamedQuery(name = "CUser.findChoicesForVote", query = "SELECT c.mIdChoice FROM CChoice c where (c.mUser= :User) and (c.mVote= :Vote)")
})
public class CUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_UTILISATEUR")
    @JsonIgnore
    public int mUserId;
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

    @ManyToMany(fetch = FetchType.LAZY, cascade ={ })
    @JoinTable(name="a_contact", joinColumns = {@JoinColumn(name="ID_UTILISATEUR",nullable = false,updatable = false)},
            inverseJoinColumns = {@JoinColumn(name="ID_CONTACT",nullable = false,updatable = false)})
    private List<CUser> mListContacts;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy="mOrganisateur")
    private List<CVote> mOrganisedVotes=null;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(name="invitation", joinColumns = {@JoinColumn(name="ID_UTILISATEUR",nullable = false,updatable = false)},
            inverseJoinColumns = {@JoinColumn(name="ID_VOTE",nullable = false,updatable = false)})
    private List<CVote> mParticipatingVotes;

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

    public CUser(int pUserId, String pEmail, String pPassword) {
        this.mUserId = pUserId;
        this.mEmail = pEmail;
        this.mPassword = pPassword;
    }

    public List<Integer> obtainParticipatingVotesIds() {
        List<Integer> lIdVotes=new ArrayList<>();
        for(CVote v:mParticipatingVotes)
            lIdVotes.add(v.getIdVote());
        return lIdVotes;
    }

    public void addParticipatingVotes(CVote pVote){
        mParticipatingVotes.add(pVote);
    }

    public int getUserId() {
        return mUserId;
    }

    @JsonSetter("userId")
    public void setId(int pId) {
        this.mUserId = pId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CUser user = (CUser) o;

        if(this.getEmail()!=user.getEmail()) return false;

        if(this.getUserId()!=user.getUserId()) return false;

        if(this.getName()!=user.getName()) return false;

        if(this.getFirstName()!=user.getFirstName()) return false;

        if(this.getPassword()!=user.getPassword()) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return this.getEmail()!=null?this.getEmail().hashCode():0;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String pEmail) {
        this.mEmail = pEmail;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String pPassword) {
        this.mPassword = pPassword;
    }

    public String getName() {
        return mName;
    }

    public void setName(String pName) {
        this.mName = pName;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String pFirstName) {
        this.mFirstName = pFirstName;
    }

    /* public List<CVote> getmOrganisedVotes() {
        return mOrganisedVotes;
    }*/

    public void addContact(CUser pUser){
        this.mListContacts.add(pUser);
    }

    public List<CUser> obtainContacts(){
        CUserDAO lUserDAO=new CUserDAO();
        for(CUser lUser:this.mListContacts) {
            lUserDAO.detach(lUser);
            lUser.setPassword("contact");
        }
        return this.mListContacts;
    }

    @Override
    public String toString() {
        return "CUser{" +
                "mUserId=" + mUserId +
                ", mEmail='" + mEmail + '\'' +
                ", mPassword='" + mPassword + '\'' +
                ", mName='" + mName + '\'' +
                ", mFirstName='" + mFirstName + '\'' +
                '}';
    }
}
