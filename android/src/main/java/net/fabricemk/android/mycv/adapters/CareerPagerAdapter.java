package net.fabricemk.android.mycv.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import net.fabricemk.android.mycv.fragments.CareerTimelineFragment;
import net.fabricemk.android.mycv.fragments.ContactFragment;

public class CareerPagerAdapter extends FragmentPagerAdapter  {

    Context ctxt = null;

    public CareerPagerAdapter(Context ctxt, FragmentManager mgr) {
        super(mgr);
        this.ctxt=ctxt;
    }

    @Override
    public int getCount() {
        return(2);
    }

    @Override
    public Fragment getItem(int position) {
        return(new CareerTimelineFragment());
    }

    @Override
    public String getPageTitle(int position) {
        return("Tab " + position);
    }
}
