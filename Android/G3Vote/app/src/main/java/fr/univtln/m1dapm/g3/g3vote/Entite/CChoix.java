package fr.univtln.m1dapm.g3.g3vote.Entite;

/**
 * Created by ludo on 05/05/15.
 */
public class CChoix {

    private int mNbVote; ///Variable de comptage du nombre de vote

    public CChoix() {
    }

    public int getmNbVote() {
        return mNbVote;
    }

    public void updatemNbVote(int mNbVote) {
        this.mNbVote += mNbVote;
    }
}
