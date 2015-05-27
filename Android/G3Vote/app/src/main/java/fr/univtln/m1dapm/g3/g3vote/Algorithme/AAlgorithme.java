package fr.univtln.m1dapm.g3.g3vote.Algorithme;

import fr.univtln.m1dapm.g3.g3vote.Entite.CResultat;
import fr.univtln.m1dapm.g3.g3vote.Entite.CVote;

/**
 * Created by ludo on 05/05/15.
 */
public abstract class AAlgorithme {

/*<<<<<<< HEAD
    protected List mRegles;//List<CRegle> mRegles
    protected List mCandidats;//List<CChoix> mCandidats
    protected int mIdVote;
=======*/
    protected CVote mVote;

    public AAlgorithme() {
    }

    public AAlgorithme(CVote pVote) {
        this.mVote = pVote;
    }

    protected abstract void initVote();

    protected abstract CResultat CalculResultat();
}
