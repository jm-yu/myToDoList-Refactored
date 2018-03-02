package jmyu.ufl.edu.mytodolist.utils;

import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.widget.TextView;

/**
 * Created by jmyu on 3/1/18.
 */

public class UIUtils {
    public static void setTextViewStrikeThrough(@NonNull TextView tv, boolean strikeThrough) {
        if (strikeThrough) {
            // strike through effect on the text
            tv.setPaintFlags(tv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            // no strike through effect
            tv.setPaintFlags(tv.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }
}
