package fr.univtln.m1dapm.g3.g3vote.Interface;

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
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

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

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chub);

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        Intent lIntent=getIntent();
        sLoggedUser=(CUser)lIntent.getSerializableExtra(CCommunication.LOGGED_USER);
        CTaskParam lParams=new CTaskParam(CRequestTypesEnum.get_votes,sLoggedUser.getUserId());
        CVotesAsync lVotesAsc=new CVotesAsync();
        lVotesAsc.execute(lParams);

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
    }


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

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }

    //handle the clik on the validate button
    public void validateVoteType(View view) {
        Spinner spin = (Spinner)findViewById(R.id.voteTypeList);
        final EditText lET_VoteName = (EditText)findViewById(R.id.voteNameInput);
        final Button lB_DateDebut = (Button)findViewById(R.id.bVoteDateBegin);
        final Button lB_DateFin = (Button)findViewById(R.id.bVoteDateEnd);
        final String lVoteName = lET_VoteName.getText().toString();
        final String lDateDebut=lB_DateDebut.getText().toString();
        final String lDateFin=lB_DateFin.getText().toString();


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
            default:
                break;
        }
        lIntent.putExtra("VOTE_NAME",lVoteName);
        lIntent.putExtra("START_DATE",lDateDebut);
        lIntent.putExtra("END_DATE",lDateFin);
        startActivity(lIntent);
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
                        Log.e("TEST CODE","CODE: "+lCode);
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