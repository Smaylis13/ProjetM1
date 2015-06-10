package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Algorithme.Borda.CAlgoBorda;
import fr.univtln.m1dapm.g3.g3vote.Algorithme.UninominalMajoritaire.CVoteUninominalMajoritaire;
import fr.univtln.m1dapm.g3.g3vote.Communication.CCommunication;
import fr.univtln.m1dapm.g3.g3vote.Communication.CRequestTypesEnum;
import fr.univtln.m1dapm.g3.g3vote.Communication.CTaskParam;
import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.Entite.CChoice;
import fr.univtln.m1dapm.g3.g3vote.Entite.CResult;
import fr.univtln.m1dapm.g3.g3vote.Entite.CVote;
import fr.univtln.m1dapm.g3.g3vote.R;

public class CResultUninominalActivity extends AppCompatActivity {
    private CVote mVote;
    private static List<CChoice> mChoices;
    private ArrayList<CResult> lListResultCandidate;
    private List<CResult> mResults;

    public static void setChoices(List<CChoice> pChoices) {
        mChoices = pChoices;
    }

    protected void onCreate(Bundle pSavedInstanceState){
        super.onCreate(pSavedInstanceState);
        setContentView(R.layout.activity_cresult_uninominal);

        Bundle lExtras = getIntent().getExtras();
        if (lExtras==null){
            return;
        }
        mVote = (CVote) lExtras.get("VOTE");
       // mChoices = extras.getParcelableArrayList("CHOICES");
        mResults = mVote.getResultVote();
        if(mResults == null || mResults.isEmpty()){
            calculateResults();
        }
        lListResultCandidate = (ArrayList<CResult>)mResults;

        BarChart lBarChart = (BarChart) findViewById(R.id.barChartResultUninominal);

        BarData lBarData = new BarData(getCandidateList(), getResultData());

        lBarChart.setData(lBarData);
        lBarChart.setDescription("Résultat vote");
        lBarChart.animateY(2000);
    }

    public void calculateResults(){

        if(mVote.getTypes().getNom().equals("Uninominal à 1 tour")) {
            CVoteUninominalMajoritaire lVoteUM = new CVoteUninominalMajoritaire(mVote);
            lVoteUM.initVote(mChoices, 1, 1);
            mResults.addAll(lVoteUM.resultat());
        }
        else if(mVote.getTypes().getNom().equals("Borda")) {
            CAlgoBorda lBorda=new CAlgoBorda(mVote);
            lBorda.initVote(mChoices);
            mResults.add(lBorda.CalculResult());
        }
        CTaskParam lParams=new CTaskParam(CRequestTypesEnum.add_results,mResults);
        CCommunication lCom=new CCommunication();
        lCom.execute(lParams);
    }
    public ArrayList<String> getCandidateList(){
        ArrayList<String> lListCandidat = new ArrayList<>();
        ArrayList<CCandidate> lListCandidatComplet = (ArrayList<CCandidate>) mVote.getCandidates();
        Log.i("ID : ", "" + lListCandidatComplet.get(0).getIdCandidat());
        Log.i("Nom : ", "" + lListCandidatComplet.get(0).getNomCandidat());


        for(CResult iter : lListResultCandidate){
            int lIdCandidat = iter.getCandidat();
            for(int i = 0; i < lListCandidatComplet.size(); ++i){
                if(lListCandidatComplet.get(i).getIdCandidat() == lIdCandidat){
                    lListCandidat.add(lListCandidatComplet.get(i).getNomCandidat());
                }
            }
        }
        Log.i("Taille lListCandidat : ", "" + lListCandidat.size());
        return lListCandidat;
    }

    public BarDataSet getResultData(){

        ArrayList<BarEntry> lValueSet = new ArrayList<>();
        int i = 0;
        for(CResult iter : lListResultCandidate){
            lValueSet.add(new BarEntry(iter.getOrder(), i));
            ++i;
        }
        Log.i("Value set : ", "" + lValueSet.toString());

        BarDataSet lBarDataSet = new BarDataSet(lValueSet, "Nombre de votes");
        lBarDataSet.setColor(Color.rgb(0, 155, 0));

        return lBarDataSet;
    }
}
