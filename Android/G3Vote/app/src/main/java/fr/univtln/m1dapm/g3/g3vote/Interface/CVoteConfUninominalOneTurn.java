package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.R;

/**
 * Created by chris on 20/05/15.
 */
public class CVoteConfUninominalOneTurn extends AppCompatActivity {

    private static ArrayList<CCandidate> mListCandidat = new ArrayList<CCandidate>();

    private CCandidatAdapter mAdapter;

    private String mVoteName;
    private String mDateDebut;
    private String mDateFin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cvote_conf_uninominal_one_turn);
        Bundle extras = getIntent().getExtras();
        if (extras==null){
            return;
        }
        mVoteName = (String) extras.get("VOTE_NAME");
        mDateDebut = (String) extras.get("START_DATE");
        mDateFin = (String) extras.get("END_DATE");


        //Récupération du composant ListView
        ListView list = (ListView)findViewById(R.id.lLVUninomialOneTurn);

        //Récupération de la liste des personnes
        //ArrayList<CCandidat> mListCandidat = new ArrayList<CCandidat>();
        //CCandidat candidat = new CCandidat();
        mListCandidat.add(new CCandidate());
        mListCandidat.add(new CCandidate());
        mAdapter = new CCandidatAdapter(this, mListCandidat);
        //Création et initialisation de l'Adapter pour les personnes


        //Initialisation de la liste avec les données
        list.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cvote_conf_uninomial_one_turn, menu);
        return true;
    }

    //send vote parameters to the participant selection
    public void validateConfUniOne(View view) {
        for (int i = 0; i < mListCandidat.size() ; i++) {
            ListView test = (ListView)findViewById(R.id.lLVUninomialOneTurn);
            View cde = test.getChildAt(i);
            EditText editText=(EditText)cde.findViewById(R.id.choiceName);
            String string=editText.getText().toString();
            mListCandidat.get(i).setNomCandidat(string);
            Log.i("test",string);
        }
        Intent lIntent = new Intent(this,CTestActivity.class);
        lIntent.putExtra("liste de Candidat", mListCandidat);
        lIntent.putExtra("VOTE_NAME", mVoteName);
        lIntent.putExtra("START_DATE", mDateFin);
        lIntent.putExtra("END_DATE", mDateDebut);
        startActivity(lIntent);

    }

    public void addChoiceButton(View view) {
        mListCandidat.add(new CCandidate());
        mAdapter.notifyDataSetChanged();
    }

    public void removeChoiceButton(View view) {
        if (mListCandidat.size()>2){
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
}
