package fr.univtln.m1dapm.g3.g3vote.TestAlgo;

import android.util.Log;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import fr.univtln.m1dapm.g3.g3vote.Algorithme.STV.CAlgoSTV;
import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.Entite.CChoice;
import fr.univtln.m1dapm.g3.g3vote.Entite.CVote;

/**
 * Created by ludo on 18/05/15.
 */
public class CAlgoSTVTest extends TestCase {

    private final String[] mNomsCands = {"Jack", "Paul", "Laurent", "Bernard", "Bob", "Momo", "Georges",
            "Carlos", "Leon", "Theo", "John", "Hector", "Lea", "Sophie", "Bea", "Jeanne", "Toto",
            "Eli", "Will", "Brad", "Chris", "Jacques", "Lou", "Lola", "Phil", "Grant", "Val", "Lee",
            "Bruce", "Clark", "Didier", "Emma", "Joey", "Monique", "Ted"};

    public void testCalculResultat() throws Exception {

        //    Log.i("Vote : ", "Demarrage");
        int lNbCands = 10;
        CVote lVote = new CVote(1, "test");

        List<CCandidate> lCands = genererCandidats(lNbCands);

        //       Log.i("Vote : ", "Creation Vote");
        CAlgoSTV lAlgo = new CAlgoSTV(lVote, 5);

        List<CChoice> lChoices = new ArrayList<>();

        Random lRandom = new Random();
        for(int i=0; i<500; i++)
        {
            List<CCandidate> lList1 = new ArrayList<>(lCands);
            Collections.shuffle(lList1);
        }

        Log.i("Vote : ", "Taille liste choix : " + lChoices.size());


        //       Log.i("Vote : ", "Initialisation Vote");
        lAlgo.initVote(lChoices);

        Log.i("Vote : ", "Demarrage Calcul Resultat");
        List<Integer> lListRes = new LinkedList<>();

        double debut = (double) System.currentTimeMillis();

        lListRes = lAlgo.CalculResultat();

        double fin = (double) System.currentTimeMillis();

        Log.i("Vote : ", "Duree du calcul : " + (fin-debut) + "ms");

        for(Integer candId : lListRes)
            Log.i("Vote : ", "Vainqueurs : " + lCands.get(candId).getNomCandidat());
    }

    private List<CCandidate> genererCandidats(int pNbCand)
    {
        List<CCandidate> lCands = new ArrayList<>();
        for(int i=0; i<pNbCand; i++)
            lCands.add(new CCandidate(i, mNomsCands[i]));

        return lCands;
    }

    public void setUp() throws Exception {
        super.setUp();

    }

}