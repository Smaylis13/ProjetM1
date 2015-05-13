package fr.univtln.m1dapm.g3.g3vote.TestAlgo;

import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Algorithme.STV.CAlgoSTV;
import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidat;
import fr.univtln.m1dapm.g3.g3vote.Entite.CResultat;
import fr.univtln.m1dapm.g3.g3vote.Entite.CVote;

/**
 * Created by ludo on 07/05/15.
 */
public class TestAlgoSTV {

    public static void main(String[] args) {

        CVote lVote = new CVote(15, "test");

        CCandidat lCand1 = new CCandidat(1,"Jack");
        lVote.addCandidat(lCand1);
        CCandidat lCand2 = new CCandidat(2,"Paul");
        lVote.addCandidat(lCand2);
        CCandidat lCand3 = new CCandidat(3,"Laurent");
        lVote.addCandidat(lCand3);
        CCandidat lCand4 = new CCandidat(4,"Bernard");
        lVote.addCandidat(lCand4);
        CCandidat lCand5 = new CCandidat(5,"Bob");
        lVote.addCandidat(lCand5);

        CAlgoSTV lAlgo = new CAlgoSTV(lVote, 2);

        CResultat<List<CCandidat>> lListRes = new CResultat<>();

        lListRes.copieValeur(lAlgo.CalculResultat());



    }
}