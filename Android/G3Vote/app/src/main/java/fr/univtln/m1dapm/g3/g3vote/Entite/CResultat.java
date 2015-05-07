package fr.univtln.m1dapm.g3.g3vote.Entite;

/**
 * Created by ludo on 05/05/15.
 */
public class CResultat<T> {

    private T mValeur;

    public CResultat() {
    }

    public CResultat(T pValeur) {
        mValeur = pValeur;
    }

    public T getmValeur() {
        return mValeur;
    }

    public void setmValeur(T mValeur) {
        this.mValeur = mValeur;
    }
}
