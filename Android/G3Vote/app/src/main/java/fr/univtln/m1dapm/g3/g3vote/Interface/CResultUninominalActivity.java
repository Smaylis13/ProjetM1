package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

import fr.univtln.m1dapm.g3.g3vote.Entite.CResult;
import fr.univtln.m1dapm.g3.g3vote.Entite.CVote;
import fr.univtln.m1dapm.g3.g3vote.R;

/**
 * Created by sebastien on 03/06/15.
 */
public class CResultUninominalActivity extends AppCompatActivity {
    private CVote mVote;
    private ArrayList<CResult> lListResultCandidate;

    protected void onCreate(Bundle pSavedInstanceState){
        super.onCreate(pSavedInstanceState);
        setContentView(R.layout.activity_cresult_uninominal);

        Bundle extras = getIntent().getExtras();
        if (extras==null){
            return;
        }
        mVote = (CVote) extras.get("VOTE");
        //lListResultCandidate = (ArrayList) mVote.getmResultVote();

        BarChart lBarChart = (BarChart) findViewById(R.id.barChartResultUninominal);

        BarData lBarData = new BarData(getCandidateList(), getResultData());

        lBarChart.setData(lBarData);
        lBarChart.setDescription("Résultat vote");
    }

    public ArrayList<String> getCandidateList(){
        ArrayList<String> lListCandidat = new ArrayList<>();
        for(CResult iter : lListResultCandidate){
            //lListCandidat.add(iter.getCandidat().getNomCandidat());
        }
        return lListCandidat;
    }

    public ArrayList<BarDataSet> getResultData(){

        ArrayList<BarEntry> lValueSet = new ArrayList<>();
        int i = 0;
        for(CResult iter : lListResultCandidate){
            lValueSet.add(new BarEntry(iter.getResultat(), i));
        }

        ArrayList<BarDataSet> lDataSets = null;

        BarDataSet lBarDataSet1 = new BarDataSet(lValueSet, "Brand 1");
        lBarDataSet1.setColor(Color.rgb(0, 155, 0));

        lDataSets = new ArrayList<>();
        lDataSets.add(lBarDataSet1);
        return lDataSets;
    }
}
