package fr.univtln.m1dapm.g3.g3vote.Algorithme.kemeny_young;

import java.util.ArrayList;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidat;

/**
 * Created by Pierre on 07/05/2015.
 */
public class CKemenyYoungTest {



    public static void main(String[] args) {
        CKemenyYoung kemenyYoung=new CKemenyYoung();
        CCandidat a=new CCandidat("Memphis");
        CCandidat b=new CCandidat("Nashville");
        CCandidat c=new CCandidat("Chattanooga");
        CCandidat d=new CCandidat("knoxville");
        List list = new ArrayList();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        kemenyYoung.inittab(list.size(),list);


        int i;
        List listmem = new ArrayList();
        listmem.add(a);
        listmem.add(b);
        listmem.add(c);
        listmem.add(d);

        for (i=0;i<42;i++) {
            kemenyYoung.nouveauVote(listmem);
        }

        List listnash = new ArrayList();
        listnash.add(b);
        listnash.add(c);
        listnash.add(d);
        listnash.add(a);

        for (i=0;i<26;i++) {
            kemenyYoung.nouveauVote(listnash);
        }

        List listchat = new ArrayList();
        listchat.add(c);
        listchat.add(d);
        listchat.add(b);
        listchat.add(a);

        for (i=0;i<15;i++) {
            kemenyYoung.nouveauVote(listchat);
        }

        List listkno = new ArrayList();
        listkno.add(d);
        listkno.add(c);
        listkno.add(b);
        listkno.add(a);

        for (i=0;i<17;i++) {
            kemenyYoung.nouveauVote(listkno);
        }

        kemenyYoung.affiche();


    }

}
