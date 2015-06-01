package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.R;

/**
 * Created by sebastien on 29/05/15.
 */
public class CVoteUninominal extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cvote_uninominal);
        //Récupération du composant ListView
        ListView lList = (ListView)findViewById(R.id.LVChoixCandidatUninominal);


        //Récupération de la liste des personnes
        ArrayList<CCandidate> lListCandidate = CCandidate.getAListOfCandidate();

        //Création et initialisation de l'Adapter pour les personnes
        CCandidateUniqueChoiceAdapter adapter = new CCandidateUniqueChoiceAdapter(this, lListCandidate);

        //Initialisation de la liste avec les données
        lList.setAdapter(adapter);

        //TODO:Recuperer les candidats sur le serveur et les afficher

    }

    public void vote(View pView){
        //TODO:Vérifier qu'un candidat est bien selectionné
        // On crée le dialogue
        AlertDialog.Builder lConfirmationDialog = new AlertDialog.Builder(CVoteUninominal.this);
        // On modifie le titre
        lConfirmationDialog.setTitle("Confirmation de vote");
        // On modifie le message
        lConfirmationDialog.setMessage("Êtes-vous sûr de vouloir voter pour ce candidat ?");
        // Bouton Oui
        lConfirmationDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //TODO:Envoyer le vote au serveur et afficher un toast pour confirmer le vote

                // On termine l'activité et on retourne sur la page principale
                Intent intent = new Intent(CVoteUninominal.this, CHubActivity.class);
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
}
