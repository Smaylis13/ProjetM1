package fr.univtln.m1dapm.g3.g3vote.Entite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ludo on 05/05/15.
 */
public class CChoix {

    private int mNbVote; ///Variable de comptage du nombre de vote
    private List<CCandidat> mOrdre;

    public CChoix(List<CCandidat> pChoix) {
        mOrdre = new ArrayList<CCandidat>(pChoix);
    }

    public int getNbVote() {
        return mNbVote;
    }

    public void updateNbVote(int mNbVote) {
        this.mNbVote += mNbVote;
    }

    public CCandidat getIndexValue(int pIndice)
    {
        return mOrdre.get(pIndice);
    }

    public List<CCandidat> getChoix() { return mOrdre; }
}
