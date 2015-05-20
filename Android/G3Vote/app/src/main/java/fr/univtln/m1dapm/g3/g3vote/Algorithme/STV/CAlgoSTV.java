package fr.univtln.m1dapm.g3.g3vote.Algorithme.STV;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import fr.univtln.m1dapm.g3.g3vote.Algorithme.AAlgorithme;
import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidat;
import fr.univtln.m1dapm.g3.g3vote.Entite.CListChoix;
import fr.univtln.m1dapm.g3.g3vote.Entite.CResultat;
import fr.univtln.m1dapm.g3.g3vote.Entite.CVote;

/**
 * Created by ludo on 05/05/15.
 */
public class CAlgoSTV extends AAlgorithme {

    protected double mQuota;
    protected int mNbElu, mNbVote;

    protected Map<CListChoix, Double> mChoix;

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
    public void initVote(Map<CListChoix, Double> pChoix)
    {
        int lNbVote=0, lNbElu=0;
        mVote.getRegles();
        getChoix(pChoix);

        Calcul_Quota();

    }

    /// Charge la liste des choix faits par les votants de la BDD
    private void getChoix(Map<CListChoix, Double> pChoix)
    {
        mChoix = new HashMap<>(pChoix);

        for (Map.Entry choix : mChoix.entrySet())
            mNbVote += (double)choix.getValue();
    }

    /// Methode de calcul de l'algorithme STV
    public CResultat CalculResultat()
    {
        boolean lNewElu = false;
        List<CCandidat> lElus = new ArrayList<>();
        List<CCandidat> lElim = new ArrayList<>();
        CResultat<List<CCandidat>> lResultat = new CResultat<>();
        Map<CCandidat,Double> lCandNbVote;

        lCandNbVote = calcNbVote();

        /// boucle de comptage des elus
        while(lElus.size() < mNbElu)
        {
           Map<CCandidat, Double> ltmpCandVote = new HashMap<>(lCandNbVote);
/*
            double lNbvote = 0.0; /// Comptage du nombre de votes restants

            for(Map.Entry choix : mChoix.entrySet())
                lNbvote += (double)choix.getValue();

            Log.i("Vote : ", "Nombre de votes restants : " + lNbvote);
*/
            lNewElu = false;
            /// Verification de la presence d'une majoritee
           for(Map.Entry cand : lCandNbVote.entrySet())
           {
               /// Presence d'une majoritee
               if((double)cand.getValue() >= mQuota)
               {
                   lNewElu = true;
          //         Log.i("Vote : ", "Elu : " + ((CCandidat) cand.getKey()).getNom() + " avec " + cand.getValue() + " voix");
                   lElus.add((CCandidat)cand.getKey());
                   ltmpCandVote = distribSurplus(ltmpCandVote,(CCandidat)cand.getKey());
                   break;
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
    private Map<CCandidat, Double> distribSurplus(Map<CCandidat, Double> pCandVote, CCandidat pCandElu)
    {
        Map<CCandidat, Double> lCandVote = new HashMap<>(pCandVote);
        double lSurplus;
        double lRatioVote;

        lSurplus = pCandVote.get(pCandElu) - mQuota;
  //      Log.i("Vote : ", "surplus " + lSurplus);

        /// Parcours de la liste des choix
        for (CListChoix choix : mChoix.keySet())
        {
            List<CCandidat> cands = choix.getChoix();
       //     cands.retainAll(pCandVote.keySet());

            /// Le candidat elu est le premier dans ce choix
            if(cands.get(0) == pCandElu && cands.size()>1)
            {
                /// incrementation proportionnelle du nombre de vote du second candidat
                double lNbVoteChoix = mChoix.get(choix);
                double lNbVoteCandElu = pCandVote.get(pCandElu);
                lRatioVote =  lNbVoteChoix / lNbVoteCandElu ;
                lCandVote.put(cands.get(1), ((lRatioVote*lSurplus) + lCandVote.get(cands.get(1))));
                mChoix.put(choix, (lRatioVote * lSurplus));
/*
                Log.i("Vote : ", "Ratio " + lRatioVote + " pour " + cands.get(1).getNom());
                Log.i("Vote : ", "ancien score " + pCandVote.get(cands.get(1)));
                Log.i("Vote : ", "gagne " + (lRatioVote*lSurplus) + " voix ");
                Log.i("Vote : ", "nouveau score " + lCandVote.get(cands.get(1)));
                Log.i("Vote : ", "////////////////////////////////////////////");*/
            }
        }

        lCandVote.remove(pCandElu);

        removeCandidat(pCandElu);

        return lCandVote;
    }

    /// Methode d'heritage des votes d'un candidat elimine
    private Map<CCandidat, Double> heriteVote(Map<CCandidat, Double> pCandVote, CCandidat pCandElim)
    {
        Map<CCandidat, Double> lCandVote = new HashMap<>(pCandVote);

        /// Parcours de la liste des choix
        for (CListChoix choix : mChoix.keySet())
        {
            List<CCandidat> cands = new ArrayList<>(choix.getChoix());
       //     cands.retainAll(pCandVote.keySet());

            /// Le candidat elimine est le premier dans ce choix
            if(cands.get(0) == pCandElim && cands.size()>1) {
          /*      Log.i("Vote : ", "Candidat elim : " + pCandElim.getNom() + " Nb voix : " + pCandVote.get(pCandElim));
                Log.i("Vote : ", "Candidat suivant : " + cands.get(1).getNom() + " Nb voix : " + pCandVote.get(cands.get(1)));
                Log.i("Vote : ", "gagne : " + mChoix.get(choix) + " voix");*/
                lCandVote.put(cands.get(1), (lCandVote.get(cands.get(1)) + mChoix.get(choix))); /// Ajout du nombre de vote au second candidat
            //    Log.i("Vote : ", "Candidat : " + cands.get(1).getNom() + " nouveau Nb voix : " + lCandVote.get(cands.get(1)));
            }

        }

        lCandVote.remove(pCandElim);
        removeCandidat(pCandElim);

        return lCandVote;
    }

    /// Methode de recherche du candidat avec le moins de vote
    private CCandidat getCandidatElim(Map<CCandidat, Double> pMap)
    {
        CCandidat lCandElim = new CCandidat();
        double lMin=0;

        /// Parcours de la liste du nombre de vote par candidat
        for(Map.Entry cand : pMap.entrySet()) {
            if(lMin == 0) {
                lMin = (double) cand.getValue();
                lCandElim = (CCandidat) cand.getKey();
            }
            if ((double)cand.getValue() < lMin)
            {
                lMin = (double)cand.getValue();
                lCandElim = (CCandidat)cand.getKey();
            }
        }


        return lCandElim;
    }

    /// Methode de calcul du nombre de vote pour chaque candidat
    private Map<CCandidat, Double> calcNbVote()
    {
        Map<CCandidat, Double> lCandNbVote  = new HashMap<>();

        for (CCandidat cand : mChoix.entrySet().iterator().next().getKey().getChoix())
                lCandNbVote.put(cand, 0.0);

        ///Parcours de la liste des choix
        for(CListChoix choix : mChoix.keySet())
        {
            CCandidat lCand = choix.getIndexValue(0);

            if(lCandNbVote.containsKey(lCand))
                lCandNbVote.put(lCand, lCandNbVote.get(lCand) + mChoix.get(choix));
        }

        return lCandNbVote;
    }

    /// Methode de calcul du quota necessaire a l'election d'un candidat
    private void Calcul_Quota()
    {
        mQuota = (mNbVote/(mNbElu+1))+1;

 //       Log.i("Vote : ", "Quota : " + mQuota);
    }

    private void removeCandidat(CCandidat pCand)
    {
        for(CListChoix choix : mChoix.keySet())
            choix.removeCandidat(pCand);
    }
}
