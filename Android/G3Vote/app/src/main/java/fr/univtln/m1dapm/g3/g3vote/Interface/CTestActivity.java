package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.R;

public class CTestActivity extends ActionBarActivity {

    private static ArrayList<CCandidate> listCandidat = new ArrayList<CCandidate>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ctest);

        Bundle extras = getIntent().getExtras();
        if (extras==null){
            return;
        }
        RelativeLayout rl = (RelativeLayout)findViewById(R.id.test);
        listCandidat=(ArrayList<CCandidate>)extras.get("liste de Candidat");
        for (int i = 0; i <listCandidat.size() ; i++) {
            TextView textView = new TextView(this);
            textView.setText(listCandidat.get(i).getNom());
            rl.addView(textView);
        }

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
