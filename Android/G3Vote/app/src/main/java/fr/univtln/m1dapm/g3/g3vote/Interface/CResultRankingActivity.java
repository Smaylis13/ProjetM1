package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.Entite.CResult;
import fr.univtln.m1dapm.g3.g3vote.Entite.CVote;
import fr.univtln.m1dapm.g3.g3vote.R;

/**
 * Created by sebastien on 03/06/15.
 */
public class CResultRankingActivity extends AppCompatActivity {
    private CCandidatAdapter mAdapter;
    private List<CCandidate> mCandidateList;
    private List<CResult> mResultList;

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
        mResultList = new ArrayList<>(mVote.getResultVote());

        Collections.sort(mResultList, new Comparator<CResult>() {
            @Override
            public int compare(CResult lhs, CResult rhs) {
                return lhs.compareTo(rhs);
            }
        });
        mCandidateList = new ArrayList<>(mVote.getCandidates());
        List<CCandidate> lWinningCandidatesList = new ArrayList<>();
        int i = 0;
        for(CResult res : mResultList) {
            int lIdCandidat = res.getCandidat();
            if(mCandidateList.get(i).getIdCandidat() == lIdCandidat){
                lWinningCandidatesList.add(mCandidateList.get(i));
            }
            ++i;
        }

        ListView lListViewResult = (ListView) findViewById(R.id.listViewRankingResult);

        mAdapter = new CCandidatAdapter(this, lWinningCandidatesList);
        lListViewResult.setAdapter(mAdapter);
    }
}
