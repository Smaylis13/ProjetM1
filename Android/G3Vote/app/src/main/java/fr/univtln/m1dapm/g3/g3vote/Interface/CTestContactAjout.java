package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import fr.univtln.m1dapm.g3.g3vote.Communication.CCommunication;
import fr.univtln.m1dapm.g3.g3vote.Communication.CRequestTypesEnum;
import fr.univtln.m1dapm.g3.g3vote.Communication.CTaskParam;
import fr.univtln.m1dapm.g3.g3vote.R;

public class CTestContactAjout extends ActionBarActivity {

    private String mNomContact;
    private String mPrenomContact;
    private String mMailContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras==null){
            return;
        }
        mNomContact = (String) extras.get("NomContact");
        mPrenomContact = (String) extras.get("PrenomContact");
        mMailContact = (String) extras.get("MailContact");
        setContentView(R.layout.activity_ctest_contact_ajout);

        Log.i("contact", mNomContact);
        Log.i("contact",mPrenomContact);
        Log.i("contact",mMailContact);
        CTaskParam lParams=new CTaskParam(CRequestTypesEnum.add_contact,mMailContact);
        CCommunication lCom=new CCommunication();
        lCom.execute(lParams);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ctest_contact_ajout, menu);
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
