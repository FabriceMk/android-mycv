package net.fabricemk.android.mycv.tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class AndroidTools {

    public static boolean isOnline(Context ctxt) {
        ConnectivityManager connMgr = (ConnectivityManager)
                ctxt.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    public static int getDrawableIdFromName(Context ctxt, String drawableName) {
        return ctxt.getResources().getIdentifier(drawableName , "drawable", ctxt.getPackageName());
    }
}
