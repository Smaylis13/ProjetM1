package fr.univtln.madapm.votemanager.metier.vote;

import java.util.List;

/**
 * Created by civars169 on 05/05/15.
 * copyright Christian
 */
public class CListChoix extends CChoix<List<CCandidate>>
{
    public CListChoix(List<CCandidate> pChoix) {
        super(pChoix);
    }

    public CCandidate getIndexValue(int pIndice)
    {
        return (CCandidate)mChoix.get(pIndice);
    }


}
