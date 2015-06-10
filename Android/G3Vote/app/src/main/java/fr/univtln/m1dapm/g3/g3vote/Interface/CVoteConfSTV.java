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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.Entite.CType;
import fr.univtln.m1dapm.g3.g3vote.R;

public class CVoteConfSTV extends AppCompatActivity {


    private ArrayList<CCandidate> mListCandidat = new ArrayList<>();
    private CCandidatAdapter mAdapter;

    private String mVoteName;
    private String mDateDebut;
    private String mDateFin;
    private static final String TYPE_VOTE = "STV";
    private CType mTypeVote ;
    private ListView mList;

    @Override
    protected void onCreate(Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        setContentView(R.layout.activity_cvote_conf_stv);
        mTypeVote = new CType(1,"STV",this.getString(R.string.stvDescription));

        Bundle lExtras = getIntent().getExtras();
        if (lExtras == null){
            return;
        }
        mVoteName = (String) lExtras.get("VOTE_NAME");
        mDateDebut = (String) lExtras.get("START_DATE");
        mDateFin = (String) lExtras.get("END_DATE");
        mList = (ListView) findViewById(R.id.lLVSTV);
        mListCandidat.add(new CCandidate());
        mListCandidat.add(new CCandidate());
        mAdapter = new CCandidatAdapter(this, mListCandidat);

        mList.setAdapter(mAdapter);
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    public void validateConfSTV(View pView) {
        final EditText lNbGagnant = (EditText)findViewById(R.id.nbGagnant);
        hideSoftKeyboard(this);
        int lNbError = 0;
        mAdapter.notifyDataSetChanged();
        // on verifie que tous les champs sont bien remplis
        for (int i = 0; i < mListCandidat.size(); i++) {
            if ((mListCandidat.get(i).getNomCandidat() == null) || (mListCandidat.get(i).getDescriptionCandidat() == null)
                    || (lNbGagnant.getText() == null)){
                Toast.makeText(getApplicationContext(), "Vous n'avez pas rempli tous les champs", Toast.LENGTH_LONG).show();
                lNbError++;
            }
        }
        if (lNbError == 0){
            Intent lIntent = new Intent(this,CParticipantActivity.class);
            int lNbGagnantInt = Integer.parseInt(lNbGagnant.getText().toString());
            lIntent.putExtra("liste de Candidat", mListCandidat);
            lIntent.putExtra("VOTE_NAME", mVoteName);
            lIntent.putExtra("START_DATE", mDateDebut);
            lIntent.putExtra("END_DATE", mDateFin);
            lIntent.putExtra("VOTE_TYPE", mTypeVote);
            lIntent.putExtra("NB_GAGNANT", lNbGagnantInt);
            startActivity(lIntent);
        }
    }

    public void addChoiceButton(View pView) {
        hideSoftKeyboard(this);
        mListCandidat.add(new CCandidate());
        mAdapter.notifyDataSetChanged();
    }

    public void removeChoiceButton(View pView) {
        if (mListCandidat.size() > 2) {
            mListCandidat.remove(mListCandidat.size()-1);
            // ListView test = (ListView)findViewById(R.id.lLVUninomialOneTurn);
            //Log.i("contenu",listCandidat.toString());
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu pMenu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cvote_conf_stv, pMenu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem pItem) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int lId = pItem.getItemId();

        //noinspection SimplifiableIfStatement
        if (lId == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(pItem);
    }



    @Override
    public void onBackPressed() {
        // On crée le dialogue
        AlertDialog.Builder lConfirmationDialog = new AlertDialog.Builder(CVoteConfSTV.this);
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
}
