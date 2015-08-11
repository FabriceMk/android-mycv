package net.fabricemk.android.mycv.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import net.fabricemk.android.mycv.R;

public class ImageFragment extends Fragment {

    public static final String KEY_IMAGE_RESOURCE = "ImageFragment_image";

    public static ImageFragment create(int imageRes) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();

        if(imageRes != 0) {
            args.putInt(KEY_IMAGE_RESOURCE, imageRes);
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
        View v = inflater.inflate(R.layout.layout_image_fragment, container, false);

        ImageView image = (ImageView) v.findViewById(R.id.image);

        Bundle args = this.getArguments();

        if(args.containsKey(KEY_IMAGE_RESOURCE)) {
            image.setImageResource(args.getInt(KEY_IMAGE_RESOURCE));
        }

        return v;
    }

}
