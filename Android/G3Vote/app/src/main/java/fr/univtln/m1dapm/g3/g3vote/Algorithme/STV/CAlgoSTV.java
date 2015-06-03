package fr.univtln.m1dapm.g3.g3vote.Algorithme.STV;


import java.util.Arrays;
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

    /**
     * Quota nécessaire à l'élection d'un candidat
     */
    protected double mQuota;

    /**
     * Nombre d'élu voulu
     */
    protected int mNbElu;

    /**
     * Nombre de vote effectué
     */
    protected int mNbVote;

    /**
     * Liste des différents votes effectués
     */
    protected List<List<Integer>> mChoice;

    /**
     * Liste des quantités de chaque vote différents
     */
    private List<Double> mChoiceNumb;

    /**
     * Liste référentielle de l'identifiant des candidats
     */
    private List<Integer> mIdCands;

    /**
     * Liste de présence des candidats dans le calcul
     */
    private List<Boolean> mCheckCands;


    /**
     * Constructeur de l'algorithme STV
     * @param pVote Vote dont on veux calculer le resultat
     * @param pNbElu Nombre de candidats devant sortir vainqueur
     */
    public CAlgoSTV(CVote pVote, int pNbElu) {
        super(pVote);
        mNbElu = pNbElu;

    }


    /**
     * Initialisation des éléments nécessaire au calcul du résultat
     * @param pChoices  Liste des réponses des utilisateurs
     */

    public void initVote(List<CChoice> pChoices)
    {
        getChoix(pChoices);

        Calcul_Quota();
        //Log.i("Vote : ", "Quota : " + mQuota);
    }


    /**
     * Charge la liste des choix faits par les votants
     * @param pChoices  Liste des réponses des utilisateurs
     */
    private void getChoix(List<CChoice> pChoices)
    {
        mChoice = new LinkedList<>();
        mChoiceNumb = new LinkedList<>();
        List<Integer> lUserList= new LinkedList<>();

        List<List<Integer>> lChoices = new LinkedList<>();

        /// Initialisation de la liste des réponses par utilisateur
        for(int i=0; i<(pChoices.size()/mVote.getCandidates().size()); i++)
        {
            lUserList.add(i, pChoices.get(i*mVote.getCandidates().size()).getUser().getUserId());
            Integer[] lDefaultValue = new Integer[mVote.getCandidates().size()];
            Arrays.fill(lDefaultValue, new Integer(0));
            lChoices.add(Arrays.asList(lDefaultValue));
        }

        /// Remplissage de la liste des réponses par utilisateur
        for (CChoice choice : pChoices)
        {
            int lUserId = lUserList.indexOf(choice.getUser().getUserId());
            List<Integer> lCandList = lChoices.get(lUserId);

            lCandList.set(choice.getScore()-1, choice.getCandidate().getIdCandidat());
            lChoices.set(lUserId, lCandList);
        }

        mNbVote = lChoices.size();

        // Création de la liste des réponses différentes
        for(List<Integer> choice : lChoices)
        {
            int lChoiceIndex;

            if (mChoice.contains(choice)) {
                lChoiceIndex = mChoice.indexOf(choice);
                mChoiceNumb.set(lChoiceIndex, mChoiceNumb.get(lChoiceIndex) + 1.0);
            }
            else
            {
                lChoiceIndex = mChoice.size();
                mChoice.add(lChoiceIndex, choice);
                mChoiceNumb.add(lChoiceIndex, 1.0);
            }
        }
    }


    /**
     * Methode de calcul de l'algorithme STV
     * @return La liste des identifiants des candidats vainqueurs
     */
    public List<Integer> CalculResultat()
    {
        boolean lNewElu;
        List<Integer> lElus = new LinkedList<>();
        List<Integer> lElim = new LinkedList<>();
        List<Double> lCandNbVote;

        lCandNbVote = calcNbVote();

        /// boucle de comptage des candidats elus
        while(lElus.size() < mNbElu)
        {
            List<Double> ltmpCandVote = new LinkedList<>(lCandNbVote);

 /*
            double lNbvote = 0.0; /// Comptage du nombre de votes restants
            for(Double db : mChoiceNumb)
                lNbvote += db;
            Log.i("Vote : ", "Nombre de votes restants : " + lNbvote);
*/
            lNewElu = false;
            /// Verification de la presence d'une majoritee
            for(Double candValue : lCandNbVote)
            {
                /// Presence d'une majoritee
                if(candValue.doubleValue() >= mQuota && mCheckCands.get(lCandNbVote.indexOf(candValue)))
                {
                    lNewElu = true;
                    /// Log.i("Vote : ", "Elu : " + ((CCandidat) cand.getKey()).getNom() + " avec " + cand.getValue() + " voix");
                    lElus.add(lElus.size(), mIdCands.get(lCandNbVote.indexOf(candValue)));
                    ltmpCandVote = distribSurplus(ltmpCandVote, lElus.get(lElus.size()-1));
                    break;
                }
            }

            /// Absence de nouveaux elus : elimination d'un candidat
            if (!lNewElu)
            {
                lElim.add(lElim.size(), getCandidatElim(ltmpCandVote));
                ltmpCandVote = heriteVote(ltmpCandVote, lElim.get(lElim.size()-1));
            }

            lCandNbVote = ltmpCandVote;

        }
        return lElus;
    }


    /**
     * Réatribution des voix en plus d'un candidat elu
     * @param pCandVote Liste du nombre de voix en faveur de chaque candidat
     * @param pIdCandElu Identifiant du candidat elu
     * @return Liste modifiée du nombre de voix en faveur de chaque candidat
     */
    private List<Double> distribSurplus(List<Double> pCandVote, int pIdCandElu)
    {
        List<Double> lCandVote = new LinkedList<>(pCandVote);
        double lSurplus;
        double lRatioVote;
        int lCandIndex = mIdCands.indexOf(pIdCandElu);

        lSurplus = pCandVote.get(lCandIndex) - mQuota;
     //   Log.i("Vote : ", "surplus " + lSurplus);

        /// Parcours de la liste des choix
        for (int i = 0; i < mChoice.size(); i++) {
            List<Integer> choice = mChoice.get(i);
               /// Le candidat elu est le premier dans ce choix
            if (choice.get(0) == pIdCandElu && choice.size() > 1) {
                /// incrementation proportionnelle du nombre de vote du second candidat
                int lindex = mIdCands.indexOf(choice.get(1));double lNbVoteChoix = mChoiceNumb.get(i);
                double lNbVoteCandElu = pCandVote.get(lCandIndex);
                lRatioVote = lNbVoteChoix / lNbVoteCandElu;
/*
                Log.i("Vote : ", "Ratio " + lRatioVote + " pour " + choice.get(1));
                Log.i("Vote : ", "ancien score " + pCandVote.get(lindex));
*/
                lCandVote.set(lindex, ((lRatioVote * lSurplus) + lCandVote.get(lindex)));
                mChoiceNumb.set(i, (lRatioVote * lSurplus));
/*
                Log.i("Vote : ", "gagne " + (lRatioVote * lSurplus) + " voix ");
                Log.i("Vote : ", "nouveau score " + lCandVote.get(lindex));
                Log.i("Vote : ", "////////////////////////////////////////////");
*/
            }
        }

        lCandVote.set(lCandIndex, 0.0);
        removeCandidat(pIdCandElu);
        mCheckCands.set(lCandIndex, false);

        return lCandVote;
    }


    /**
     * Heritage des votes d'un candidat éliminé
     * @param pCandVote Liste du nombre de voix en faveur de chaque candidat
     * @param pIdCandElim Identifiant du candidat éliminé
     * @return  Liste modifiée du nombre de voix en faveur de chaque candidat
     */
    private List<Double> heriteVote(List<Double> pCandVote, int pIdCandElim)
    {
        List<Double> lCandVote = new LinkedList<>(pCandVote);
        int lCandIndex = mIdCands.indexOf(pIdCandElim);

        /// Parcours de la liste des choix
        for (int i = 0; i < mChoice.size(); i++)
        {
            List<Integer> choice = mChoice.get(i);
            /// Le candidat elimine est le premier dans ce choix
            if(choice.get(0) == pIdCandElim && choice.size()>1) {
                int lindex = mIdCands.indexOf(choice.get(1));

/*                Log.i("Vote : ", "Candidat elim : " + pIdCandElim + " Nb voix : " + lCandVote.get(pIdCandElim));
                Log.i("Vote : ", "Candidat suivant : " + choice.get(1) + " Nb voix : " + lCandVote.get(lindex));
                Log.i("Vote : ", "gagne : " + mChoiceNumb.get(i) + " voix");
*/
                lCandVote.set(lindex, lCandVote.get(lindex) + mChoiceNumb.get(i)); /// Ajout du nombre de vote au second candidat

/*                Log.i("Vote : ", "Candidat : " + choice.get(1) + " nouveau Nb voix : " + lCandVote.get(lindex));
                Log.i("Vote : ", "////////////////////////////////////////////");
*/
            }
        }

        lCandVote.set(lCandIndex, 0.0);
        removeCandidat(pIdCandElim);
        mCheckCands.set(lCandIndex, false);

        return lCandVote;
    }


    /**
     * Recherche du candidat avec le moins de vote
     * @param pCandVote Liste du nombre de voix en faveur de chaque candidat
     * @return Identifiant du candidat avec le nombre de voix le plus bas
     */
    private int getCandidatElim(List<Double> pCandVote)
    {
        double lMin = Collections.max(pCandVote);
        int lindex = pCandVote.indexOf(lMin);

        for(int i=0; i<pCandVote.size(); i++) {
            if (mCheckCands.get(i)) {   /// Le candidat est toujours en course
                if (lMin > pCandVote.get(i) ) {
                    lMin = pCandVote.get(i);
                    lindex = i;
                }
            }
        }

        return mIdCands.get(lindex);
    }


    /**
     * Calcul du nombre de vote pour chaque candidat
     * @return Liste du nombre de vote pour chaque candidat
     */
    private List<Double> calcNbVote()
    {
        List<Double> lCandNbVote;

        mIdCands = new LinkedList<>();

        Double[] lDefaultValue = new Double[mVote.getCandidates().size()];
        Arrays.fill(lDefaultValue, new Double(0.0));
        lCandNbVote = Arrays.asList(lDefaultValue);

        Boolean[] lInitValue = new Boolean[mVote.getCandidates().size()];
        Arrays.fill(lInitValue, new Boolean(true));
        mCheckCands = Arrays.asList(lInitValue);

        /// Initialisation de la liste de réference des candidats
        for (int i=0; i<mChoice.get(0).size(); i++)
            mIdCands.add(i,mChoice.get(0).get(i));

        ///Parcours de la liste des choix
        for(List<Integer> choice : mChoice) {
            int lindex = mIdCands.indexOf(choice.get(0));
            lCandNbVote.set(lindex, lCandNbVote.get(lindex) + 1.0);
        }

        return lCandNbVote;
    }


    /**
     * Calcul du quota nécessaire a l'élection d'un candidat
     */
    private void Calcul_Quota()
    {
        mQuota = (mNbVote/(mNbElu+1))+1;
    }


    /**
     * Suppression d'un candidat dans la liste des votes
     * @param pIdCand Identifiant du candidat a retirer
     */
    private void removeCandidat(int pIdCand)
    {
        for(int i=0; i<mChoice.size(); i++) {
            List<Integer> lchoice = new LinkedList<>(mChoice.get(i));
            lchoice.remove((Object)pIdCand);
            mChoice.set(i, lchoice);
        }
    }
}