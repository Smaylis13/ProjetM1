package fr.univtln.m1dapm.g3.g3vote.Algorithme.VoteMajoritaire;

import java.util.ArrayList;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Algorithme.AAlgorithme;


public class CAlgoVoteMaj extends AAlgorithme {


    public CAlgoVoteMaj() {
        super();
    }


    @Override
    protected void initVote() {
        mRegles = new ArrayList<CRegleVM>();
        mCandidats = new ArrayList<CChoixVM>();
    }

    /**
     *
     * @param pChoice
     * @param pRules
     * @return
     */
    public CChoixVM calculer(List<CChoixVM> pChoice, List<CRegleVM> pRules) {
        int lNbChoix    = pChoice.size();
        int lNbRegle = pRules.size();
        for (CRegleVM v : pRules) {
            for (int i = 0; i < lNbChoix; i++) {
                CChoixVM c = pChoice.get(i);
                int value = v.getMapCandidateNote().get(c);// key = candidat
                pChoice.get(i).addNote(value);

            }
        }
        // Le trie
        for (CChoixVM c : pChoice) {
            c.trieNote();
        }
        // recherche du vaiqueur
        int max = 0;
        CChoixVM vainqueur = null;
        int mediane = (lNbRegle + 1) / 2;
        for (CChoixVM c : pChoice) {
            if (c.getNote().get(mediane) > max) {
                max = c.getNote().get(mediane);
                vainqueur = c;
            }
        }
        return vainqueur;
    }
}
