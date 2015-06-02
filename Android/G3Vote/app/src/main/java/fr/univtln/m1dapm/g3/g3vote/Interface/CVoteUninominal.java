package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Communication.CCommunication;
import fr.univtln.m1dapm.g3.g3vote.Communication.CRequestTypesEnum;
import fr.univtln.m1dapm.g3.g3vote.Communication.CTaskParam;
import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.Entite.CVote;
import fr.univtln.m1dapm.g3.g3vote.R;

/**
 * Created by sebastien on 29/05/15.
 */
public class CVoteUninominal extends AppCompatActivity {

    private CVote mVote;
    private static CCandidateUniqueChoiceAdapter sAdapter;
    private ListView lList;
    private static List<CCandidate> sListCandidat;

    public static void setlList(List<CCandidate> pList) {
        sListCandidat = new ArrayList<>(pList);

        sAdapter.notifyDataSetChanged();
    }

    public CVoteUninominal(){}

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cvote_uninominal);

        Bundle extras = getIntent().getExtras();
        if (extras==null){
            return;
        }
        mVote = (CVote) extras.get("VOTE");
        /*

        CTaskParam lParams = new CTaskParam(CRequestTypesEnum.get_candidates, mIdVote, mTypeVote);
        CCommunication lCom = new CCommunication();
        lCom.execute(lParams);
*/


        //Récupération du composant ListView
        lList = (ListView)findViewById(R.id.LVChoixCandidatUninominal);

        sListCandidat = mVote.getCandidates();
        //Récupération de la liste des personnes
        //ArrayList<CCandidate> lListCandidate = CCandidate.getAListOfCandidate();

        //Création et initialisation de l'Adapter pour les personnes
        sAdapter = new CCandidateUniqueChoiceAdapter(this, sListCandidat);

        //Initialisation de la liste avec les données
        lList.setAdapter(sAdapter);

        //TODO:Recuperer les candidats sur le serveur et les afficher

    }

    public void vote(View pView){
        int position = lList.getCheckedItemPosition();
        if(position != -1){
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

                    int position = lList.getCheckedItemPosition();

                    CCandidate lCandidat  = (CCandidate) lList.getItemAtPosition(position);


                    Log.i("Candidat voté : ", lCandidat.toString());
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
        else {
            // On crée le dialogue
            AlertDialog.Builder lConfirmationDialog = new AlertDialog.Builder(CVoteUninominal.this);
            // On modifie le titre
            lConfirmationDialog.setTitle("Aucun candidat selectionné");
            // On modifie le message
            lConfirmationDialog.setMessage("Vous n'avez selectionné aucun candidat");
            // Bouton OK: on ferme le dialogue
            lConfirmationDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();

                }
            });
            // On affiche le message
            lConfirmationDialog.show();
        }

    }

    @Override
    public void onBackPressed() {
        // On crée le dialogue
        AlertDialog.Builder lConfirmationDialog = new AlertDialog.Builder(CVoteUninominal.this);
        // On modifie le titre
        lConfirmationDialog.setTitle("Arrêt vote");
        // On modifie le message
        lConfirmationDialog.setMessage("Voulez-vous arrêter de voter ?");
        // Bouton Oui
        lConfirmationDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // On termine l'activité
                finish();
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
