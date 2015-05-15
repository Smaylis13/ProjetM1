package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import fr.univtln.m1dapm.g3.g3vote.R;

public class CLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clogin);
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
        Intent intent = new Intent(this,CHubActivity.class);
        startActivity(intent);
    }
}
