package fr.univtln.madapm.votemanager.metier.vote;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by civars169 on 12/05/15.
 * copyright Christian
 */
@Entity
@Table(name="type")
public class CType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_TYPE")
    @JsonIgnore
    private int mIdType;
    @Column(name="NOM_TYPE",nullable = false)
    private String mName;
    @Column(name="DESCRIPTION_TYPE",nullable = false)
    private String mDescription;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="autorise", joinColumns = {@JoinColumn(name="ID_TYPE",nullable = false,updatable = false)},
            inverseJoinColumns = {@JoinColumn(name="ID_REGLE",nullable = false,updatable = false)})
    private List<CRule> mListRulesAllowed;

    public CType(){}

    public CType(int pidType, String pName, String pDescription) {
        this.mIdType = pidType;
        this.mName = pName;
        this.mDescription = pDescription;
    }

    public int getidType() {
        return mIdType;
    }

    public void setidType(int pIdType) {
        this.mIdType = pIdType;
    }

    public String getNom() {
        return mName;
    }

    public void setNom(String pName) {
        this.mName = pName;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String pDescription) {
        this.mDescription = pDescription;
    }

    @Override
    public String toString() {
        return "CType{" +
                "midType=" + mIdType +
                ", mName='" + mName + '\'' +
                ", mDescription='" + mDescription + '\'' +
                '}';
    }
}
