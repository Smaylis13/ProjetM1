package fr.univtln.m1dapm.g3.g3vote.Algorithme.KemenyYoung;

import java.util.ArrayList;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Algorithme.AAlgorithme;
import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidat;
import fr.univtln.m1dapm.g3.g3vote.Entite.CChoixpossible;

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
     * le nombre de candidat
     *
     */
    private int mNombreCandidat;
    /**
     * la liste des resultat possible est une liste de liste
     */
    private List<CChoixpossible> mListResult;
    /**
     * la taille de la liste des tesultat possible est calculer
     */
    private int mTailleList;


    /////////////////////////////////////////////////////////////////////////////////////////////
    /*           LES REGLES DE CODAGES !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!   */
    /////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * constructeur du vote CKemenyYoung
     * <p>le constructeur n'initialise pas les parametre </p>
     *
     * @see CKemenyYoung#inittab(int, List)
     */
    public CKemenyYoung() {
    }

    @Override
    protected void initVote() {

    }


    /**
     * initialise vraiment le vote et lance la génération de la liste resultat
     *
     * <p>initialise les diférente valeur puis lance la generation de la liste resultat</p>
     * @param pNombreCandidat
     *              nombre de candidat qui peuvent étre choisi
     * @param pListCand
     *              la liste des candidat
     *
     *
     * @see CKemenyYoung#generate(int, List)
     */
    public void inittab(int pNombreCandidat, List<CCandidat> pListCand) {
        this.mNombreCandidat = pNombreCandidat;

        int i;
        mTailleList = 1;
        for (i = 0; i < mNombreCandidat; i++) {
            mTailleList = mTailleList * (mNombreCandidat - i);
        }
        mListResult = new ArrayList<CChoixpossible>();// crée la liste des résultat possible
        generate(mNombreCandidat, pListCand); //remplie la liste des résultat possible




        /*for (i = 0; i < fact; i++) {
            System.out.println(mListResult.get(i).toString());
            System.out.println(mListResult.get(i).getNbVote());
            //A optimisé
        }*/


    }

    /**
     * affiche la liste resultat dans la sorti sout
     */

    public void affiche () {
        for (int i = 0; i < mTailleList; i++) {
            System.out.println(mListResult.get(i).toString());
        }
    }

    /**
     * generation de la liste des resultat possible
     * <p>
     *     place les candidat les un après les autre de manierre recursive pour crée toute les combinason possible
     * </p>
     * @param pNombreCandidat
     *              nombre de candidat restant a placer
     * @param pListCand
     *              liste des candidat restant a placer
     * @return pListCand
     */

    public List<CCandidat> generate(int pNombreCandidat, List<CCandidat> pListCand) {
        int i;

        CChoixpossible lChoixpossible = new CChoixpossible(pListCand); //création de choix


        if (pNombreCandidat == 1) {

            mListResult.add(lChoixpossible); // mise en place du choix parmi les résultat possible

        } else {
            CCandidat pcandidattemporaire;
            for (i = 0; i < pNombreCandidat; i++) { // fait tout les choix possible
                pListCand = generate(pNombreCandidat - 1, pListCand);
                if (pNombreCandidat % 2 == 0) {
                    pcandidattemporaire = pListCand.get(i);
                    pListCand.set(i, pListCand.get(pNombreCandidat - 1));
                    pListCand.set(pNombreCandidat - 1, pcandidattemporaire);
                } else {
                    pcandidattemporaire = pListCand.get(0);
                    pListCand.set(0, pListCand.get(pNombreCandidat - 1));
                    pListCand.set(pNombreCandidat - 1, pcandidattemporaire);
                }

            }
        }
        return pListCand;
    }

    /**
     * envoie le gagnant
     * <p>cherche dans la liste des resultat possible celui qui a le plus de point et le renvoi</p>
     * @param pnbvainqueur
     *              le nombre de vainqueur que la fonction vous renvéra
     * @return la liste gagnante
     *
     * @see CKemenyYoung#mListResult
     */

    public List<CCandidat> resultat (int pnbvainqueur ) {
        int maxvote = 0;
        int maxvoteposition=0;
        for (int i = 0; i < mListResult.size(); i++) {
            if (mListResult.get(i).getNbVote() > maxvote) {
                maxvote = mListResult.get(i).getNbVote();
                maxvoteposition=i;
            }
        }
        return mListResult.get(maxvoteposition).getChoix();
    }


    /**
     * recoi le nouveau vote
     * <p> récupére le vote envoyer par le votant ajoute les point au différent résultat possible  </p>
     * @param choix
     *              choix effectuer par le votant
     */

    public void nouveauVote(List<CCandidat> choix) { // ajout des point liée au nouveau vote
        CCandidat candidat1 = new CCandidat();
        CCandidat candidat2 = new CCandidat();
        int y;
        int z;
        int poid = 0;
        for (int i = 0; i < mTailleList; i++) {
            for (int j = 0; j < mNombreCandidat; j++) {
                for (int x = j + 1; x < mNombreCandidat; x++) {
                    candidat1 = choix.get(j);
                    candidat2 = choix.get(x);
                    y = 0;
                    z = 0;

                    for (y=0;y<mNombreCandidat;y++){//trouve la position de candida 1 dans le résultat en cour
                        if (candidat1.equals( mListResult.get(i).getIndexValue(y))){
                            break;
                        }
                    }
                    for (z=0;z<mNombreCandidat;z++){//trouve la position de candida 2 dans le résultat en cour
                        if (candidat2.equals(mListResult.get(i).getIndexValue(z))){
                            break;
                        }
                    }

                    if (y < z) { // vérifie que ils sont dans le même ordre
                       poid++; // si oui on ajoute 1 point au vote
                    }
                }

            }

        mListResult.get(i).updateNbVote(poid);
            poid=0;
        }
    }
}

