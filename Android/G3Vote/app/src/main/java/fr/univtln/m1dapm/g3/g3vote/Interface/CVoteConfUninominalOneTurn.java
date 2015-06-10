package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.Entite.CType;
import fr.univtln.m1dapm.g3.g3vote.R;

/**
 * Created by chris on 20/05/15.
 */
public class CVoteConfUninominalOneTurn extends AppCompatActivity {

    private ArrayList<CCandidate> mListCandidat = new ArrayList<>();

    private CCandidatAdapter mAdapter;

    private String mVoteName;
    private String mDateDebut;
    private String mDateFin;
    private static final String TYPE_VOTE = "Uninominal à 1 tour";
    private CType mTypeVote ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTypeVote = new CType(6,"Uninominal à 1 tour",getResources().getString(R.string.uninomialOneTurnVoteDescription));
        setContentView(R.layout.activity_cvote_conf_uninominal_one_turn);
        Bundle lExtras = getIntent().getExtras();
        if (lExtras == null){
            return;
        }
        mVoteName = (String) lExtras.get("VOTE_NAME");
        mDateDebut = (String) lExtras.get("START_DATE");
        mDateFin = (String) lExtras.get("END_DATE");


        //Récupération du composant ListView
        ListView lListView = (ListView)findViewById(R.id.lLVUninomialOneTurn);

        //Récupération de la liste des personnes
        //ArrayList<CCandidat> mListCandidat = new ArrayList<CCandidat>();
        //CCandidat candidat = new CCandidat();
        mListCandidat.add(new CCandidate());
        mListCandidat.add(new CCandidate());
        mAdapter = new CCandidatAdapter(this, mListCandidat);
        //Création et initialisation de l'Adapter pour les personnes


        //Initialisation de la liste avec les données
        lListView.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cvote_conf_uninomial_one_turn, menu);
        return true;
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }
    //send vote parameters to the participant selection
    public void validateConfUniOne(View view) {
        hideSoftKeyboard(this);
        int lNbErrors = 0;
        mAdapter.notifyDataSetChanged();

        // on verifie que tous les champs sont bien remplis
        for (int i = 0; i < mListCandidat.size(); i++) {
            if ((mListCandidat.get(i).getNomCandidat() == null) || (mListCandidat.get(i).getDescriptionCandidat() == null )){
                Toast.makeText(getApplicationContext(), "Vous n'avez pas rempli tous les champs", Toast.LENGTH_LONG).show();
                lNbErrors++;
            }
        }
        if (lNbErrors == 0){
            Intent lIntent = new Intent(this, CParticipantActivity.class);
            lIntent.putExtra("liste de Candidat", mListCandidat);
            lIntent.putExtra("VOTE_NAME", mVoteName);
            lIntent.putExtra("START_DATE", mDateDebut);
            lIntent.putExtra("END_DATE", mDateFin);
            lIntent.putExtra("VOTE_TYPE", mTypeVote);
            startActivity(lIntent);
        }
    }

    public void addChoiceButton(View view) {
        mListCandidat.add(new CCandidate());
        mAdapter.notifyDataSetChanged();
    }

    public void removeChoiceButton(View view) {
        if (mListCandidat.size() > 2){
            mListCandidat.remove(mListCandidat.size() - 1);
           // ListView test = (ListView)findViewById(R.id.lLVUninomialOneTurn);
            //Log.i("contenu",mListCandidat.toString());
            mAdapter.notifyDataSetChanged();
        }

    }
    @Override
    public void onBackPressed() {
        // On crée le dialogue
        AlertDialog.Builder lConfirmationDialog = new AlertDialog.Builder(CVoteConfUninominalOneTurn.this);
        // On modifie le titre
        lConfirmationDialog.setTitle("Arrêt création vote");
        // On modifie le message
        lConfirmationDialog.setMessage("Voulez-vous arrêter la création du vote ?");
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

        return super.onOptionsItemSelected(pItem);
    }
}
