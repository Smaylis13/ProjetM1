package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.Entite.CType;
import fr.univtln.m1dapm.g3.g3vote.R;

public class CVoteConfMajorityJugement extends AppCompatActivity {



    private ArrayList<CCandidate> mListCandidat = new ArrayList<>();
    private String mVoteName;
    private String mDateDebut;
    private String mDateFin;
    private CCandidatAdapter mAdapter;
    private static final String TYPE_VOTE = "MAJORITY";
    private CType mTypeVote ;
    private int mCalculationMethod = -1;// 0 : Meyenne, 1 : Médiane, 2 : Somme

    @Override
    protected void onCreate(Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        mTypeVote = new CType(5,"MAJORITY",getResources().getString(R.string.jugementMajorityDescription));
        setContentView(R.layout.activity_cvote_conf_majority_jugement);

        Bundle extras = getIntent().getExtras();
        if (extras==null){
            return;
        }
        mVoteName = (String) extras.get("VOTE_NAME");
        mDateDebut = (String) extras.get("START_DATE");
        mDateFin = (String) extras.get("END_DATE");
        ListView lList = (ListView)findViewById(R.id.lLVMajorityJugement);
        mListCandidat.add(new CCandidate());
        mListCandidat.add(new CCandidate());
        mAdapter = new CCandidatAdapter(this, mListCandidat);

        lList.setAdapter(mAdapter);
    }


    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    public void calculationMethod(View view){
        final CharSequence[] items = {" Moyenne "," Médiane "," Somme "};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selectionner le mode de calcul");
        final Button lButton = (Button) findViewById(R.id.calculationMethod);
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {

                //Intent i=new Intent(getApplicationContext(),SudokuActivity.class);;
                mCalculationMethod = item;
                switch (item)// when item  option seletced
                {
                    case 0:
                        lButton.setText(" Moyenne ");
                        break;
                    case 1:
                        lButton.setText(" Médiane ");
                        break;
                    case 2:
                        lButton.setText(" Somme ");
                        break;
                }

            }
        });
        final AlertDialog lDialog = builder.create();
        lDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        lDialog.dismiss();
                    }
                });
        lDialog.show();
    }
    public void validateConfMajorityJugement(View pView) {
        hideSoftKeyboard(this);
        int j=0;
        mAdapter.notifyDataSetChanged();

        // on verifie que tous les champs sont bien remplis
        for (int i = 0; i < mListCandidat.size() ; i++) {
            if ((mListCandidat.get(i).getNomCandidat()==null) || (mListCandidat.get(i).getDescriptionCandidat()==null )){
                Toast.makeText(getApplicationContext(), "vous n'avez pas remplis tous les champs", Toast.LENGTH_LONG).show();
                j++;
            }
        }
        if (mCalculationMethod == -1){
            j++;
            Toast.makeText(getApplicationContext(), "Veuillez renseigner le mode de calcul", Toast.LENGTH_LONG).show();
        }

        if (j==0){
            Intent lIntent = new Intent(this,CParticipantActivity.class);
            lIntent.putExtra("liste de Candidat", mListCandidat);
            lIntent.putExtra("VOTE_NAME", mVoteName);
            lIntent.putExtra("START_DATE", mDateDebut);
            lIntent.putExtra("END_DATE", mDateFin);
            lIntent.putExtra("VOTE_TYPE", mTypeVote);
            lIntent.putExtra("CALCULATIONMETHOD", mCalculationMethod);
            startActivity(lIntent);
        }

        /*hideSoftKeyboard(this);
        Intent lIntent = new Intent(this, CParticipantActivity.class);
        lIntent.putExtra("liste de Candidat", mListCandidat);
        lIntent.putExtra("VOTE_NAME", mVoteName);
        lIntent.putExtra("START_DATE", mDateDebut);
        lIntent.putExtra("END_DATE", mDateFin);
        lIntent.putExtra("VOTE_TYPE", TYPE_VOTE);
        lIntent.putExtra("CALCULATIONMETHOD", mCalculationMethod);
        if (mCalculationMethod != -1){
            startActivity(lIntent);
        }else {
            Toast.makeText(getApplicationContext(), "Veuillez renseigner le mode de calcul", Toast.LENGTH_LONG).show();
        }*/
    }

    public void addChoiceButton(View pView) {
        mListCandidat.add(new CCandidate());
        mAdapter.notifyDataSetChanged();
    }

    public void removeChoiceButton(View pView) {
        if (mListCandidat.size() > 2) {
            mListCandidat.remove(mListCandidat.size() - 1);
            // ListView test = (ListView)findViewById(R.id.lLVUninomialOneTurn);
            //Log.i("contenu",listCandidat.toString());
            mAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cvote_conf_majority_jugement, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int lId = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (lId == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
