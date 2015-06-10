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
import android.widget.ListView;

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
 * Created by sebastien on 29/05/15.
 */
public class CVoteUninominal extends AppCompatActivity {

    private CVote mVote;
    private static CCandidateUniqueChoiceAdapter sAdapter;
    private ListView mList;
    private static List<CCandidate> sListCandidat;
    private static Context sContext;

    public static Context getsContext() {
        return sContext;
    }

    public static void setlList(List<CCandidate> pList) {
        sListCandidat = new ArrayList<>(pList);

        sAdapter.notifyDataSetChanged();
    }

    public CVoteUninominal(){}

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        sContext=getApplicationContext();
        setContentView(R.layout.activity_cvote_uninominal);

        Bundle extras = getIntent().getExtras();
        if (extras==null){
            return;
        }
        mVote = (CVote) extras.get("VOTE");
        /*

        CTaskParam lParams = new CTaskParam(CRequestTypesEnum.get_candidates, mIdVote, mTypeVote);
        CCommunication lCom = new CCommunication();
        lCom.execute(lParams);
*/


        //Récupération du composant ListView
        mList = (ListView)findViewById(R.id.LVChoixCandidatUninominal);

        sListCandidat = mVote.getCandidates();
        //Récupération de la liste des personnes
        //ArrayList<CCandidate> lListCandidate = CCandidate.getAListOfCandidate();

        //Création et initialisation de l'Adapter pour les personnes
        sAdapter = new CCandidateUniqueChoiceAdapter(this, sListCandidat);

        //Initialisation de la liste avec les données
        mList.setAdapter(sAdapter);

    }

    public void vote(View pView){
        int position = mList.getCheckedItemPosition();
        if(position != -1){
            // On crée le dialogue
            AlertDialog.Builder lConfirmationDialog = new AlertDialog.Builder(CVoteUninominal.this);
            // On modifie le titre
            lConfirmationDialog.setTitle("Confirmation de vote");
            // On modifie le message
            lConfirmationDialog.setMessage("Êtes-vous sûr de vouloir voter pour ce candidat ?");
            // Bouton Oui
            lConfirmationDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    int position = mList.getCheckedItemPosition();

                    CCandidate lCandidat  = (CCandidate) mList.getItemAtPosition(position);

                    Log.i("Candidat voté : ", lCandidat.toString());

                    CChoice lUniqueChoice = new CChoice(mVote.getIdVote(), CHubActivity.getsLoggedUser().getUserId(), lCandidat.getIdCandidat(), 1);

                    CTaskParam lParams = new CTaskParam(CRequestTypesEnum.add_choice,lUniqueChoice);
                    CCommunication lCom = new CCommunication();
                    lCom.execute(lParams);

                    /*
                    // On termine l'activité et on retourne sur la page principale
                    Intent lIntent = new Intent(CVoteUninominal.this, CHubActivity.class);
                    lIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(lIntent);
                    */
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
        else {
            // On crée le dialogue
            AlertDialog.Builder lConfirmationDialog = new AlertDialog.Builder(CVoteUninominal.this);
            // On modifie le titre
            lConfirmationDialog.setTitle("Aucun candidat selectionné");
            // On modifie le message
            lConfirmationDialog.setMessage("Vous n'avez selectionné aucun candidat");
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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_vote_uninominal, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        // On crée le dialogue
        AlertDialog.Builder lConfirmationDialog = new AlertDialog.Builder(CVoteUninominal.this);
        // On modifie le titre
        lConfirmationDialog.setTitle("Arrêt vote");
        // On modifie le message
        lConfirmationDialog.setMessage("Voulez-vous arrêter de voter ?");
        // Bouton Oui
        lConfirmationDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // On termine l'activité
                finish();
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

    public void uninominalVoteHelp(MenuItem item) {
        AlertDialog.Builder lUninominalVoteHelpAD = new AlertDialog.Builder(CVoteUninominal.this);
        lUninominalVoteHelpAD.setTitle(getString(R.string.help_title));
        lUninominalVoteHelpAD.setMessage(getString(R.string.uninominalvote_help_message));
        lUninominalVoteHelpAD.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        lUninominalVoteHelpAD.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem pItem) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int lId = pItem.getItemId();

        //noinspection SimplifiableIfStatement
        if (lId == R.id.action_settings) {
            Intent lIntent = new Intent(this,CSettingAccount.class);
            startActivity(lIntent);
            return true;
        }
        if (lId == R.id.websiteMenu){
            Intent lIntent = new Intent(this, CWebSiteActivity.class);
            startActivity(lIntent);
            return true;
        }

        return super.onOptionsItemSelected(pItem);
    }
}
