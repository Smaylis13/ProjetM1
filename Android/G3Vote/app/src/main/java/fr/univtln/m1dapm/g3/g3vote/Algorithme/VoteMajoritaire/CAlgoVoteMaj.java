package fr.univtln.m1dapm.g3.g3vote.Algorithme.VoteMajoritaire;

import java.util.ArrayList;
import java.util.List;


public class CAlgoVoteMaj { //extends AAlgorithme {
    List<CChoixVM> mChoices = new ArrayList<CChoixVM>();
    List<CVotantVM> mVotants = new ArrayList<CVotantVM>();

    public CAlgoVoteMaj() {

    }

    public CAlgoVoteMaj(List<CChoixVM> pChoices,List<CVotantVM> pVotants){
        this.mChoices = pChoices;
        this.mVotants = pVotants;
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
     * @return CChoixVM
     */
    public CChoixVM calculerMediane() {
        //int lNbChoix    = mChoices.size();
        int lNbVotant = mVotants.size();

        /*for (CVotantVM v : mVotants) {
            for (int i = 0; i < lNbChoix; i++) {
                CChoixVM c = mChoices.get(i);
                int value = v.getMapCandidateNote().get(c);// key = candidat
                mChoices.get(i).addNote(value);

            }
        }*/
        // Le trie
        for (CChoixVM c : mChoices) {
            c.trieNote();
        }
        // recherche du vaiqueur
        int max = 0;
        CChoixVM vainqueur = null;
        int mediane = (lNbVotant + 1) / 2;
        for (CChoixVM c : mChoices) {
            if (c.getNotes().get(mediane) > max) {
                max = c.getNotes().get(mediane);
                vainqueur = c;
            }
        }
        // on cherche s'il ya d'autres vainqueurs qui ont la même médiane
        List<CChoixVM> vainqueurs = new ArrayList<CChoixVM>();// il peut y avoir plusieurs Gagnants
        for (CChoixVM c : mChoices) {
            if (vainqueur.getNotes().get(mediane) == c.getNotes().get(mediane)){
                vainqueurs.add(c);
            }
        }
        if (vainqueurs.size() <= 1 ){//s'il exite qu'un seul vainqueur on sort
            return vainqueur;
        }
        //pour les départager on supprime la valeur de la médiane et on recommence avec la nouvelle liste
        mChoices.clear();
        for (CChoixVM c : vainqueurs){
            c.subNote(mediane);
            mChoices.add(c);
        }
        // Si la taille des est moins de 2 alors on return null ce qui veut dire qu'il n'y a pas de gagnant!
        if ( mChoices.get(0).getNotes().size() < 2){
            return null;
        }
        return calculerMediane();
        /*
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

        return vainqueur;*/
    }

    /**
     *
     * @return le gagnant en fonction de sa moyenne
     */
    public CChoixVM calculerMoyenne() {
        //int lNbChoix  = mChoices.size();
        int lNbVotant = mVotants.size();
        //parcour la liste des votants pour récupérer les notes attribué aux candidats
        /*for (CVotantVM v : mVotants) {
            for (int i = 0; i < lNbChoix; i++) {
                CChoixVM c = mChoices.get(i);
                int value = v.getMapCandidateNote().get(c);// key = candidat
                mChoices.get(i).addNote(value);

            }
        }*/
        // recherche du vaiqueur
        double max = 0.0;
        CChoixVM vainqueur = null;

        for (CChoixVM c : mChoices) {
            if (c.moyenneNote() > max) {
                max = c.moyenneNote();
                vainqueur = c;
            }
        }
        // on cherche s'il ya d'autres vainqueurs qui ont la même moyenne
        List<CChoixVM> vainqueurs = new ArrayList<CChoixVM>();
        for (CChoixVM c : mChoices) {
            if (vainqueur.getmMoyenne() == c.getmMoyenne()){
                vainqueurs.add(c);
            }
        }
        if (vainqueurs.size() <= 1 ){//s'il exite qu'un seul vainqueur on sort
            return vainqueur;
        }

        int mediane = (lNbVotant + 1) / 2;
        mChoices.clear();
        for (CChoixVM c : vainqueurs){
            c.subNote(mediane);
            mChoices.add(c);
        }
        // Si la taille des est moins de 2 alors on return null ce qui veut dire qu'il n'y a pas de gagnant!
        if ( mChoices.get(0).getNotes().size() < 2){
            return null;
        }
        return calculerMoyenne();
    }

    /**
     *
     * @return gagnant par somme des points reçu
     */
    public CChoixVM calculerSomme() {
        int lNbVotant = mVotants.size();

        // recherche du vaiqueur
        double max = 0.0;
        CChoixVM vainqueur = null;

        for (CChoixVM c : mChoices) {
            if (c.sommeNote() > max) {
                max = c.sommeNote();
                vainqueur = c;
            }
        }
        // on cherche s'il ya d'autres vainqueurs qui ont la même somme
        List<CChoixVM> vainqueurs = new ArrayList<CChoixVM>();
        for (CChoixVM c : mChoices) {
            assert vainqueur != null;
            if (vainqueur.getmSomme() == c.getmSomme()){
                vainqueurs.add(c);
            }
        }
        if (vainqueurs.size() <  2 ){//s'il exite qu'un seul vainqueur on sort
            return vainqueur;
        }

        int mediane = (lNbVotant + 1) / 2;
        mChoices.clear();
        for (CChoixVM c : vainqueurs){
            c.subNote(mediane);
            mChoices.add(c);
        }
        // Si la taille des est moins de 2 alors on return null ce qui veut dire qu'il n'y a pas de gagnant!
        if ( mChoices.get(0).getNotes().size() < 2){
            return null;
        }
        return calculerSomme();
    }

}
