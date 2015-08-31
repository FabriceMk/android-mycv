package net.fabricemk.android.mycv.tools;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

public class AndroidTools {

    public static boolean isOnline(Context ctxt) {
        ConnectivityManager connMgr = (ConnectivityManager)
                ctxt.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    public static void launchWirelessSettings(Context ctxt) {
        Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctxt.startActivity(intent);
    }

    public static int getDrawableIdFromName(Context ctxt, String drawableName) {
        return ctxt.getResources().getIdentifier(drawableName , "drawable", ctxt.getPackageName());
    }
}
