package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.R;

public class CTestActivity extends AppCompatActivity {

    private static ArrayList<CCandidate> listCandidat = new ArrayList<CCandidate>();
    private CCandidatAffichageAdapter madapter;
    private String mVoteName;
    private String mDateDebut;
    private String mDateFin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ctest);

        ListView list = (ListView)findViewById(R.id.LVCandidat);
        List lListNomCandidat =new ArrayList();
        madapter = new CCandidatAffichageAdapter(this, android.R.layout.simple_list_item_1);
        list.setAdapter(madapter);
        Bundle extras = getIntent().getExtras();
        if (extras==null){
            return;
        }
        RelativeLayout rl = (RelativeLayout)findViewById(R.id.test);
        listCandidat=(ArrayList<CCandidate>)extras.get("liste de Candidat");
        mVoteName = (String) extras.get("VOTE_NAME");
        mDateDebut = (String) extras.get("START_DATE");
        mDateFin = (String) extras.get("END_DATE");
        TextView lTVVoteName = (TextView) findViewById(R.id.voteName);
        TextView lTVDateDebut = (TextView) findViewById(R.id.recapDateDebut);
        TextView lTVDateFin = (TextView) findViewById(R.id.recapDateFin);
        lTVVoteName.setText(mVoteName);
        lTVDateDebut.setText(mDateDebut);
        lTVDateFin.setText(mDateFin);

        for (int i = 0; i <listCandidat.size() ; i++) {
            lListNomCandidat.add(listCandidat.get(i).getNom());
            madapter.add(listCandidat.get(i));
        }



    }


    public void validate (View view){

        //Intent intent = new Intent(this,/*A remplire*/.class);
        //intent.putExtra("liste de Candidat",listCandidat);
        //startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ctest, menu);
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
