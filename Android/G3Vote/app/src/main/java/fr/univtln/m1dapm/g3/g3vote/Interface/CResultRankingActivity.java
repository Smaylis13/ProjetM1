package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Algorithme.KemenyYoung.CKemenyYoung;
import fr.univtln.m1dapm.g3.g3vote.Algorithme.STV.CAlgoSTV;
import fr.univtln.m1dapm.g3.g3vote.Communication.CCommunication;
import fr.univtln.m1dapm.g3.g3vote.Communication.CRequestTypesEnum;
import fr.univtln.m1dapm.g3.g3vote.Communication.CTaskParam;
import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.Entite.CChoice;
import fr.univtln.m1dapm.g3.g3vote.Entite.CResult;
import fr.univtln.m1dapm.g3.g3vote.Entite.CRule;
import fr.univtln.m1dapm.g3.g3vote.Entite.CVote;
import fr.univtln.m1dapm.g3.g3vote.R;

public class CResultRankingActivity extends AppCompatActivity {
    private static List<CChoice> mChoices;
    private List<CResult> mResults;
    private int mGood = 0;

    private CVote mVote;

    @Override
    protected void onCreate(Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        setContentView(R.layout.activity_cresult_ranking);

        Bundle lExtras = getIntent().getExtras();
        if (lExtras == null){
            return;
        }
        mVote = (CVote) lExtras.get("VOTE");
        mResults = mVote.getResultVote();
        if(mResults == null || mResults.isEmpty()){
            calculateResults();
        }
        Log.i("Mon vote : ", mVote.toString());
        List<CResult> lResultList = mResults;
        if(mGood == 0) {
            Collections.sort(lResultList, new Comparator<CResult>() {
                @Override
                public int compare(CResult lhs, CResult rhs) {
                    return lhs.getOrder() - rhs.getOrder();
                }
            });
            List<CCandidate> lCandidateList = new ArrayList<>(mVote.getCandidates());
            List<CCandidate> lWinningCandidatesList = new ArrayList<>();
            for (CResult res : lResultList) {
                int lIdCandidat = res.getCandidat();
                for (int i = 0; i < lCandidateList.size(); ++i) {
                    if (lCandidateList.get(i).getIdCandidat() == lIdCandidat) {
                        lWinningCandidatesList.add(lCandidateList.get(i));
                    }
                }
            }

            ListView lListViewResult = (ListView) findViewById(R.id.listViewRankingResult);
            Log.i("Mon vote : ", lWinningCandidatesList.toString());
            CResultMultipleCandidatAdapter lAdapter = new CResultMultipleCandidatAdapter(this, lWinningCandidatesList);
            lListViewResult.setAdapter(lAdapter);
        }
    }

    private void calculateResults() {
        mResults=new ArrayList<>();
        CRule lRuleNbElus = null;
        if(mVote.getTypes().getNom().equals("STV")){
            for(CRule lRule:mVote.getRegles()){
                if(lRule.getRuleName().equals("NB_GAGNANT"))
                    lRuleNbElus=lRule;
            }
            CAlgoSTV lAlgoSTV=new CAlgoSTV(mVote,Integer.parseInt(lRuleNbElus != null ? lRuleNbElus.getDescription() : null));
            mGood=lAlgoSTV.initVote(mChoices);
            if(mGood==0) {
                List<Integer> lElusId = lAlgoSTV.CalculResultat();
                for (int lId : lElusId) {
                    mResults.add(new CResult(1, mVote.getIdVote(), lId));
                }
            }

        }
        else if(mVote.getTypes().getNom().equals("Kemeny-Young")){
            CKemenyYoung lKemenyYoung=new CKemenyYoung(mVote);
            lKemenyYoung.initVote(mChoices);
            mResults=lKemenyYoung.CalculResultat();

        }
        if(mGood==0) {
            CTaskParam lParams = new CTaskParam(CRequestTypesEnum.add_results, mResults);
            CCommunication lCom = new CCommunication();
            lCom.execute(lParams);
        }
    }

    public static void setChoices(List<CChoice> pChoices) {
        mChoices=pChoices;
    }
}
