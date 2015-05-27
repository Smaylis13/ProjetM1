package fr.univtln.m1dapm.g3.g3vote.Algorithme.Condorcet;

import java.util.ArrayList;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.Entite.CChoixpossible;

/**
 * Created by Pierre on 21/05/2015.
 */
public class CCondorcetTest {


    public static void main(String[] args) {

        CCondorcet condorcet = new CCondorcet();
        List<CChoixpossible> Listresult=new ArrayList<CChoixpossible>();

        CCandidate a=new CCandidate("Memphis");
        CCandidate b=new CCandidate("Nashville");
        CCandidate c=new CCandidate("Chattanooga");
        CCandidate d=new CCandidate("knoxville");


        List list = new ArrayList();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);


        CChoixpossible choix1 =new CChoixpossible(list);
        choix1.updateNbVote(42);
        Listresult.add(choix1);

        List list2 = new ArrayList();
        list2.add(b);
        list2.add(c);
        list2.add(d);
        list2.add(a);
        CChoixpossible choix2 =new CChoixpossible(list2);
        choix2.updateNbVote(26);
        Listresult.add(choix2);



        List list3 = new ArrayList();
        list3.add(c);
        list3.add(d);
        list3.add(b);
        list3.add(a);
        CChoixpossible choix3 =new CChoixpossible(list3);
        choix3.updateNbVote(15);
        Listresult.add(choix3);

        List list4 = new ArrayList();
        list4.add(d);
        list4.add(c);
        list4.add(b);
        list4.add(a);
        CChoixpossible choix4 =new CChoixpossible(list4);
        choix4.updateNbVote(17);
        Listresult.add(choix4);


        condorcet.resultat(Listresult);
    }
}
