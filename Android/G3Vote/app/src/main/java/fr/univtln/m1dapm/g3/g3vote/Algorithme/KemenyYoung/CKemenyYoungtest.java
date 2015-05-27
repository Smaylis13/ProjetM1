package fr.univtln.m1dapm.g3.g3vote.Algorithme.KemenyYoung;

import java.util.ArrayList;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;

/**
 * Created by Pierre on 19/05/2015.
 */
public class CKemenyYoungtest {

    public static void main(String[] args) {


        CKemenyYoung kemenyYoung=new CKemenyYoung();
        CCandidate a=new CCandidate("Memphis");
        CCandidate b=new CCandidate("Nashville");
        CCandidate c=new CCandidate("Chattanooga");
        CCandidate d=new CCandidate("knoxville");
        CCandidate e=new CCandidate("toulon");
        CCandidate f=new CCandidate("la garde");
        CCandidate g=new CCandidate("cuers");





        List list = new ArrayList();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);
        list.add(f);
        list.add(g);


        kemenyYoung.inittab(list.size(),list);


        int i;
        List listmem = new ArrayList();
        listmem.add(a);
        listmem.add(b);
        listmem.add(c);
        listmem.add(d);
        listmem.add(e);
        listmem.add(f);
        listmem.add(g);


        for (i=0;i<42;i++) {
            kemenyYoung.nouveauVote(listmem);
        }

        List listnash = new ArrayList();
        listnash.add(b);
        listnash.add(c);
        listnash.add(g);
        listnash.add(d);
        listnash.add(e);
        listnash.add(f);
        listnash.add(a);

        for (i=0;i<26;i++) {
            kemenyYoung.nouveauVote(listnash);
        }

        List listchat = new ArrayList();
        listchat.add(c);
        listchat.add(d);
        listchat.add(b);
        listchat.add(g);
        listchat.add(e);
        listchat.add(f);
        listchat.add(a);

        for (i=0;i<15;i++) {
            kemenyYoung.nouveauVote(listchat);
        }

        List listkno = new ArrayList();
        listkno.add(d);
        listkno.add(g);
        listkno.add(f);
        listkno.add(e);
        listkno.add(c);
        listkno.add(b);
        listkno.add(a);


        for (i=0;i<17;i++) {
            kemenyYoung.nouveauVote(listkno);
        }
        List listresult = new ArrayList();
        //kemenyYoung.affiche();
        listresult=kemenyYoung.resultat(4);


        System.out.println(listresult);
    }
}
