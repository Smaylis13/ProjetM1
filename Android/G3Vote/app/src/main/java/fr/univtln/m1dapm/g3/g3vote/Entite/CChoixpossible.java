package fr.univtln.m1dapm.g3.g3vote.Entite;

import java.util.ArrayList;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidat;

/**
 * Created by Pierre on 09/05/2015.
 */
public class CChoixpossible {


    private int mNbVote; ///Variable de comptage du nombre de vote
    private List<CCandidat> mOrdre;

    public CChoixpossible(List<CCandidat> pChoix) {
        mOrdre = new ArrayList<CCandidat>(pChoix);
        mNbVote=0;
    }

    public int getNbVote() {
        return mNbVote;
    }
    public void setNbVote(int pnbvote){
        this.mNbVote=pnbvote;
    }

    public void updateNbVote(int mNbVote) {
        this.mNbVote += mNbVote;
    }

    public CCandidat getIndexValue(int pIndice)
    {
        return mOrdre.get(pIndice);
    }

    public List<CCandidat> getChoix() { return mOrdre; }

    @Override
    public String toString() {
        return "CChoixpossible{" +
                "mNbVote=" + mNbVote +
                ", mOrdre=" + mOrdre +
                '}';
    }

}
