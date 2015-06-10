package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import fr.univtln.m1dapm.g3.g3vote.Communication.CCommunication;
import fr.univtln.m1dapm.g3.g3vote.Communication.CRequestTypesEnum;
import fr.univtln.m1dapm.g3.g3vote.Communication.CTaskParam;
import fr.univtln.m1dapm.g3.g3vote.R;

public class CSuppressionCompte extends ActionBarActivity {

    private static Context sContext;

    public static Context getContext() {
        return sContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sContext=getApplicationContext();
        setContentView(R.layout.activity_csupression_compte);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_csuppression_compte, menu);
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



    public void suprimer (View view) {
        //changer destination pour suprimer le compte
        final EditText lET_Mail = (EditText)findViewById(R.id.mailInputsupression);
        final EditText lET_Password = (EditText)findViewById(R.id.passInputsupression);
        final String lMail = lET_Mail.getText().toString();
        final String lPassword = lET_Password.getText().toString();
        if (lMail.isEmpty()) {
        }else{
            CTaskParam lParam=new CTaskParam(CRequestTypesEnum.delete_user,lMail+"/"+lPassword);
            CCommunication lCom=new CCommunication();
            lCom.execute(lParam);
        }
    }
}
