package fr.univtln.m1dapm.g3.g3vote.Algorithme.UninominalMajoritaire;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import fr.univtln.m1dapm.g3.g3vote.Algorithme.KemenyYoung.CKemenyYoung;
import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidat;

/**
 * Created by Pierre on 20/05/2015.
 */
public class CVoteUninominalMajoritaireTest {

    public static void main(String[] args) {

        CVoteUninominalMajoritaire vote = new CVoteUninominalMajoritaire();
        CCandidat a=new CCandidat("a");
        CCandidat b=new CCandidat("b");
        CCandidat c=new CCandidat("c");
        CCandidat d=new CCandidat("d");
        CCandidat e=new CCandidat("e");
        CCandidat f=new CCandidat("f");
        CCandidat g=new CCandidat("g");
        List list = new ArrayList();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);
        list.add(f);
        list.add(g);
        vote.initVote(list, 2, 3);
        for (int i=0;i<50;i++){
            vote.nouveauVote(a);
        }
        for (int i=0;i<60;i++){
            vote.nouveauVote(b);
        }
        for (int i=0;i<75;i++){
            vote.nouveauVote(c);
        }
        for (int i=0;i<20;i++){
            vote.nouveauVote(d);
        }
        for (int i=0;i<65;i++){
            vote.nouveauVote(e);
        }
        for (int i=0;i<95;i++){
            vote.nouveauVote(f);
        }
        for (int i=0;i<15;i++){
            vote.nouveauVote(g);
        }

        vote.affiche();
        vote.toursuivant();
        System.out.println("tour suivant");
        vote.affiche();

        for (int i=0;i<50;i++){
            vote.nouveauVote(e);
        }
        for (int i=0;i<60;i++){
            vote.nouveauVote(f);
        }
        for (int i=0;i<75;i++) {
            vote.nouveauVote(c);
        }

        CCandidat result;
        result=vote.resultat();

       System.out.println(result);
    }
}
