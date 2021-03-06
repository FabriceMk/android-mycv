package net.fabricemk.android.mycv.ui.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import net.fabricemk.android.mycv.AppController;
import net.fabricemk.android.mycv.R;
import net.fabricemk.android.mycv.ui.adapters.TripPagerAdapter;
import net.fabricemk.android.mycv.models.TripItem;
import net.fabricemk.android.mycv.parsers.TripJsonParser;
import net.fabricemk.android.mycv.tools.AndroidTools;
import net.fabricemk.android.mycv.ui.activities.IToolbarable;

import java.util.List;

/**
 * A fragment which displays the different trips/exhibitions
 * It uses a ViewPager where each trip/exhibition is in a specific fragment
 *
 * This fragment uses some network capabilities if available:
 * If an Internet connection is detected, it tries to load a remote JSON with Volley
 * as the data source and it also uses a MapView to display the location of the trip
 *
 * If no connection is detected, it fallbacks on a local JSON file and a Snackbar notify the user
 * that he is in a offline mode
 */
public class TripFragment extends Fragment {
    private ViewGroup mRoot;

    private Toolbar mToolbar;

    private ViewPager mViewPager;

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

        // Small hack to have cards which break the edge of the Toolbar
        int margin = (int)getResources().getDimension(R.dimen.viewpager_special_margin);
        mViewPager.setPageMargin(-margin);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);

        mToolbar.setTitle(getString(R.string.trips));
        ((IToolbarable)getActivity()).setupToolbar(mToolbar);

        List<TripItem> trips = null;

        if (AndroidTools.isOnline(getActivity())) {
            /*
             * If connnectivity is found, we try to load a remote JSON
             * with Volley.
             *
             * As we will use GSON to map the JSON to a POJO we don't need
             * a JSONObjectRequest
             */

            final String tag_json_obj = "string_req";

            final ProgressDialog pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage(getString(R.string.loading));
            pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pDialog.show();

            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    getString(R.string.url_endpoint_trip),
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            onlineMode(response);
                            // Dismiss the progress dialog
                            pDialog.dismiss();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                         public void onErrorResponse(VolleyError error) {
                            VolleyLog.d(tag_json_obj, "Error: " + error.getMessage());
                            // Dismiss the progress dialog
                            pDialog.dismiss();
                        }
                    }
            );

            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(stringRequest);
        } else {
            /*
             * No connection, we use the offline mode with local data source
             */
            offlineMode();
        }
    }

    private void onlineMode(String json) {
        List<TripItem> trips = TripJsonParser.parse(json);

        mViewPager.setAdapter(new TripPagerAdapter(getActivity(), getFragmentManager(), trips));
    }

    /**
     * If no connection we show a snackbar to notify that the Google Maps won't load
     * and we load local data
     */
    private void offlineMode() {
        List<TripItem> trips = TripJsonParser.parseLocal(getActivity());

        Snackbar
            .make(mRoot, R.string.maps_no_connection, Snackbar.LENGTH_LONG)
//            .setAction(R.string.snackbar_turn_on_connection, new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    AndroidTools.launchWirelessSettings(getActivity());
//                }
//            })
            .show();

        mViewPager.setAdapter(new TripPagerAdapter(getActivity(), getFragmentManager(), trips));
    }

}
