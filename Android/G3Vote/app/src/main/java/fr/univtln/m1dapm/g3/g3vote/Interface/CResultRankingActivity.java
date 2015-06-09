package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

/**
 * Created by sebastien on 03/06/15.
 */
public class CResultRankingActivity extends AppCompatActivity {
    private CResultMultipleCandidatAdapter mAdapter;
    private List<CCandidate> mCandidateList;
    private List<CResult> mResultList;
    private static List<CChoice> mChoices;
    private List<CResult> mResults;

    private CVote mVote;

    @Override
    protected void onCreate(Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        setContentView(R.layout.activity_cresult_ranking);

        Bundle extras = getIntent().getExtras();
        if (extras==null){
            return;
        }
        mVote = (CVote) extras.get("VOTE");
        mResults=mVote.getResultVote();
        if(mResults==null||mResults.isEmpty()){
            calculateResults();
        }
        Log.i("Mon vote : ", mVote.toString());
        List<CResult> lListResultatFaux = new ArrayList<>();
        lListResultatFaux.add(new CResult(0, 2, mVote.getIdVote(), 4));
        lListResultatFaux.add(new CResult(1, 0, mVote.getIdVote(), 3));
        lListResultatFaux.add(new CResult(2, 1, mVote.getIdVote(), 5));
        mVote.setResultVote(lListResultatFaux);
        mResultList =mResults;
        Log.i("Avant sort : ", "" + mResultList.get(0).getCandidat());
/*        Log.i("Avant sort : ", "" + mResultList.get(1).getCandidat());
        Log.i("Avant sort : ", "" + mResultList.get(2).getCandidat());

        Log.i("Apres sort : ", "" + mResultList.get(0).getCandidat());
        Log.i("Apres sort : ", "" + mResultList.get(1).getCandidat());
        Log.i("Apres sort : ", "" + mResultList.get(2).getCandidat());*/
        Collections.sort(mResultList, new Comparator<CResult>() {
            @Override
            public int compare(CResult lhs, CResult rhs) {
                return lhs.getOrder() - rhs.getOrder();
            }
        });
        mCandidateList = new ArrayList<>(mVote.getCandidates());
        List<CCandidate> lWinningCandidatesList = new ArrayList<>();
        for(CResult res : mResultList) {
            int lIdCandidat = res.getCandidat();
            for (int i = 0; i < mCandidateList.size(); ++i){
                if (mCandidateList.get(i).getIdCandidat() == lIdCandidat) {
                    lWinningCandidatesList.add(mCandidateList.get(i));
                }
            }
        }

        ListView lListViewResult = (ListView) findViewById(R.id.listViewRankingResult);
        Log.i("Mon vote : ", lWinningCandidatesList.toString());
        mAdapter = new CResultMultipleCandidatAdapter(this, lWinningCandidatesList);
        lListViewResult.setAdapter(mAdapter);

    }

    private void calculateResults() {
        mResults=new ArrayList<>();
        CRule lRuleNbElus = null;
        if(mVote.getTypes().getNom().equals("STV")){
            for(CRule lRule:mVote.getRegles()){
                if(lRule.getRuleName().equals("NB_GAGNANT"))
                    lRuleNbElus=lRule;
            }
            CAlgoSTV lAlgoSTV=new CAlgoSTV(mVote,Integer.parseInt(lRuleNbElus.getDescription()));
            lAlgoSTV.initVote(mChoices);
            List<Integer> lElusId=lAlgoSTV.CalculResultat();
            for(int lId:lElusId){
                mResults.add(new CResult(1,mVote.getIdVote(),lId));
            }

        }
        CTaskParam lParams=new CTaskParam(CRequestTypesEnum.add_results,mResults);
        CCommunication lCom=new CCommunication();
        lCom.execute(lParams);
    }

    public static void setChoices(List<CChoice> pChoices) {
        mChoices=pChoices;
    }
}
