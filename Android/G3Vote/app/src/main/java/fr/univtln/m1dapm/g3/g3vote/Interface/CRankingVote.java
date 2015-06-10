package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Communication.CCommunication;
import fr.univtln.m1dapm.g3.g3vote.Communication.CRequestTypesEnum;
import fr.univtln.m1dapm.g3.g3vote.Communication.CTaskParam;
import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.Entite.CChoice;
import fr.univtln.m1dapm.g3.g3vote.Entite.CVote;
import fr.univtln.m1dapm.g3.g3vote.R;

/**
 * Created by sebastien on 02/06/15.
 */
public class CRankingVote extends AppCompatActivity {
    private CVote mVote;
    private CStableArrayAdapter mAdapter;
    private CDynamicListView mListView;
    private ArrayList<CCandidate> mListCandidats;
    private static Context sContext;

    public static Context getsContext() {
        return sContext;
    }

    @Override
    protected void onCreate(Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        sContext=getApplicationContext();
        setContentView(R.layout.activity_crankvote);

        Bundle lExtras = getIntent().getExtras();
        if (lExtras==null){
            return;
        }
        mVote = (CVote) lExtras.get("VOTE");

        mListCandidats = (ArrayList) mVote.getCandidates();
        /*mListCandidats.get(1).setId(1);
        ArrayList<CCandidate> lListCandidats2 = (ArrayList) CCandidate.getAListOfCandidate();

        Log.i("ListCandidatServeur : ", mListCandidats.toString());
        Log.i("ListCandidatAppli : ", lListCandidats2.toString());*/



        mAdapter = new CStableArrayAdapter(this, mListCandidats);
        mListView = (CDynamicListView) findViewById(R.id.dynamicListView);

        mListView.setCandidateList(mListCandidats);
        mListView.setAdapter(mAdapter);
        //mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.crankvote, menu);
        return true;
    }

    public void sendRankingVote(View pView){
        // On crée le dialogue
        AlertDialog.Builder lConfirmationDialog = new AlertDialog.Builder(CRankingVote.this);
        // On modifie le titre
        lConfirmationDialog.setTitle("Confirmation de vote");
        // On modifie le message
        lConfirmationDialog.setMessage("Êtes-vous sûr de votre classement ?");
        // Bouton Oui
        lConfirmationDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                List<CChoice> lChoiceList = new ArrayList<>();
                for(int i = 0; i < mListView.getCount(); ++i){
                    CCandidate lCandidate = (CCandidate) mListView.getItemAtPosition(i);
                    CChoice lChoice = new CChoice(mVote.getIdVote(), CHubActivity.getsLoggedUser().getUserId(), lCandidate.getIdCandidat(), i+1);
                    lChoiceList.add(lChoice);
                }
                Log.i("Choix : ", lChoiceList.toString());
                //TODO:Envoyer le vote au serveur et afficher un toast pour confirmer le vote
                CTaskParam lParams=new CTaskParam(CRequestTypesEnum.add_choices,lChoiceList,"rank");
                CCommunication lCom=new CCommunication();
                lCom.execute(lParams);
                // On termine l'activité et on retourne sur la page principale
                /*Intent lIntent = new Intent(CRankingVote.this, CHubActivity.class);
                lIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(lIntent);*/
            }
        });

        // Bouton non: on ferme le dialogue
        lConfirmationDialog.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        // On affiche le message
        lConfirmationDialog.show();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent lIntent = new Intent(this,CSettingAccount.class);
            startActivity(lIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void rankVoteHelp(MenuItem item) {
        AlertDialog.Builder lRankVoteHelpAD = new AlertDialog.Builder(CRankingVote.this);
        lRankVoteHelpAD.setTitle(getString(R.string.help_title));
        lRankVoteHelpAD.setMessage(getString(R.string.rankvote_help_message));
        lRankVoteHelpAD.setPositiveButton("Ok",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        lRankVoteHelpAD.show();
    }
}

