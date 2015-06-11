package fr.univtln.m1dapm.g3.g3vote.TestAlgo;

import android.util.Log;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Algorithme.STV.CAlgoSTV;
import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.Entite.CChoice;
import fr.univtln.m1dapm.g3.g3vote.Entite.CUser;
import fr.univtln.m1dapm.g3.g3vote.Entite.CVote;

/**
 * Created by ludo on 18/05/15.
 */
public class CAlgoSTVTest extends TestCase {

    /**
     * Tableau de nom des candidats
     */
    private final String[] mNomsCands = {"Jack", "Paul", "Laurent", "Bernard", "Bob", "Momo", "Georges",
            "Carlos", "Leon", "Theo", "John", "Hector", "Lea", "Sophie", "Bea", "Jeanne", "Toto",
            "Eli", "Will", "Brad", "Chris", "Jacques", "Lou", "Lola", "Phil", "Grant", "Val", "Lee",
            "Bruce", "Clark", "Didier", "Emma", "Joey", "Monique", "Ted"};

    /**
     * Test de calcul du resultat de l'algorithme STV
     * @throws Exception
     */
    public void testCalculResultat() throws Exception {

        //    Log.i("Vote : ", "Demarrage");
        int lNbCands = 10;
        CVote lVote = new CVote(1, "test");

        List<CCandidate> lCands = genererCandidats(lNbCands);

        lVote.setCandidates(lCands);

        List<Integer> lCandsId = new ArrayList<>();

        for(CCandidate cand : lCands)
                lCandsId.add(cand.getIdCandidat());

        //       Log.i("Vote : ", "Creation Vote");
        CAlgoSTV lAlgo = new CAlgoSTV(lVote, 4);

        List<CChoice> lChoices = new ArrayList<>();

        for(int i=0; i<27; i++)
        {
            List<Integer> lList1 = new ArrayList<>(lCandsId);
            Collections.shuffle(lList1);
            for (int j = 0; j <lList1.size() ; j++)
            {
                CUser luser = new CUser(mNomsCands[i], mNomsCands[i], "", "");
                luser.setUserId(i);
                CChoice choice = new CChoice(lVote.getIdVote(), luser.getUserId(), lList1.get(j), j+1);
                lChoices.add(choice);
            }
        }

        /// Log.i("Vote : ", "Initialisation Vote");
        lAlgo.initVote(lChoices);

         Log.i("Vote : ", "Demarrage Calcul Resultat");
        List<Integer> lListRes;

        double debut = (double) System.currentTimeMillis();

        lListRes = lAlgo.CalculResultat();

        double fin = (double) System.currentTimeMillis();

        Log.i("Vote : ", "Duree du calcul : " + (fin-debut) + "ms");

        for(Integer candId : lListRes)
            Log.i("Vote : ", "Vainqueurs : ID : " + candId + " Nom : " + lCands.get(candId).getNomCandidat());

    }

    /**
     * Generation d'une liste de candidat
     * @param pNbCand   Nombre de candidat a generer
     * @return  Liste de candidat
     */
    private List<CCandidate> genererCandidats(int pNbCand)
    {
        List<CCandidate> lCands = new ArrayList<>();
        for(int i=0; i<pNbCand; i++)
            lCands.add(new CCandidate(i, mNomsCands[i]));

        return lCands;
    }
}