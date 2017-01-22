package net.fabricemk.android.mycv.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.fabricemk.android.mycv.R;
import net.fabricemk.android.mycv.ui.adapters.EducationTimelineListAdapter;
import net.fabricemk.android.mycv.models.EducationItem;
import net.fabricemk.android.mycv.parsers.EducationJsonParser;

import java.util.List;

/**
 * A fragment which shows the Education background as a Timeline (for now just a vertical list)
 *
 * Each diploma/formation is represented by a card with all the details and no deeper interactions
 * are possible
 */
public class EducationTimelineFragment extends Fragment
        implements IPageable {

    private static final String KEY_TITLE = "EducationTimelineFragment.title";

    String title;

    RecyclerView recycler;
    EducationTimelineListAdapter adapter;

    List<EducationItem> data;

    public static EducationTimelineFragment newInstance(String title) {
        EducationTimelineFragment fragment = new EducationTimelineFragment();
        Bundle args = new Bundle();
        args.putString(KEY_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString(KEY_TITLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_education_timeline, container, false);

        recycler = (RecyclerView) v.findViewById(R.id.recylerview);

        // The list won't change so we can add this optimization
        recycler.setHasFixedSize(true);

        // The recyclerView needs a LayoutManager to manage items
        // positioning
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(llm);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);

        initData();

        adapter = new EducationTimelineListAdapter(data);
        recycler.setAdapter(adapter);
    }

    private void initData() {
        data = EducationJsonParser.parseLocal(getActivity());

    }

    public String getTitle() {
        return title;
    }
}
