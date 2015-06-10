package fr.univtln.m1dapm.g3.g3vote.Algorithme.KemenyYoung;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Algorithme.AAlgorithme;
import fr.univtln.m1dapm.g3.g3vote.Entite.CChoice;
import fr.univtln.m1dapm.g3.g3vote.Entite.CResult;
import fr.univtln.m1dapm.g3.g3vote.Entite.CVote;

/**
 * Created by Pierre on 07/05/2015.
 */

/**
 * <b>CKemenyYoung est la classe qui gére les vote de kemeny young</b>
 *<p>
 * un vote CKemenyYoung est caractérisé par les informations suivante
 *<ul>
 *     <li>un nombre de candidat </li>
 *     <li> une liste des resultat possible  </li>
 *     <li> la taille de la liste des resultat possible </li>
 *
 *</ul>
 *</p>
 *
 * @author rouzic pierre
 *
 */
public class CKemenyYoung extends AAlgorithme {

    /**
     * Liste des choix des participants
     */
    private List<List<Integer>> mChoices;

    /**
     * Liste des identifiants des candidats
     */
    private List<Integer> mIdCands;

    /**
     * Liste du nombre de choix fait
     */
    private List<Integer> mNumbChoice;

    /**
     * Liste des duels entre candidats
     */
    private List<List<Integer>> mDualList;


    /**
     * constructeur du vote CKemenyYoung
     */
    public CKemenyYoung(CVote pVote) {
        super(pVote);
    }

    /**
     * Inititalisation du vote
     * @param pChoices Liste des choix fait par les participants
     */
    public void initVote(List<CChoice> pChoices) {
        List<List<Integer>> lChoices = new ArrayList<>();
        mIdCands = new ArrayList<>();
        mDualList = new ArrayList<>();

        List<Integer> lUserList = new ArrayList<>();

        /// Liste des identifiants des candidats
        for (int i = 0; i < mVote.getCandidates().size(); i++) {
            mIdCands.add(i, mVote.getCandidates().get(i).getIdCandidat());
            mDualList.add(i, new ArrayList<>(Collections.nCopies(mVote.getCandidates().size(), 0)));
        }

        int lUserId;
        int lIndex;


        /// Liste des choix par utilisateur
        for (int i = 0; i < pChoices.size(); i++) {
            lUserId = pChoices.get(i).getIdUser();
            if (!lUserList.contains(lUserId))
            {
                lUserList.add(lUserList.size(), lUserId);
                lChoices.add(new ArrayList<>(Collections.nCopies(mIdCands.size(), 0)));
            }
            lIndex = lUserList.indexOf(lUserId);
            lChoices.get(lIndex).set((pChoices.get(i).getScore()-1),pChoices.get(i).getIdCandidate());

        }

        mChoices = new ArrayList<>();
        mNumbChoice = new ArrayList<>();

        /// Liste des choix differents
        while(lChoices.size()>0)
        {
            mChoices.add(mChoices.size(), lChoices.get(0));
            mNumbChoice.add(mChoices.size()-1, Collections.frequency(lChoices, lChoices.get(0)));
            lChoices.removeAll(Collections.singleton(mChoices.get(mChoices.size() - 1)));
        }

        //TODO SOUCI SUR LA BOUCLE, taille de mNumbChoice < taille de mChoices
        /// Creation de la table des duels entre candidats
        for (int i = 0; i < mChoices.size(); i++) {
            List<Integer> lchoice = mChoices.get(i);
            for (int j = 0; j < mIdCands.size(); j++) {
                int lCand1 = mIdCands.indexOf(lchoice.get(j));
                for (int k = j+1; k < mIdCands.size(); k++) {
                    int lCand2 = mIdCands.indexOf(lchoice.get(k));
                    int lValue = mDualList.get(lCand1).get(lCand2);
                    mDualList.get(lCand1).set(lCand2, lValue + mNumbChoice.get(i));
                }
            }
        }
    }

    /**
     * Calcul du resultat par methode naïve ou heuristique
     * @return La liste des choix ayant le plus haut score
     */
    public List<CResult> CalculResultat()
    {
        List<Integer> lScore;
        /*
        if (mIdCands.size() > 8 )
            lScore = KYHeuristique();
        else*/
        lScore = KYNaif();

        List<CResult> lResult = new ArrayList<>();
        int lindex;

        int lMax = Collections.max(lScore);
        int lOccur = Collections.frequency(lScore, lMax);
        //for (int i = 0; i < lOccur; i++) {
            lindex = lScore.indexOf(lMax);
            for (int j = 0; j < mIdCands.size(); j++) {
                lResult.add(new CResult(j+1, mVote.getIdVote(), mChoices.get(lindex).get(j)));
            }
            mChoices.remove(lindex);
            lScore.remove(lindex);
        //}

        return lResult;
    }

    /**
     * Calcul du score pour le choix possible
     * @param pChoice choix possible de l'algorithme
     * @return Le score obtenu par le choix
     */
    private int CalculScore(List<Integer> pChoice)
    {
        int lScore = 0;
        int lindex1, lindex2;

        for (int i = 0; i < pChoice.size()-1; i++) {
            lindex1 = mIdCands.indexOf(pChoice.get(i));
            for (int j = i+1; j < mIdCands.size(); j++) {
                lindex2 = mIdCands.indexOf(pChoice.get(j));
                lScore += mDualList.get(lindex1).get(lindex2);
            }
        }

        return lScore;
    }

    /**
     * Calcul de tout les choix possible par heuristique
     * @return La liste des scores pour chaque choix possible
     */
    private List<Integer> KYHeuristique()
    {
        return null;
    }

    /**
     * Calcul naïf de tout les choix possibles
     * @return La liste des scores pour chaque choix
     */
    private List<Integer> KYNaif()
    {
        mChoices = new ArrayList<>();
        ///Initialisation du tableau de choix possible
        for (int i = 0; i < mIdCands.size(); i++) {
            mChoices.add(i, new ArrayList<Integer>());
            mChoices.get(i).add(mIdCands.get(i));
        }


        /// Calcul de tout les choix possible
        for (int i = 0; i < mChoices.size(); i++) {
            List<Integer> lChoice = mChoices.get(i);
            for (int j = 0; j < mIdCands.size(); j++) {
                if(!lChoice.contains(mIdCands.get(j))){
                    mChoices.add(mChoices.size(), lChoice);
                    mChoices.get(mChoices.size()-1).add(mIdCands.get(j));
                }
            }
        }

        List<Integer> lChoiceScore = new ArrayList<>();
        Collections.reverse(mChoices);

        /// Calcul du score pour chaque choix possible
        for (int i = 0; i < mChoices.size(); i++)
            if(mChoices.get(i).size() == mIdCands.size())
                lChoiceScore.add(CalculScore(mChoices.get(i)));

        return lChoiceScore;
    }

}

