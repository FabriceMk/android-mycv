package net.fabricemk.android.mycv.services;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import net.fabricemk.android.mycv.R;
import net.fabricemk.android.mycv.tools.CommunicationTools;
import net.fabricemk.android.mycv.tools.StringTools;

public class ListenerServiceFromWear extends WearableListenerService {

    private static final String SEND_EMAIL_WEAR_PATH = "/send-email-wear";
    private static final String GO_WEBSITE_WEAR_PATH = "/go-website-wear";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {

        /*
         * Receive the message from wear
         */
        if (messageEvent.getPath().equals(GO_WEBSITE_WEAR_PATH)) {
            CommunicationTools.launchURL(this, getString(R.string.my_site));
        }

        if (messageEvent.getPath().equals(SEND_EMAIL_WEAR_PATH)) {
            CommunicationTools.sendEmail(this, StringTools.buildMailAddress(this), "");
        }

    }

}
