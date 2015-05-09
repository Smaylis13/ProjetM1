package fr.univtln.m1dapm.g3.g3vote.Algorithme.VoteMajoritaire;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CAlgoVoteMaj { //extends AAlgorithme {
    List<CChoixVM> mChoice = new ArrayList<CChoixVM>();
    List<CRegleVM> mRules = new ArrayList<CRegleVM>();

    public CAlgoVoteMaj() {

    }

    public CAlgoVoteMaj(List<CChoixVM> pChoice,List<CRegleVM> pRules){
        this.mChoice = pChoice;
        this.mRules = pRules;
    }

   /* @Override
    protected void initVote() {

    }

    @Override
    protected void initVote() {
        mRegles = new ArrayList<CRegleVM>();
        mCandidats = new ArrayList<CChoixVM>();
    }*/

    /**
     *
     * @return
     */
    public CChoixVM calculer() {
        int lNbChoix    = mChoice.size();
        int lNbRegle = mRules.size();
        for (CRegleVM v : mRules) {
            for (int i = 0; i < lNbChoix; i++) {
                CChoixVM c = mChoice.get(i);
                int value = v.getMapCandidateNote().get(c);// key = candidat
                mChoice.get(i).addNote(value);

            }
        }
        // Le trie
        for (CChoixVM c : mChoice) {
            c.trieNote();
        }
        // recherche du vaiqueur
        int max = 0;
        CChoixVM vainqueur = null;
        int mediane = (lNbRegle + 1) / 2;
        for (CChoixVM c : mChoice) {
            if (c.getNote().get(mediane) > max) {
                max = c.getNote().get(mediane);
                vainqueur = c;
            }
        }
        // on cherche s'il ya d'autres vainqueurs qui ont la même médiane
        List<CChoixVM> vainqueurs = new ArrayList<CChoixVM>();// il peut y avoir plusieurs Gagnants
        for (CChoixVM c : mChoice) {
            if (vainqueur.getNote().get(mediane) == c.getNote().get(mediane)){
                vainqueurs.add(c);
            }
        }
        if (vainqueurs.size() <= 1 ){//s'il exite qu'un seul vainqueur on sort
            return vainqueur;
        }
        // sinon pour les départager :
        // associé à un entier (le max entre moins/plus de la médiane)
        Map<CChoixVM,Integer> medianeMap = new HashMap<CChoixVM,Integer>();
        int moins = 0;
        int plus = 0;
        for (CChoixVM c : mChoice){
            for (int i : c.getNbNote().keySet()){
                if ( i != mediane){
                    if(i < mediane){
                        moins += c.getNbNote().get(i);
                    }else{
                        plus += c.getNbNote().get(i);
                    }
                }
            }
            if(moins > plus){
                medianeMap.put(c,-moins);
            }else{
                medianeMap.put(c,plus);
            }
        }
        int maxValue = 0;

        for (Map.Entry<CChoixVM, Integer> entry : medianeMap.entrySet()){
            if (entry.getValue().compareTo(maxValue) > 0){
                maxValue = entry.getValue();
                vainqueur = entry.getKey();
            }
        }

        return vainqueur;
    }


}
