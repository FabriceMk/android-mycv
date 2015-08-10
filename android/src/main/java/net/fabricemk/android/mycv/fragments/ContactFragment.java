package net.fabricemk.android.mycv.fragments;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.fabricemk.android.mycv.R;
import net.fabricemk.android.mycv.tools.CommunicationTools;
import net.fabricemk.android.mycv.ui.activities.IToolbarable;

public class ContactFragment extends Fragment {

    Toolbar mToolbar;

    private CollapsingToolbarLayout mCollapsingToolbarLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contact, container, false);

        initViewInfos(v);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);

        ((IToolbarable)getActivity()).setupToolbar(mToolbar);
    }

    /**
     * Init the views, buttons and create the correct listeners
     * @param v
     */
    private void initViewInfos(View v) {
        mToolbar = (Toolbar) v.findViewById(R.id.toolbar);

        mCollapsingToolbarLayout = (CollapsingToolbarLayout) v.findViewById(R.id.collapsing_toolbar);
        mCollapsingToolbarLayout.setTitle(getString(R.string.my_full_name_short));

        ImageView header = (ImageView)v.findViewById(R.id.header);
        header.setImageResource(R.drawable.mwa);

        // Name
        View subView = v.findViewById(R.id.name_item);
        ImageView icon = (ImageView) subView.findViewById(R.id.icon);
        TextView firstRow = (TextView) subView.findViewById(R.id.row_1);
        icon.setImageResource(R.drawable.ic_account);
        firstRow.setText(R.string.my_full_name);
        subView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommunicationTools.addAsContact(getActivity(),
                        getString(R.string.my_full_name),
                        getString(R.string.my_mail));
            }
        });

        // Mail
        subView = v.findViewById(R.id.mail_item);
        icon = (ImageView) subView.findViewById(R.id.icon);
        firstRow = (TextView) subView.findViewById(R.id.row_1);
        icon.setImageResource(R.drawable.ic_email);
        firstRow.setText(R.string.my_mail);
        subView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommunicationTools.sendEmail(getActivity(), getString(R.string.my_mail), "");
            }
        });

        // Site
        subView = v.findViewById(R.id.site_item);
        icon = (ImageView) subView.findViewById(R.id.icon);
        firstRow = (TextView) subView.findViewById(R.id.row_1);
        icon.setImageResource(R.drawable.ic_web);
        firstRow.setText(R.string.my_site);
        subView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommunicationTools.launchURL(getActivity(), getString(R.string.my_site));
            }
        });


        // Social Buttons
        subView = v.findViewById(R.id.googleplus_item);
        subView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommunicationTools.launchGooglePlus(getActivity(), getString(R.string.my_google));
            }
        });

        subView = v.findViewById(R.id.github_item);
        subView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommunicationTools.launchGithub(getActivity(), getString(R.string.my_github));
            }
        });

        subView = v.findViewById(R.id.linkedin_item);
        subView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommunicationTools.launchLinkedIn(getActivity(), getString(R.string.my_linkedin_id));
            }
        });

    }



}
