package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import fr.univtln.m1dapm.g3.g3vote.Communication.CCommunication;
import fr.univtln.m1dapm.g3.g3vote.Communication.CRequestTypesEnum;
import fr.univtln.m1dapm.g3.g3vote.Communication.CTaskParam;
import fr.univtln.m1dapm.g3.g3vote.R;

public class CContactAjout extends AppCompatActivity {

    private static Context sContext;

    public static Context getsContext() {
        return sContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ccontact_ajout);
        sContext=getApplicationContext();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ccontact_ajout, menu);
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
    public void ValidationAjoutContact(View view) {
        final EditText lNomContact = (EditText)findViewById(R.id.editNomContact);
        final EditText lPrenomContact = (EditText)findViewById(R.id.editPrenomContact);
        final EditText lMailContact= (EditText)findViewById(R.id.editMailContact);
        final String lNomContactString = lNomContact.getText().toString();
        final String lPrenomContactString = lPrenomContact.getText().toString();
        final String lMailContactString = lMailContact.getText().toString();

        if (!(lNomContactString.isEmpty()&&lPrenomContactString.isEmpty()&&lMailContactString.isEmpty())){



            /*Intent lIntent = new Intent(this,CTestContactAjout.class);
            lIntent.putExtra("NomContact",lNomContactString);
            lIntent.putExtra("PrenomContact",lPrenomContactString);
            lIntent.putExtra("MailContact",lMailContactString);*/
            CTaskParam lParams=new CTaskParam(CRequestTypesEnum.add_contact,lMailContactString);
            CCommunication lCom=new CCommunication();
            lCom.execute(lParams);

            CTaskParam lParams2=new CTaskParam(CRequestTypesEnum.get_contacts);
            CCommunication lCom2=new CCommunication();
            lCom2.execute(lParams2);





        } else{
            Toast.makeText(getApplicationContext(), "Entrez tout les champ!", Toast.LENGTH_LONG).show();
        }


    }
}
