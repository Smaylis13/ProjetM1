package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Communication.CCommunication;
import fr.univtln.m1dapm.g3.g3vote.Communication.CRequestTypesEnum;
import fr.univtln.m1dapm.g3.g3vote.Communication.CTaskParam;
import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.Entite.CUser;
import fr.univtln.m1dapm.g3.g3vote.R;

public class CContactSupression extends ActionBarActivity {
    private List<CUser> mListContact = new ArrayList<CUser>();
    ListView mListContactView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ccontact_supression);

        Bundle extras = getIntent().getExtras();
        if (extras==null){
            return;
        }
        mListContact = (List<CUser>) extras.get("listecontact");

        mListContactView = (ListView)this.findViewById(R.id.ListContact);
        CUserAdapter cUserAdapter = new CUserAdapter(this,mListContact);
        mListContactView.setAdapter(cUserAdapter);
        mListContactView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, final long id) {
                // On cree le dialogue
                AlertDialog.Builder lConfirmationDialog = new AlertDialog.Builder(CContactSupression.this);
                // On modifie le titre
                lConfirmationDialog.setTitle("supprimer contact");
                // On modifie le message
                lConfirmationDialog.setMessage("Voulez-vous supprimer ce contact?");
                // Bouton Oui
                lConfirmationDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        CUser lContact=(CUser)mListContactView.getItemAtPosition(position);
                        CTaskParam lParams=new CTaskParam(CRequestTypesEnum.delete_contact,CHubActivity.getsLoggedUser().getUserId()+"/"+lContact.getUserId());
                        CCommunication lCom=new CCommunication();
                        lCom.execute(lParams);
                        //Log.i("test", "id = " + mListContactView.getItemAtPosition(position));

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



                return false;
            }
        });

    }

    public void retour(View view){
        Intent lIntent = new Intent(this,CHubActivity.class);

        startActivity(lIntent);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ccontact_supression, menu);


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
