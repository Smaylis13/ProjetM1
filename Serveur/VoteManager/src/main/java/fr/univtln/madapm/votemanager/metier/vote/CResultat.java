package fr.univtln.madapm.votemanager.metier.vote;

/**
 * Created by civars169 on 12/05/15.
 * copyright Christian
 */
public class CResultat {

    private int midresultat;
    private String mresultat;
    private CVote mvote;
    private CCandidat mcandidat;

    public CResultat(int pidresultat, String presultat, CVote pvote, CCandidat pcandidat) {
        this.midresultat = pidresultat;
        this.mresultat = presultat;
        this.mvote = pvote;
        this.mcandidat = pcandidat;
    }

    public int getMidresultat() {
        return midresultat;
    }

    public void setMidresultat(int pidresultat) {
        this.midresultat = pidresultat;
    }

    public String getMresultat() {
        return mresultat;
    }

    public void setMresultat(String presultat) {
        this.mresultat = presultat;
    }

    public CVote getMvote() {
        return mvote;
    }

    public void setMvote(CVote pvote) {
        this.mvote = pvote;
    }

    public CCandidat getCandidat() {
        return mcandidat;
    }

    public void setCandidat(CCandidat pcandidat) {
        this.mcandidat = pcandidat;
    }

    @Override
    public String toString() {
        return "CResultat{" +
                "midresultat=" + midresultat +
                ", mresultat='" + mresultat + '\'' +
                ", mvote=" + mvote +
                '}';
    }
}
