package eu.forcom.android.publiccore.util;

import android.util.Log;

public class ICMPUtil {

    /**
     * Executes single ping and return
     * @param ip - address to ping
     * @return true - ping was successful
     */
    public static boolean ping(String ip) {
        Runtime runtime = Runtime.getRuntime();

        try {
            Process process = runtime.exec("/system/bin/ping -c 1 " + ip);
            return process.waitFor() == 0;

        } catch (Exception ignore) {
            Log.e(TAG, "Error while", ignore);
        }

        return false;
    }

    private final static String TAG = "ICMPUtil";

}
