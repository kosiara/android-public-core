package eu.forcom.android.publiccore.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.format.DateFormat;
import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Utility for common Android tasks.
 */
public class AndroidUtil {

    /* Configuration.SCREENLAYOUT_SIZE_LARGE; Configuration.SCREENLAYOUT_SIZE_NORMAL; Configuration.SCREENLAYOUT_SIZE_SMALL; */
    public static int getScreenSize(Context context) {
        return context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK;
    }

    public static Bitmap loadBitmapFromResource(Context context, int id) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 3;

            return BitmapFactory.decodeResource(context.getResources(), id, options);
        } catch (Exception ex) {
            Log.e(TAG, String.format("Loading bitmap from resource with id '%s' failed. ", ex));
        }

        return null;
    }

    public static String getCurrentDateTime() {
        DateFormat df = new DateFormat();
        return df.format("yyyy-MM-dd hh:mm:ss", new java.util.Date()).toString();
    }

    public static String getVersionName(Activity activity) {
        try {
            PackageInfo pInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);
            return pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Getting package version failed " + e.toString());
        }

        return "";
    }

    public static String getCompilationDateTime(Activity activity) {
        try{
            ApplicationInfo ai = activity.getPackageManager().getApplicationInfo(activity.getPackageName(), 0);
            ZipFile zf = new ZipFile(ai.sourceDir);
            ZipEntry ze = zf.getEntry("classes.dex");
            long time = ze.getTime();
            String d = SimpleDateFormat.getInstance().format(new java.util.Date(time));

            zf.close();
            return d;
        } catch (Exception e){
            Log.e(TAG, "Getting compilation date/time failed " + e.toString());
        }

        return "";
    }

    private final static String TAG = "AndroidUtil";
}
