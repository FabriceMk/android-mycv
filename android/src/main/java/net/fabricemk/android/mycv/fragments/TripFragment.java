package net.fabricemk.android.mycv.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.fabricemk.android.mycv.R;
import net.fabricemk.android.mycv.adapters.TripPagerAdapter;
import net.fabricemk.android.mycv.ui.activities.IToolbarable;

public class TripFragment extends Fragment {

    Toolbar mToolbar;

    ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_trip, container, false);

        mToolbar = (Toolbar) v.findViewById(R.id.toolbar);
        mViewPager = (ViewPager) v.findViewById(R.id.view_pager);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);

        mToolbar.setTitle(getString(R.string.trips));
        ((IToolbarable)getActivity()).setupToolbar(mToolbar);

        mViewPager.setAdapter(new TripPagerAdapter(getActivity(), getFragmentManager()));

    }

}