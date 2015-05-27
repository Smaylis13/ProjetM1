package fr.univtln.m1dapm.g3.g3vote.Entite;

/**
 * Created by Pierre on 20/05/2015.
 */
public class CVoixCandidat {
    private CCandidate mcandidat;
    private int mvote;

    public CVoixCandidat() {
    }

    public CVoixCandidat(CCandidate mcandidat) {
        this.mcandidat = mcandidat;
        mvote=0;
    }

    public CVoixCandidat(CCandidate mcandidat, int mvote) {
        this.mcandidat = mcandidat;
        this.mvote = mvote;
    }

    public CCandidate getMcandidat() {
        return mcandidat;
    }

    public void setMcandidat(CCandidate mcandidat) {
        this.mcandidat = mcandidat;
    }

    public int getMvote() {
        return mvote;
    }

    public void setMvote(int mvote) {
        this.mvote = mvote;
    }
    public void addMvote(){
        mvote=mvote+1;
    }

    @Override
    public String toString() {
        return "CVoixCandidat{" +
                "mcandidat=" + mcandidat +
                ", mvote=" + mvote +
                '}';
    }



}
