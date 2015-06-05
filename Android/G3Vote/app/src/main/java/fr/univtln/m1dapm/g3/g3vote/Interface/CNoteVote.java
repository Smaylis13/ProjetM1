package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Communication.CCommunication;
import fr.univtln.m1dapm.g3.g3vote.Communication.CRequestTypesEnum;
import fr.univtln.m1dapm.g3.g3vote.Communication.CTaskParam;
import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.Entite.CChoice;
import fr.univtln.m1dapm.g3.g3vote.Entite.CVoixCandidat;
import fr.univtln.m1dapm.g3.g3vote.Entite.CVote;
import fr.univtln.m1dapm.g3.g3vote.R;

public class CNoteVote extends AppCompatActivity {

    private CVote mVote;
    private CCandidatAffichageNoteAdapter adapter;
    private List<String> mnotepossible;
    private Spinner mlistespin;
    private LayoutInflater mlayoutInflater;
    private View mpromptsView;
    private List<CVoixCandidat> mNotecandidat;
    private static Context sContext;

    public static Context getsContext() {
        return sContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cnote_vote);

        // prepare la nouvelle forme de boite de dialogue


        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        mVote = (CVote) extras.get("VOTE");

        List<CCandidate> lListCandidats = (ArrayList) mVote.getCandidates();
        mNotecandidat = new ArrayList<>();
        for (int i = 0; i <lListCandidats.size() ; i++) {
            CVoixCandidat ltmpvoicandidat=new CVoixCandidat(lListCandidats.get(i),0);
            mNotecandidat.add(ltmpvoicandidat);
        }
        ListView lListViewCandidate = (ListView) this.findViewById(R.id.lLVCandidateListvote);
        adapter = new CCandidatAffichageNoteAdapter(this, R.id.lLVCandidateListvote,mNotecandidat);
        lListViewCandidate.setAdapter(adapter);
        adapter.addAll(lListCandidats);


        }

    public void validenotevote (View view){

        List<CChoice> lListechoix;
        lListechoix=new ArrayList<CChoice>();

        for (int i = 0; i <mNotecandidat.size() ; i++) {
            CChoice ltmpchoix=new CChoice();
            ltmpchoix.setIdVote(mVote.getIdVote());
            ltmpchoix.setIdUser(CHubActivity.getsLoggedUser().getUserId());
            ltmpchoix.setIdCandidate(mNotecandidat.get(i).getMcandidat().getIdCandidat());
            ltmpchoix.setScore(mNotecandidat.get(i).getMvote());
            lListechoix.add(ltmpchoix);
        }
        Log.i("test","liste des candidat et leur point "+mNotecandidat);
        CTaskParam lParams=new CTaskParam(CRequestTypesEnum.add_choices,lListechoix,"note");
        CCommunication lCom=new CCommunication();
        lCom.execute(lParams);
        /*Intent lIntent = new Intent(this,CHubActivity.class);
        startActivity(lIntent);*/

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cnote_vote, menu);
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
