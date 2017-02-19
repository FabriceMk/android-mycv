package net.fabricemk.android.mycv.tools;

import android.content.Context;

import net.fabricemk.android.mycv.R;

/**
 * Tools related to string manipulation
 */
public class StringTools {

    public static String buildMailAddress(Context ctxt) {
        return ctxt.getString(R.string.my_mail) + "@" + ctxt.getString(R.string.my_mail_provider);
    }
}
