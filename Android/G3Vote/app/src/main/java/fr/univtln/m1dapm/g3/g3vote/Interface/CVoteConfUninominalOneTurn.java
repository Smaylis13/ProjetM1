package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidat;
import fr.univtln.m1dapm.g3.g3vote.R;

/**
 * Created by chris on 20/05/15.
 */
public class CVoteConfUninominalOneTurn extends AppCompatActivity {

    private ArrayList<CCandidat> listCandidat = new ArrayList<CCandidat>();
    private CCandidat candidat = new CCandidat();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cvote_conf_uninominal_one_turn);

        //Récupération du composant ListView
        ListView list = (ListView)findViewById(R.id.lListViewMyVote);

        //Récupération de la liste des personnes
        ArrayList<CCandidat> listCandidat = new ArrayList<CCandidat>();
        CCandidat candidat = new CCandidat();
        listCandidat.add(candidat);

        //Création et initialisation de l'Adapter pour les personnes
        CCandidatAdapter adapter = new CCandidatAdapter(this, listCandidat);

        //Initialisation de la liste avec les données
        list.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cvote_conf_uninomial_one_turn, menu);
        return true;
    }

    public void addChoiceButton(View view) {



    }

    public void removeChoiceButton(View view) {
    }
}
