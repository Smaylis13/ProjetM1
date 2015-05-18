package fr.univtln.madapm.votemanager.metier.vote;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Ookami on 15/05/2015.
 */
@Embeddable
public class CKeyChoice implements Serializable {

    private int mIdVote;
    private int mIdCandidate;
    private int mIdUser;
}
