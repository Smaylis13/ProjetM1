package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.R;

public class CVoteConfMajorityJugement extends AppCompatActivity {



    private static ArrayList<CCandidate> mlistCandidat = new ArrayList<CCandidate>();
    private CCandidate mcandidat = new CCandidate();

    private CCandidatAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cvote_conf_majority_jugement);

        ListView list = (ListView)findViewById(R.id.lLVMajorityJugement);
        mlistCandidat.add(mcandidat);
        mlistCandidat.add(mcandidat);
        adapter = new CCandidatAdapter(this, mlistCandidat);

        list.setAdapter(adapter);
    }



    public void validateConfCondorcet(View view) {
        for (int i = 0; i <mlistCandidat.size() ; i++) {
            ListView test = (ListView)findViewById(R.id.lLVMajorityJugement);
            View cde = test.getChildAt(i);
            EditText editText=(EditText)cde.findViewById(R.id.choiceName);
            String string=editText.getText().toString();
            mlistCandidat.get(i).setNom(string);
            Log.i("test", string);
        }
        Intent intent = new Intent(this,CTestActivity.class);
        intent.putExtra("liste de Candidat",mlistCandidat);
        startActivity(intent);

    }

    public void addChoiceButton(View view) {
        mlistCandidat.add(mcandidat);
        adapter.notifyDataSetChanged();
    }

    public void removeChoiceButton(View view) {
        if (mlistCandidat.size() > 2) {
            mlistCandidat.remove(mlistCandidat.lastIndexOf(mcandidat));
            // ListView test = (ListView)findViewById(R.id.lLVUninomialOneTurn);
            //Log.i("contenu",listCandidat.toString());
            adapter.notifyDataSetChanged();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cvote_conf_majority_jugement, menu);
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
