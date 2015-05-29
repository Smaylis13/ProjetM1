package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.Entite.CUser;
import fr.univtln.m1dapm.g3.g3vote.R;

public class CParticipantActivity extends AppCompatActivity {

    //variables pour la récupération des données de l'activité précédente
    private String mVoteName;
    private String mDateDebut;
    private String mDateFin;
    private ArrayList<CCandidate> mListCandidat = new ArrayList<CCandidate>();
    private String mTypeVote;


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

        //Récupération du composant ListView
        ListView list = (ListView)this.findViewById(R.id.lLVParticipantList);

        //Récupération de la liste des personnes
        ArrayList<CUser> mListUser = CUser.getAListOfUser();

        //Création et initialisation de l'Adapter pour les personnes
        CUserChoiceAdapter adapter = new CUserChoiceAdapter(this, mListUser);

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
