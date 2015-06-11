package fr.univtln.m1dapm.g3.g3vote.Communication;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;

import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.Entite.CChoice;
import fr.univtln.m1dapm.g3.g3vote.Entite.CCryptoBean;
import fr.univtln.m1dapm.g3.g3vote.Entite.CResult;
import fr.univtln.m1dapm.g3.g3vote.Entite.CSessionManager;
import fr.univtln.m1dapm.g3.g3vote.Entite.CUser;
import fr.univtln.m1dapm.g3.g3vote.Entite.CVote;
import fr.univtln.m1dapm.g3.g3vote.Interface.CContactAjout;
import fr.univtln.m1dapm.g3.g3vote.Interface.CHubActivity;
import fr.univtln.m1dapm.g3.g3vote.Interface.CHubContactFragment;
import fr.univtln.m1dapm.g3.g3vote.Interface.CHubMyVotesFragment;
import fr.univtln.m1dapm.g3.g3vote.Interface.CLoginActivity;
import fr.univtln.m1dapm.g3.g3vote.Interface.CModifCompte;
import fr.univtln.m1dapm.g3.g3vote.Interface.CNoteVote;
import fr.univtln.m1dapm.g3.g3vote.Interface.CRankingVote;
import fr.univtln.m1dapm.g3.g3vote.Interface.CRecapVoteActivity;
import fr.univtln.m1dapm.g3.g3vote.Interface.CResultJugementMajoritaire;
import fr.univtln.m1dapm.g3.g3vote.Interface.CResultRankingActivity;
import fr.univtln.m1dapm.g3.g3vote.Interface.CResultUninominalActivity;
import fr.univtln.m1dapm.g3.g3vote.Interface.CSubActivity;
import fr.univtln.m1dapm.g3.g3vote.Interface.CSuppressionCompte;
import fr.univtln.m1dapm.g3.g3vote.Interface.CVoteUninominal;
import fr.univtln.m1dapm.g3.g3vote.crypto.CCrypto;

/*import com.google.gson.Gson;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;*/

/**
 * Created by ludo on 05/05/15.
 */
public class CCommunication extends AsyncTask<Object, Void, Integer> {
    public static final String SERVER_URL="http://37.59.104.200:80/";
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
        CCrypto lCrypto=new CCrypto();
        byte[] lMessageBytes;
        String lMessageCrypte;
        String lDecryptString;

        try {
            switch (lParams.getRequestType()) {
                case regId_user:
                    lUrl = new URL(SERVER_URL+"user/"+lParams.getObject());
                    lHttpCon = (HttpURLConnection) lUrl.openConnection();
                    Log.i("GCM_TAG",SERVER_URL+"user/"+lParams.getObject());
                    lHttpCon.setRequestMethod("POST");
                    lHttpCon.setRequestProperty("ID", CLoginActivity.getUniqueKey().toString());
                    lHttpCon.setRequestProperty("Content-Type", "application/json");
                    lHttpCon.setRequestProperty("Accept", "application/json");
                    lCode=lHttpCon.getResponseCode();
                    if(lCode!=200) {
                        return lCode;
                    }

                    break;
                case log_user:
                    lUrl = new URL(SERVER_URL+"user/connect");
                    lHttpCon = (HttpURLConnection) lUrl.openConnection();
                    CUser lUser = (CUser) lParams.getObject();
                    Log.i("test",lUser.toString());
                    String lJsonString=lMapper.writeValueAsString(lUser);
                    JSONObject lUserOBJ = new JSONObject(lJsonString);
                    lMessageBytes=lCrypto.publicEncrypt(lUserOBJ.toString(),(SecretKeySpec)lCrypto.getKey());
                    lMessageCrypte = new String(Hex.encodeHex(lMessageBytes));

                    lHttpCon.setDoOutput(true);
                    lHttpCon.setDoInput(true);
                    lHttpCon.setRequestMethod("POST");
                    lHttpCon.setRequestProperty("ID", CLoginActivity.getUniqueKey().toString());
                    lHttpCon.setRequestProperty("Content-Type", "application/json");
                    lHttpCon.setRequestProperty("Accept", "application/json");
                    lOut = new OutputStreamWriter(lHttpCon.getOutputStream());
                    //lOut=lHttpCon.getOutputStream();
                    lOut.write(lMessageCrypte);

                    lOut.flush();
                    lCode=lHttpCon.getResponseCode();
                    if(lCode==200) {
                        //lOut.close();
                        lIn = new BufferedInputStream(lHttpCon.getInputStream());
                        lResponse = readStream(lIn);
                        lDecryptString=lCrypto.publicDecrypt(lCrypto.getKey(),Hex.decodeHex(lResponse.toCharArray()));
                        CUser lLoggedUser=lMapper.readValue(lDecryptString,CUser.class);
                        Intent lLogIntent=new Intent(CLoginActivity.getsContext(),CHubActivity.class);
                        lLogIntent.putExtra(LOGGED_USER, lLoggedUser);
                        lLogIntent.putExtra("LOGGER",false);
                        lLogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        CLoginActivity.getsContext().startActivity(lLogIntent);
                        //CLoginActivity.getsActivity().finish();
                        //return lCode;
                    }
                    else if(lCode==401){
                        return lCode;
                    }
                break;
                case auth_user:
                    OAuthClientRequest lRequest = OAuthClientRequest
                            .tokenLocation( SERVER_URL+ "auth/token")
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
                    lMessageBytes=lCrypto.publicEncrypt(lNewUserOBJ.toString(),(SecretKeySpec)lCrypto.getKey());
                    lMessageCrypte = new String(Hex.encodeHex(lMessageBytes));
                    lHttpCon.setDoOutput(true);
                    lHttpCon.setDoInput(true);
                    lHttpCon.setRequestMethod("PUT");
                    lHttpCon.setRequestProperty("ID", CLoginActivity.getUniqueKey().toString());
                    lHttpCon.setRequestProperty("Content-Type", "application/json");
                    lHttpCon.setRequestProperty("Accept", "application/json");
                    lOut = new OutputStreamWriter(lHttpCon.getOutputStream());
                    //lOut=lHttpCon.getOutputStream();
                    lOut.write(lMessageCrypte);
                    lOut.flush();
                    lCode=lHttpCon.getResponseCode();
                    if(lCode==201) {
                        //lOut.close();
                        lIn = new BufferedInputStream(lHttpCon.getInputStream());
                        lResponse = readStream(lIn);
                        lDecryptString=lCrypto.publicDecrypt(lCrypto.getKey(),Hex.decodeHex(lResponse.toCharArray()));
                        lNewUser.setUserId(Integer.decode(lDecryptString));
                        //CSubActivity.getIntentCSubActivity().putExtra(LOGGED_USER,lNewUser);
                        Intent lIntent = new Intent(CSubActivity.getsContext(), CHubActivity.class);

                        lIntent.putExtra(LOGGED_USER, lNewUser);
                        lIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        CSubActivity.getsContext().startActivity(lIntent);
                    }
                    else
                        return lCode;
                break;
                case update_user:
                    lUser=(CUser)lParams.getObject();
                    String lJsonStringUser=lMapper.writeValueAsString(lUser);
                    JSONObject lUserJsonObj = new JSONObject(lJsonStringUser);
                    lMessageBytes=lCrypto.publicEncrypt(lUserJsonObj.toString(),(SecretKeySpec)lCrypto.getKey());
                    lMessageCrypte = new String(Hex.encodeHex(lMessageBytes));
                    lHttpCon = (HttpURLConnection) lUrl.openConnection();
                    lHttpCon.setRequestMethod("POST");
                    lHttpCon.setRequestProperty("ID", CLoginActivity.getUniqueKey().toString());
                    lHttpCon.setDoOutput(true);
                    lHttpCon.setDoInput(true);
                    lHttpCon.setRequestProperty("Content-Type", "application/json");
                    //lHttpCon.setRequestProperty("Accept", "application/json");
                    lOut = new OutputStreamWriter(lHttpCon.getOutputStream());

                    lOut.write(lMessageCrypte);
                    lOut.flush();
                    lCode=lHttpCon.getResponseCode();
                    if(lCode==200) {
                        CHubActivity.getsLoggedUser().setEmail(lUser.getEmail());
                        CHubActivity.getsLoggedUser().setFirstName(lUser.getFirstName());
                        CHubActivity.getsLoggedUser().setName(lUser.getName());
                        CHubActivity.getsLoggedUser().setPassword(lUser.getPassword());
                        Intent lIntent = new Intent(CModifCompte.getsContext(), CHubActivity.class);
                        lIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        CModifCompte.getsContext().startActivity(lIntent);

                        //lOut.close();
                       /* lIn = new BufferedInputStream(lHttpCon.getInputStream());
                        lResponse = readStream(lIn);
                        lNewVote.setIdVote(Integer.decode(lResponse));*/
                    }
                    else
                        return lCode;

                    break;

                case delete_user:
                    lUrl = new URL(SERVER_URL+"user/"+lParams.getObject());
                    lHttpCon = (HttpURLConnection) lUrl.openConnection();
                    lHttpCon.setDoInput(true);
                    lHttpCon.setRequestMethod("DELETE");
                    lHttpCon.setRequestProperty("ID", CLoginActivity.getUniqueKey().toString());
                    //lOut.close();
                    //CSubActivity.getIntentCSubActivity().putExtra(LOGGED_USER,lNewUser);
                    lCode = lHttpCon.getResponseCode();

                    if(lCode==200){
                        Intent lIntent = new Intent(CSuppressionCompte.getContext(), CLoginActivity.class);
                        lIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        lIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        CSessionManager lSession = new CSessionManager(CSuppressionCompte.getContext());
                        lSession.logoutUser();
                        CSuppressionCompte.getContext().startActivity(lIntent);
                    }
                    else if(lCode==401){
                        return lCode;
                    }
                break;
                case get_vote:
                    lUrl = new URL(SERVER_URL+"vote/"+Integer.toString((int)lParams.getObject()));
                    lHttpCon = (HttpURLConnection) lUrl.openConnection();
                    lHttpCon.setDoInput(true);
                    lHttpCon.setRequestMethod("GET");
                    lHttpCon.setRequestProperty("Accept", "application/json");
                    lHttpCon.setRequestProperty("ID", CLoginActivity.getUniqueKey().toString());
                    lCode=lHttpCon.getResponseCode();
                    if(lCode==200) {
                        //lOut.close();
                        lIn = new BufferedInputStream(lHttpCon.getInputStream());
                        lResponse = readStream(lIn);

                        Gson lGson = new Gson();
                        CVote lGottenVote=lGson.fromJson(lResponse, CVote.class);
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
                    lHttpCon.setRequestProperty("ID", CLoginActivity.getUniqueKey().toString());
                    lHttpCon.setRequestProperty("Accept", "application/json");
                    lCode=lHttpCon.getResponseCode();
                    if(lCode==200) {
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
                case add_new_vote:
                    lUrl = new URL(SERVER_URL+"vote");
                    lHttpCon = (HttpURLConnection) lUrl.openConnection();
                    CVote lNewVote = (CVote) lParams.getObject();
                    String lJsonStringNewVote=lMapper.writeValueAsString(lNewVote);
                    JSONObject lNewVoteOBJ = new JSONObject(lJsonStringNewVote);
                    lMessageBytes=lCrypto.publicEncrypt(lNewVoteOBJ.toString(),(SecretKeySpec)lCrypto.getKey());
                    lMessageCrypte = new String(Hex.encodeHex(lMessageBytes));
                    Log.e("CREATEDVOTE",lNewVoteOBJ.toString());
                    lHttpCon.setDoOutput(true);
                    lHttpCon.setDoInput(true);
                    lHttpCon.setRequestMethod("PUT");
                    lHttpCon.setRequestProperty("ID", CLoginActivity.getUniqueKey().toString());
                    lHttpCon.setRequestProperty("Content-Type", "application/json");
                    lHttpCon.setRequestProperty("Accept", "application/json");
                    lOut = new OutputStreamWriter(lHttpCon.getOutputStream());
                    //lOut=lHttpCon.getOutputStream();
                    lOut.write(lMessageCrypte);
                    lOut.flush();
                    lCode=lHttpCon.getResponseCode();
                    if(lCode==200) {
                        CRecapVoteActivity.startIntentActivity();
                        //lOut.close();
                       /* lIn = new BufferedInputStream(lHttpCon.getInputStream());
                        lResponse = readStream(lIn);
                        lNewVote.setIdVote(Integer.decode(lResponse));*/
                    }
                    else
                        return lCode;

                    break;
                case add_choice:
                    lUrl = new URL(SERVER_URL+"choice");
                    CChoice lChoice = (CChoice)lParams.getObject();
                    lHttpCon = (HttpURLConnection) lUrl.openConnection();
                    lHttpCon.setDoOutput(true);
                    lHttpCon.setDoInput(true);
                    lHttpCon.setRequestMethod("PUT");
                    lHttpCon.setRequestProperty("ID", CLoginActivity.getUniqueKey().toString());
                    lHttpCon.setRequestProperty("Content-Type", "application/json");
                    lHttpCon.setRequestProperty("Accept", "application/json");
                    String lJSONStringChoice=lMapper.writeValueAsString(lChoice);
                    Log.e("ADDCHOICEUNIQUE",lJSONStringChoice);
                    lOut = new OutputStreamWriter(lHttpCon.getOutputStream());
                    JSONObject lChoiceJson=new JSONObject(lJSONStringChoice);
                    lMessageBytes=lCrypto.publicEncrypt(lChoiceJson.toString(),(SecretKeySpec)lCrypto.getKey());
                    lMessageCrypte = new String(Hex.encodeHex(lMessageBytes));
                    //lOut=lHttpCon.getOutputStream();
                    lOut.write(lMessageCrypte);
                    lOut.flush();
                    lCode=lHttpCon.getResponseCode();
                    if(lCode==200) {
                        Intent lIntent = new Intent(CVoteUninominal.getsContext(), CHubActivity.class);

                        lIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        lIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Log.e("TEST",lIntent.toString());
                        CVoteUninominal.getsContext().startActivity(lIntent);
                        //lOut.close();
                       /* lIn = new BufferedInputStream(lHttpCon.getInputStream());
                        lResponse = readStream(lIn);
                        lNewVote.setIdVote(Integer.decode(lResponse));*/
                    }
                    else
                        return lCode;

                    break;
                case add_choices:
                    lUrl = new URL(SERVER_URL+"choice/multiples");
                    List<CChoice> lListChoices=(List<CChoice>)lParams.getObject();
                    lHttpCon = (HttpURLConnection) lUrl.openConnection();
                    lHttpCon.setDoOutput(true);
                    lHttpCon.setDoInput(true);
                    lHttpCon.setRequestMethod("PUT");
                    lHttpCon.setRequestProperty("ID", CLoginActivity.getUniqueKey().toString());
                    lHttpCon.setRequestProperty("Content-Type", "application/json");
                    lHttpCon.setRequestProperty("Accept", "application/json");
                    String lChoicesToString=lMapper.writeValueAsString(lListChoices);
                    Log.e("ADDCHOICE",lChoicesToString);
                    lOut = new OutputStreamWriter(lHttpCon.getOutputStream());
                    JSONArray lChoicesJson=new JSONArray(lChoicesToString);
                    lMessageBytes=lCrypto.publicEncrypt(lChoicesJson.toString(),(SecretKeySpec)lCrypto.getKey());
                    lMessageCrypte = new String(Hex.encodeHex(lMessageBytes));
                    //JSONObject lChoicesJson=new JSONObject(lChoicesToString);
                    //lOut=lHttpCon.getOutputStream();
                    lOut.write(lMessageCrypte);
                    lOut.flush();
                    lCode=lHttpCon.getResponseCode();
                    if(lCode==200) {
                        if(((String)lParams.getType()).equals("rank")){
                            Intent lIntent = new Intent(CRankingVote.getsContext(), CHubActivity.class);
                            lIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            lIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            CRankingVote.getsContext().startActivity(lIntent);
                        }
                        if(((String)lParams.getType()).equals("note")){
                            Intent lIntent = new Intent(CNoteVote.getsContext(), CHubActivity.class);
                            lIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            lIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            CNoteVote.getsContext().startActivity(lIntent);
                        }
                        //lOut.close();
                       /* lIn = new BufferedInputStream(lHttpCon.getInputStream());
                        lResponse = readStream(lIn);
                        lNewVote.setIdVote(Integer.decode(lResponse));*/
                    }
                    else
                        return lCode;

                    break;
                case get_choices:
                    lUrl = new URL(SERVER_URL+"choice/all/"+Integer.toString((int)lParams.getObject()));
                    lHttpCon = (HttpURLConnection) lUrl.openConnection();
                    lHttpCon.setDoInput(true);
                    lHttpCon.setRequestMethod("GET");
                    lHttpCon.setRequestProperty("ID", CLoginActivity.getUniqueKey().toString());
                    lHttpCon.setRequestProperty("Accept", "application/json");
                    lCode=lHttpCon.getResponseCode();
                    if(lCode==200) {
                        lIn = new BufferedInputStream(lHttpCon.getInputStream());
                        lResponse = readStream(lIn);
                        lDecryptString=lCrypto.publicDecrypt(lCrypto.getKey(),Hex.decodeHex(lResponse.toCharArray()));
                        ArrayList<CChoice> lChoices = lMapper.readValue(lDecryptString, new TypeReference<ArrayList<CChoice>>(){});
                        if(((String)lParams.getType()).equals("uninominal")) {
                            CResultUninominalActivity.setChoices(lChoices);
                        }
                        else if(((String)lParams.getType()).equals("rank")) {
                            CResultRankingActivity.setChoices(lChoices);
                        }
                        else if(((String)lParams.getType()).equals("note")){
                            CResultJugementMajoritaire.setChoices(lChoices);
                        }
                        //CHubMyVotesFragment.getsIntent().putParcelableArrayListExtra("CHOICES",lChoices);
                        CHubMyVotesFragment.startActivityIntent();
                       // CHubMyVotesFragment.getIntent().addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        //CHubMyVotesFragment.getsContext().startActivity(CHubMyVotesFragment.getIntent());
                        //CVoteUninominal.setlList(lCandidates);
                    }
                    else
                        return lCode;

                    break;

                case get_candidates:
                    lUrl = new URL(SERVER_URL+"vote/"+Integer.toString((int)lParams.getObject())+"/candidats");
                    lHttpCon = (HttpURLConnection) lUrl.openConnection();
                    lHttpCon.setDoInput(true);
                    lHttpCon.setRequestMethod("GET");
                    lHttpCon.setRequestProperty("ID", CLoginActivity.getUniqueKey().toString());
                    lHttpCon.setRequestProperty("Accept", "application/json");
                    lCode=lHttpCon.getResponseCode();
                    if(lCode==200) {
                        lIn = new BufferedInputStream(lHttpCon.getInputStream());
                        lResponse = readStream(lIn);
                        lDecryptString=lCrypto.publicDecrypt(lCrypto.getKey(),Hex.decodeHex(lResponse.toCharArray()));
                        Type listType = new TypeToken<ArrayList<CCandidate>>() {}.getType();
                        ArrayList<CCandidate> lCandidates = new Gson().fromJson(lDecryptString, listType);
                        CVoteUninominal.setlList(lCandidates);
                    }
                    else
                        return lCode;

                    break;

                case add_contact:
                    lUrl = new URL(SERVER_URL+"user/contact/"+CHubActivity.getsLoggedUser().getUserId()+"/"+lParams.getObject());
                    lHttpCon = (HttpURLConnection) lUrl.openConnection();
                    lHttpCon.setRequestMethod("PUT");
                    lHttpCon.setRequestProperty("ID", CLoginActivity.getUniqueKey().toString());
                    lHttpCon.setRequestProperty("Content-Type", "application/json");
                    lHttpCon.setRequestProperty("Accept", "application/json");
                    lCode=lHttpCon.getResponseCode();
                    if(lCode==200) {
                        //lOut.close();
                        /*Intent lIntent = new Intent(CContactAjout.getsContext(),CHubActivity.class);
                        lIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        lIntent.setAction("OPEN_TAB_CONTACT");
                        CContactAjout.getsContext().startActivity(lIntent);*/
                    }
                    else
                        return lCode;

                    break;
                case delete_contact:
                    lUrl = new URL(SERVER_URL+"user/contact/"+lParams.getObject());
                    lHttpCon = (HttpURLConnection) lUrl.openConnection();
                    lHttpCon.setRequestMethod("DELETE");
                    lHttpCon.setRequestProperty("ID", CLoginActivity.getUniqueKey().toString());
                    lHttpCon.setRequestProperty("Content-Type", "application/json");
                    lHttpCon.setRequestProperty("Accept", "application/json");
                    lCode=lHttpCon.getResponseCode();
                    if(lCode==200) {
                        //lOut.close();
                       /* lIn = new BufferedInputStream(lHttpCon.getInputStream());
                        lResponse = readStream(lIn);
                        lNewVote.setIdVote(Integer.decode(lResponse));*/
                    }
                    else
                        return lCode;

                    break;
                case get_contacts:
                    lUrl = new URL(SERVER_URL+"user/contact/"+CHubActivity.getsLoggedUser().getUserId());
                    lHttpCon = (HttpURLConnection) lUrl.openConnection();
                    lHttpCon.setDoInput(true);
                    lHttpCon.setRequestMethod("GET");
                    lHttpCon.setRequestProperty("ID", CLoginActivity.getUniqueKey().toString());
                    lHttpCon.setRequestProperty("Accept", "application/json");
                    lCode=lHttpCon.getResponseCode();
                    if(lCode==200) {
                        //lOut.close();
                        lIn = new BufferedInputStream(lHttpCon.getInputStream());
                        lResponse = readStream(lIn);
                        lDecryptString=lCrypto.publicDecrypt(lCrypto.getKey(),Hex.decodeHex(lResponse.toCharArray()));
                        ArrayList<CUser> lContacts = lMapper.readValue(lDecryptString, new TypeReference<ArrayList<CUser>>(){});
                        CHubContactFragment.setsContacts(lContacts);


                    }
                    else
                        return lCode;

                    break;
                case generate_keys:
                    lUrl = new URL(SERVER_URL+"crypto");
                    CCryptoBean lCryptoBean=(CCryptoBean)lParams.getObject();
                    String lCryptoString=lMapper.writeValueAsString(lCryptoBean);
                    JSONObject lCryptoJson=new JSONObject(lCryptoString);
                    lHttpCon = (HttpURLConnection) lUrl.openConnection();
                    lHttpCon.setDoOutput(true);
                    lHttpCon.setDoInput(true);
                    lHttpCon.setRequestMethod("PUT");
                    lHttpCon.setRequestProperty("ID",Integer.toString(15128));
                    lHttpCon.setRequestProperty("Content-Type", "application/json");
                    lHttpCon.setRequestProperty("Accept", "application/json");
                    lOut = new OutputStreamWriter(lHttpCon.getOutputStream());
                    //lOut=lHttpCon.getOutputStream();
                    lOut.write(lCryptoJson.toString());
                    lOut.flush();
                    lCode=lHttpCon.getResponseCode();
                    if(lCode==200) {
                        //lOut.close();
                        lIn = new BufferedInputStream(lHttpCon.getInputStream());
                        lResponse = readStream(lIn);
                        BigInteger lKey=new BigInteger(lResponse);
                        lCrypto.receiveKeyParam(lKey);
                    }
                    else
                        return lCode;

                    break;

                case add_results:
                    lUrl = new URL(SERVER_URL+"result");
                    List<CResult> lListResults=(List<CResult>)lParams.getObject();
                    lHttpCon = (HttpURLConnection) lUrl.openConnection();
                    lHttpCon.setDoOutput(true);
                    lHttpCon.setDoInput(true);
                    lHttpCon.setRequestMethod("PUT");
                    lHttpCon.setRequestProperty("ID", CLoginActivity.getUniqueKey().toString());
                    lHttpCon.setRequestProperty("Content-Type", "application/json");
                    lHttpCon.setRequestProperty("Accept", "application/json");
                    String lReTest=lMapper.writeValueAsString(lListResults.get(0));
                    String lResultsToString=lMapper.writeValueAsString(lListResults);
                    lOut = new OutputStreamWriter(lHttpCon.getOutputStream());
                    JSONArray lResultsJson=new JSONArray(lResultsToString);
                    lMessageBytes=lCrypto.publicEncrypt(lResultsJson.toString(),(SecretKeySpec)lCrypto.getKey());
                    lMessageCrypte = new String(Hex.encodeHex(lMessageBytes));
                    //JSONObject lChoicesJson=new JSONObject(lChoicesToString);
                    //lOut=lHttpCon.getOutputStream();
                    lOut.write(lMessageCrypte);
                    lOut.flush();
                    lCode=lHttpCon.getResponseCode();
                    if(lCode==200) {

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
        } catch (DecoderException e) {
            e.printStackTrace();
        }
        return null;

    }


    public void onPostExecute(Integer pCode){
        if(pCode!=null) {
            if (pCode == 401)
                Toast.makeText(CLoginActivity.getsContext(), "Mauvais login/mot de passe", Toast.LENGTH_SHORT).show();
            if(pCode==409)
                Toast.makeText(CSubActivity.getsContext(), "L'élément existe déjà", Toast.LENGTH_SHORT).show();
            if(pCode==455)
                Toast.makeText(CModifCompte.getsContext(), "L'élément existe déjà", Toast.LENGTH_SHORT).show();
            if(pCode==456)
                Toast.makeText(CContactAjout.getsContext(), "Ce contact n'existe pas dans la BDD", Toast.LENGTH_SHORT).show();
        }
    }
    //startActivity(mIntent);


    public static String readStream(InputStream is) {
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
