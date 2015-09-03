package net.fabricemk.android.mycv.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import net.fabricemk.android.mycv.R;
import net.fabricemk.android.mycv.models.TripItem;

import java.util.List;

public class TripItemFragment extends Fragment implements OnMapReadyCallback {

    private static final String TRIP_ITEM_KEY = "TripItemFragment.trip";

    TripItem trip;

    GoogleMap googleMap;

    TextView eventNameView;
    TextView dateView;
    TextView descriptionView;
    MapView mapView;
    ImageView photoView;

    private boolean needsInit = false;

    public static TripItemFragment newInstance(TripItem trip) {
        TripItemFragment fragment = new TripItemFragment();
        Bundle args = new Bundle();
        args.putSerializable(TRIP_ITEM_KEY, trip);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        trip = (TripItem) getArguments().getSerializable(TRIP_ITEM_KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.trip_item, container, false);

        eventNameView = (TextView) v.findViewById(R.id.event);
        dateView = (TextView) v.findViewById(R.id.date);
        descriptionView = (TextView) v.findViewById(R.id.description);

        eventNameView.setText(trip.getEventName());
        dateView.setText(trip.getDates());
        descriptionView.setText(trip.getDescription());

        mapView = (MapView) v.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        photoView = (ImageView) v.findViewById(R.id.photo);

        String[] pictures = trip.getPictures();
        if (pictures != null && pictures.length > 0) {
            Glide.with(getActivity())
                    .load(pictures[0])
                    .placeholder(R.drawable.placeholder)
                    .into(photoView);
        }
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState == null) {
            needsInit = true;
        }

    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public final void onLowMemory() {
        mapView.onLowMemory();
        super.onLowMemory();
    }


    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;

//        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(LatLng point) {
//                //TODO: your onclick stuffs
//            }
//        });

        // Config markers
        if (needsInit) {
            LatLng location = new LatLng(
                    trip.getLocation().getLatitude(),
                    trip.getLocation().getLongitude());

            map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 6));

            map.addMarker(new MarkerOptions()
                    .position(location)
                    .title(trip.getEventName()));
        }

    }


}
