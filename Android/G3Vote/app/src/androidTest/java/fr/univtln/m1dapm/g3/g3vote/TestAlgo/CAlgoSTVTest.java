package fr.univtln.m1dapm.g3.g3vote.TestAlgo;

import android.util.Log;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import fr.univtln.m1dapm.g3.g3vote.Algorithme.STV.CAlgoSTV;
import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.Entite.CListChoix;
import fr.univtln.m1dapm.g3.g3vote.Entite.CResultat;
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

        Map<CListChoix, Double> lChoix = new HashMap<>();
        Random lRandom = new Random();
        for(int i=0; i<500; i++)
        {
            List<CCandidate> lList1 = new ArrayList<>(lCands);
            Collections.shuffle(lList1);
            CListChoix lChoix1 = new CListChoix(lList1);
            if(lChoix.containsKey(lChoix1))
                lChoix.put(lChoix1, (double)(lRandom.nextInt(19)+1) + lChoix.get(lChoix1));
            else
                lChoix.put(lChoix1, (double)(lRandom.nextInt(19)+1));
        }

        Log.i("Vote : ", "Taille liste choix : " + lChoix.size());


 //       Log.i("Vote : ", "Initialisation Vote");
        lAlgo.initVote(lChoix);

        Log.i("Vote : ", "Demarrage Calcul Resultat");
        CResultat<List<CCandidate>> lListRes = new CResultat<>();

        double debut = (double) System.currentTimeMillis();

        lListRes.copieValeur(lAlgo.CalculResultat());

        double fin = (double) System.currentTimeMillis();

        Log.i("Vote : ", "Duree du calcul : " + (fin-debut) + "ms");

        for(CCandidate cand : lListRes.getValeur())
            Log.i("Vote : ", "Vainqueurs : " + cand.getNom());
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