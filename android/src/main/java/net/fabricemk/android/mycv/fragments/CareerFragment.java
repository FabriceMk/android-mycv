package net.fabricemk.android.mycv.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.fabricemk.android.mycv.R;
import net.fabricemk.android.mycv.adapters.CareerPagerAdapter;

public class CareerFragment extends Fragment {

    ViewPager mViewPager;
    TabLayout mTabLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_career, container, false);

        mViewPager = (ViewPager) v.findViewById(R.id.view_pager);
        mTabLayout = (TabLayout) v.findViewById(R.id.tabs);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);

        mViewPager.setAdapter(new CareerPagerAdapter(getActivity(), getFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
    }

}
