package eu.forcom.android.publiccore.util;

import android.util.Log;

public class SleepUtil {
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Log.e(TAG ,"Error sleeping in SleepUtil.sleep()");
        }
    }

    private final static String TAG = "SleepUtil";
}
