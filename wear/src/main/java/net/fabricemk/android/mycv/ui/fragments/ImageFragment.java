package net.fabricemk.android.mycv.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.fabricemk.android.mycv.R;

/**
 * A fragment used to display a centered full screen image with an optional description
 */
public class ImageFragment extends Fragment {

    public static final String KEY_TITLE_RESOURCE = "ImageFragment_title";
    public static final String KEY_IMAGE_RESOURCE = "ImageFragment_image";

    public static ImageFragment create(int imageRes, String title) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();

        if(imageRes != 0) {
            args.putInt(KEY_IMAGE_RESOURCE, imageRes);
        }

        if(! title.isEmpty()) {
            args.putString(KEY_TITLE_RESOURCE, title);
        }

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_image_card, container, false);

        ImageView image = (ImageView) v.findViewById(R.id.image);
        TextView title = (TextView) v.findViewById(R.id.title);

        Bundle args = this.getArguments();

        if(args.containsKey(KEY_IMAGE_RESOURCE)) {
            image.setImageResource(args.getInt(KEY_IMAGE_RESOURCE));
        }

        if(args.containsKey(KEY_TITLE_RESOURCE)) {
            title.setText(args.getString(KEY_TITLE_RESOURCE));
        } else {
            title.setVisibility(View.GONE);
        }

        return v;
    }

}
