package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import fr.univtln.m1dapm.g3.g3vote.Algorithme.VoteMajoritaire.CAlgoVoteMaj;
import fr.univtln.m1dapm.g3.g3vote.Communication.CCommunication;
import fr.univtln.m1dapm.g3.g3vote.Communication.CRequestTypesEnum;
import fr.univtln.m1dapm.g3.g3vote.Communication.CTaskParam;
import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.Entite.CChoice;
import fr.univtln.m1dapm.g3.g3vote.Entite.CResult;
import fr.univtln.m1dapm.g3.g3vote.Entite.CVote;
import fr.univtln.m1dapm.g3.g3vote.R;

public class CResultJugementMajoritaire extends AppCompatActivity {
    private CVote mVote;
    private static List<CChoice> mChoices;
    private ArrayList<CResult> mListResultCandidate;
    private List<CResult> mResults;
    String mCalculationMethod;

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
        mCalculationMethod = mVote.getRegles().get(0).getDescription();

        if(mResults == null||mResults.isEmpty()){
            calculateResults();
        }

        mListResultCandidate = (ArrayList<CResult>)mResults;

        BarChart lBarChart = (BarChart) findViewById(R.id.barChartResultUninominal);

        BarData lBarData = new BarData(getCandidateList(), getResultData());

        lBarChart.setData(lBarData);
        lBarChart.setDescription("Résultat vote");
        if(mChoices.size() == mVote.getCandidates().size() && mCalculationMethod.equals("1")){
            // On crée le dialogue
            AlertDialog.Builder lConfirmationDialog = new AlertDialog.Builder(CResultJugementMajoritaire.this);
            // On modifie le titre
            lConfirmationDialog.setTitle("Vote unique enregistré");
            // On modifie le message
            lConfirmationDialog.setMessage("Attention, vote unique enregistré, les notes attribuées sont affichées sans calcul.");
            // Bouton OK: on ferme le dialogue
            lConfirmationDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();

                }
            });
            // On affiche le message
            lConfirmationDialog.show();
        }
        lBarChart.animateY(2000);
    }

    public void calculateResults(){

        CAlgoVoteMaj lVoteJM = new CAlgoVoteMaj(mVote);
        lVoteJM.initVote(mChoices);
        switch (mCalculationMethod){
            case "0":
                mResults.addAll(lVoteJM.calculateAverage());
                break;
            case "1":
                mResults.addAll(lVoteJM.calculateMedian());
                break;
            case "2":
                mResults.addAll(lVoteJM.calculateSum());
                break;
        }

        CTaskParam lParams = new CTaskParam(CRequestTypesEnum.add_results, mResults);
        CCommunication lCom = new CCommunication();
        lCom.execute(lParams);
    }
    public ArrayList<String> getCandidateList(){
        ArrayList<String> lListCandidat = new ArrayList<>();
        ArrayList<CCandidate> lListCandidatComplet = (ArrayList<CCandidate>) mVote.getCandidates();
        Log.i("ID : ", "" + lListCandidatComplet.get(0).getIdCandidat());
        Log.i("Nom : ", "" + lListCandidatComplet.get(0).getNomCandidat());


        for(CResult iter : mListResultCandidate){
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
        for(CResult iter : mListResultCandidate){

            lValueSet.add(new BarEntry(iter.getOrder(), i));
            ++i;
        }
        Log.i("Value set : ", "" + lValueSet.toString());
        BarDataSet lBarDataSet = null;

        switch (mCalculationMethod){
            case "0":
                lBarDataSet = new BarDataSet(lValueSet, "Moyenne obtenue");
                break;
            case "1":
                lBarDataSet = new BarDataSet(lValueSet, "Médiane obtenue");
                break;
            case "2":
                lBarDataSet = new BarDataSet(lValueSet, "Somme des notes");
                break;
            default:
                break;
        }

        if (lBarDataSet != null) {
            lBarDataSet.setColor(Color.rgb(0, 155, 0));
        }

        return lBarDataSet;
    }
}
