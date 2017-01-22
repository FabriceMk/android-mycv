package net.fabricemk.android.mycv.tools;

import android.content.Context;
import android.content.Intent;
import android.support.wearable.activity.ConfirmationActivity;

/**
 * A utility class related to UI
 */
public class UITools {

    /**
     * Launches a confirmation animation with a message
     * @param ctxt an Android context
     * @param message the confirmation message
     */
    public static void launchConfirmationAnimation(Context ctxt, String message) {
        Intent intent = new Intent(ctxt, ConfirmationActivity.class);
        intent.putExtra(ConfirmationActivity.EXTRA_ANIMATION_TYPE,
                ConfirmationActivity.OPEN_ON_PHONE_ANIMATION);
        intent.putExtra(ConfirmationActivity.EXTRA_MESSAGE,
                message);
        ctxt.startActivity(intent);
    }
}
