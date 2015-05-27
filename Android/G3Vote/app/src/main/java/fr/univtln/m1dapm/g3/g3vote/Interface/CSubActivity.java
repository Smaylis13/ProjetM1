package fr.univtln.m1dapm.g3.g3vote.Interface;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import fr.univtln.m1dapm.g3.g3vote.Communication.CCommunication;
import fr.univtln.m1dapm.g3.g3vote.Communication.CEnvoiMessage;
import fr.univtln.m1dapm.g3.g3vote.Communication.CRequestTypesEnum;
import fr.univtln.m1dapm.g3.g3vote.Communication.CTaskParam;
import fr.univtln.m1dapm.g3.g3vote.Entite.CUser;
import fr.univtln.m1dapm.g3.g3vote.R;

public class CSubActivity extends AppCompatActivity {
    private static Context sContext;

    public static Context getsContext() {
        return sContext;
    }

    public static void setsContext(Context sContext) {
        CSubActivity.sContext = sContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csub);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cinscription, menu);
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

    //TODO: handle the inscription on the server
    public void validateSub(View view) {
        final EditText lET_FirstName = (EditText)findViewById(R.id.userFirstNameInput);
        final EditText lET_Name = (EditText)findViewById(R.id.userLastNameInput);
        final EditText lET_Mail = (EditText)findViewById(R.id.userMailInput);
        final EditText lET_Password = (EditText)findViewById(R.id.userPasswordInput);
        final String lFirstName = lET_FirstName.getText().toString();
        final String lName = lET_Name.getText().toString();
        final String lMail = lET_Mail.getText().toString();
        final String lPassword = lET_Password.getText().toString();
        if (!lFirstName.isEmpty() && !lName.isEmpty() && !lMail.isEmpty() && !lPassword.isEmpty()) {
            CUser lUser = new CUser(lFirstName, lName, lMail, lPassword);
            CTaskParam lParams=new CTaskParam(CRequestTypesEnum.add_new_user,lUser);
            sContext=getApplicationContext();
            CCommunication lCom=new CCommunication();
            lCom.execute(lParams);
        }
        else{
            Toast.makeText(this,getString(R.string.subValidateErrorMessage),Toast.LENGTH_SHORT).show();
        }
    }
}
