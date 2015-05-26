package fr.univtln.m1dapm.g3.g3vote.Communication;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

/*import com.google.gson.Gson;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;*/
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import fr.univtln.m1dapm.g3.g3vote.Entite.CUser;
import fr.univtln.m1dapm.g3.g3vote.Interface.CHubActivity;
import fr.univtln.m1dapm.g3.g3vote.Interface.CLoginActivity;
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
        String lResponse=null;
        CTaskParam lParams=(CTaskParam)pObject[0];
        /*
        try {
            switch (lParams.getRequestType()) {
                case log_user:
                    lUrl = new URL(SERVER_URL+"user/connect");
                    lHttpCon = (HttpURLConnection) lUrl.openConnection();
                    JSONObject lUserOBJ = new JSONObject();
                    CUser lUser = (CUser) lParams.getObject();
                    lUserOBJ.put("mEmail", lUser.getEmail());
                    lUserOBJ.put("mPassword", lUser.getPassword());
                    lUserOBJ.put("mFirstName", "unknown");
                    lUserOBJ.put("mName", "unknown");
                    lHttpCon.setDoOutput(true);
                    lHttpCon.setDoInput(true);
                    lHttpCon.setRequestMethod("POST");
                    lHttpCon.setRequestProperty("Content-Type", "application/json");
                    lHttpCon.setRequestProperty("Accept", "application/json");
                    lOut = new OutputStreamWriter(lHttpCon.getOutputStream());
                    //lOut=lHttpCon.getOutputStream();
                    lOut.write(lUserOBJ.toString());
                    lOut.flush();
                    //lOut.close();
                    in = new BufferedInputStream(lHttpCon.getInputStream());
                    lResponse = readStream(in);
                    JSONObject lJsonUser=new JSONObject(lResponse);

                    //Log.e("TEST",lResponse);
                    Gson lGson = new Gson();
                    String test= lGson.toJson(lUser);
                    CUser lLoggedUser=lGson.fromJson(lResponse, CUser.class);
                    Intent lLogIntent=new Intent(CLoginActivity.getsContext(),CHubActivity.class);
                    lLogIntent.putExtra(LOGGED_USER, lLoggedUser);
                    lLogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    CSubActivity.getsContext().startActivity(lLogIntent);
                break;
                case auth_user:
                    OAuthClientRequest lRequest = OAuthClientRequest
                            .tokenLocation("http://10.21.205.16:80/" + "auth/token")
                            .setGrantType(GrantType.PASSWORD)
                            .setClientId(CCommon.CLIENT_ID)
                            .setClientSecret(CCommon.CLIENT_SECRET)
                            .setUsername(CCommon.USERNAME)
                            .setPassword(CCommon.PASSWORD)
                            .buildQueryMessage();

                    lRequest.setHeader(OAuth.HeaderType.CONTENT_TYPE, "multipart/form-data");
                    lRequest.setHeader(OAuth.OAUTH_RESPONSE_TYPE, "application/json");
                    lRequest.setHeader("response_type","application/json");
                    OAuthClient lAuthClient = new OAuthClient(new URLConnectionClient());
                    OAuthAccessTokenResponse lOAuthResponse = lAuthClient.accessToken(lRequest, OAuth.HttpMethod.POST);

                    break;

                case add_new_user:
                    lUrl = new URL(SERVER_URL+"user");
                    lHttpCon = (HttpURLConnection) lUrl.openConnection();
                    JSONObject lNewUserOBJ = new JSONObject();
                    CUser lNewUser = (CUser) lParams.getObject();
                    lNewUserOBJ.put("mEmail", lNewUser.getEmail());
                    lNewUserOBJ.put("mPassword", lNewUser.getPassword());
                    lNewUserOBJ.put("mFirstName", lNewUser.getFirstName());
                    lNewUserOBJ.put("mName", lNewUser.getName());
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
                    lResponse = readStream(in);
                    lNewUser.setId(Integer.decode(lResponse));
                    //CSubActivity.getIntentCSubActivity().putExtra(LOGGED_USER,lNewUser);
                    Intent lIntent=new Intent(CSubActivity.getsContext(),CHubActivity.class);
                    lIntent.putExtra(LOGGED_USER,lNewUser);
                    lIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    CSubActivity.getsContext().startActivity(lIntent);
                break;

                case delete_user:
                    //lUrl = new URL(SERVER_URL+"user/"+USER_ID);
                    lHttpCon = (HttpURLConnection) lUrl.openConnection();
                    lHttpCon.setDoInput(true);
                    lHttpCon.setRequestMethod("DELETE");
                    //lOut.close();
                    in = new BufferedInputStream(lHttpCon.getInputStream());
                    lResponse = readStream(in);
                    //CSubActivity.getIntentCSubActivity().putExtra(LOGGED_USER,lNewUser);
                    int lReponse = lHttpCon.getResponseCode();
                break;

            }
        }catch (ProtocolException e) {
            Log.e("CCommunication", e.toString());
        }catch (MalformedURLException e) {
            Log.e("CCommunication", e.toString());
        }catch (JSONException e) {
            Log.e("CCommunication", e.toString());
        }catch (IOException e) {
            Log.e("CCommunication", e.toString());
        } catch (OAuthSystemException e) {
            Log.e("CCommunication",e.toString());
        } catch (OAuthProblemException e) {
            Log.e("CCommunication",e.toString());
        }*/
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
