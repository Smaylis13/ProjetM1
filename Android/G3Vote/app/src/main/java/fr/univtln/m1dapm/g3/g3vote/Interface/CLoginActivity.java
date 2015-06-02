package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import fr.univtln.m1dapm.g3.g3vote.Communication.CCommunication;
import fr.univtln.m1dapm.g3.g3vote.Communication.CRequestTypesEnum;
import fr.univtln.m1dapm.g3.g3vote.Communication.CTaskParam;
import fr.univtln.m1dapm.g3.g3vote.Entite.CUser;
import fr.univtln.m1dapm.g3.g3vote.R;

public class CLoginActivity extends AppCompatActivity {
    private static Context sContext;
    public final static String EXTRA_LOGIN = "USER_LOGIN";

    public static Context getsContext() {
        return sContext;
    }

    public static void setsContext(Context sContext) {
        CLoginActivity.sContext = sContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clogin);
        sContext=getApplicationContext();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_clogin, menu);
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

    //gere le clic sur le bouton s'insrire
    public void Sub(View view) {
        Intent intent = new Intent(this,CSubActivity.class);
        startActivity(intent);
    }

    //gere le clic sur le bouton se connecter
    public void Log(View view) {
        //TODO: handle the connection of the user
        final EditText lET_Mail = (EditText)findViewById(R.id.mailInput);
        final EditText lET_Password = (EditText)findViewById(R.id.passInput);
        final String lMail = lET_Mail.getText().toString();
        final String lPassword = lET_Password.getText().toString();

        if (!(lET_Mail.getText().toString().isEmpty())){
            /*CTaskParam lParams=new CTaskParam(CRequestTypesEnum.log_user,new CUser(null,null,lMail,lPassword));
            CCommunication lCom=new CCommunication();
            lCom.execute(lParams);*/
            Intent lIntent = new Intent(this,CHubActivity.class);
            lIntent.putExtra(EXTRA_LOGIN,lET_Mail.getText().toString());
            startActivity(lIntent);
        }else{
            Toast.makeText(getApplicationContext(),"Entrez un mail!",Toast.LENGTH_LONG).show();
        }

    }
}
