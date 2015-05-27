package fr.univtln.m1dapm.g3.g3vote.Algorithme.UninominalMajoritaire;

import java.util.ArrayList;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Algorithme.AAlgorithme;
import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.Entite.CVoixCandidat;

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
    private List<CVoixCandidat> mListeCandidat;//liste des candidat
    /**
     * nombre de tour (1 ou 2 actuelement)
     */
    private int mNbtour;// nombre de tour (1 ou 2)
    /**
     * nombre de candidat au tour deux
     */
    private int mNbCandidatTour2;// nombre de candidat qui passe au deusiemme tour


    @Override
    protected void initVote() {
    initVote();
    }
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
     * <p>initialise les parametre   </p>
     * @param pListeCandidat
     *          la liste des candidat qui se sont presenter
     * @param pNbtour
     *          le nombre de tour prevu (1 ou 2)
     * @param pNbcandidattour2
     *          le nombre de candidat quil restera au tour 2
     */

    protected void initVote(List<CCandidate> pListeCandidat,int pNbtour,int pNbcandidattour2) {
        /** initialise le vote avec pour argument la liste des candidat**/
        mListeCandidat=new ArrayList<CVoixCandidat>();
        mNbtour=pNbtour;
        mNbCandidatTour2=pNbcandidattour2;
        for (int i =0;i<pListeCandidat.size();i++){//mise en place de la liste pour ce vote
            CVoixCandidat voixCandidat=new CVoixCandidat(pListeCandidat.get(i));
            mListeCandidat.add(voixCandidat);
        }
    }

    /**
     * traitement d'un nouveau vote
     *
     * <p> a l'arriver d'un nouveau bulletin ajoute une voi au candidat dans la liste </p>
     * @param pchoix
     *          candidat choisi par le votant
     */
    public void nouveauVote(CCandidate pchoix){// ajoute un au nombre de vote pour le candidat
        /** permet de valider le choix d'un votant avec pour argument le candidat choisi **/
        for (int i = 0; i <mListeCandidat.size() ; i++) {
            if (mListeCandidat.get(i).getMcandidat()==pchoix){
                mListeCandidat.get(i).addMvote();
                return;
            }

        }
    }

    /**
     * fini le tour 1
     * <p>cherche les candidat avec le plus de voix et les place dans une nouvelle liste de taille le nombre de candidat
     * au tour deux puis transforme la liste des candidat en la liste des candidat au tour deux </p>
     */
    public void toursuivant(){

        List<CVoixCandidat> plistetour2=new ArrayList<>();//cree la nouvelle liste de candidat pour le tour suivant
        int minvoteplace=0;
        int minvote=mListeCandidat.get(0).getMvote();
        plistetour2.add(mListeCandidat.get(0));
        for(int i=1;i<mListeCandidat.size();i++){  // pour chaque candidat
            if (plistetour2.size()<mNbCandidatTour2){ // si la liste pour le tour suivant n'est pas pleinne
                plistetour2.add(mListeCandidat.get(i)); // les ajouter
                if (minvote>mListeCandidat.get(i).getMvote()){ // garder le minimum de vote et son emplacement
                    minvote= mListeCandidat.get(i).getMvote();
                    minvoteplace=i;
                }
            }else{ // si la liste en complette
                /*System.out.println("candidat "+i);
                System.out.println(plistetour2);
                System.out.println("plus petit score "+minvote);
                System.out.println("place du plus petit score "+minvoteplace);
                System.out.println("suivant "+mListeCandidat.get(i).getMvote());*/
                if (minvote < mListeCandidat.get(i).getMvote()){ // si un des candidat a plus de vote que le minimum dans la nouvelle liste
                    //System.out.println("vote superieur trouver");
                    plistetour2.set(minvoteplace,mListeCandidat.get(i));
                    minvote=plistetour2.get(0).getMvote();
                    minvoteplace=0;
                    for (int j = 1; j <plistetour2.size() ; j++) {
                        if (minvote>plistetour2.get(j).getMvote()){
                            minvote=plistetour2.get(j).getMvote();
                            minvoteplace=j;
                        }
                    }
                }
            }

        }
        mListeCandidat.clear();
        for (int i = 0; i <plistetour2.size() ; i++) {
            mListeCandidat.add(plistetour2.get(i));
            mListeCandidat.get(i).setMvote(0);
        }
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
        Integer pnbvotemax=0;
        Integer pnbvote;
        CCandidate vainqueur = new CCandidate() ;

        for (int i = 0; i < mListeCandidat.size(); i++) {
            if (mListeCandidat.get(i).getMvote() > pnbvotemax) {
                pnbvotemax = mListeCandidat.get(i).getMvote();
                vainqueur = mListeCandidat.get(i).getMcandidat();
            }
        }


       return vainqueur;
    }

}
