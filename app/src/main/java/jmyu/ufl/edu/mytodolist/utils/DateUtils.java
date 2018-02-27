package jmyu.ufl.edu.mytodolist.utils;

import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jmyu on 2/18/18.
 */

public class DateUtils {
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd HH:mm", Locale.getDefault());
    private static DateFormat timeFormatDate = new SimpleDateFormat("EEE, MMM dd, yyyy", Locale.getDefault());
    private static DateFormat timeFormatTime = new SimpleDateFormat("HH:MM", Locale.getDefault());


    @NonNull
    public static Date stringToDate(@NonNull String string) {
        try {
            return dateFormat.parse(string);
        } catch (ParseException e) {
            return Calendar.getInstance().getTime();
        }
    }

    @NonNull
    public static String timeToString(@NonNull Date date) {
        return dateFormat.format(date);
    }
    public static String timeToStringDate(@NonNull Date date) {
        return timeFormatDate.format(date);
    }
    public static String timeToStringTime(@NonNull Date date) {
        return timeFormatTime.format(date);
    }


}
