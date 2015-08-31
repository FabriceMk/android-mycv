package net.fabricemk.android.mycv.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.fabricemk.android.mycv.R;
import net.fabricemk.android.mycv.adapters.TripPagerAdapter;
import net.fabricemk.android.mycv.models.TripItem;
import net.fabricemk.android.mycv.parsers.TripJsonParser;
import net.fabricemk.android.mycv.tools.AndroidTools;
import net.fabricemk.android.mycv.ui.activities.IToolbarable;

import java.util.List;

public class TripFragment extends Fragment {
    ViewGroup mRoot;

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

        mRoot = (ViewGroup)v.findViewById(R.id.root);

        mToolbar = (Toolbar) v.findViewById(R.id.toolbar);
        mViewPager = (ViewPager) v.findViewById(R.id.view_pager);

        int margin = (int)getResources().getDimension(R.dimen.viewpager_special_margin);
        mViewPager.setPageMargin(-margin);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);

        mToolbar.setTitle(getString(R.string.trips));
        ((IToolbarable)getActivity()).setupToolbar(mToolbar);

        List<TripItem> trips = TripJsonParser.parseLocal(getActivity());
        mViewPager.setAdapter(new TripPagerAdapter(getActivity(), getFragmentManager(), trips));

        // If no connection we show a snackbar to notify that the Google Maps won't load
        if (! AndroidTools.isOnline(getActivity())) {
            Snackbar
                .make(mRoot, R.string.maps_no_connection, Snackbar.LENGTH_LONG)
                .setAction(R.string.snackbar_turn_on_connection, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AndroidTools.launchWirelessSettings(getActivity());
                    }
                })
                .show(); // Don’t forget to show!
        }

    }

}
