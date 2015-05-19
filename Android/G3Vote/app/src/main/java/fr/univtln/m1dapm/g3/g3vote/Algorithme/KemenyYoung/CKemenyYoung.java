package fr.univtln.m1dapm.g3.g3vote.Algorithme.KemenyYoung;

import java.util.ArrayList;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Algorithme.AAlgorithme;
import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidat;

/**
 * Created by Pierre on 07/05/2015.
 */
public class CKemenyYoung extends AAlgorithme {

    private int mNombreCandidat;
    private List<CChoixpossible> mListResult;
    private int mTailleList;


    /////////////////////////////////////////////////////////////////////////////////////////////
    /*           LES REGLES DE CODAGES !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!   */
    /////////////////////////////////////////////////////////////////////////////////////////////

    public CKemenyYoung() {
    }

    @Override
    protected void initVote() {

    }


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
    public void affiche () {
        for (int i = 0; i < mTailleList; i++) {
            System.out.println(mListResult.get(i).toString());
        }
    }


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

