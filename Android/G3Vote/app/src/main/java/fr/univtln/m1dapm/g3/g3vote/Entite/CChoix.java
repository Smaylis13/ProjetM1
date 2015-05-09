package fr.univtln.m1dapm.g3.g3vote.Entite;

/**
 * Created by ludo on 05/05/15.
 */
public class CChoix<T> {

    protected T mChoix;

    public CChoix(T pChoix) {
        mChoix = pChoix;
    }

    public T getChoix() { return mChoix; }

    public void setChoix(T pChoix) {
        this.mChoix = pChoix;
    }
}

