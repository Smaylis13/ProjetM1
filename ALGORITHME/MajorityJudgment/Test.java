package fr.univtln.bruno.exemple.bibliotheque.TestU1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by igacha664 on 05/05/15.
 */
public class Test {
    public static int NB_VOTANT = 21;
    public static int NB_CANDIDATE = 5;

    public static void main(String[] args) {

        // entrée
        List<Candidate> candidates = new ArrayList<Candidate>();
        List<Votant> votants = new ArrayList<Votant>();
        // init Entrée
        for( int i = 0 ; i < NB_CANDIDATE ; i++) {
            candidates.add(new Candidate("Cand0" + i));
        }
        for(int i = 0; i < NB_VOTANT ; i++){
            Votant v = new Votant();// on crée un votant
            for ( Candidate c : candidates) {//on rempli sa liste (candidate <==> note)
                Random rand = new Random();
                int note = rand.nextInt(5);// une note aléatoire pour le test
                v.addCN(c,note);
            }
            votants.add(v); // en fin on l'ajoute à la liste
        }
        // début
        for( Votant v : votants) {
            for (int i = 0 ; i < NB_CANDIDATE ; i++) {
                Candidate c = candidates.get(i);
                int value = v.getMapCandidateNote().get(c);// key = candidat
                candidates.get(i).addNote(value);

            }
        }
        // Le trie
        for (Candidate c : candidates) {
            c.trieNote();
        }
        // recherche du vaiqueur
        int max = 0;
        Candidate vainqueur = null;
        int mediane = ( NB_VOTANT + 1 ) / 2;
        for (Candidate c : candidates){
            if(c.getNote().get(mediane) > max){
                max = c.getNote().get(mediane);
                vainqueur = c;
            }
        }
        System.out.println("Le vainqueur est : "+vainqueur);

        for (Candidate c : candidates){
            System.out.println("\n\n"+c+"\t\t Médiane ===> "+c.getNote().get(mediane));
        }
    }
}