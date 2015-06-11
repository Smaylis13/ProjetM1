package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import fr.univtln.m1dapm.g3.g3vote.Entite.CSessionManager;
import fr.univtln.m1dapm.g3.g3vote.R;

public class CSettingAccount extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csetting_account);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_csetting_account, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_settings) {
            Intent lIntent = new Intent(this,CSettingAccount.class);
            finish();
            startActivity(lIntent);
            return true;
        }
        if(id == R.id.websiteMenu){
            Intent lIntent = new Intent(this, CWebSiteActivity.class);
            startActivity(lIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void modifiercompte (View view){
        Intent lIntent = new Intent(this,CModifCompte.class);
        startActivity(lIntent);
    }

    public void deconnexion(View view){
        CSessionManager lSession = new CSessionManager(this);
        lSession.logoutUser();
        Intent lIntent = new Intent(this,CLoginActivity.class);
        lIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(lIntent);
        this.finish();
        CHubActivity.sActivity.finish();
    }

    public void supprimercompte(View view){
        final Intent lIntent = new Intent(this,CSuppressionCompte.class);
        // On cree le dialogue
        AlertDialog.Builder lConfirmationDialog = new AlertDialog.Builder(CSettingAccount.this);
        // On modifie le titre
        lConfirmationDialog.setTitle("Supprimer compte");
        // On modifie le message
        lConfirmationDialog.setMessage("Voulez-vous supprimer votre compte ?");
        // Bouton Oui
        lConfirmationDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //supression du compte

                startActivity(lIntent);

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
