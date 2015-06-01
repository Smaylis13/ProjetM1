package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.content.Context;

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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.java_websocket.client.WebSocketClient;

import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import fr.univtln.m1dapm.g3.g3vote.Communication.CCommunication;
import fr.univtln.m1dapm.g3.g3vote.Communication.CRequestTypesEnum;
import fr.univtln.m1dapm.g3.g3vote.Communication.CTaskParam;
import fr.univtln.m1dapm.g3.g3vote.Entite.CUser;
import fr.univtln.m1dapm.g3.g3vote.Entite.CVote;
import fr.univtln.m1dapm.g3.g3vote.R;
import fr.univtln.m1dapm.g3.g3vote.Service.CGcmIntentService;

public class CHubActivity extends AppCompatActivity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;
    private static CUser sLoggedUser;

    public static CUser getsLoggedUser() {
        return sLoggedUser;
    }

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    // issma
    public static final String PREFS_PROPERTY_REG_ID = "registration_id";
    public static final String GCM_TAG = "GCM_TAG";
    public static final long GCM_TIME_TO_LIVE = 60L * 60L * 24L * 7L * 4L; // 4 Weeks
    public static final String PROPERTY_APP_VERSION = "appVersion";
    public static final String PREFS_NAME = "EVS";
    public static final String GCM_SENDER_ID  = "565177955471";//"730047535014";
    public static final String REGISTER = "fr.univtln.m1dapm.REGISTER";
    private String mMail = "test@gmail.com";
    private GoogleCloudMessaging mGcm;
    private String mRegid;
    private Context mContext;
    private AtomicInteger msgId = new AtomicInteger();
    private WebSocketClient mWebSocketClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chub);

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        Intent lIntent=getIntent();
  /*      sLoggedUser=(CUser)lIntent.getSerializableExtra(CCommunication.LOGGED_USER);
        CTaskParam lParams=new CTaskParam(CRequestTypesEnum.get_votes,sLoggedUser.getUserId());
        CVotesAsync lVotesAsc=new CVotesAsync();
        lVotesAsc.execute(lParams);
*/
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }

        // issma
       // mMail=sLoggedUser.getEmail();

        mGcm = GoogleCloudMessaging.getInstance(this);

		mContext = getApplicationContext();
		mRegid = getRegistrationId();
		if(mRegid.equals("")){
			registerInBackground();
		}

		// Handle possible notification intent if app was not running
		handleNotification(getIntent().getExtras());
    }
    /**
     * If this activity was started or brought to the front using an intent from a notification type
     * GCM message inform other devices the message was handled
     * @param extras Extras bundle from incoming intent
     */
    private void handleNotification(Bundle extras){
		if(extras != null && extras.containsKey("action")
			&& extras.containsKey("notification_key")
			&& CGcmIntentService.NOTIFICATION.equalsIgnoreCase(extras.getString("action"))) {
			// Send a notification clear message upstream to clear on other devices
			sendClearMessage(extras.getString("notification_key"));
		}
    }
    /**
     * Upstream a GCM message letting other devices know to clear the notification as
     * it has been handled on this device
     * @param notification_key The GCM registered notification key for the user's devices
     */
    private void sendClearMessage(String notification_key){
		new AsyncTask<String, Void, String>(){
			@Override
			protected String doInBackground(String... params){
				String msg = "";
				try	{
					Bundle data = new Bundle();
					data.putString("action", CGcmIntentService.CLEAR_NOTIFICATION);
					String id = Integer.toString(msgId.incrementAndGet());
					mGcm.send(params[0], id, data);
					msg = "Sent notification clear message";
                    Log.i(GCM_TAG, "Registration not found.id" );
				}
				catch (IOException ex){
					msg = "Error :" + ex.getMessage();
                    Log.i(GCM_TAG,msg);
				}
					return msg;
			}
		}.execute(notification_key);

    }

    /**
     * Gets the current registration ID for application on GCM service, if there
     * is one.
     * <p>
     * If result is empty, the app needs to register.
     *
     * @return registration ID, or empty string if there is no existing
     *         registration ID.
     */
    private String getRegistrationId(){
		final SharedPreferences prefs = getGcmPreferences(mContext);
		String registrationId = prefs.getString(PREFS_PROPERTY_REG_ID, "");
		if (registrationId == null || registrationId.equals("")){
			Log.i(GCM_TAG, "Registration not found.");
			return "";
		}
		// Check if app was updated; if so, it must clear the registration ID
		// since the existing regID is not guaranteed to work with the new
		// app version.
		int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
		int currentVersion = getAppVersion(mContext);
		if (registeredVersion != currentVersion){
			return "";
		}
		return registrationId;
    }

    /**
     * @return Application's {@code SharedPreferences}.
     * @param context
     */
    private SharedPreferences getGcmPreferences(Context context){
        // This sample app persists the registration ID in shared preferences,
        return getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }
    /**
     * @return Application's version code from the {@code PackageManager}.
     */
    private static int getAppVersion(Context context){
		try{
			PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			return packageInfo.versionCode;
		}catch (PackageManager.NameNotFoundException e){
			// should never happen
			throw new RuntimeException("Could not get package name: " + e);
		}
    }
    /**
     * Registers the application with GCM servers asynchronously.
     * <p>
     * Stores the registration ID and the app versionCode in the application's
     * shared preferences.
     */
    private void registerInBackground(){
        new AsyncTask<Void, Void, String>()
        {
            @Override
            protected String doInBackground(Void... params){
                String msg = "";
                try{
                    if (mGcm == null){
                        mGcm = GoogleCloudMessaging.getInstance(mContext);
                    }
                    mRegid = mGcm.register(GCM_SENDER_ID);
                    msg = "Device registered, registration ID=" + mRegid;
                    // You should send the registration ID to your server over
                    // HTTP, so it can use GCM/HTTP or CCS to send messages to your app.
                    sendRegistrationIdToBackend();
                    // For this demo: we use upstream GCM messages to send the
                    // registration ID to the 3rd party server
                    // Persist the regID - no need to register again.
                    storeRegistrationId(mContext, mRegid);
                }
                catch (IOException ex){
                    msg = "Error :" + ex.getMessage();
                    Log.i(GCM_TAG,msg);
                    // If there is an error, don't just keep trying to register.
                    // Require the user to click a button again, or perform
                    // exponential back-off.
                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg){
                //Send vers le serveur
                //sendToServer("register_id:"+mRegid);
                Log.i(GCM_TAG,msg);
            }
        }.execute(null, null, null);
    }
    /**
     * Sends the registration ID to the 3rd party server via an upstream
     * GCM message. Ideally this would be done via HTTP to guarantee success or failure
     * immediately, but it would require an HTTP endpoint.
     */
    private void sendRegistrationIdToBackend(){
        new AsyncTask<String, Void, String>()
        {
            @Override
            protected String doInBackground(String... params){
                String msg = "";
                try	{
                    Bundle data = new Bundle();
                    data.putString("name", params[0]);
                    data.putString("action", REGISTER);
                    String id = Integer.toString(msgId.incrementAndGet());
                    mGcm.send(GCM_SENDER_ID + "@gcm.googleapis.com", id,data);
                    msg = "Sent registration";
                }catch (IOException ex)	{
                    msg = "Error :" + ex.getMessage();
                }
                return msg;
            }
        }.execute(mMail);
    }
    /**
     * Stores the registration ID and the app versionCode in the application's
     * {@code SharedPreferences}.
     *
     * @param context
     *            application's context.
     * @param regId
     *            registration ID
     */
    private void storeRegistrationId(Context context, String regId){
        final SharedPreferences prefs = getGcmPreferences(context);
        int appVersion = getAppVersion(context);
        Log.i(GCM_TAG, "Saving regId on app version " + appVersion);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PREFS_PROPERTY_REG_ID, regId);
        editor.putInt(PROPERTY_APP_VERSION, appVersion);
        editor.commit();
    }

    /*---------------------------------------------------------------------------------------------*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chub, menu);
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

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }



    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        //ICI pour l'interface des différent fragments
        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position){
                case 0:
                    return CHubMyVotesFragment.newInstance(position);
                case 1:
                    return CHubCreateVoteFragment.newInstance(position + 1);
                case 2:
                    return CHubContactFragment.newInstance(position + 1);
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        //Tab titles
        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    //handle click on button below text date fin
    public void showDateEndPickerDialog(View view) {
        android.support.v4.app.DialogFragment newFragment = new CDateEndPickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    //handle click on button below text date debut
    public void showDateBeginPickerDialog(View view) {
        android.support.v4.app.DialogFragment newFragment = new CDateBeginPickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static java.util.Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance(Locale.FRANCE);
        calendar.set(year, month, day);

        return calendar.getTime();
    }

    //handle the clik on the validate button
    public void validateVoteType(View view) throws ParseException {
        Spinner spin = (Spinner)findViewById(R.id.voteTypeList);
        final EditText lET_VoteName = (EditText)findViewById(R.id.voteNameInput);
        final Button lB_DateDebut = (Button)findViewById(R.id.bVoteDateBegin);
        final Button lB_DateFin = (Button)findViewById(R.id.bVoteDateEnd);
        final String lVoteName = lET_VoteName.getText().toString();
        final String lDateDebut=lB_DateDebut.getText().toString();
        final String lDateFin=lB_DateFin.getText().toString();

        Date lDateDeDebut=new Date();
        Date lDateDeFin=new Date();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (!lDateDebut.equals("Choix date")){
            lDateDeDebut = dateFormat.parse(lDateDebut);
        }
        if (!lDateFin.equals("Choix date")){
            lDateDeFin = dateFormat.parse(lDateFin);
        }

        Log.i("valeur boutons","le début: "+lDateDebut+" la fin :"+lDateFin);

        int test = spin.getSelectedItemPosition();
        Intent lIntent=null;
        switch (test) {
            case 0://STV
                lIntent = new Intent(this, CVoteConfSTV.class);
                break;
            case 1://Kemeny-young
                lIntent = new Intent(this, CVoteConfKemenyYoung.class);
                break;
            case 2://Jugement Majoritaire
                lIntent = new Intent(this, CVoteConfMajorityJugement.class);
                break;
            case 3://uninominal à 1 tour
                lIntent = new Intent(this, CVoteConfUninominalOneTurn.class);
                break;
            case 4://uninominal à 2 tour
                lIntent = new Intent(this, CVoteConfUninominalTwoTurn.class);
                break;
            case 5://Condorcet
                lIntent = new Intent(this, CVoteConfCondorcet.class);
                break;
            case 6://Borda
                lIntent = new Intent(this, CVoteConfBorda.class);
                break;
            default:
                break;
        }

        if(lVoteName.isEmpty()){
            Toast.makeText(this, getString(R.string.validateVoteTypeETempty), Toast.LENGTH_SHORT).show();
        }
        else if (lDateDebut.equals("Choix date")){
            Toast.makeText(this,getString(R.string.dateBeginEmpty),Toast.LENGTH_SHORT).show();
        }
        else if (lDateFin.equals("Choix date")){
            Toast.makeText(this,getString(R.string.dateEndEmpty),Toast.LENGTH_SHORT).show();
        }
        else if(lDateDeDebut.compareTo(lDateDeFin)>0) {
            Toast.makeText(this,getString(R.string.dateBeginAfterDateEnd),Toast.LENGTH_SHORT).show();
        }
        else{
            lIntent.putExtra("VOTE_NAME",lVoteName);
            lIntent.putExtra("START_DATE",lDateDebut);
            lIntent.putExtra("END_DATE",lDateFin);
            startActivity(lIntent);
        }


    }

    public class CVotesAsync extends AsyncTask<Object, Void, Integer> {
        public static final String SERVER_URL = "http://10.21.205.16:80/";
        private final ProgressDialog mDialog = new ProgressDialog(CHubActivity.this);

        @Override
        protected Integer doInBackground(Object... pObject) {
            URL lUrl = null;
            OutputStreamWriter lOut=null;
            HttpURLConnection lHttpCon = null;
            InputStream lIn = null;
            String lResponse=null;
            int lCode;
            CTaskParam lParams=(CTaskParam)pObject[0];

            try {
                switch (lParams.getRequestType()) {
                    case get_votes:
                        lUrl = new URL(SERVER_URL+"vote/all/"+Integer.toString((int)lParams.getObject()));
                        lHttpCon = (HttpURLConnection) lUrl.openConnection();
                        lHttpCon.setDoInput(true);
                        lHttpCon.setRequestMethod("GET");
                        lHttpCon.setRequestProperty("Accept", "application/json");
                        lCode=lHttpCon.getResponseCode();
                        if(lCode==200) {
                            //lOut.close();
                            lIn = new BufferedInputStream(lHttpCon.getInputStream());
                            lResponse = readStream(lIn);
                            Type listType = new TypeToken<ArrayList<CVote>>() {}.getType();
                            //ArrayList<CVote> lVotes = new Gson().fromJson(lResponse, listType);
                            ObjectMapper lMapper=new ObjectMapper();
                            ArrayList<CVote> lVotes = lMapper.readValue(lResponse, new TypeReference<ArrayList<CVote>>(){});
                            Message lMsg=new Message();
                            lMsg.what=0;
                            lMsg.obj=lVotes;
                            mHandler.sendMessage(lMsg);

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
        }catch (IOException e) {
            Log.e("CCommunication", e.toString());
        }
        return null;
        }

        @Override
        protected void onPreExecute() {
            mDialog.setMessage("Getting votes...");
            mDialog.setCancelable(false);
            mDialog.show();

        }

        public void onPostExecute(Integer pCode){
            mDialog.cancel();
        }

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

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message pMsg) {
            switch (pMsg.what) {
                case 0:
                    CHubMyVotesFragment.getInstance().setmVotes((List<CVote>)pMsg.obj);
                    break;
                default:
                    break;
            }
        }
    };

}
