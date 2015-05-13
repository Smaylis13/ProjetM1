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

    public T getValeur() {
        return mValeur;
    }

    public void setValeur(T pValeur) {
        this.mValeur = pValeur;
    }

    public void copieValeur( CResultat pResultat){
        this.setValeur((T) pResultat.getValeur());
    }
}
