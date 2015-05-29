package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.R;

public class CVoteConfSTV extends AppCompatActivity {


    private static ArrayList<CCandidate> mListCandidat = new ArrayList<CCandidate>();
    private CCandidate mCandidat;

    private CCandidatAdapter mAdapter;

    private String mVoteName;
    private DateFormat mDateDebut;
    private DateFormat mDateFin;
    private static final String TYPE_VOTE = "STV";

    @Override
    protected void onCreate(Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        setContentView(R.layout.activity_cvote_conf_stv);

        Bundle extras = getIntent().getExtras();
        if (extras==null){
            return;
        }
        mVoteName = (String) extras.get("VOTE_NAME");
        mDateDebut = (DateFormat) extras.get("START_DATE");
        mDateFin = (DateFormat) extras.get("END_DATE");
        ListView lList = (ListView)findViewById(R.id.lLVSTV);
        mListCandidat.add(new CCandidate());
        mListCandidat.add(new CCandidate());
        mAdapter = new CCandidatAdapter(this, mListCandidat);

        lList.setAdapter(mAdapter);
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    public void validateConfSTV(View pView) {
        hideSoftKeyboard(this);
        Intent lIntent = new Intent(this,CTestActivity.class);
        lIntent.putExtra("liste de Candidat",mListCandidat);
        lIntent.putExtra("VOTE_NAME", mVoteName);
        lIntent.putExtra("START_DATE", mDateFin);
        lIntent.putExtra("END_DATE", mDateDebut);
        lIntent.putExtra("VOTE_TYPE", TYPE_VOTE);
        startActivity(lIntent);
    }

    public void addChoiceButton(View pView) {

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
