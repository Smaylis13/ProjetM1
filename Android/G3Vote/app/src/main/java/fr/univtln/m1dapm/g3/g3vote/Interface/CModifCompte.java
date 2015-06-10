package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import fr.univtln.m1dapm.g3.g3vote.Communication.CCommunication;
import fr.univtln.m1dapm.g3.g3vote.Communication.CRequestTypesEnum;
import fr.univtln.m1dapm.g3.g3vote.Communication.CTaskParam;
import fr.univtln.m1dapm.g3.g3vote.Entite.CUser;
import fr.univtln.m1dapm.g3.g3vote.R;

public class CModifCompte extends ActionBarActivity {

    EditText mModifNom ;
    EditText mModifPrenom ;
    EditText mModifMail ;
    EditText mModifMdp ;
    private static Context sContext;

    public static Context getsContext() {
        return sContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sContext=getApplicationContext();
        setContentView(R.layout.activity_cmodif_compte);
        mModifNom = (EditText)findViewById(R.id.editnommodifcompte);
        mModifPrenom = (EditText)findViewById(R.id.editprenommodifcompte);
        mModifMail = (EditText)findViewById(R.id.editmailmodifcompte);
        mModifMdp = (EditText)findViewById(R.id.editmdpmodifcompte);


        mModifNom.setText(CHubActivity.getsLoggedUser().getName());
        mModifPrenom.setText(CHubActivity.getsLoggedUser().getFirstName());
        mModifMail.setText(CHubActivity.getsLoggedUser().getEmail());
        mModifMdp.setText(CHubActivity.getsLoggedUser().getPassword());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cmodif_compte, menu);
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
            Intent lIntent = new Intent(this,CSettingAccount.class);
            finish();
            startActivity(lIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void validemodifcompte (View view){
        // On cree le dialogue
        AlertDialog.Builder lConfirmationDialog = new AlertDialog.Builder(CModifCompte.this);
        // On modifie le titre
        lConfirmationDialog.setTitle("modification de compte");
        // On modifie le message
        lConfirmationDialog.setMessage("Voulez-vous vraiment modifier votre compte ?");
        // Bouton Oui
        lConfirmationDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CUser lNewUserParams=new CUser(CHubActivity.getsLoggedUser().getUserId(),mModifPrenom.getText().toString(),mModifNom.getText().toString(),mModifMail.getText().toString(),mModifMdp.getText().toString());
                CTaskParam lParams=new CTaskParam(CRequestTypesEnum.update_user,lNewUserParams);
                CCommunication lCom=new CCommunication();
                lCom.execute(lParams);
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
