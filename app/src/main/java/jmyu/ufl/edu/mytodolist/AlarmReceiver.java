package jmyu.ufl.edu.mytodolist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by jmyu on 3/2/18.
 */

public class AlarmReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("alarm", "11111111111111");
        Toast.makeText(context, "123", Toast.LENGTH_LONG).show();

    }
}
