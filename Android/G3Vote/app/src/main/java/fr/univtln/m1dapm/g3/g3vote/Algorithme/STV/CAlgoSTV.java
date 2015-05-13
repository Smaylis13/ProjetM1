package fr.univtln.m1dapm.g3.g3vote.Algorithme.STV;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.univtln.m1dapm.g3.g3vote.Algorithme.AAlgorithme;
import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidat;
import fr.univtln.m1dapm.g3.g3vote.Entite.CChoix;
import fr.univtln.m1dapm.g3.g3vote.Entite.CListChoix;
import fr.univtln.m1dapm.g3.g3vote.Entite.CResultat;
import fr.univtln.m1dapm.g3.g3vote.Entite.CVote;

/**
 * Created by ludo on 05/05/15.
 */
public class CAlgoSTV extends AAlgorithme {

    protected int mQuota, mNbElu, mNbVote;

    protected Map<CChoix<List>, Integer> mChoix;

    public CAlgoSTV() {
        super();
    }

    public CAlgoSTV(CVote pVote, int pNbElu) {
        super(pVote);
        mNbElu = pNbElu;

        initVote();
    }


    @Override
    protected void initVote() {

    }

    /// Methode d'initialisation du vote (charge les regles)
    //@Override
    public void initVote(Map<CChoix<List>, Integer> pChoix)
    {
        int lNbVote=0, lNbElu=0;
        mVote.getRegles();
        getChoix(pChoix);

        Calcul_Quota();

    }

    /// Charge la liste des choix faits par les votants de la BDD
    public void getChoix(Map<CChoix<List>, Integer> pChoix)
    {
        mChoix = new HashMap<CChoix<List>, Integer>(pChoix);

        for (Map.Entry choix : mChoix.entrySet())
            mNbVote += (int)choix.getValue();
    }

    /// Methode de calcul de l'algorithme STV
    public CResultat CalculResultat()
    {
        boolean lNewElu = false;
        List<CCandidat> lElus = new ArrayList<CCandidat>();
        List<CCandidat> lElim = new ArrayList<CCandidat>();
        CResultat<List<CCandidat>> lResultat = new CResultat<>();
        Map<CCandidat,Integer> lCandNbVote;

        lCandNbVote = calcNbVote();

        /// boucle de comptage des elus
        while(lElus.size() < mNbElu)
        {
           Map ltmpCandVote = new HashMap<CCandidat, Integer>(lCandNbVote);

            /// Verification de la presence d'une majoritee
           for(Map.Entry cand : lCandNbVote.entrySet())
           {
               /// Presence d'une majoritee
               if((int)cand.getValue() >= mQuota)
               {
                   lNewElu = true;
                   lElus.add((CCandidat)cand.getKey());
                   ltmpCandVote = distribSurplus(ltmpCandVote,(CCandidat)cand);
               }
           }

            /// Absence de nouveaux elus : elimination d'un candidat
           if (!lNewElu)
           {
               lElim.add(getCandidatElim(ltmpCandVote));

               ltmpCandVote = heriteVote(ltmpCandVote, lElim.get(lElim.size()-1));
           }

           lCandNbVote = ltmpCandVote;

        }

        lResultat.setValeur(lElus);
        return  lResultat;
    }

    /// Methode de reattribution des voix en plus d'un candidat elu
    private Map<CCandidat, Integer> distribSurplus(Map<CCandidat, Integer> pCandVote, CCandidat pCandElu)
    {
        Map<CCandidat, Integer> lCandVote = new HashMap<CCandidat, Integer>(pCandVote);
        int lSurplus, lRatio;

        lSurplus = pCandVote.get(pCandElu) - mQuota;

        /// Parcours de la liste des choix
        for (CChoix<List> choix : mChoix.keySet())
        {
            List<CCandidat> cands = new ArrayList<CCandidat>(choix.getChoix());
            cands.retainAll(pCandVote.keySet());

            /// Le candidat elu est le premier candidat dans ce choix
            if(cands.get(0) == pCandElu && cands.size()>1)
            {
                /// incrementation proportionnelle du nombre de vote du second candidat
                lRatio = mChoix.get(choix) / pCandVote.get(pCandElu);
                lCandVote.put(cands.get(1), (lRatio*lSurplus) );
            }
        }

        lCandVote.remove(pCandElu);

        return lCandVote;
    }

    /// Methode d'heritage des votes d'un candidat elimine
    private Map<CCandidat, Integer> heriteVote(Map<CCandidat, Integer> pCandVote, CCandidat pCandElim)
    {
        Map<CCandidat, Integer> lCandVote = new HashMap<>(pCandVote);

        /// Parcours de la liste des choix
        for (CChoix<List> choix : mChoix.keySet())
        {
            List<CCandidat> cands = new ArrayList<CCandidat>(choix.getChoix());
            cands.retainAll(pCandVote.keySet());

            /// Le candidat elimine est le premier dans ce choix
            if(cands.get(0) == pCandElim && cands.size()>1)
                lCandVote.put(cands.get(1), pCandVote.get(cands.get(1)) + mChoix.get(choix) ); /// Ajout du nombre de vote au second candidat

        }

        lCandVote.remove(pCandElim);

        return lCandVote;
    }

    /// Methode de recherche du candidat avec le moins de vote
    private CCandidat getCandidatElim(Map<CCandidat, Integer> pMap)
    {
        CCandidat lCandElim = new CCandidat();
        int lMin=0;

        /// Parcours de la liste du nombre de vote par candidat
        for(Map.Entry cand : pMap.entrySet()) {
            if(lMin > 0)
                lMin = (int)cand.getValue();
            if ((int)cand.getValue() < lMin)
            {
                lMin = (int)cand.getValue();
                lCandElim = (CCandidat)cand.getKey();
            }
        }


        return lCandElim;
    }

    /// Methode de calcul du nombre de vote pour chaque candidat
    private Map<CCandidat, Integer> calcNbVote()
    {
        Map<CCandidat, Integer> lCandNbVote  = new HashMap<CCandidat, Integer>();

        ///Parcours de la liste des choix
        for(Map.Entry choix : mChoix.entrySet())
        {
            CListChoix lchoix = (CListChoix) choix;
            CCandidat lCand = lchoix.getIndexValue(0);

            if(lCandNbVote.containsKey(lCand))
                lCandNbVote.put(lCand, lCandNbVote.get(lCand) + mChoix.get(choix));
            else
                lCandNbVote.put(lCand, mChoix.get(choix));

            /*  REMPLISSAGE DE LA LISTE FAUX!!!!!!!!!!!!!
            if (lCandNbVote.containsKey(choix.getKey()))
                lCandNbVote.put((CCandidat)choix.getKey(),(Integer)((int)lCandNbVote.get(choix.getKey())+(int)choix.getValue()));
            else
                lCandNbVote.put((CCandidat) choix.getKey(), (Integer) choix.getValue());
            */
        }

        return lCandNbVote;
    }

    /// Methode de calcul du quota necessaire a l'election d'un candidat
    private void Calcul_Quota()
    {
        mQuota = (mNbVote/mNbElu)+1;
    }
}
