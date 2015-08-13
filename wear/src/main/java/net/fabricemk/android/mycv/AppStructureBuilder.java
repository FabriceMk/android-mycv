package net.fabricemk.android.mycv;

import android.app.Fragment;
import android.content.Context;
import android.support.wearable.view.CardFragment;
import android.view.Gravity;

import net.fabricemk.android.mycv.fragments.ImageFragment;
import net.fabricemk.android.mycv.fragments.SendMessageToMobileFragment;

import java.util.ArrayList;
import java.util.List;

public class AppStructureBuilder {

    private static final float MAXIMUM_CARD_EXPANSION_FACTOR = 3.0f;


    public static List<List<Fragment>> buildAppStructure(Context ctxt) {

        List<List<Fragment>> fragmentsMatrix = new ArrayList<>();

        String title = null;
        String text = null;
        int iconId = 0;

        // We first build each row separately

        // Row 1
        List<Fragment> row1 = new ArrayList<>();


        title = ctxt.getString(R.string.contact);
        text = ctxt.getString(R.string.my_full_name);;
        iconId = R.drawable.ic_account;

        CardFragment fragment = CardFragment.create(title, text, iconId);
        fragment.setCardGravity(Gravity.BOTTOM);
        fragment.setExpansionEnabled(true);
        fragment.setExpansionDirection(CardFragment.EXPAND_DOWN);
        fragment.setExpansionFactor(MAXIMUM_CARD_EXPANSION_FACTOR);

        row1.add(fragment);

        // bis
        title = ctxt.getString(R.string.email);
        text = ctxt.getString(R.string.my_mail);;
        iconId = R.drawable.ic_email;

        CardFragment fragment2 = CardFragment.create(title, text, iconId);
        fragment2.setCardGravity(Gravity.BOTTOM);
        fragment2.setExpansionEnabled(true);
        fragment2.setExpansionDirection(CardFragment.EXPAND_DOWN);
        fragment2.setExpansionFactor(MAXIMUM_CARD_EXPANSION_FACTOR);

        row1.add(fragment2);

        SendMessageToMobileFragment sendMessageToMobileFragment =
                SendMessageToMobileFragment
                        .create(ctxt.getString(R.string.my_site));

        row1.add(sendMessageToMobileFragment);


        // Row 2
        List<Fragment> row2 = new ArrayList<>();

        title = ctxt.getString(R.string.about);
        text = ctxt.getString(R.string.about_me_short);;
        iconId = R.drawable.ic_responsive;

        CardFragment fragment3 = CardFragment.create(title, text, iconId);
        fragment3.setExpansionEnabled(true);
        fragment3.setExpansionDirection(CardFragment.EXPAND_DOWN);
        fragment3.setExpansionFactor(MAXIMUM_CARD_EXPANSION_FACTOR);

        row2.add(fragment3);

        // QR Code fragment
        ImageFragment imageFragment = ImageFragment.create(R.drawable.qr_mysite, ctxt.getString(R.string.website));
        row2.add(imageFragment);

        // Add the rows to the matrix
        fragmentsMatrix.add(row1);
        fragmentsMatrix.add(row2);

        return fragmentsMatrix;

    }

}
