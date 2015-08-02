package net.fabricemk.android.mycv.tools;

import android.content.Context;

public class AndroidTools {

    public static int getDrawableIdFromName(Context ctxt, String drawableName) {
        return ctxt.getResources().getIdentifier(drawableName , "drawable", ctxt.getPackageName());
    }
}
