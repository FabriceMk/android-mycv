package net.fabricemk.android.mycv.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.fabricemk.android.mycv.R;
import net.fabricemk.android.mycv.adapters.EducationTimelineListAdapter;
import net.fabricemk.android.mycv.models.EducationItem;
import net.fabricemk.android.mycv.parsers.EducationJsonParser;

import java.util.List;

public class EducationTimelineFragment extends Fragment
        implements IPageable {

    String title;

    RecyclerView recycler;
    EducationTimelineListAdapter adapter;

    List<EducationItem> data;

    public static EducationTimelineFragment newInstance(String title) {
        EducationTimelineFragment fragment = new EducationTimelineFragment();
        Bundle args = new Bundle();
        args.putString("TITLE", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString("TITLE");
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

        // TEMP
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
