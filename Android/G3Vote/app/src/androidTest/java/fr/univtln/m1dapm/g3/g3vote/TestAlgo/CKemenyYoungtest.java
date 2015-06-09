package fr.univtln.m1dapm.g3.g3vote.TestAlgo;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Algorithme.KemenyYoung.CKemenyYoung;
import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.Entite.CChoice;
import fr.univtln.m1dapm.g3.g3vote.Entite.CUser;
import fr.univtln.m1dapm.g3.g3vote.Entite.CVote;

/**
 * Created by Pierre on 19/05/2015.
 */
public class CKemenyYoungtest extends TestCase{

    public static void main(String[] args) {

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
        lcand.add(f);

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

        for (int i = 0; i < 40; i++) {
            Collections.shuffle(list);
            lchoices.addAll(generateChoice(list, lvote));
        }
    }

    private static List<CChoice> generateChoice (List<Integer> pCandList, CVote pVote)
    {
        List<CChoice> lchoices = new ArrayList<>();

        for (int i = 0; i < pCandList.size(); i++) {
            lchoices.add(new CChoice(pVote.getIdVote(), new CUser().getUserId(),pCandList.get(i), i+1));
        }

        return lchoices;
    }
}
