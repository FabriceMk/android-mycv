package net.fabricemk.android.mycv.tools;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;


public class CommunicationTools {

    public static void sendEmail(Context ctxt, String recipient, String message) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", recipient, null));
        ctxt.startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }

    public static void launchURL(Context ctxt, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url));
        ctxt.startActivity(intent);
    }

    public static void launchLinkedIn(Context ctxt, String linkedInID) {
        String url = "http://www.linkedin.com/profile/view?id=" + linkedInID;
        launchURL(ctxt, url);
    }

    public static void launchGooglePlus(Context ctxt, String googlePlusID) {
        String url = "https://plus.google.com/+" + googlePlusID;
        launchURL(ctxt, url);
    }

    public static void launchGithub(Context ctxt, String githubID) {
        String url = "https://github.com/" + githubID;
        launchURL(ctxt, url);
    }

    /**
     * Add a contact through email address with some informations like name pre-set.
     * This operation does not force the contact creation by default and prompt the user.
     *
     * @param ctxt
     * @param name
     * @param email
     */
    public static void addAsContact(Context ctxt, String name, String email) {
        Intent intent = new Intent(
                ContactsContract.Intents.SHOW_OR_CREATE_CONTACT,
                Uri.parse("mailto:" + email));

        intent.putExtra(ContactsContract.Intents.Insert.NAME, name);

        ctxt.startActivity(intent);
    }
}
