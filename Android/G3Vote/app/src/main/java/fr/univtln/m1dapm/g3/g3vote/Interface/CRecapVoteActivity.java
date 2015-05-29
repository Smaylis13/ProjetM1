package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.Entite.CUser;
import fr.univtln.m1dapm.g3.g3vote.R;

public class CRecapVoteActivity extends AppCompatActivity {

    //variables pour la récupération des données de l'activité précédente
    private String mVoteName;
    private String mDateDebut;
    private String mDateFin;
    private ArrayList<CCandidate> mListCandidat = new ArrayList<CCandidate>();
    private ArrayList<CUser> mListParticipant = new ArrayList<CUser>();
    private String mTypeVote;

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
        mTypeVote = (String) extras.get("VOTE_TYPE");

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
        lTVTypeVote.setText(mTypeVote);

        //met la date de début
        TextView lTVDateBegin = (TextView)this.findViewById(R.id.lTVDateBegin);
        lTVDateBegin.setText("duree : du "+mDateDebut+" au ");

        //met la date de fin
        TextView lTVDateEnd = (TextView)this.findViewById(R.id.lTVDateEnd);
        lTVDateEnd.setText(mDateFin);

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
}
