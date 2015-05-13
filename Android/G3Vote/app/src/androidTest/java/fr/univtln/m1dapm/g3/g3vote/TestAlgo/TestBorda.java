package fr.univtln.m1dapm.g3.g3vote.TestAlgo;

import java.util.ArrayList;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Algorithme.Borda.CAlgoBorda;
import fr.univtln.m1dapm.g3.g3vote.Algorithme.Borda.CCandidatBorda;

/**
 * Created by lyamsi on 12/05/15.
 */
public class TestBorda {
    public static void main(String[] args) {
        // 4 Candidats :
        CCandidateBorda A = new CCandidateBorda("A");
        CCandidateBorda B = new CCandidateBorda("B");
        CCandidateBorda C = new CCandidateBorda("C");
        CCandidateBorda D = new CCandidateBorda("D");
        // 4 clessemnt pour ce test
        List<CCandidateBorda> classement1 = new ArrayList<CCandidateBorda>();
        List<CCandidateBorda> classement2 = new ArrayList<CCandidateBorda>();
        List<CCandidateBorda> classement3 = new ArrayList<CCandidateBorda>();
        List<CCandidateBorda> classement4 = new ArrayList<CCandidateBorda>();
        // 1) ABCD 2) BCDA 3) CDBA 4) DCBA
        classement1.add(A);classement1.add(B);classement1.add(C);classement1.add(D);
        classement2.add(B);classement2.add(C);classement2.add(D);classement2.add(A);
        classement3.add(C);classement3.add(D);classement3.add(B);classement3.add(A);
        classement4.add(D);classement4.add(C);classement4.add(B);classement4.add(A);
        CAlgoBorda cAlgoBorda = new CAlgoBorda();
        // 42 ont choisis un classement ABCD
        for (int i = 0; i < 42; i++) {
            cAlgoBorda.put(classement1);
        }
        // 26 ont choisis un classement BCDA
        for (int i = 0; i < 26; i++) {
            cAlgoBorda.put(classement2);
        }
        // 15 ont choisis un classement CDBA
        for (int i = 0; i < 15; i++) {
            cAlgoBorda.put(classement3);
        }
        // 17 ont choisis un classement DCBA
        for (int i = 0; i < 17; i++) {
            cAlgoBorda.put(classement4);
        }


        // Calcule du gagnant :
        System.out.println(cAlgoBorda.borda());

    }
}
