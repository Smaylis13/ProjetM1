package fr.univtln.m1dapm.g3.g3vote.Receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by lyamsi on 18/05/15.
 */
public class CNetworkReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            Intent serviceIntent = new Intent("fr.univtln.m1dapm.g3.g3vote.Service.CNotificationService");
            context.startService(serviceIntent);
        }

        ConnectivityManager conn =  (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conn.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.getDetailedState() == NetworkInfo.DetailedState.CONNECTED) {
            Log.i("EECS780", "connected");
            Intent serviceIntent = new Intent("fr.univtln.m1dapm.g3.g3vote.Service.CNotificationService");
            context.startService(serviceIntent);
        }
        else if(networkInfo != null){
            NetworkInfo.DetailedState state = networkInfo.getDetailedState();
            Log.i("EECS780", state.name());
        }
        else {
            Log.i("EECS780", "lost connection");
            AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
            /*PendingIntent operation = PendingIntent.getService(context, 0, PushService.pingIntent(context), PendingIntent.FLAG_NO_CREATE);
            if(operation != null){
                am.cancel(operation);
                operation.cancel();
            }
            context.startService(PushService.closeIntent(context));
        */}
    }
    }
}
