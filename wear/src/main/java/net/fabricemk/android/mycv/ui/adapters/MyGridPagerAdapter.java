package net.fabricemk.android.mycv.ui.adapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.wearable.view.FragmentGridPagerAdapter;

import net.fabricemk.android.mycv.AppStructureBuilder;
import net.fabricemk.android.mycv.R;

import java.util.List;

public class MyGridPagerAdapter extends FragmentGridPagerAdapter {

    private static final float MAXIMUM_CARD_EXPANSION_FACTOR = 3.0f;

    private final Context mContext;
    private List<List<Fragment>> fragmentsMatrix;

    public MyGridPagerAdapter(Context ctxt, FragmentManager fm) {
        super(fm);
        mContext = ctxt;

        fragmentsMatrix = AppStructureBuilder.buildAppStructure(mContext);
    }


    // Override methods in FragmentGridPagerAdapter

    @Override
    public Fragment getFragment(int row, int column) {
        Fragment fragment = fragmentsMatrix.get(row).get(column);
        return fragment;
    }

    @Override
    public int getRowCount() {
        return fragmentsMatrix.size();
    }

    @Override
    public int getColumnCount(int row) {
        return fragmentsMatrix.get(row).size();
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
                //case 2: backgroundId = R.drawable.skill_android; break;
            }
        }

        if (backgroundId == 0) {
            return BACKGROUND_NONE;
        } else {
            return mContext.getResources().getDrawable(backgroundId);
        }
    }
}
