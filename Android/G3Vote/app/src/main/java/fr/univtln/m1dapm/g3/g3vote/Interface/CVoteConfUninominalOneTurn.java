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

import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.R;

/**
 * Created by chris on 20/05/15.
 */
public class CVoteConfUninominalOneTurn extends AppCompatActivity {

    private static ArrayList<CCandidate> mListCandidat = new ArrayList<CCandidate>();

    private CCandidatAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cvote_conf_uninominal_one_turn);

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
            mListCandidat.get(i).setNom(string);
            Log.i("test",string);
        }
        Intent intent = new Intent(this,CTestActivity.class);
        intent.putExtra("liste de Candidat", mListCandidat);
        startActivity(intent);

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
}
