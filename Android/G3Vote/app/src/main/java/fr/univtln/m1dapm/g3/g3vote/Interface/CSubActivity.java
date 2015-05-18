package fr.univtln.m1dapm.g3.g3vote.Interface;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import fr.univtln.m1dapm.g3.g3vote.Entite.CUser;
import fr.univtln.m1dapm.g3.g3vote.R;

public class CSubActivity extends AppCompatActivity {

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
        final EditText ET_prenom = (EditText)findViewById(R.id.userFirstNameInput);
        final EditText ET_nom = (EditText)findViewById(R.id.userLastNameInput);
        final EditText ET_mail = (EditText)findViewById(R.id.userMailInput);
        final EditText ET_password = (EditText)findViewById(R.id.userPasswordInput);
        final String prenom = ET_prenom.getText().toString();
        final String nom = ET_nom.getText().toString();
        final String mail = ET_mail.getText().toString();
        final String password = ET_password.getText().toString();
        if (prenom != null && !prenom.isEmpty() && nom!=null && !nom.isEmpty() && mail!=null &&
               !mail.isEmpty() && password!= null && !password.isEmpty()) {
            CUser user = new CUser(prenom, nom, mail, password);
            Intent intent = new Intent(this,CHubActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this,getString(R.string.subValidateErrorMessage),Toast.LENGTH_SHORT).show();
        }
    }
}
