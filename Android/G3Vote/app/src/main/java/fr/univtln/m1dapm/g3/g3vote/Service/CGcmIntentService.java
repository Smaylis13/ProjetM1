package fr.univtln.m1dapm.g3.g3vote.Service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import fr.univtln.m1dapm.g3.g3vote.Interface.CHubActivity;
import fr.univtln.m1dapm.g3.g3vote.R;
import fr.univtln.m1dapm.g3.g3vote.Receiver.CGcmBroadcastReceiver;

/**
 * Created by lyamsi on 18/05/15.
 */
public class CGcmIntentService extends IntentService {
    public static final String TAG = "GCM_TEST";
    public static final String CLEAR_NOTIFICATION = "fr.univtln.m1dapm.CLEAR_NOTIFICATION";
    public static final String NOTIFICATION = "fr.univtln.m1dapm.NOTIFICATION";
    public static final int NOTIFICATION_ID = 1;
    private NotificationManager mNotificationManager;
    NotificationCompat.Builder builder;

    public CGcmIntentService()
    {
        super("CGcmIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);

        String messageType = gcm.getMessageType(intent);

        if (!extras.isEmpty()){
            if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)){
                sendNotification("Send error: " + extras.toString(), null);
            }
            else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)){
                sendNotification("Deleted messages on server: " + extras.toString(), null);
            }
            else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)){
                // Post notification of received message.
                String message = ((intent.getExtras() == null) ? "Empty Bundle" : intent.getExtras()
                        .getString("message"));
                if (intent.getExtras() != null
                        && CLEAR_NOTIFICATION.equals(intent.getExtras()
                        .getString("action"))){
                    clearNotification();
                }
                else{
                    sendNotification(message, extras);
                }
                Log.i(TAG, "Received: " + message);
            }
        }
        CGcmBroadcastReceiver.completeWakefulIntent(intent);
    }

    /**
     * @param msg
     * @param extras
     */
    private void sendNotification(String msg, Bundle extras)
    {
        mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(this, CHubActivity.class).setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);


        if (extras != null)
        {
            intent.putExtras(extras);
            intent.setAction(NOTIFICATION);
        }
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("EVS")
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                        .setContentText(msg)
                        .setTicker(msg)
                        .setAutoCancel(true)
                        .setSound(Settings.System.DEFAULT_NOTIFICATION_URI);

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }

    /**
     * Remove the app's notification
     */
    private void clearNotification()
    {
        mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancel(NOTIFICATION_ID);
    }

}