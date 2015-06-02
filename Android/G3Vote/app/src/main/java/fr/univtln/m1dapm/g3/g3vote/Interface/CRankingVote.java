package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.Entite.CVote;
import fr.univtln.m1dapm.g3.g3vote.R;

/**
 * Created by sebastien on 02/06/15.
 */
public class CRankingVote extends AppCompatActivity {
    private CVote mVote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crankvote);

        Bundle extras = getIntent().getExtras();
        if (extras==null){
            return;
        }
        mVote = (CVote) extras.get("VOTE");

        ArrayList<CCandidate> lListCandidats = (ArrayList) mVote.getCandidates();
        //ArrayList<CCandidate> lListCandidats = (ArrayList) CCandidate.getAListOfCandidate();

        CStableArrayAdapter adapter = new CStableArrayAdapter(this, lListCandidats);
        CDynamicListView listView = (CDynamicListView) findViewById(R.id.dynamicListView);

        listView.setCandidateList(lListCandidats);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }
}

