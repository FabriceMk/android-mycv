package net.fabricemk.android.mycv.tools;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

/**
 * Misc Tools related to the Android framework
 */
public class AndroidTools {

    /**
     * Checks if the device has an Internet connection
     * @param ctxt context
     * @return true if connected to Internet, false otherwise
     */
    public static boolean isOnline(Context ctxt) {
        ConnectivityManager connMgr = (ConnectivityManager)
                ctxt.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    /**
     * Launches the devices Settings/Wireless network activity
     * @param ctxt context
     */
    public static void launchWirelessSettings(Context ctxt) {
        Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctxt.startActivity(intent);
    }

    /**
     * Gets a drawable from its name (should be a resource)
     * @param ctxt context
     * @param drawableName the drawable name
     * @return the resource ID
     */
    public static int getDrawableIdFromName(Context ctxt, String drawableName) {
        return ctxt.getResources().getIdentifier(drawableName , "drawable", ctxt.getPackageName());
    }
}
