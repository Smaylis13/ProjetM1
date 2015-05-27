package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.content.Intent;
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

public class CVoteConfKemenyYoung extends AppCompatActivity {


    private static ArrayList<CCandidate> listCandidat = new ArrayList<CCandidate>();
    private CCandidate candidat = new CCandidate();

    private CCandidatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cvote_conf_kemeny_young);


        ListView list = (ListView)findViewById(R.id.lLVKemenyYoung);
        listCandidat.add(candidat);
        listCandidat.add(candidat);
        adapter = new CCandidatAdapter(this, listCandidat);

        list.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cvote_conf_kemeny_young, menu);
        return true;
    }


    public void validateConfKemenyYoung(View view) {
        for (int i = 0; i <listCandidat.size() ; i++) {
            ListView test = (ListView)findViewById(R.id.lLVKemenyYoung);
            View cde = test.getChildAt(i);
            EditText editText=(EditText)cde.findViewById(R.id.choiceName);
            String string=editText.getText().toString();
            listCandidat.get(i).setNom(string);
            Log.i("test", string);
        }
        Intent intent = new Intent(this,CTestActivity.class);
        intent.putExtra("liste de Candidat",listCandidat);
        startActivity(intent);

    }

    public void addChoiceButton(View view) {
        listCandidat.add(candidat);
        adapter.notifyDataSetChanged();
    }

    public void removeChoiceButton(View view) {
        if (listCandidat.size()>2){
            listCandidat.remove(listCandidat.lastIndexOf(candidat));
            // ListView test = (ListView)findViewById(R.id.lLVUninomialOneTurn);
            //Log.i("contenu",listCandidat.toString());
            adapter.notifyDataSetChanged();
        }

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
