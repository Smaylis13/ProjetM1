package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.http.HttpEntity;
import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Communication.CCommunication;
import fr.univtln.m1dapm.g3.g3vote.Communication.CRequestTypesEnum;
import fr.univtln.m1dapm.g3.g3vote.Communication.CTaskParam;
import fr.univtln.m1dapm.g3.g3vote.Entite.CUser;
import fr.univtln.m1dapm.g3.g3vote.R;
import fr.univtln.m1dapm.g3.g3vote.crypto.CCrypto;

public class CContactAjout extends ActionBarActivity {

    private static Context sContext;
    private SearchView mSearch;
    private ListView mLvSearch;
    private List<String> mRes = new ArrayList<>();
    private List<String> mListContactMail = new ArrayList<String>();


    public static Context getsContext() {
        return sContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ccontact_ajout);

        sContext=this;
        Bundle extras = getIntent().getExtras();
        if (extras != null){
           List<CUser> lListContact = (List<CUser>) extras.get("listecontact");
            for(CUser lUser : lListContact){
                mListContactMail.add(lUser.getEmail());
            }

        }


        mSearch = (SearchView) findViewById(R.id.searchView);
        mLvSearch = (ListView) findViewById(R.id.lvSearch);

        mLvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder lConfirmationDialog = new AlertDialog.Builder(CContactAjout.this);
                lConfirmationDialog.setTitle("Ajouter un contact");
                lConfirmationDialog.setMessage("Voulez-vous ajouter '"+mLvSearch.getItemAtPosition(position) + "' à vos contacts ?");
                lConfirmationDialog.setPositiveButton("Ajouter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CTaskParam lParams=new CTaskParam(CRequestTypesEnum.add_contact,mLvSearch.getItemAtPosition(position));
                        CCommunication lCom=new CCommunication();
                        lCom.execute(lParams);

                        Toast.makeText(getsContext(),"Contact "+mLvSearch.getItemAtPosition(position).toString()+" ajouté",
                                Toast.LENGTH_SHORT).show();

                        CTaskParam lParams2=new CTaskParam(CRequestTypesEnum.get_contacts);
                        CCommunication lCom2=new CCommunication();
                        lCom2.execute(lParams2);
                        mListContactMail.add(String.valueOf(mLvSearch.getItemAtPosition(position)));
                        new CallAPI().execute(CCommunication.SERVER_URL + "user/all",
                                ((SearchView) findViewById(R.id.searchView)).getQuery().toString());

                    }
                });
                lConfirmationDialog.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                // On affiche le message
                lConfirmationDialog.show();
            }
        });

        //***setOnQueryTextListener***
        mSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getBaseContext(), query,
                        Toast.LENGTH_SHORT).show();
                mSearch.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.length() > 2) {
                    mLvSearch.setVisibility(View.VISIBLE);
                    new CallAPI().execute(CCommunication.SERVER_URL+"user/all", newText);
                }else{
                    mLvSearch.setVisibility(View.INVISIBLE);
                }
                return false;
            }
        });

    }
    public static String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
        InputStream in = entity.getContent();
        StringBuffer out =
                new StringBuffer();
        int n = 1;
        while (n>0) {
            byte[] b = new byte[4096];
            n =  in.read(b);
            if (n>0) out.append(new String(b, 0, n));
        }
        return out.toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ccontact_ajout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            //noinspection SimplifiableIfStatement
            case R.id.action_settings:
                Intent lIntent = new Intent(this, CSettingAccount.class);
                startActivity(lIntent);
                this.finish();
                return true;
            case R.id.check_contact:
                onBackPressed();
                this.finish();
                return true;

            default:
                this.finish();
                return true;
        }
//        return super.onOptionsItemSelected(item);
    }

    /**
     * Récupère la liste des utilisateurs et affiche que leurs mail dans la SearchView ( ListView)
     */
       private class CallAPI extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String text="";
            String lDecryptString = null;

            String lService_url = params[0];
            String pSearch = params[1];
            URL lUrl;
            int lCode;
            try {
                lUrl = new URL(lService_url);

                HttpURLConnection lHttpCon = (HttpURLConnection) lUrl.openConnection();
                    lHttpCon.setDoInput(true);
                    lHttpCon.setRequestMethod("GET");
                    lHttpCon.setRequestProperty("Accept", "application/json");
                    lHttpCon.setRequestProperty("ID", CLoginActivity.getUniqueKey().toString());
                    lCode=lHttpCon.getResponseCode();
                BufferedInputStream lIn = new BufferedInputStream(lHttpCon.getInputStream());
                String lResponse = CCommunication.readStream(lIn);
                CCrypto lCrypto=new CCrypto();

                lDecryptString=lCrypto.publicDecrypt(lCrypto.getKey(), Hex.decodeHex(lResponse.toCharArray()));
                JSONArray json = new JSONArray(lDecryptString);

                mRes.clear();
                for (int i = 0; i < json.length(); i++){
                    if (json.getJSONObject(i).getString("email").contains(pSearch)
                            && contactNotExist(json.getJSONObject(i).getString("email"))){
                        mRes.add(json.getJSONObject(i).getString("email"));

                    }

                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DecoderException e) {
                e.printStackTrace();
            } catch (Exception e) {
                Log.e("SEARCH", e.getLocalizedMessage());
                return "Error";

            }
            return text;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s.equals("Error")){
                  Toast.makeText(sContext, "please try later...", Toast.LENGTH_LONG).show();
            }else{
                mLvSearch.setAdapter(new ArrayAdapter<>(sContext,android.R.layout.simple_list_item_1,mRes));
            }
        }
    }



    private boolean contactNotExist(String pMail) {
        for (String lmail : mListContactMail) {
            if (lmail.equals(pMail))
                return false;
        }
       return true;
}


}
