package fr.univtln.m1dapm.g3.g3vote.Algorithme.STV;

import android.support.v4.app.INotificationSideChannel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.univtln.m1dapm.g3.g3vote.Algorithme.AAlgorithme;
import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidat;
import fr.univtln.m1dapm.g3.g3vote.Entite.CChoix;
import fr.univtln.m1dapm.g3.g3vote.Entite.CRegle;
import fr.univtln.m1dapm.g3.g3vote.Entite.CResultat;
import fr.univtln.m1dapm.g3.g3vote.Entite.CVote;

/**
 * Created by ludo on 05/05/15.
 */
public class CAlgoSTV extends AAlgorithme {

    int mQuota, mNbElu, mNbVote;

    Map<CChoix, Integer> mChoice;

    public CAlgoSTV() {
        super();
    }

    public CAlgoSTV(CVote pVote) {
        super(pVote);
    }



    /// Methode d'initialisation du vote (charge les regles)
    @Override
    protected void initVote()
    {
        int lNbVote=0, lNbElu=0;
        getRegles();
        getChoix();

        Calcul_Quota();
    }


    /// Charge la liste des choix faits par les votants de la BDD
    private void getChoix()
    {
        mChoice = new HashMap<CChoix, Integer>();

        for (Map.Entry choix : mChoice.entrySet())
            mNbVote += (int)choix.getValue();
    }

    /// Methode de calcul de l'algorithme STV
    private CResultat Calul(List<CChoix> pListChoix)
    {
        boolean lNewElu = false;
        List<CCandidat> lElus = new ArrayList<CCandidat>();
        List<CCandidat> lElim = new ArrayList<CCandidat>();
        Map<CCandidat,Integer> lCandNbVote;

        lCandNbVote = calcNbVote();

        CResultat lResult =  new CResultat();

        while(lElus.size() < mNbElu)
        {
           Map ltmpCandVote = new HashMap<CCandidat, Integer>(lCandNbVote);
           for(Map.Entry cand : lCandNbVote.entrySet())
           {
               if((int)cand.getValue()>= mQuota)
               {
                   lNewElu = true;
                   lElus.add((CCandidat)cand.getKey());
                   ltmpCandVote = distribSurplus(ltmpCandVote,(CCandidat)cand);
               }
           }

           if (!lNewElu)
           {
               Collections.min(lCandNbVote.values());

           }

           lCandNbVote = ltmpCandVote;

        }

        return  lResult;
    }

    /// Methode de reattribution des voix en surplus
    private Map<CCandidat, Integer> distribSurplus(Map<CCandidat, Integer> pCandVote, CCandidat pCAndElu)
    {
        Map<CCandidat, Integer> lCandVote = new HashMap<CCandidat, Integer>(pCandVote);
        int lSurplus, lRatio;

        lSurplus = (int)pCandVote.get(pCAndElu) - mQuota;

        for (CChoix choix : mChoice.keySet())
        {
            List<CCandidat> cands = new ArrayList<CCandidat>(choix.getChoix());
            cands.retainAll(pCandVote.keySet());

            for(int i=0; i<cands.size(); i++)
            {
                if(cands.get(i)==pCAndElu && i<(cands.size()-1) )
                {
                    lRatio = mChoice.get(choix) / pCandVote.get(pCAndElu);
                    lCandVote.put(cands.get(i+1), (pCandVote.get(pCAndElu) + (lRatio*lSurplus)) );
                }
            }
        }

        lCandVote.remove(pCAndElu);

        return lCandVote;
    }

    /// Methode de calcul du nombre de vote pour chaque candidat
    private Map<CCandidat, Integer> calcNbVote()
    {
        Map<CCandidat, Integer> lCandNbVote  = new HashMap<CCandidat, Integer>();

        for(Map.Entry c : mChoice.entrySet())
        {

            if (lCandNbVote.containsKey(c.getKey()))
                lCandNbVote.put((CCandidat)c.getKey(),(Integer)((int)lCandNbVote.get(c.getKey())+(int)c.getValue()));
            else
                lCandNbVote.put((CCandidat) c.getKey(), (Integer) c.getValue());

        }

        return lCandNbVote;
    }

    /// Methode de calcul du quota necessaire a l'election d'un candidat
    private void Calcul_Quota()
    {
        mQuota = (mNbVote/mNbElu)+1;
    }
}
