package fr.univtln.m1dapm.g3.g3vote.Algorithme;

import java.util.ArrayList;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Entite.CChoix;
import fr.univtln.m1dapm.g3.g3vote.Entite.CRegle;
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
    protected List<CRegle> mRegles;
    protected List<CChoix> mCandidats;
    protected CVote mVote;

    public AAlgorithme() {
    }

    public AAlgorithme(CVote pVote) {
        this.mVote = pVote;
    }

    /// Methode de mise en place des regles
    protected void getRegles()
    {
        mRegles = new ArrayList<CRegle>();

    }

    protected abstract void initVote();
}
