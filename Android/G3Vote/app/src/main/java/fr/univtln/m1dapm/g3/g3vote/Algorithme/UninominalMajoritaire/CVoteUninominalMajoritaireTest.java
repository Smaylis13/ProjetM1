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
        List list = new ArrayList();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        vote.initVote(list);

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
        CCandidat result;
        result=vote.resultat();

        System.out.println(result);
    }
}
