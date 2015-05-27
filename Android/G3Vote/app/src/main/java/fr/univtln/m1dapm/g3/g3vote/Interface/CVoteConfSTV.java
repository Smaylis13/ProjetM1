package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.R;

public class CVoteConfSTV extends AppCompatActivity {


    private static ArrayList<CCandidate> mListCandidat = new ArrayList<CCandidate>();
    private CCandidate mCandidat = new CCandidate();

    private CCandidatAdapter mAdapter;

    @Override
    protected void onCreate(Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        setContentView(R.layout.activity_cvote_conf_stv);

        ListView lList = (ListView)findViewById(R.id.lLVSTV);
        mListCandidat.add(mCandidat);
        mListCandidat.add(mCandidat);
        mAdapter = new CCandidatAdapter(this, mListCandidat);

        lList.setAdapter(mAdapter);
    }



    public void validateConfSTV(View pView) {
        for (int i = 0; i <mListCandidat.size() ; i++) {
            ListView lTest = (ListView)findViewById(R.id.lLVSTV);
            View lCde = lTest.getChildAt(i);
            EditText lEditText = (EditText) lCde.findViewById(R.id.choiceName);
            String lString = lEditText.getText().toString();
            mListCandidat.get(i).setNom(lString);
            Log.i("test", lString);
        }
        Intent lIntent = new Intent(this,CTestActivity.class);
        lIntent.putExtra("liste de Candidat",mListCandidat);
        startActivity(lIntent);

    }

    public void addChoiceButton(View pView) {
        mListCandidat.add(mCandidat);
        mAdapter.notifyDataSetChanged();
    }

    public void removeChoiceButton(View pView) {
        if (mListCandidat.size() > 2) {
            mListCandidat.remove(mListCandidat.lastIndexOf(mCandidat));
            // ListView test = (ListView)findViewById(R.id.lLVUninomialOneTurn);
            //Log.i("contenu",listCandidat.toString());
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu pMenu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cvote_conf_stv, pMenu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem pItem) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int lId = pItem.getItemId();

        //noinspection SimplifiableIfStatement
        if (lId == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(pItem);
    }
}
