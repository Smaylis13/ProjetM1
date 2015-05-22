package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidat;
import fr.univtln.m1dapm.g3.g3vote.R;

/**
 * Created by chris on 20/05/15.
 */
public class CVoteConfUninominalOneTurn extends AppCompatActivity {

    private static ArrayList<CCandidat> listCandidat = new ArrayList<CCandidat>();
    private CCandidat candidat = new CCandidat();

    private CCandidatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cvote_conf_uninominal_one_turn);

        //Récupération du composant ListView
        ListView list = (ListView)findViewById(R.id.lLVUninomialOneTurn);

        //Récupération de la liste des personnes
        //ArrayList<CCandidat> listCandidat = new ArrayList<CCandidat>();
        //CCandidat candidat = new CCandidat();
        listCandidat.add(candidat);
        listCandidat.add(candidat);
        adapter = new CCandidatAdapter(this, listCandidat);
        //Création et initialisation de l'Adapter pour les personnes


        //Initialisation de la liste avec les données
        list.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cvote_conf_uninomial_one_turn, menu);
        return true;
    }

    //send vote parameters to the participant selection
    public void validateConfUniOne(View view) {
        for (int i = 0; i <listCandidat.size() ; i++) {
            ListView test = (ListView)findViewById(R.id.lLVUninomialOneTurn);
            View cde = test.getChildAt(i);
            EditText editText=(EditText)cde.findViewById(R.id.choiceName);
            String string=editText.getText().toString();
            listCandidat.get(i).setNom(string);
            Log.i("test",string);
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
}
