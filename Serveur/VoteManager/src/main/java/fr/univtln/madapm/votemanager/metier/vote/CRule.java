package fr.univtln.madapm.votemanager.metier.vote;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.univtln.madapm.votemanager.metier.user.CGroup;

import javax.persistence.*;
import java.util.List;

/**
 * Created by civars169 on 12/05/15.
 * copyright Christian
 */
@Entity
@Table(name="regle")
public class CRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_REGLE")
    @JsonIgnore
    private int mIdRegle;
    @Column(name="DESCRIPTION_REGLE")
    private String mDescription;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="autorise", joinColumns = {@JoinColumn(name="ID_REGLE",nullable = false,updatable = false)},
            inverseJoinColumns = {@JoinColumn(name="ID_TYPE",nullable = false,updatable = false)})
    private List<CType> mListTypesAllow;

    public CRule(){}
    public CRule(int pIdRegle, String pDescription) {
        this.mIdRegle = pIdRegle;
        this.mDescription = pDescription;
    }



    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String pDescription) {
        this.mDescription = pDescription;
    }

    @Override
    public String toString() {
        return "CRegle{" +
                "midRegle=" + mIdRegle +
                ", mdescription='" + mDescription + '\'' +
                '}';
    }
}
