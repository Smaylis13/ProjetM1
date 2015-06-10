package fr.univtln.m1dapm.g3.g3vote.Algorithme.Borda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Algorithme.AAlgorithme;
import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.Entite.CChoice;
import fr.univtln.m1dapm.g3.g3vote.Entite.CResult;
import fr.univtln.m1dapm.g3.g3vote.Entite.CVote;

/**
 * Created by lyamsi on 12/05/15.
 */
public class CAlgoBorda extends AAlgorithme{

    /**
     * Liste des choix faits par les participants
     */
    private List<List<Integer>> mChoices;

    /**
     * Liste des identifiants des candidats
     */
    private List<Integer> mIdCands;

    /**
     * Liste des scores pour chaque candidats
     */
    private List<Integer> mCandsScore;


    /**
     * Constructeur de l'algo
     * @param pVote Vote pour lequel on veux calculer le resultat
     */
    public CAlgoBorda(CVote pVote) {
        super(pVote);
    }

    /**
     * Initialisation de l'algorithme de calcul
     * @param pChoices Liste des choix fait par les participants
     */
    public void initVote(List<CChoice> pChoices)
    {
        mChoices = new ArrayList<>();
        mIdCands = new ArrayList<>();
        mCandsScore = new ArrayList<>();

        List<CCandidate> lCands = new ArrayList<>(mVote.getCandidates());

        /// initialise la liste des identifiants des candidats et des notes associées
        for (int i = 0; i < lCands.size(); i++) {
            mIdCands.add(i, lCands.get(i).getIdCandidat());
            mChoices.add(new ArrayList<Integer>());
        }

        int lindex;

        /// remplis la liste des notes attribués a chaque candidats
        for(CChoice choice : pChoices)
        {
            lindex = mIdCands.indexOf(choice.getIdCandidate());
            mChoices.get(lindex).add(choice.getScore());
        }
    }

    /**
     * Calcul du score de chaque candidats
     * @param pIndex indice de la liste a calculer
     */
    private void CalculateScore(int pIndex)
    {
        int lScore = 0;

        for (Integer value : mChoices.get(pIndex))
            lScore += value;

        mCandsScore.add(pIndex, lScore);
    }

    /**
     * Renvoi le candidat ayant le meilleur score
     * @return candidat gagnant
     */
    public CResult CalculResult(){

        CResult lResult;

        /// calcul le score de chaque candidats
        for (int i = 0; i < mIdCands.size(); i++) {
            CalculateScore(i);
        }

        int lMax = Collections.max(mCandsScore);

        lResult = new CResult(0, mVote.getIdVote(), mIdCands.get(mCandsScore.indexOf(lMax)));

        return lResult;
    }

}
