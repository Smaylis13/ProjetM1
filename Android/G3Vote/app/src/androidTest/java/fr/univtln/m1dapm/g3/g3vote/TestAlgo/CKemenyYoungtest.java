package fr.univtln.m1dapm.g3.g3vote.TestAlgo;

import android.util.Log;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Algorithme.KemenyYoung.CKemenyYoung;
import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.Entite.CChoice;
import fr.univtln.m1dapm.g3.g3vote.Entite.CResult;
import fr.univtln.m1dapm.g3.g3vote.Entite.CUser;
import fr.univtln.m1dapm.g3.g3vote.Entite.CVote;

/**
 * Created by Pierre on 19/05/2015.
 */
public class CKemenyYoungtest extends TestCase{

    private final String[] mNomUser = {"Jack", "Paul", "Laurent", "Bernard", "Bob", "Momo", "Georges",
            "Carlos", "Leon", "Theo", "John", "Hector", "Lea", "Sophie", "Bea", "Jeanne", "Toto",
            "Eli", "Will", "Brad", "Chris", "Jacques", "Lou", "Lola", "Phil", "Grant", "Val", "Lee",
            "Bruce", "Clark", "Didier", "Emma", "Joey", "Monique", "Ted"};


    public void testCalculResultat() throws Exception {

        CCandidate a=new CCandidate(84,"Memphis");
        CCandidate b=new CCandidate(51,"Nashville");
        CCandidate c=new CCandidate(894,"Chattanooga");
        CCandidate d=new CCandidate(21,"knoxville");
        CCandidate e=new CCandidate(19,"toulon");
        CCandidate f=new CCandidate(659,"la garde");
        CCandidate g=new CCandidate(9156,"cuers");

        List<CCandidate> lcand = new ArrayList<>();

        lcand.add(a);
        lcand.add(b);
        lcand.add(c);
        lcand.add(d);
        lcand.add(e);
        lcand.add(f);
        lcand.add(g);

        CVote lvote = new CVote(6846, "zefze");

        lvote.setCandidates(lcand);

        CKemenyYoung lkemenyYoung=new CKemenyYoung(lvote);

        List<Integer> list = new ArrayList();
        list.add(a.getIdCandidat());
        list.add(b.getIdCandidat());
        list.add(c.getIdCandidat());
        list.add(d.getIdCandidat());
        list.add(e.getIdCandidat());
        list.add(f.getIdCandidat());
        list.add(g.getIdCandidat());

        List<CChoice> lchoices = new ArrayList<>();

        for(int i=0; i<35; i++)
        {
            Collections.shuffle(list);
            for (int j = 0; j <list.size() ; j++)
            {
                CUser luser = new CUser(mNomUser[i], mNomUser[i], "", "");
                luser.setUserId(i);
                CChoice choice = new CChoice(lvote.getIdVote(), luser.getUserId(), list.get(j), j+1);
                lchoices.add(choice);
            }
        }

        lkemenyYoung.initVote(lchoices);

        List<CResult> lResult;

        lResult = lkemenyYoung.CalculResultat();

        List<List<Integer>> lChoiceRes = new ArrayList<>();

        for (int i = 0; i < lResult.size(); i++) {
            if (i%lcand.size() == 0)
                lChoiceRes.add(new ArrayList<Integer>(Collections.nCopies(lcand.size(),0)));

            lChoiceRes.get(i/lcand.size()).set(lResult.get(i).getOrder()-1, lResult.get(i).getCandidat());
        }

        for (List<Integer> choice : lChoiceRes)
            Log.i("Vote : ", choice.toString());
    }

}
