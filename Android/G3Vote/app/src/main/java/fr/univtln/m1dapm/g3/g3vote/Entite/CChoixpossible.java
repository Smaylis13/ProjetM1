package fr.univtln.m1dapm.g3.g3vote.Entite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pierre on 09/05/2015.
 */
public class CChoixpossible {


    private int mNbVote; ///Variable de comptage du nombre de vote
    private List<CCandidate> mOrdre;

    public CChoixpossible(List<CCandidate> pChoix) {
        mOrdre = new ArrayList<CCandidate>(pChoix);
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

    public CCandidate getIndexValue(int pIndice)
    {
        return mOrdre.get(pIndice);
    }

    public List<CCandidate> getChoix() { return mOrdre; }

    @Override
    public String toString() {
        return "CChoixpossible{" +
                "mNbVote=" + mNbVote +
                ", mOrdre=" + mOrdre +
                '}';
    }

}
