package net.fabricemk.android.mycv.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import net.fabricemk.android.mycv.fragments.TripItemFragment;

public class TripPagerAdapter extends FragmentStatePagerAdapter {

    Context ctxt = null;

    public TripPagerAdapter(Context ctxt, FragmentManager mgr) {
        super(mgr);
        this.ctxt = ctxt;
    }

    @Override
    public int getCount() {
        return(2);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TripItemFragment();
            case 1:
                return new TripItemFragment();
            default:
                return null;
        }
    }
}
