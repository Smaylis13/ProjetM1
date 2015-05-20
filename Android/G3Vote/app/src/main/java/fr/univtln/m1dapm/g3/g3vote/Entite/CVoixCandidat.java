package fr.univtln.m1dapm.g3.g3vote.Entite;

/**
 * Created by Pierre on 20/05/2015.
 */
public class CVoixCandidat {
    private CCandidat mcandidat;
    private int mvote;

    public CVoixCandidat() {
    }

    public CVoixCandidat(CCandidat mcandidat) {
        this.mcandidat = mcandidat;
        mvote=0;
    }

    public CVoixCandidat(CCandidat mcandidat, int mvote) {
        this.mcandidat = mcandidat;
        this.mvote = mvote;
    }

    public CCandidat getMcandidat() {
        return mcandidat;
    }

    public void setMcandidat(CCandidat mcandidat) {
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
