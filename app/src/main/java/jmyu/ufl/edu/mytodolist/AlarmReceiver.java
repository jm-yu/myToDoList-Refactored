package jmyu.ufl.edu.mytodolist;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import jmyu.ufl.edu.mytodolist.models.Todo;

/**
 * Created by jmyu on 3/2/18.
 */

public class AlarmReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        final String CHANNEL_ID = "jmyu.ufl.edu.mytodolist.alert";
        final int notificationId = 100;
        Todo todo = intent.getParcelableExtra(TodoEditActivity.KEY_TODO);

        NotificationHelper noti = new NotificationHelper(context);


        Intent resultIntent = new Intent(context, TodoEditActivity.class);
        resultIntent.putExtra(TodoEditActivity.KEY_TODO, todo);
        resultIntent.putExtra(TodoEditActivity.KEY_NOTIFICATION_ID, notificationId);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(context,
                0,
                resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder nb = noti.getNotification1(TodoEditActivity.KEY_TODO, todo.text).setContentIntent(resultPendingIntent);

        // notificationId allows you to update the notification later on, like canceling it
        noti.notify(notificationId, nb);


    }
}
