package edu.scu.c16drawing;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Rushitaa on 2/12/2015.
 */
public class MyReceiver extends BroadcastReceiver {

    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
//        Bundle bundle = intent.getExtras();
//        Object[] messages = (Object[]) bundle.get("pdus");
//        SmsMessage message = SmsMessage.createFromPdu((byte[]) messages[0]);
//        Log.d("MYRECEIVER", message.getMessageBody() + " from " +
//        message.getOriginatingAddress());
//        Intent i = new Intent();
//        i.setClassName("edu.scu.c16drawing", "edu.scu.c16drawing.MainActivity");
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(i);000000000
        int requestCode = 0;
        int flags = 0;
        Intent intent1 = new Intent(context, PaintActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, requestCode, intent1, flags);
        int id = 12345;
        Notification n  = new Notification.Builder(context)
                .setContentTitle("New Notification")
                .setContentText("Lets Draw!")
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .getNotification();

        NotificationManager notificationManager =
                (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);

        notificationManager.notify(id, n);



    }


}



