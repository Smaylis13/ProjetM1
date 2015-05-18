package fr.univtln.madapm.votemanager.metier.vote;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Ookami on 17/05/2015.
 */
@Embeddable
public class CKeyDeleguation implements Serializable {
    private int mIdUser;
    private int mIdRepresentative;
    private int mIdVote;
}
