package net.fabricemk.android.mycv.ui.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import net.fabricemk.android.mycv.R;
import net.fabricemk.android.mycv.ui.fragments.CareerTimelineFragment;
import net.fabricemk.android.mycv.ui.fragments.EducationTimelineFragment;

/**
 * The adapter class which displays both Career Fragment and Education Fragment in a ViewPager
 */
public class CareerPagerAdapter extends FragmentStatePagerAdapter {

    Context ctxt = null;

    public CareerPagerAdapter(Context ctxt, FragmentManager mgr) {
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
                return CareerTimelineFragment.newInstance(ctxt.getString(R.string.tab_career));
            case 1:
                return EducationTimelineFragment.newInstance(ctxt.getString(R.string.tab_education));
            default:
                return null;
        }
    }

    @Override
    public String getPageTitle(int position) {
        String title;
        switch (position) {
            case 0:
                title = ctxt.getString(R.string.tab_career);
                break;
            case 1:
                title = ctxt.getString(R.string.tab_education);
                break;
            default:
                title = null;
        }
        return title;
    }
}
