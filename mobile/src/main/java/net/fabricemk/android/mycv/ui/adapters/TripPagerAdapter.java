package net.fabricemk.android.mycv.ui.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import net.fabricemk.android.mycv.ui.fragments.TripItemFragment;
import net.fabricemk.android.mycv.models.TripItem;

import java.util.List;

/**
 * This is the adapter for the ViewPager used to display the different trips/exhibitions
 */
public class TripPagerAdapter extends FragmentStatePagerAdapter {

    private Context ctxt = null;

    private List<TripItem> tripList;

    public TripPagerAdapter(Context ctxt, FragmentManager mgr, List<TripItem> tripList) {
        super(mgr);
        this.ctxt = ctxt;
        this.tripList = tripList;
    }

    @Override
    public int getCount() {
        return(tripList.size());
    }

    @Override
    public Fragment getItem(int position) {
        return TripItemFragment.newInstance(tripList.get(position));
    }

}
