package fr.univtln.m1dapm.g3.g3vote.Algorithme.Condorcet;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Algorithme.AAlgorithme;
import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidat;
import fr.univtln.m1dapm.g3.g3vote.Entite.CChoixpossible;
import fr.univtln.m1dapm.g3.g3vote.Entite.CVoixCandidat;

/**
 * Created by Pierre on 21/05/2015.
 */


/**
 * <p> cette classe permet de chercher les vainqueurs par la methode de condorcet</p>
 */
public class CCondorcet extends AAlgorithme {


    /**
     * constructeur de CCondorcet
     */
    public CCondorcet (){initVote();}
    @Override
    protected void initVote() {
    }


    /**
     * resultat calcule et renvoi les gagnant par la methode de condorcet
     * <p>la fonction calcule tout les duel puis ajoute les point de chaque candidat par raport au autre , enfin la fonction crée une liste des candidat
     * dans l'ordre décroissant </p>
     * @param pListResult
     *          liste des vote et le nombre de foi que ils furent choisi
     * @return liste des gagnant dans l'ordre
     */
    public List<CCandidat> resultat (List<CChoixpossible> pListResult){
        //System.out.println(pListResult);
        CCandidat candidat1 = new CCandidat();
        CCandidat candidat2 = new CCandidat();
        List<CChoixpossible> duel=new ArrayList<CChoixpossible>();
        int l,m,numduel=0;
        for (int i = 0; i <pListResult.get(0).getChoix().size() ; i++) {
            for (int j = i+1; j <pListResult.get(0).getChoix().size() ; j++) {// crée le duel de candidat
                candidat1 = pListResult.get(0).getChoix().get(i);
                candidat2 = pListResult.get(0).getChoix().get(j);
                List<CCandidat> duocandidat =new ArrayList<CCandidat>();
                duocandidat.add(candidat1);
                duocandidat.add(candidat2);
                CChoixpossible choixduel =new CChoixpossible(duocandidat);
                duel.add(choixduel);


                for (int k = 0; k <pListResult.size() ; k++) { // conte les point pour le duel
                    l=0;
                    m=0;
                    for (l = 0; l <pListResult.get(k).getChoix().size() ; l++) {
                        if (candidat1.equals( pListResult.get(k).getChoix().get(l))){
                            break;
                        }
                    }
                    for (m = 0; m <pListResult.get(k).getChoix().size() ; m++) {
                        if (candidat2.equals( pListResult.get(k).getChoix().get(m))){
                            break;
                        }
                    }
                    //System.out.println(pListResult.get(k));
                    //System.out.println("k = "+k+" candidat1 = "+candidat1+" candidat2 = "+candidat2);
                    //System.out.println("numduel = "+numduel+" l = " +pListResult.get(k).getChoix().get(l)+" m= "+pListResult.get(k).getChoix().get(m));
                    //System.out.println(k+" dans la confrontation "+candidat1+" "+candidat2);
                    if (l<m){
                        //System.out.println("1er gagne");
                        duel.get(numduel).updateNbVote(pListResult.get(k).getNbVote());

                    }else{
                        //System.out.println("2emme gagne");
                        duel.get(numduel).updateNbVote(-(pListResult.get(k).getNbVote()));
                    }
                    //System.out.println("un nombre de voi egal a "+pListResult.get(k).getNbVote());
                    //System.out.println(duel.get(numduel));
                }
                numduel++;
            }
        }
        System.out.println(duel);
        List<Integer> point=new ArrayList();
        for (int i = 0; i <pListResult.get(0).getChoix().size() ; i++) {
            point.add(0);
            for (int j = 0; j <duel.size() ; j++) {
                if (pListResult.get(0).getChoix().get(i).equals(duel.get(j).getIndexValue(0))){
                    point.set(i,point.get(i)+duel.get(j).getNbVote());
                }else if (pListResult.get(0).getChoix().get(i).equals(duel.get(j).getIndexValue(1))){
                    point.set(i,point.get(i)-duel.get(j).getNbVote());
                }

            }
        }
        //System.out.println(point);
        int tmp;
        List<Integer> indice =new ArrayList<Integer>();
        for (int i = 0; i <point.size() ; i++) {
            indice.add(i);
        }
        for (int i = 0; i <point.size() ; i++) {
            for (int j = 0; j < (point.size()-1)-i; j++) {
                if (point.get(j)<point.get(j+1)){
                    tmp=point.get(j);
                    point.set(j, point.get(j + 1));
                    point.set(j+1,tmp);
                    tmp=indice.get(j);
                    indice.set(j, indice.get(j + 1));
                    indice.set(j+1,tmp);

                }
            }

        }
        List<CCandidat> resultat =new ArrayList<CCandidat>();
        for (int i = 0; i <indice.size() ; i++) {
            resultat.add(pListResult.get(0).getChoix().get(indice.get(i)));
        }
        System.out.println(resultat);
        return resultat;
    }



}
