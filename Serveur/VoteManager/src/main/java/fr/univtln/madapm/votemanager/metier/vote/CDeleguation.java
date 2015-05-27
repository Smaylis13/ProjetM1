package fr.univtln.madapm.votemanager.metier.vote;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.univtln.madapm.votemanager.metier.user.CUser;

import javax.persistence.*;

/**
 * Created by Ookami on 17/05/2015.
 */
@Entity
@Table(name="delegue", uniqueConstraints =@UniqueConstraint(columnNames={"ID_UTILISATEUR","ID_MANDATAIRE","ID_VOTE"}))
public class CDeleguation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_DELEGATION")
    @JsonIgnore
    private int mIdDeleguation;


    @ManyToOne
    @JoinColumn(name = "ID_UTILISATEUR")
    private CUser mUser;

    @ManyToOne
    @JoinColumn(name = "ID_MANDATAIRE")
    private CUser mRepresentativeUser;

    @ManyToOne
    @JoinColumn(name = "ID_VOTE" )
    private CVote mVote;

    public CDeleguation(){}

    public CDeleguation(CUser pUser, CUser pRepresentativeUser, CVote pVote) {
        this.mUser = pUser;
        this.mRepresentativeUser = pRepresentativeUser;
        this.mVote = pVote;
    }
}
