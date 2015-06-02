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
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Communication.CCommunication;
import fr.univtln.m1dapm.g3.g3vote.Communication.CRequestTypesEnum;
import fr.univtln.m1dapm.g3.g3vote.Communication.CTaskParam;
import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.Entite.CType;
import fr.univtln.m1dapm.g3.g3vote.Entite.CUser;
import fr.univtln.m1dapm.g3.g3vote.Entite.CVote;
import fr.univtln.m1dapm.g3.g3vote.R;

public class CRecapVoteActivity extends AppCompatActivity {

    //variables pour la récupération des données de l'activité précédente
    private String mVoteName;
    private String mDateDebut;
    private String mDateFin;
    private ArrayList<CCandidate> mListCandidat = new ArrayList<CCandidate>();
    private ArrayList<CUser> mListParticipant = new ArrayList<CUser>();
<<<<<<< HEAD
    private String mTypeVote;
    //Majority
    private int mCalculationMethod;
=======
    private CType mTypeVote;
>>>>>>> 6b640f145ca551b6944240bf77f4863e5fef15a4

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crecap_vote);

        //stockage des données de l'activity precedente
        Bundle extras = getIntent().getExtras();
        if (extras==null){
            return;
        }
        mVoteName = (String) extras.get("VOTE_NAME");
        mDateDebut = (String) extras.get("START_DATE");
        mDateFin = (String) extras.get("END_DATE");
        mListCandidat = (ArrayList<CCandidate>)extras.get("liste de Candidat");
        mListParticipant=(ArrayList<CUser>)extras.get("liste de participant");
<<<<<<< HEAD
        mTypeVote = (String) extras.get("VOTE_TYPE");
        mCalculationMethod = (int) extras.get("CALCULATIONMETHOD");
=======
        mTypeVote = (CType) extras.getSerializable("VOTE_TYPE");
>>>>>>> 6b640f145ca551b6944240bf77f4863e5fef15a4

        Log.i("donner candida", mListCandidat.toString());
        //remplis la liste des participants
        ListView lListParticipant = (ListView)this.findViewById(R.id.lLVParticipantList);
        CUserAdapter cUserAdapter = new CUserAdapter(this,mListParticipant);
        lListParticipant.setAdapter(cUserAdapter);

        //remplis la liste des candidats
        ListView lListCandidate = (ListView)this.findViewById(R.id.lLVCandidateList);
        CCandidatAffichageAdapter cCandidatAdapter = new CCandidatAffichageAdapter(this,R.id.lLVCandidateList);
        lListCandidate.setAdapter(cCandidatAdapter);
        List lListNomCandidat =new ArrayList();
        for (int i = 0; i <mListCandidat.size() ; i++) {
            lListNomCandidat.add(mListCandidat.get(i).getNomCandidat());
            cCandidatAdapter.add(mListCandidat.get(i));
        }

        //met le nom du vote
        TextView lTVNameVote = (TextView)this.findViewById(R.id.lTVNameVote);
        lTVNameVote.setText(mVoteName);

        //met le type du vote
        TextView lTVTypeVote = (TextView)this.findViewById(R.id.lTVTypeVote);
        lTVTypeVote.setText(mTypeVote.getNom());

        //met la date de début
        TextView lTVDateBegin = (TextView)this.findViewById(R.id.lTVDateBegin);
        lTVDateBegin.setText("duree : du "+mDateDebut+" au ");

        //met la date de fin
        TextView lTVDateEnd = (TextView)this.findViewById(R.id.lTVDateEnd);
        lTVDateEnd.setText(mDateFin);

    }

    public void validate (View pView) throws ParseException {

        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date lDateDeb = new java.sql.Date(simpleDateFormat.parse(mDateDebut).getTime());
        Date lDateFin = new java.sql.Date(simpleDateFormat.parse(mDateFin).getTime());

        CVote lVote = new CVote(mVoteName, "", true, lDateDeb, lDateFin, CHubActivity.getsLoggedUser().getUserId(), null, mTypeVote, null, mListCandidat, null);
        CTaskParam lParams = new CTaskParam(CRequestTypesEnum.add_new_vote, lVote);
        CCommunication lCom = new CCommunication();
        lCom.execute(lParams);

        Intent intent = new Intent(this, CHubActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    public void cancelVoteCreation(View pView){
        // On crée le dialogue
        AlertDialog.Builder lConfirmationDialog = new AlertDialog.Builder(CRecapVoteActivity.this);
        // On modifie le titre
        lConfirmationDialog.setTitle("Annulation création vote");
        // On modifie le message
        lConfirmationDialog.setMessage("Voulez-vous annuler la création du vote ?");
        // Bouton Oui
        lConfirmationDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // On termine l'activité et on retourne sur la page principale
                Intent intent = new Intent(CRecapVoteActivity.this, CHubActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
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
        getMenuInflater().inflate(R.menu.menu_crecap_vote, menu);
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
        AlertDialog.Builder lConfirmationDialog = new AlertDialog.Builder(CRecapVoteActivity.this);
        // On modifie le titre
        lConfirmationDialog.setTitle("Retour page précédente");
        // On modifie le message
        lConfirmationDialog.setMessage("Voulez-vous retourner sur la page d'invitation ?");
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
