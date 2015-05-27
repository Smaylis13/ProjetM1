package fr.univtln.m1dapm.g3.g3vote.Algorithme.Borda;

import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;

/**
 * Created by lyamsi on 12/05/15.
 */
public class CCandidateBorda extends CCandidate {
    private int mTotal;

    public CCandidateBorda(int mId, String mNom, String mDescription) {
        super(mId, mNom, mDescription);
        this.mTotal = 0;
    }

    public CCandidateBorda(String mNom) {
        super(mNom);
        this.mTotal = 0;
    }

    public int getmTotal() {
        return mTotal;
    }

    public void setmTotal(int mTotal) {
        this.mTotal = mTotal;
    }

    @Override
    public String toString() {
        return super.toString()+"CCandidaatBorda{" +
                "mTotal=" + mTotal +
                '}';
    }
}
