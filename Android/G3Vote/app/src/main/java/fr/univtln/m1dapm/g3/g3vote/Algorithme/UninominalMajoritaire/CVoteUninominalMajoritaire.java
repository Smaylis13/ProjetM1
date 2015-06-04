package fr.univtln.m1dapm.g3.g3vote.Algorithme.UninominalMajoritaire;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Algorithme.AAlgorithme;
import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;

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
    private List<CCandidate> mListeCandidat;//liste des candidat

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

    public  CVoteUninominalMajoritaire(){}

    /**
     * initialisation du vote
     *
     * <p>initialise les parametres   </p>
     * @param pListeCandidat
     *          la liste des candidats qui se sont presentes
     * @param pNbtour
     *          le nombre de tour prevu (1 ou 2)
     * @param pNbcandidattour2
     *          le nombre de candidat quil restera au tour 2
     */
    protected void initVote(List<CCandidate> pListeCandidat, int pNbtour, int pNbcandidattour2) {
        /** initialise le vote avec pour argument la liste des candidats**/
        mListeCandidat = new ArrayList<>(pListeCandidat);
        Integer[] lDefaultVal = new Integer[pListeCandidat.size()];
        Arrays.fill(lDefaultVal, 0);
        mCandNumbVote = new ArrayList<>(Arrays.asList(lDefaultVal));
        mNbtour=pNbtour;
        mNbCandidatTour2=pNbcandidattour2;
        /*
        for (int i =0;i<pListeCandidat.size();i++){//mise en place de la liste pour ce vote
            CCandidate lCand = new CCandidate(pListeCandidat.get(i));
            mListeCandidat.add(lCand);
        }*/
    }

    /**
     * traitement d'un nouveau vote
     *
     * <p> A l'arrivée d'un nouveau bulletin, ajoute une voix au candidat dans la liste </p>
     * @param pchoix candidat choisi par le votant
     */
    public void nouveauVote(CCandidate pchoix){
        /** permet de valider le choix d'un votant avec pour argument le candidat choisi **/
        int lindex = mListeCandidat.indexOf(pchoix);
        mCandNumbVote.set(lindex, mCandNumbVote.get(lindex)+1);
    }

    /**
     * fini le tour 1
     * <p>cherche les candidat avec le plus de voix et les place dans une nouvelle liste de taille le nombre de candidat
     * au tour deux puis transforme la liste des candidat en la liste des candidat au tour deux </p>
     */
    public void tourSuivant(){

        List<CCandidate> lListTour = new ArrayList<>();//cree la nouvelle liste de candidat pour le tour suivant
        List<Integer> lCandNumbVote = new ArrayList<>();
        int lNbCandT2 = 0;

        while(lNbCandT2 < mNbCandidatTour2)
        {
            int lindex = mCandNumbVote.indexOf(Collections.max(mCandNumbVote));
            lListTour.add(mListeCandidat.get(lindex));
            lCandNumbVote.add(mCandNumbVote.get(lindex));
            mCandNumbVote.remove(lindex);
            mListeCandidat.remove(lindex);
            lNbCandT2++;
        }

        mListeCandidat = lListTour;
        mCandNumbVote = lCandNumbVote;

    }

    /**
     * fonction d'affichage sur la sortie sout
     */
    public void affiche(){
        for (int i = 0; i <mListeCandidat.size() ; i++) {
            System.out.println(mListeCandidat.get(i));
        }
    }

    /**
     * fonction de calcule de resultat
     * <p>cherche dans la liste des candidat celui qui a le plus de voix , pui le renvoi </p>
     * @return le candidat qui a le plus de voix
     */
    public CCandidate resultat(){// renvoi le candidat avec le plus de vote pour lui
        /** donne le resultat du vote sans parametre avec pour retour le candidat avec le plus de vote pour lui  **/
        CCandidate vainqueur;
        int lindex = mCandNumbVote.indexOf(Collections.max(mCandNumbVote));

        vainqueur = mListeCandidat.get(lindex);

       return vainqueur;
    }

}
