package fr.univtln.m1dapm.g3.g3vote.Algorithme.VoteMajoritaire;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by lyamsi on 09/05/15.
 */
public class TestVM {
    public static int NB_VOTANT = 21;
    public static int NB_CANDIDATE = 5;
    public static void main(String[] args) {
        // entrée
        List<CChoiceVM> candidates = new ArrayList<CChoiceVM>();
        List<CVoterVM> votants = new ArrayList<CVoterVM>();
        // init Entrée
        for( int i = 0 ; i < NB_CANDIDATE ; i++) {
            candidates.add(new CChoiceVM("Cand0" + i));
        }
        for(int i = 0; i < NB_VOTANT ; i++){
            CVoterVM v = new CVoterVM();// on crée un votant
            for ( CChoiceVM c : candidates) {//on rempli sa liste (candidate <==> note)
                Random rand = new Random();
                int note = rand.nextInt(5);// une note aléatoire pour le test
                v.addCN(c,note);// add map c,note
                c.addNote(note);//add note dans candidat
            }
            votants.add(v); // en fin on l'ajoute à la liste
        }

        CAlgoVoteMaj cAlgoVoteMaj = new CAlgoVoteMaj(candidates,votants);
        System.out.println(cAlgoVoteMaj.calculateSum());
        /*System.out.println(cAlgoVoteMaj.calculateAverage());

        System.out.println(cAlgoVoteMaj.calculateMedian());*/


    }
}
