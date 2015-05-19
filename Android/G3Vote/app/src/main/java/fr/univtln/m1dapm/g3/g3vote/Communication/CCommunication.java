package fr.univtln.m1dapm.g3.g3vote.Communication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Debug;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import fr.univtln.m1dapm.g3.g3vote.Entite.CUser;
import fr.univtln.m1dapm.g3.g3vote.Interface.CHubActivity;
import fr.univtln.m1dapm.g3.g3vote.Interface.CSubActivity;

/**
 * Created by ludo on 05/05/15.
 */
public class CCommunication extends AsyncTask<Object, Void, String> {
    public static final String SERVER_URL="http://192.168.1.92:9999/";
    public final static String LOGGED_USER = "fr.univtln.m1dapm.g3.g3vote.LOGGED_USER";



    @Override
    protected String doInBackground(Object...pObject) {
        URL lUrl = null;
        OutputStreamWriter lOut=null;
        HttpURLConnection lHttpCon = null;
        InputStream in = null;
        CTaskParam lParams=(CTaskParam)pObject[0];
        try {
            switch (lParams.getRequestType()) {
                case add_new_user:
                    lUrl = new URL(SERVER_URL+"user");
                    lHttpCon = (HttpURLConnection) lUrl.openConnection();
                    JSONObject lNewUserOBJ = new JSONObject();
                    CUser lNewUser = (CUser) lParams.getObject();
                    lNewUserOBJ.put("email", lNewUser.getMail());
                    lNewUserOBJ.put("password", lNewUser.getPassword());
                    lNewUserOBJ.put("firstName", lNewUser.getFirstName());
                    lNewUserOBJ.put("name", lNewUser.getLastName());
                    lHttpCon.setDoOutput(true);
                    lHttpCon.setDoInput(true);
                    lHttpCon.setRequestMethod("PUT");
                    lHttpCon.setRequestProperty("Content-Type", "application/json");
                    lHttpCon.setRequestProperty("Accept", "application/json");
                    lOut = new OutputStreamWriter(lHttpCon.getOutputStream());
                    //lOut=lHttpCon.getOutputStream();
                    lOut.write(lNewUserOBJ.toString());
                    lOut.flush();
                    //lOut.close();
                    in = new BufferedInputStream(lHttpCon.getInputStream());
                    String response = readStream(in);
                    lNewUser.setIdUser(Integer.decode(response));
                    //CSubActivity.getIntentCSubActivity().putExtra(LOGGED_USER,lNewUser);
                    Intent lIntent=new Intent(CSubActivity.getsContext(),CHubActivity.class);
                    lIntent.putExtra(LOGGED_USER,lNewUser);
                    lIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    CSubActivity.getsContext().startActivity(lIntent);
                    int lResponse = lHttpCon.getResponseCode();
                break;
            }
        }catch (ProtocolException e) {
            Log.e("CCommunication", e.toString());
        }catch (MalformedURLException e) {
            Log.e("CCommunication", e.toString());
        }catch (JSONException e) {
            Log.e("CCommunication", e.toString());
        } catch (IOException e) {
            Log.e("CCommunication", e.toString());
        }
        return null;

    }

    //startActivity(mIntent);


    private String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while(i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }

}
