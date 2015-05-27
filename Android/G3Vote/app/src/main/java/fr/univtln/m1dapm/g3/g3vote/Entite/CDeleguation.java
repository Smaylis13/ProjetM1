package fr.univtln.m1dapm.g3.g3vote.Entite;

/**
 * Created by Ookami on 27/05/2015.
 */
public class CDeleguation {
    private int mIdDeleguation;
    private CUser mUser;
    private CUser mRepresentativeUser;
    private CVote mVote;

    public CDeleguation(){}

    public CDeleguation(CUser pUser, CUser pRepresentativeUser, CVote pVote) {
        this.mUser = pUser;
        this.mRepresentativeUser = pRepresentativeUser;
        this.mVote = pVote;
    }
}
