package fr.univtln.m1dapm.g3.g3vote.Algorithme.UninominalMajoritaire;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Algorithme.AAlgorithme;
import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.Entite.CChoice;
import fr.univtln.m1dapm.g3.g3vote.Entite.CResult;
import fr.univtln.m1dapm.g3.g3vote.Entite.CVote;

/**
 * Created by Pierre on 20/05/2015.
 */


/**
 * <b>CVoteUninomialMajoritaire est la classe qui gere les vote similaire au vote presidenciel francais </b>
 *<p>
 * un vote CVoteUninominalMajoritaire est caracterise par les informations suivante
 *<ul>
 *     <li>une liste des candidat  </li>
 *     <li> le nombre de tour  </li>
 *     <li> le nombre de candidat au tour deux  </li>
 *
 *</ul>
 *</p>
 *
 * @author rouzic pierre
 *
 */
public class CVoteUninominalMajoritaire extends AAlgorithme{

    /**
     * liste des candidat
     */
    private List<Integer> mChoices;//liste des candidat

    private List<Integer> mIdCands;

    /**
     * Liste du nombre de vote pour chaque candidat
     */
    private List<Integer> mCandNumbVote;

    /**
     * nombre de tour (1 ou 2 actuelement)
     */
    private int mNbtour;// nombre de tour (1 ou 2)

    /**
     * nombre de candidat au tour deux
     */
    private int mNbCandidatTour2;// nombre de candidat qui passe au deusiemme tour


    /**
     * constructeur du vote CVoteUninominalMajoritaire
     * <p>le constructeur n'initialise pas les parametre </p>
     *
     * @see CVoteUninominalMajoritaire#initVote(List, int, int)
     */
    public  CVoteUninominalMajoritaire(CVote pVote)
    {
        super(pVote);
        mIdCands=new ArrayList<>();
        mChoices=new ArrayList<>();
    }

    /**
     * initialisation du vote
     *
     * <p>initialise les parametres   </p>
     * @param pChoices
     *          la liste des candidats qui se sont presentes
     * @param pNbtour
     *          le nombre de tour prevu (1 ou 2)
     * @param pNbcandidattour2
     *          le nombre de candidat quil restera au tour 2
     */
    public void initVote(List<CChoice> pChoices, int pNbtour, int pNbcandidattour2) {
        /** initialise le vote avec pour argument la liste des candidats**/

        for (CCandidate cand : mVote.getCandidates()) {
            mIdCands.add(cand.getIdCandidat());
        }

        for (CChoice choice : pChoices) {
            mChoices.add(choice.getIdCandidate());
        }

        mCandNumbVote = new ArrayList<>();

        mNbtour=pNbtour;
        mNbCandidatTour2=pNbcandidattour2;

    }

    /**
     * Calcul du score pour chaque candidats
     */
    private void CalcScore() {
        for (int i = 0; i < mIdCands.size(); i++)
            mCandNumbVote.add(Collections.frequency(mChoices, mIdCands.get(i)));
    }

    /**
     * fini le tour 1
     * <p>cherche les candidat avec le plus de voix et les place dans une nouvelle liste de taille le nombre de candidat
     * au tour deux puis transforme la liste des candidat en la liste des candidat au tour deux </p>
     */
    public void tourSuivant(){

        List<Integer> lListTour = new ArrayList<>();//cree la nouvelle liste de candidat pour le tour suivant
        List<Integer> lCandNumbVote = new ArrayList<>();
        int lNbCandT2 = 0;

        while(lNbCandT2 < mNbCandidatTour2)
        {
            int lindex = mCandNumbVote.indexOf(Collections.max(mCandNumbVote));
            lListTour.add(mIdCands.get(lindex));
            lCandNumbVote.add(mCandNumbVote.get(lindex));
            mCandNumbVote.remove(lindex);
            mIdCands.remove(lindex);
            lNbCandT2++;
        }

        mIdCands = lListTour;
        mCandNumbVote = lCandNumbVote;
    }

    /**
     * fonction de calcul du resultat
     * <p>cherche dans la liste des candidat celui qui a le plus de voix , puis le renvoi </p>
     * @return le candidat qui a le plus de voix
     */
    public List<CResult> resultat(){// renvoi le candidat avec le plus de vote pour lui
        /** donne le resultat du vote sans parametre avec pour retour le candidat avec le plus de vote pour lui  **/

        CalcScore();
        if(mNbtour == 2)
            tourSuivant();
       // int lindex = mCandNumbVote.indexOf(Collections.max(mCandNumbVote));
        //CResult lWinner = new CResult(1,mVote.getIdVote(), mIdCands.get(lindex));

        List<CResult> lResults=new ArrayList<>();

        for(int i=0;i<mIdCands.size();i++){
            CResult lResult=new CResult(mCandNumbVote.get(i),mVote.getIdVote(),mIdCands.get(i));

            lResults.add(lResult);

        }

        return lResults;
    }

}
