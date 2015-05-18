package fr.univtln.madapm.votemanager.metier.vote;

import fr.univtln.madapm.votemanager.metier.user.CUser;

import javax.persistence.*;

/**
 * Created by Ookami on 17/05/2015.
 */
@Entity
@Table(name="delegue")
public class CDeleguation {

    @EmbeddedId
    private CKeyDeleguation mIdDeleguation;

    @ManyToOne
    @MapsId("mIdUser")
    @JoinColumn(name = "ID_UTILISATEUR")
    private CUser mUser;

    @ManyToOne
    @MapsId("mIdRepresentative")
    @JoinColumn(name = "ID_MANDATAIRE")
    private CUser mRepresentativeUser;

    @ManyToOne
    @MapsId("mIdVote")
    @JoinColumn(name = "ID_VOTE" )
    private CVote mVote;

    public CDeleguation(){}

    public CDeleguation(CUser pUser, CUser pRepresentativeUser, CVote pVote) {
        this.mUser = pUser;
        this.mRepresentativeUser = pRepresentativeUser;
        this.mVote = pVote;
    }
}
