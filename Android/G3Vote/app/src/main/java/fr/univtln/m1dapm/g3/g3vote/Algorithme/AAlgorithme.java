package fr.univtln.m1dapm.g3.g3vote.Algorithme;

import fr.univtln.m1dapm.g3.g3vote.Entite.CVote;

/**
 * Created by ludo on 05/05/15.
 */
public abstract class AAlgorithme {

    protected CVote mVote;

    public AAlgorithme() {
    }

    public AAlgorithme(CVote pVote) {
        this.mVote = pVote;
    }
}
