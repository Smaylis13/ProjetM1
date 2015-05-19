package fr.univtln.m1dapm.g3.g3vote.Algorithme.VoteMajoritaire;

import java.util.ArrayList;
import java.util.List;


public class CAlgoVoteMaj {
    List<CChoiceVM> mChoices = new ArrayList<CChoiceVM>();
    List<CVoterVM> mVoters = new ArrayList<CVoterVM>();

    public CAlgoVoteMaj() {

    }

    public CAlgoVoteMaj(List<CChoiceVM> pChoices,List<CVoterVM> pVoters){
        this.mChoices = pChoices;
        this.mVoters = pVoters;
    }

    /**
     *
     * @return CChoixVM
     */
    public CChoiceVM calculateMedian() {
        int lNbVoting = mVoters.size();

        // Le trie
        for (CChoiceVM c : mChoices) {
            c.sortNote();
        }
        // recherche du vaiqueur
        int max = 0;
        CChoiceVM lWinner = null;
        int mediane = (lNbVoting + 1) / 2;
        for (CChoiceVM c : mChoices) {
            if (c.getNotes().get(mediane) > max) {
                max = c.getNotes().get(mediane);
                lWinner = c;
            }
        }
        // on cherche s'il ya d'autres vainqueurs qui ont la même médiane
        List<CChoiceVM> lWinners = new ArrayList<CChoiceVM>();// il peut y avoir plusieurs Gagnants
        for (CChoiceVM c : mChoices) {
            if (lWinner.getNotes().get(mediane) == c.getNotes().get(mediane)){
                lWinners.add(c);
            }
        }
        if (lWinners.size() <= 1 ){//s'il exite qu'un seul vainqueur on sort
            return lWinner;
        }
        //pour les départager on supprime la valeur de la médiane et on recommence avec la nouvelle liste
        mChoices.clear();
        for (CChoiceVM c : lWinners){
            c.subNote(mediane);
            mChoices.add(c);
        }
        // Si la taille des est moins de 2 alors on return null ce qui veut dire qu'il n'y a pas de gagnant!
        if ( mChoices.get(0).getNotes().size() < 2){
            return null;
        }
        return calculateMedian();
    }

    /**
     *
     * @return le gagnant en fonction de sa moyenne
     */
    public CChoiceVM calculateAverage() {
        int lNbVoting = mVoters.size();
        // recherche du vaiqueur
        double max = 0.0;
        CChoiceVM lWinner = null;

        for (CChoiceVM c : mChoices) {
            if (c.moyenneNote() > max) {
                max = c.moyenneNote();
                lWinner = c;
            }
        }
        // on cherche s'il ya d'autres vainqueurs qui ont la même moyenne
        List<CChoiceVM> lWinners = new ArrayList<CChoiceVM>();
        for (CChoiceVM c : mChoices) {
            if (lWinner.getmAvg() == c.getmAvg()){
                lWinners.add(c);
            }
        }
        if (lWinners.size() <= 1 ){//s'il exite qu'un seul vainqueur on sort
            return lWinner;
        }

        int mediane = (lNbVoting + 1) / 2;
        mChoices.clear();
        for (CChoiceVM c : lWinners){
            c.subNote(mediane);
            mChoices.add(c);
        }
        // Si la taille des notes est moins de 2 alors on return null ce qui veut dire qu'il n'y a pas de gagnant!
        if ( mChoices.get(0).getNotes().size() < 2){
            return null;
        }
        return calculateAverage();
    }

    /**
     *
     * @return gagnant par somme des points reçu
     */
    public CChoiceVM calculateSum() {
        int lNbVoting = mVoters.size();

        // recherche du vaiqueur
        double lMax = 0.0;
        CChoiceVM lWinner = null;

        for (CChoiceVM c : mChoices) {
            if (c.sommeNote() > lMax) {
                lMax = c.getmSum();
                lWinner = c;
            }
        }
        // on cherche s'il ya d'autres vainqueurs qui ont la même somme
        List<CChoiceVM> lWinners = new ArrayList<CChoiceVM>();
        for (CChoiceVM c : mChoices) {
            assert lWinner != null;
            if (lWinner.getmSum() == c.getmSum()){
                lWinners.add(c);
            }
        }
        if (lWinners.size() <  2 ){//s'il exite qu'un seul vainqueur on sort
            return lWinner;
        }

        int mediane = (lNbVoting + 1) / 2;
        mChoices.clear();
        for (CChoiceVM c : lWinners){
            c.subNote(mediane);
            mChoices.add(c);
        }
        // Si la taille des est moins de 2 alors on return null ce qui veut dire qu'il n'y a pas de gagnant!
        if ( mChoices.get(0).getNotes().size() < 2){
            return null;
        }
        return calculateSum();
    }

}
