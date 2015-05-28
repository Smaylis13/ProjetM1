package fr.univtln.m1dapm.g3.g3vote.Receiver;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import fr.univtln.m1dapm.g3.g3vote.Service.CGcmIntentService;

/**
 * Created by lyamsi on 18/05/15.
 */
public class CGcmBroadcastReceiver extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

    try {
      JSONObject json = new JSONObject(intent.getExtras().getString("com.parse.Data"));

      String notificationText = json.getString("alert");
        Log.i("RECEIVE", "JSON : "+notificationText);

    }catch(JSONException e){
        Log.i("RECEIVE", "JSONException: " + e.getMessage());
    }

        ComponentName comp = new ComponentName(context.getPackageName(),
                CGcmIntentService.class.getName());


        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);
    }
}