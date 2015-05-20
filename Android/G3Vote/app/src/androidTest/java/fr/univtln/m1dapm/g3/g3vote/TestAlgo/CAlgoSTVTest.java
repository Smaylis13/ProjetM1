package fr.univtln.m1dapm.g3.g3vote.TestAlgo;

import android.util.Log;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;

import fr.univtln.m1dapm.g3.g3vote.Algorithme.STV.CAlgoSTV;
import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidat;
import fr.univtln.m1dapm.g3.g3vote.Entite.CListChoix;
import fr.univtln.m1dapm.g3.g3vote.Entite.CResultat;
import fr.univtln.m1dapm.g3.g3vote.Entite.CVote;

/**
 * Created by ludo on 18/05/15.
 */
public class CAlgoSTVTest extends TestCase {

    private String[] mNomsCands = {"Jack", "Paul", "Laurent", "Bernard", "Bob", "Momo", "Georges",
            "Carlos", "Leon", "Theo", "John", "Hector", "Lea", "Sophie", "Bea", "Jeanne", "Toto",
            "Eli", "Will", "Brad"};

    public void testCalculResultat() throws Exception {

    //    Log.i("Vote : ", "Demarrage");
        int lNbCands = 20;
        CVote lVote = new CVote(1, "test");

        List<CCandidat> lCands = genererCandidats(lNbCands);


 //       Log.i("Vote : ", "Creation Vote");
        CAlgoSTV lAlgo = new CAlgoSTV(lVote, 10);

        Map<CListChoix, Double> lChoix = new HashMap<>();
        Random lRandom = new Random();
        for(int i=0; i<200; i++)
        {
            List<CCandidat> lList1 = new ArrayList<>(lCands);
            Collections.shuffle(lList1);
            CListChoix lChoix1 = new CListChoix(lList1);
            if(lChoix.containsKey(lChoix1))
                lChoix.put(lChoix1, (double)(lRandom.nextInt(19)+1) + lChoix.get(lChoix1));
            else
                lChoix.put(lChoix1, (double)(lRandom.nextInt(19)+1));
        }


 //       Log.i("Vote : ", "Initialisation Vote");
        lAlgo.initVote(lChoix);

        Log.i("Vote : ", "Demarrage Calcul Resultat");
        CResultat<List<CCandidat>> lListRes = new CResultat<>();

        float debut = System.nanoTime();

        lListRes.copieValeur(lAlgo.CalculResultat());

        float fin = System.nanoTime();

        Log.i("Vote : ", "Duree du calcul : " + (fin-debut)/1000 + "µs");

        for(CCandidat cand : lListRes.getValeur())
            Log.i("Vote : ", "Vainqueurs : " + cand.getNom());
    }

    private List<CCandidat> genererCandidats(int pNbCand)
    {
        List<CCandidat> lCands = new ArrayList<>();
        for(int i=0; i<pNbCand; i++)
            lCands.add(new CCandidat(i, mNomsCands[i]));

        return lCands;
    }

    public void setUp() throws Exception {
        super.setUp();

    }

}