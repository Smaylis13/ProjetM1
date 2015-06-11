package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.SettingsApi;

import java.math.BigInteger;
import java.util.UUID;

import fr.univtln.m1dapm.g3.g3vote.Communication.CCommunication;
import fr.univtln.m1dapm.g3.g3vote.Communication.CRequestTypesEnum;
import fr.univtln.m1dapm.g3.g3vote.Communication.CTaskParam;
import fr.univtln.m1dapm.g3.g3vote.Entite.CCryptoBean;
import fr.univtln.m1dapm.g3.g3vote.Entite.CSessionManager;
import fr.univtln.m1dapm.g3.g3vote.Entite.CUser;
import fr.univtln.m1dapm.g3.g3vote.R;
import fr.univtln.m1dapm.g3.g3vote.crypto.CCrypto;

public class CLoginActivity extends AppCompatActivity {
    private static Context sContext;
    private static Activity sActivity;
    private static final UUID UNIQUE_KEY = UUID.randomUUID();
    public final static String EXTRA_LOGIN = "USER_LOGIN";
    private final static BigInteger PUBLIC_KEY=CCrypto.sendKeyParam();
    private SettingsApi mIpSettingsSpinner;
    private String mMailRegExp = new String("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");

    // Session Manager Class
    private CSessionManager mSession;
    public static UUID getUniqueKey() {
        return UNIQUE_KEY;
    }

    public static BigInteger getPublicKey() {
        return PUBLIC_KEY;
    }

    public static Context getsContext() {
        return sContext;
    }

    public static Activity getsActivity() {
        return sActivity;
    }

    public static void setsContext(Context sContext) {
        CLoginActivity.sContext = sContext;
    }

    @Override
    protected void onResume(){
        super.onResume();
        CHubActivity.setsLoggedUser(null);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clogin);
        sContext = getApplicationContext();
        sActivity = this;
        // Session Manager
        mSession = new CSessionManager(getApplicationContext());

        if (mSession.isLoggedIn()){
            Intent lIntent = new Intent(this,CHubActivity.class);
            lIntent.putExtra("LOGGER",true);
            finish();
            startActivity(lIntent);


        }


        CTaskParam lParams = new CTaskParam(CRequestTypesEnum.generate_keys, new CCryptoBean(PUBLIC_KEY, UNIQUE_KEY));
        CCommunication lCom = new CCommunication();
        lCom.execute(lParams);



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

        if(id == R.id.websiteMenu){
            Intent lIntent = new Intent(this, CWebSiteActivity.class);
            startActivity(lIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //gere le clic sur le bouton s'insrire
    public void Sub(View view) {
        Intent intent = new Intent(this,CSubActivity.class);
        startActivity(intent);
        this.finish();
    }

    //gere le clic sur le bouton se connecter
    public void Log(View view) {
        //TODO: handle the connection of the user
        final EditText lET_Mail = (EditText)findViewById(R.id.mailInput);
        final EditText lET_Password = (EditText)findViewById(R.id.passInput);
        final String lMail = lET_Mail.getText().toString();
        final String lPassword = lET_Password.getText().toString();

        if (!(lMail.isEmpty()) && lMail.matches(mMailRegExp) ){
            CTaskParam lParams=new CTaskParam(CRequestTypesEnum.log_user,new CUser(null,null,lMail,lPassword));
            CCommunication lCom=new CCommunication();
            lCom.execute(lParams);
            //finish();
            /*Intent lIntent = new Intent(this,CHubActivity.class);
            lIntent.putExtra(EXTRA_LOGIN,lET_Mail.getText().toString());
            startActivity(lIntent);*/
        }else{
            Toast.makeText(getApplicationContext(),getString(R.string.UnvalidMail),Toast.LENGTH_LONG).show();
        }

    }
}
