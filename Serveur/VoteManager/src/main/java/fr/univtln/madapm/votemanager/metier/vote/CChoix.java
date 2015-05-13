package fr.univtln.madapm.votemanager.metier.vote;

/**
 * Created by civars169 on 05/05/15.
 * copyright Christian
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

