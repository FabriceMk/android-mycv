package net.fabricemk.android.mycv.ui.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.fabricemk.android.mycv.R;
import net.fabricemk.android.mycv.ui.adapters.CareerPagerAdapter;
import net.fabricemk.android.mycv.ui.activities.IToolbarable;

public class CareerFragment extends Fragment {

    Toolbar mToolbar;

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

        mToolbar = (Toolbar) v.findViewById(R.id.toolbar);
        mViewPager = (ViewPager) v.findViewById(R.id.view_pager);
        mTabLayout = (TabLayout) v.findViewById(R.id.tabs);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);

        mToolbar.setTitle(getString(R.string.career));
        ((IToolbarable)getActivity()).setupToolbar(mToolbar);

        mViewPager.setAdapter(new CareerPagerAdapter(getActivity(), getFragmentManager()));

        mTabLayout.setupWithViewPager(mViewPager);
    }

}
