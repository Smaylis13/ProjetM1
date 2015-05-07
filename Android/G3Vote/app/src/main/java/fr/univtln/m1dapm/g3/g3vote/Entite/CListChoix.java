package fr.univtln.m1dapm.g3.g3vote.Entite;

import java.util.List;

/**
 * Created by ludo on 07/05/15.
 */

public class CListChoix extends CChoix<List<CCandidat>>
{
    public CListChoix(List<CCandidat> pChoix) {
        super(pChoix);
    }

    public CCandidat getIndexValue(int pIndice)
    {
        return (CCandidat)mChoix.get(pIndice);
    }


}
