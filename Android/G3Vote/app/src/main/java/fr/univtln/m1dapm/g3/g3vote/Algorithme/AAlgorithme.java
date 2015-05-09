package fr.univtln.m1dapm.g3.g3vote.Algorithme;

import java.util.List;

/**
 * Created by ludo on 05/05/15.
 */
public abstract class AAlgorithme {

    protected List mRegles;//List<CRegle> mRegles
    protected List mCandidats;//List<CChoix> mCandidats
    protected int mIdVote;

    protected abstract void initVote();
}
