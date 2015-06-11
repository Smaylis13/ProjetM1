package fr.univtln.m1dapm.g3.g3vote.Entite;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

import fr.univtln.m1dapm.g3.g3vote.Interface.CLoginActivity;

/**
 * Created by chris on 10/06/15.
 */
public class CSessionManager {
    // Shared Preferences
    SharedPreferences mPref;

    // Editor for Shared preferences
    SharedPreferences.Editor mEditor;

    // Context
    Context mContext;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "EVSPref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_ID = "name";

    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";

    //password
    public static final String KEY_MDP = "mdp";

    public static final String KEY_PRENOM = "prenom";
    public static final String KEY_NOM = "nom";

    // Constructor
    public CSessionManager(Context pContext) {
        this.mContext = pContext;
        mPref = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        mEditor = mPref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(int pId, String pMail, String pPrenom, String pNom){
        // Storing login value as TRUE
        mEditor.putBoolean(IS_LOGIN, true);

        // Storing id in pref
        mEditor.putInt(KEY_ID, pId);
        mEditor.putString(KEY_EMAIL, pMail);
        mEditor.putString(KEY_NOM,pNom);
        mEditor.putString(KEY_PRENOM,pPrenom);

        // commit changes
        mEditor.commit();
    }

    /**
     * Get stored session data
     * */
    public CUser getUser(){
        CUser lUser = new CUser();
        // user name
        lUser.setEmail(mPref.getString(KEY_EMAIL, null));
        lUser.setName(mPref.getString(KEY_NOM,null));
        lUser.setFirstName(mPref.getString(KEY_PRENOM,null));
        lUser.setUserId(mPref.getInt(KEY_ID,-1));

        // return user
        return lUser;
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(mContext, CLoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            // Add new Flag to start new Activity
            //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            mContext.startActivity(i);
        }

    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        mEditor.clear();
        mEditor.putBoolean(IS_LOGIN,false);
        mEditor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(mContext, CLoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        mContext.startActivity(i);
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return mPref.getBoolean(IS_LOGIN, false);
    }
}

