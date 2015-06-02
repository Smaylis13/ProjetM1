package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Communication.CCommunication;
import fr.univtln.m1dapm.g3.g3vote.Communication.CRequestTypesEnum;
import fr.univtln.m1dapm.g3.g3vote.Communication.CTaskParam;
import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.Entite.CUser;
import fr.univtln.m1dapm.g3.g3vote.R;

public class CParticipantActivity extends AppCompatActivity {

    //variables pour la récupération des données de l'activité précédente
    private String mVoteName;
    private String mDateDebut;
    private String mDateFin;
    private ArrayList<CCandidate> mListCandidat = new ArrayList<CCandidate>();
    private ArrayList<CUser> mListParticipant = new ArrayList<CUser>();
    private String mTypeVote;
    private CUserChoiceAdapter adapter;
    //Majority
    private int mCalculationMethod;//



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cparticipant);
        //stockage des données de l'activity precedente
        Bundle extras = getIntent().getExtras();
        if (extras==null){
            return;
        }
        mVoteName = (String) extras.get("VOTE_NAME");
        mDateDebut = (String) extras.get("START_DATE");
        mDateFin = (String) extras.get("END_DATE");
        mListCandidat = (ArrayList<CCandidate>)extras.get("liste de Candidat");
        mTypeVote = (String) extras.get("VOTE_TYPE");
        //Majority
        mCalculationMethod = (int) extras.get("CALCULATIONMETHOD");


        Log.i("donner candida", mListCandidat.toString());
        //Récupération du composant ListView
        ListView list = (ListView)this.findViewById(R.id.lLVParticipantList);

        //parametrage de la listView
        list.setChoiceMode(list.CHOICE_MODE_MULTIPLE);

        //Récupération de la liste des personnes
        //ArrayList<CUser> mListUser = CUser.getAListOfUser();
        /*CTaskParam lParams=new CTaskParam(CRequestTypesEnum.get_contacts);
        CCommunication lCom=new CCommunication();
        lCom.execute(lParams);*/
        //Création et initialisation de l'Adapter pour les personnes
        adapter = new CUserChoiceAdapter(this, CHubContactFragment.getsContacts());

        //Initialisation de la liste avec les données
        list.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cparticipant, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        ListView list2 = (ListView)this.findViewById(R.id.lLVParticipantList);
        Toast.makeText(this,
                String.valueOf(list2.getCheckedItemPosition()),
                Toast.LENGTH_LONG).show();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void bValidateParticipantList(View view) {
        ListView list = (ListView)this.findViewById(R.id.lLVParticipantList);
        int tailleListView = list.getCount();
        //RelativeLayout layout =(RelativeLayout) findViewById(R.id.RLuserChoice);

  /*      for(int i = 0;i<list.getChildCount();i++)
        {
            View view = list.getChildAt(i);
            CheckedTextView cv =(CheckedTextView)view.findViewById(R.id.checkList);
            if(cv.isChecked())
*/


        for (int i = 0; i <tailleListView ; i++) {
            View view2 = list.getChildAt(i);
            CheckedTextView ctv = (CheckedTextView)view2.findViewById(R.id.participantChoiceCTV);
            if (ctv.isChecked()){
                mListParticipant.add((CUser)adapter.getItem(i));
            }
        }
        Intent lIntent = new Intent(this,CRecapVoteActivity.class);
        Log.i("donner participant",mListParticipant.toString());
        lIntent.putExtra("liste de Candidat", mListCandidat);
        lIntent.putExtra("liste de participant",mListParticipant);
        lIntent.putExtra("VOTE_NAME", mVoteName);
        lIntent.putExtra("START_DATE", mDateDebut);
        lIntent.putExtra("END_DATE", mDateFin);
        lIntent.putExtra("VOTE_TYPE", mTypeVote);
        lIntent.putExtra("CALCULATIONMETHOD", mCalculationMethod);
        startActivity(lIntent);
    }

    @Override
    public void onBackPressed() {
        // On crée le dialogue
        AlertDialog.Builder lConfirmationDialog = new AlertDialog.Builder(CParticipantActivity.this);
        // On modifie le titre
        lConfirmationDialog.setTitle("Retour page précédente");
        // On modifie le message
        lConfirmationDialog.setMessage("Voulez-vous revenir à la page d'ajout des candidats ?");
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
