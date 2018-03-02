package jmyu.ufl.edu.mytodolist.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.Date;

import jmyu.ufl.edu.mytodolist.AlarmReceiver;

/**
 * Created by jmyu on 3/2/18.
 */

public class AlarmUtils {
    public static void setAlarm(@NonNull Context context, @NonNull Date date){
        Calendar c = Calendar.getInstance();
        if (date.compareTo(c.getTime()) < 0){
            return;
        }
        AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + 60 * 1000,
                alarmIntent);
    }
}
