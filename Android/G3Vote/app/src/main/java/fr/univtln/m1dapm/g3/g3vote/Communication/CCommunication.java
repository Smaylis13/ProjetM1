package fr.univtln.m1dapm.g3.g3vote.Communication;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

/*import com.google.gson.Gson;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;*/
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import fr.univtln.m1dapm.g3.g3vote.Entite.CUser;
import fr.univtln.m1dapm.g3.g3vote.Entite.CVote;
import fr.univtln.m1dapm.g3.g3vote.Interface.CHubActivity;
import fr.univtln.m1dapm.g3.g3vote.Interface.CHubMyVotesFragment;
import fr.univtln.m1dapm.g3.g3vote.Interface.CLoginActivity;
import fr.univtln.m1dapm.g3.g3vote.Interface.CSubActivity;

/**
 * Created by ludo on 05/05/15.
 */
public class CCommunication extends AsyncTask<Object, Void, Integer> {
    public static final String SERVER_URL="http://10.21.205.16:80/";
    public final static String LOGGED_USER = "fr.univtln.m1dapm.g3.g3vote.LOGGED_USER";

    @Override
    protected Integer doInBackground(Object...pObject) {
        URL lUrl = null;
        OutputStreamWriter lOut=null;
        HttpURLConnection lHttpCon = null;
        InputStream lIn = null;
        String lResponse=null;
        ObjectMapper lMapper=new ObjectMapper();
        int lCode;
        CTaskParam lParams=(CTaskParam)pObject[0];

        try {
            switch (lParams.getRequestType()) {
                case log_user:
                    lUrl = new URL(SERVER_URL+"user/connect");
                    lHttpCon = (HttpURLConnection) lUrl.openConnection();
                    CUser lUser = (CUser) lParams.getObject();
                   /* lUserOBJ.put("mEmail", lUser.getEmail());
                    lUserOBJ.put("mPassword", lUser.getPassword());
                    lUserOBJ.put("mFirstName", "unknown");
                    lUserOBJ.put("mName", "unknown");*/

                    String lJsonString=lMapper.writeValueAsString(lUser);
                    JSONObject lUserOBJ = new JSONObject(lJsonString);
                    lHttpCon.setDoOutput(true);
                    lHttpCon.setDoInput(true);
                    lHttpCon.setRequestMethod("POST");

                    lHttpCon.setRequestProperty("Content-Type", "application/json");
                    lHttpCon.setRequestProperty("Accept", "application/json");
                    lOut = new OutputStreamWriter(lHttpCon.getOutputStream());
                    //lOut=lHttpCon.getOutputStream();
                    lOut.write(lUserOBJ.toString());
                    lOut.flush();
                    lCode=lHttpCon.getResponseCode();
                    if(lCode==200) {
                        //lOut.close();
                        lIn = new BufferedInputStream(lHttpCon.getInputStream());
                        lResponse = readStream(lIn);
                        CUser lLoggedUser=lMapper.readValue(lResponse,CUser.class);
                        Intent lLogIntent=new Intent(CLoginActivity.getsContext(),CHubActivity.class);
                        lLogIntent.putExtra(LOGGED_USER, lLoggedUser);
                        lLogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        CLoginActivity.getsContext().startActivity(lLogIntent);
                        //return lCode;
                    }
                    else if(lCode==401){
                        return lCode;
                    }
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
                    CUser lNewUser = (CUser) lParams.getObject();
                    String lJsonStringNewUser=lMapper.writeValueAsString(lNewUser);
                    JSONObject lNewUserOBJ = new JSONObject(lJsonStringNewUser);
                    lHttpCon.setDoOutput(true);
                    lHttpCon.setDoInput(true);
                    lHttpCon.setRequestMethod("PUT");
                    lHttpCon.setRequestProperty("Content-Type", "application/json");
                    lHttpCon.setRequestProperty("Accept", "application/json");
                    lOut = new OutputStreamWriter(lHttpCon.getOutputStream());
                    //lOut=lHttpCon.getOutputStream();
                    lOut.write(lNewUserOBJ.toString());
                    lOut.flush();
                    lCode=lHttpCon.getResponseCode();
                    if(lCode==201) {
                        //lOut.close();
                        lIn = new BufferedInputStream(lHttpCon.getInputStream());
                        lResponse = readStream(lIn);
                        lNewUser.setUserId(Integer.decode(lResponse));
                        //CSubActivity.getIntentCSubActivity().putExtra(LOGGED_USER,lNewUser);
                        Intent lIntent = new Intent(CSubActivity.getsContext(), CHubActivity.class);
                        lIntent.putExtra(LOGGED_USER, lNewUser);
                        lIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        CSubActivity.getsContext().startActivity(lIntent);
                    }
                    else
                        return lCode;
                break;

                case delete_user:
                    //lUrl = new URL(SERVER_URL+"user/"+USER_ID);
                    lHttpCon = (HttpURLConnection) lUrl.openConnection();
                    lHttpCon.setDoInput(true);
                    lHttpCon.setRequestMethod("DELETE");
                    //lOut.close();
                    lIn = new BufferedInputStream(lHttpCon.getInputStream());
                    lResponse = readStream(lIn);
                    //CSubActivity.getIntentCSubActivity().putExtra(LOGGED_USER,lNewUser);
                    int lReponse = lHttpCon.getResponseCode();
                break;
                case get_vote:
                    lUrl = new URL(SERVER_URL+"vote/"+Integer.toString((int)lParams.getObject()));
                    lHttpCon = (HttpURLConnection) lUrl.openConnection();
                    lHttpCon.setDoInput(true);
                    lHttpCon.setRequestMethod("GET");
                    lHttpCon.setRequestProperty("Accept", "application/json");
                    lCode=lHttpCon.getResponseCode();
                    if(lCode==201) {
                        //lOut.close();
                        lIn = new BufferedInputStream(lHttpCon.getInputStream());
                        lResponse = readStream(lIn);
                        Gson lGson = new Gson();
                        CVote lGottenVote=lGson.fromJson(lResponse, CVote.class);
                        //TODO change the intent
                        Intent lIntent = new Intent(CSubActivity.getsContext(), CHubActivity.class);
                        lIntent.putExtra("GOTTEN_VOTE", (java.io.Serializable) lGottenVote);
                        lIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        CSubActivity.getsContext().startActivity(lIntent);
                    }
                    else
                        return lCode;

                break;
                case get_votes:
                    lUrl = new URL(SERVER_URL+"vote/all/"+Integer.toString((int)lParams.getObject()));
                    lHttpCon = (HttpURLConnection) lUrl.openConnection();
                    lHttpCon.setDoInput(true);
                    lHttpCon.setRequestMethod("GET");
                    lHttpCon.setRequestProperty("Accept", "application/json");
                    lCode=lHttpCon.getResponseCode();
                    if(lCode==201) {
                        //lOut.close();
                        lIn = new BufferedInputStream(lHttpCon.getInputStream());
                        lResponse = readStream(lIn);
                        Type listType = new TypeToken<ArrayList<CVote>>() {}.getType();
                        ArrayList<CVote> lVotes = new Gson().fromJson(lResponse, listType);
                        CHubMyVotesFragment.getInstance().setmVotes(lVotes);



                        /*Intent lIntent = new Intent(CSubActivity.getsContext(), CHubActivity.class);
                        lIntent.putParcelableArrayListExtra("GOTTEN_VOTES", lVotes);
                        lIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        CSubActivity.getsContext().startActivity(lIntent);*/
                    }
                    else
                        return lCode;

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
        }
        return null;

    }


    public void onPostExecute(Integer pCode){
        if(pCode!=null) {
            if (pCode == 401)
                Toast.makeText(CLoginActivity.getsContext(), "Mauvais login/mot de passe", Toast.LENGTH_SHORT).show();
            if(pCode==409)
                Toast.makeText(CSubActivity.getsContext(), "L'élément existe déjà", Toast.LENGTH_SHORT).show();
        }
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
