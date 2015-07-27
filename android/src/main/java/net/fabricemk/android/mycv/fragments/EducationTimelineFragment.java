package net.fabricemk.android.mycv.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.fabricemk.android.mycv.R;
import net.fabricemk.android.mycv.adapters.EducationTimelineAdapter;
import net.fabricemk.android.mycv.models.EducationItem;

import java.util.ArrayList;
import java.util.List;

public class EducationTimelineFragment extends Fragment
        implements IPageable {

    String title;

    RecyclerView recycler;
    EducationTimelineAdapter adapter;

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

        adapter = new EducationTimelineAdapter(data);
        recycler.setAdapter(adapter);
    }

    private void initData() {
        data = new ArrayList<>();

        EducationItem item1 = new EducationItem();
        item1.setSchool("Universite Paris VI");
        item1.setDiploma("Master Degree of Computer Science");
        item1.setStartDate("2007");
        item1.setEndDate("2009");
        item1.setDescription("Artifical Intelligence");

        EducationItem item2 = new EducationItem();
        item2.setSchool("Universite Paris VI");
        item2.setDiploma("License of Computer Science");
        item2.setStartDate("2004");
        item2.setEndDate("2006");
        item2.setDescription("");


        data.add(item1);
        data.add(item2);

    }

    public String getTitle() {
        return title;
    }
}
