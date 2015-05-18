package fr.univtln.madapm.votemanager.metier.user;

/**
 * Created by civars169 on 05/05/15.
 * copyright Christian
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.univtln.madapm.votemanager.metier.vote.CVote;

import javax.persistence.*;
import java.util.List;

/**
 * Défini les utilisauteurs qui peuvent être organisateur et participant.
 */
@Entity
@Table(name="utilisateur",uniqueConstraints =@UniqueConstraint(columnNames={"MAIL"}))
public class CUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_UTILISATEUR")
    @JsonIgnore
    public int mId;
    @Column(name="MAIL", nullable = false)
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

    public CUser(){
    }

    public CUser(int pId, String pEmail, String pPassword) {
        this.mId = pId;
        this.mEmail = pEmail;
        this.mPassword = pPassword;
    }

    public int getId() {
        return mId;
    }

  /*  public void setId(int pId) {
        this.mId = pId;
    }*/

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

    public String getSurname() {
        return mFirstName;
    }

    public void setSurname(String pFirstName) {
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
                '}';
    }
}
