package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Communication.CCommunication;
import fr.univtln.m1dapm.g3.g3vote.Communication.CRequestTypesEnum;
import fr.univtln.m1dapm.g3.g3vote.Communication.CTaskParam;
import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.Entite.CChoice;
import fr.univtln.m1dapm.g3.g3vote.Entite.CVoixCandidat;
import fr.univtln.m1dapm.g3.g3vote.Entite.CVote;
import fr.univtln.m1dapm.g3.g3vote.R;

public class CNoteVote extends AppCompatActivity {

    private CVote mVote;
    private CCandidatAffichageNoteAdapter mAdapter;
    private List<CVoixCandidat> mNoteCandidat;
    private static Context sContext;

    public static Context getsContext() {
        return sContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cnote_vote);
        sContext = getApplicationContext();
        // prepare la nouvelle forme de boite de dialogue


        Bundle lExtras = getIntent().getExtras();
        if (lExtras == null) {
            return;
        }
        mVote = (CVote) lExtras.get("VOTE");

        List<CCandidate> lListCandidats = (ArrayList) mVote.getCandidates();
        mNoteCandidat = new ArrayList<>();
        for (int i = 0; i < lListCandidats.size(); i++) {
            CVoixCandidat lTempCandidateVoix = new CVoixCandidat(lListCandidats.get(i),0);
            mNoteCandidat.add(lTempCandidateVoix);
        }
        ListView lListViewCandidate = (ListView) this.findViewById(R.id.lLVCandidateListVote);
        mAdapter = new CCandidatAffichageNoteAdapter(this, R.id.lLVCandidateListVote, mNoteCandidat);
        lListViewCandidate.setAdapter((ListAdapter) mAdapter);
        mAdapter.addAll(lListCandidats);

        }

    public void valideNoteVote (View view){
        // On crée le dialogue
        AlertDialog.Builder lConfirmationDialog = new AlertDialog.Builder(CNoteVote.this);
        // On modifie le titre
        lConfirmationDialog.setTitle("Confirmation de vote");
        // On modifie le message
        lConfirmationDialog.setMessage("Êtes-vous sûr de vos choix ?");
        // Bouton Oui
        lConfirmationDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                List<CChoice> lChoiceList;
                lChoiceList = new ArrayList<>();

                for (int i = 0; i < mNoteCandidat.size(); i++) {
                    CChoice lTmpChoice = new CChoice();
                    lTmpChoice.setIdVote(mVote.getIdVote());
                    lTmpChoice.setIdUser(CHubActivity.getsLoggedUser().getUserId());
                    lTmpChoice.setIdCandidate(mNoteCandidat.get(i).getMcandidat().getIdCandidat());
                    lTmpChoice.setScore(mNoteCandidat.get(i).getMvote());
                    lChoiceList.add(lTmpChoice);
                }
                Log.i("test","Liste des candidats et leurs points " + mNoteCandidat);
                CTaskParam lParams = new CTaskParam(CRequestTypesEnum.add_choices, lChoiceList, "note");
                CCommunication lCom = new CCommunication();
                lCom.execute(lParams);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cnote_vote, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        // On crée le dialogue
        AlertDialog.Builder lConfirmationDialog = new AlertDialog.Builder(CNoteVote.this);
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

    public void noteVoteHelp(MenuItem item) {
        AlertDialog.Builder lNoteVoteHelpAD = new AlertDialog.Builder(CNoteVote.this);
        lNoteVoteHelpAD.setTitle(getString(R.string.help_title));
        lNoteVoteHelpAD.setMessage(getString(R.string.notevote_help_message));
        lNoteVoteHelpAD.setPositiveButton("Ok",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        lNoteVoteHelpAD.show();
    }
}
