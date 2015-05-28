package fr.univtln.m1dapm.g3.g3vote.Algorithme.STV;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Algorithme.AAlgorithme;
import fr.univtln.m1dapm.g3.g3vote.Entite.CChoice;
import fr.univtln.m1dapm.g3.g3vote.Entite.CVote;

/*
  Created by ludo on 05/05/15.
 */
public class CAlgoSTV extends AAlgorithme {

    protected double mQuota;
    protected int mNbElu, mNbVote;

    protected List<List<Integer>> mChoice;

    private List<Double> mChoiceNumb;

    public CAlgoSTV() {
        super();
    }

    public CAlgoSTV(CVote pVote, int pNbElu) {
        super(pVote);
        mNbElu = pNbElu;

    }

    /// Methode d'initialisation du vote (charge les regles)
    public void initVote(List<CChoice> pChoices)
    {
        getChoix(pChoices);

        Calcul_Quota();

    }

    /// Charge la liste des choix faits par les votants de la BDD
    private void getChoix(List<CChoice> pChoices)
    {
        mChoice = new LinkedList<>();

        List<List<Integer>> lChoices = new LinkedList<>();

        for (CChoice choice : pChoices)
        {
            List<Integer> lCandList;
            int lUserId = choice.getmUser().getUserId();

            if (lChoices.get(lUserId) == null)
                lCandList = new LinkedList<>();
            else
                lCandList = lChoices.get(lUserId);

            lCandList.set(choice.getmScore()-1, choice.getmCandidate().getmIdCandidat());
            lChoices.set(lUserId,lCandList);
        }

        mNbVote = lChoices.size();

        for(List<Integer> choice : lChoices)
        {
            List<Integer> lChoice;

            int lChoiceIndex = lChoices.indexOf(choice);

            if (mChoice.contains(choice))
                mChoiceNumb.set(lChoiceIndex, mChoiceNumb.get(lChoiceIndex));
            else
            {
                mChoice.set(lChoiceIndex, choice);
                mChoiceNumb.set(lChoiceIndex, 1.0);
            }
        }

    }

    /// Methode de calcul de l'algorithme STV
    public List<Integer> CalculResultat()
    {
        boolean lNewElu;
        List<Integer> lElus = new LinkedList<>();
        List<Integer> lElim = new LinkedList<>();
        List<Integer> lResult = new LinkedList<>();
        List<Double> lCandNbVote;

        lCandNbVote = calcNbVote();

        /// boucle de comptage des elus
        while(lElus.size() < mNbElu)
        {
            List<Double> ltmpCandVote = new LinkedList<>(lCandNbVote);
/*
            double lNbvote = 0.0; /// Comptage du nombre de votes restants

            for(Map.Entry choix : mChoix.entrySet())
                lNbvote += (double)choix.getValue();

            Log.i("Vote : ", "Nombre de votes restants : " + lNbvote);
*/
            lNewElu = false;
            /// Verification de la presence d'une majoritee
            for(Double candValue : lCandNbVote)
            {
                /// Presence d'une majoritee
                if(candValue.doubleValue() >= mQuota)
                {
                    lNewElu = true;
                    //         Log.i("Vote : ", "Elu : " + ((CCandidat) cand.getKey()).getNom() + " avec " + cand.getValue() + " voix");
                    lElus.add(lCandNbVote.indexOf(candValue));
                    ltmpCandVote = distribSurplus(ltmpCandVote, lCandNbVote.indexOf(candValue));
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

        return  lElus;
    }

    /// Methode de reattribution des voix en plus d'un candidat elu
    private List<Double> distribSurplus(List<Double> pCandVote, int pIdCandElu)
    {
        List<Double> lCandVote = new LinkedList<>(pCandVote);
        double lSurplus;
        double lRatioVote;

        lSurplus = pCandVote.get(pIdCandElu) - mQuota;
        //      Log.i("Vote : ", "surplus " + lSurplus);

        /// Parcours de la liste des choix
        for (List<Integer> choice : mChoice)
        {
            /// Le candidat elu est le premier dans ce choix
            if(choice.get(0) == pIdCandElu && choice.size()>1)
            {
                /// incrementation proportionnelle du nombre de vote du second candidat
                double lNbVoteChoix = mChoiceNumb.get(mChoice.indexOf(choice));
                double lNbVoteCandElu = pCandVote.get(pIdCandElu);
                lRatioVote =  lNbVoteChoix / lNbVoteCandElu ;
                lCandVote.set(choice.get(1), ((lRatioVote * lSurplus) + lCandVote.get(choice.get(1))));
                mChoiceNumb.set(mChoice.indexOf(choice), (lRatioVote * lSurplus));
/*
                Log.i("Vote : ", "Ratio " + lRatioVote + " pour " + cands.get(1).getNom());
                Log.i("Vote : ", "ancien score " + pCandVote.get(cands.get(1)));
                Log.i("Vote : ", "gagne " + (lRatioVote*lSurplus) + " voix ");
                Log.i("Vote : ", "nouveau score " + lCandVote.get(cands.get(1)));
                Log.i("Vote : ", "////////////////////////////////////////////");*/
            }
        }

        mChoiceNumb.set(pIdCandElu, 0.0);

        removeCandidat(pIdCandElu);

        return lCandVote;
    }

    /// Methode d'heritage des votes d'un candidat elimine
    private List<Double> heriteVote(List<Double> pCandVote, int pIdCandElim)
    {
        List<Double> lCandVote = new LinkedList<>(pCandVote);

        /// Parcours de la liste des choix
        for (List<Integer> choice : mChoice)
        {
            /// Le candidat elimine est le premier dans ce choix
            if(choice.get(0) == pIdCandElim && choice.size()>1) {
          /*      Log.i("Vote : ", "Candidat elim : " + pCandElim.getNom() + " Nb voix : " + pCandVote.get(pCandElim));
                Log.i("Vote : ", "Candidat suivant : " + cands.get(1).getNom() + " Nb voix : " + pCandVote.get(cands.get(1)));
                Log.i("Vote : ", "gagne : " + mChoix.get(choix) + " voix");*/
                lCandVote.set(choice.get(1), (lCandVote.get(choice.get(1)) + mChoiceNumb.get(mChoice.indexOf(choice)))); /// Ajout du nombre de vote au second candidat
                //    Log.i("Vote : ", "Candidat : " + cands.get(1).getNom() + " nouveau Nb voix : " + lCandVote.get(cands.get(1)));
            }

        }

        mChoiceNumb.set(pIdCandElim, 0.0);

        removeCandidat(pIdCandElim);

        return lCandVote;
    }

    /// Methode de recherche du candidat avec le moins de vote
    private int getCandidatElim(List<Double> pCandVote)
    {
        return pCandVote.indexOf(Collections.min(pCandVote));
    }

    /// Methode de calcul du nombre de vote pour chaque candidat
    private List<Double> calcNbVote()
    {
        List<Double> lCandNbVote  = new LinkedList<>();

        ///Parcours de la liste des choix
        for(List<Integer> choice : mChoice)
            lCandNbVote.set(choice.get(0), lCandNbVote.get(choice.get(0)) + 1.0);

        return lCandNbVote;
    }

    /// Methode de calcul du quota necessaire a l'election d'un candidat
    private void Calcul_Quota()
    {
        mQuota = (mNbVote/(mNbElu+1))+1;

        //       Log.i("Vote : ", "Quota : " + mQuota);
    }

    private void removeCandidat(int pIdCand)
    {
        for(List<Integer> choice : mChoice)
            choice.remove((Integer) pIdCand);
    }
}