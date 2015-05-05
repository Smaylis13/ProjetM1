package fr.univtln.m1dapm.g3.g3vote.Algorithme;

import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Entite.CChoix;
import fr.univtln.m1dapm.g3.g3vote.Entite.CRegle;

/**
 * Created by ludo on 05/05/15.
 */
public abstract class AAlgorithme {

    protected List<CRegle> mRegles;
    protected List<CChoix> mCandidats;
    protected int mIdVote;

    protected abstract void initVote();
}
