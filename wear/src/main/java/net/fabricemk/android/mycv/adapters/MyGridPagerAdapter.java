package net.fabricemk.android.mycv.adapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.view.Gravity;

import net.fabricemk.android.mycv.R;
import net.fabricemk.android.mycv.fragments.ImageFragment;

import java.util.ArrayList;
import java.util.List;

public class MyGridPagerAdapter extends FragmentGridPagerAdapter {

    private static final float MAXIMUM_CARD_EXPANSION_FACTOR = 3.0f;

    private final Context mContext;
    private List<Fragment> firstRow;

    public MyGridPagerAdapter(Context ctxt, FragmentManager fm) {
        super(fm);
        mContext = ctxt;

        initFirstRow();
    }

    private void initFirstRow() {
        firstRow = new ArrayList<>();

        String title = null;
        String text = null;
        int iconId = 0;

        // First card
        title = mContext.getString(R.string.contact);
        text = mContext.getString(R.string.my_full_name);;
        iconId = R.drawable.ic_account;

        CardFragment fragment = CardFragment.create(title, text, iconId);
        fragment.setCardGravity(Gravity.BOTTOM);
        fragment.setExpansionEnabled(true);
        fragment.setExpansionDirection(CardFragment.EXPAND_DOWN);
        fragment.setExpansionFactor(MAXIMUM_CARD_EXPANSION_FACTOR);

        firstRow.add(fragment);

        // bis
        title = mContext.getString(R.string.email);
        text = mContext.getString(R.string.my_mail);;
        iconId = R.drawable.ic_email;

        CardFragment fragment2 = CardFragment.create(title, text, iconId);
        fragment2.setCardGravity(Gravity.BOTTOM);
        fragment2.setExpansionEnabled(true);
        fragment2.setExpansionDirection(CardFragment.EXPAND_DOWN);
        fragment2.setExpansionFactor(MAXIMUM_CARD_EXPANSION_FACTOR);

        firstRow.add(fragment2);

        // ter
        title = mContext.getString(R.string.about);
        text = mContext.getString(R.string.about_me_short);;
        iconId = R.drawable.ic_responsive;

        CardFragment fragment3 = CardFragment.create(title, text, iconId);
        //fragment3.setCardGravity(Gravity.BOTTOM);
        fragment3.setExpansionEnabled(true);
        fragment3.setExpansionDirection(CardFragment.EXPAND_DOWN);
        fragment3.setExpansionFactor(MAXIMUM_CARD_EXPANSION_FACTOR);

        firstRow.add(fragment3);

        // Third card
        ImageFragment imageFragment = ImageFragment.create(R.drawable.qr_mysite);
        firstRow.add(imageFragment);

    }

    // Override methods in FragmentGridPagerAdapter

    @Override
    public Fragment getFragment(int row, int column) {
        Fragment temp = firstRow.get(column);

        return temp;
    }

    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public int getColumnCount(int row) {
        return firstRow.size();
    }

    @Override
    public Drawable getBackgroundForRow(int row) {
        int backgroundId = 0;

        switch (row) {
            case 0: backgroundId = R.drawable.mwa; break;
            case 1: backgroundId = R.drawable.skill_android; break;
            default: backgroundId = R.drawable.skill_android;
        }

        if (backgroundId == 0) {
            return BACKGROUND_NONE;
        } else {
            return mContext.getResources().getDrawable(backgroundId);
        }
    }

    @Override
    public Drawable getBackgroundForPage(int row, int column) {
        int backgroundId = 0;

        if (row == 0) {
            switch (column) {
                case 2: backgroundId = R.drawable.skill_android; break;
            }
        }

        if (backgroundId == 0) {
            return BACKGROUND_NONE;
        } else {
            return mContext.getResources().getDrawable(backgroundId);
        }
    }
}
