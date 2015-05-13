package fr.univtln.m1dapm.g3.g3vote.Algorithme.KemenyYoung;

import java.util.ArrayList;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidat;

/**
 * Created by Pierre on 07/05/2015.
 */
public class CKemenyYoung {

    private int n;
    private List<CCandidat> mListCand;
    private CCandidat tmp;
    private List<CChoixpossible> mListResult;
    private int fact;


    /////////////////////////////////////////////////////////////////////////////////////////////
    /*           LES REGLES DE CODAGES !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!   */
    /////////////////////////////////////////////////////////////////////////////////////////////

    public CKemenyYoung() {
    }


    public void inittab(int n, List<CCandidat> pListCand) {
        this.n = n;

        int i;
        fact = 1;
        for (i = 0; i < n; i++) {
            fact = fact * (n - i);
        }
        mListResult = new ArrayList<CChoixpossible>();// crée la liste des résultat possible
        generate(n, pListCand); //remplie la liste des résultat possible




        /*for (i = 0; i < fact; i++) {
            System.out.println(mListResult.get(i).toString());
            System.out.println(mListResult.get(i).getNbVote());
            //A optimisé
        }*/


    }
    public void affiche () {
        for (int i = 0; i < fact; i++) {
            System.out.println(mListResult.get(i).toString());
        }
    }


    public List<CCandidat> generate(int n, List<CCandidat> pListCand) {
        int i;

        CChoixpossible lChoixpossible = new CChoixpossible(pListCand); //création de choix


        if (n == 1) {

            mListResult.add(lChoixpossible); // mise en place du choix parmi les résultat possible

        } else {
            for (i = 0; i < n; i++) { // fait tout les choix possible
                pListCand = generate(n - 1, pListCand);
                if (n % 2 == 0) {
                    tmp = pListCand.get(i);
                    pListCand.set(i, pListCand.get(n - 1));
                    pListCand.set(n - 1, tmp);
                } else {
                    tmp = pListCand.get(0);
                    pListCand.set(0, pListCand.get(n - 1));
                    pListCand.set(n - 1, tmp);
                }

            }
        }
        return pListCand;
    }


    public void nouveauVote(List<CCandidat> choix) { // ajout des point liée au nouveau vote
        CCandidat candidat1 = new CCandidat();
        CCandidat candidat2 = new CCandidat();
        int y;
        int z;
        int poid = 0;
        for (int i = 0; i < fact; i++) {
            for (int j = 0; j < n; j++) {
                for (int x = j + 1; x < n; x++) {
                    candidat1 = choix.get(j);
                    candidat2 = choix.get(x);
                    y = 0;
                    z = 0;

                    for (y=0;y<n;y++){//trouve la position de candida 1 dans le résultat en cour
                        if (candidat1.equals( mListResult.get(i).getIndexValue(y))){
                            break;
                        }
                    }
                    for (z=0;z<n;z++){//trouve la position de candida 2 dans le résultat en cour
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

