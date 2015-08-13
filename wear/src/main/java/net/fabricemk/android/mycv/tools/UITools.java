package net.fabricemk.android.mycv.tools;

import android.content.Context;
import android.content.Intent;
import android.support.wearable.activity.ConfirmationActivity;

import net.fabricemk.android.mycv.R;

public class UITools {

    public static void launchConfirmationAnimation(Context ctxt, String message) {
        Intent intent = new Intent(ctxt, ConfirmationActivity.class);
        intent.putExtra(ConfirmationActivity.EXTRA_ANIMATION_TYPE,
                ConfirmationActivity.OPEN_ON_PHONE_ANIMATION);
        intent.putExtra(ConfirmationActivity.EXTRA_MESSAGE,
                message);
        ctxt.startActivity(intent);

    }
}
