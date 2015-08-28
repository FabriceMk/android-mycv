package net.fabricemk.android.mycv.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import net.fabricemk.android.mycv.fragments.TripFragment;
import net.fabricemk.android.mycv.fragments.TripItemFragment;
import net.fabricemk.android.mycv.models.TripItem;

import java.util.List;

public class TripPagerAdapter extends FragmentStatePagerAdapter {

    Context ctxt = null;

    List<TripItem> tripList;

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
