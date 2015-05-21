package fr.univtln.madapm.votemanager.metier.user;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.util.List;

/**
 * Created by civars169 on 05/05/15.
 * copyright Christian
 */

/**
 * Un groupe correspond à une liste de contacts pour un utilisateur.
 */
@Entity
@Table(name="groupe")
public class CGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_GROUPE")
    @JsonIgnore
    private int mIdGroup;
    @Column(name="NOM_GROUPE")
    private String mGroupName;

    @Column(name="DESCRIPTION_GROUPE")
    private String mGroupDescription;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="appartient", joinColumns = {@JoinColumn(name="ID_GROUPE",nullable = false,updatable = false)},
            inverseJoinColumns = {@JoinColumn(name="ID_UTILISATEUR",nullable = false,updatable = false)})
    private List<CUser> mListUsers;

    public CGroup(){}

    public CGroup(int pIdGroup, String pGroupName, String pGroupDescription, List<CUser> pListUsers) {
        this.mIdGroup = pIdGroup;
        this.mGroupName = pGroupName;
        this.mGroupDescription = pGroupDescription;
        this.mListUsers = pListUsers;
    }

    public int getIdgroupe() {
        return mIdGroup;
    }

    public void setIdgroupe(int pIdgroupe) {
        this.mIdGroup = pIdgroupe;
    }

    public String getNomgroupe() {
        return mGroupName;
    }

    public void setNomgroupe(String pGroupName) {
        this.mGroupName = pGroupName;
    }

    public String getDescriptiongroupe() {
        return mGroupDescription;
    }

    public void setDescriptiongroupe(String pGroupDescription) {
        this.mGroupDescription = pGroupDescription;
    }

    public List<CUser> getListUsers() {
        return mListUsers;
    }

    public void setListUsers(List<CUser> pListUsers) {
        this.mListUsers = pListUsers;
    }

    @Override
    public String toString() {
        return "CGroupe{" +
                "mIdGroup=" + mIdGroup +
                ", mGroupName='" + mGroupName + '\'' +
                ", mGroupDescription='" + mGroupDescription + '\'' +
                ", mListUsers=" + mListUsers +
                '}';
    }
}
