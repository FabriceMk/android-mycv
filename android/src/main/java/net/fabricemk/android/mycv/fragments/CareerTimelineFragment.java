package net.fabricemk.android.mycv.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.fabricemk.android.mycv.R;
import net.fabricemk.android.mycv.adapters.CareerTimelineAdapter;
import net.fabricemk.android.mycv.models.CareerItem;

import java.util.ArrayList;
import java.util.List;

public class CareerTimelineFragment extends Fragment
        implements IPageable {

    String title;

    RecyclerView recycler;
    CareerTimelineAdapter adapter;

    List<CareerItem> data;

    public static CareerTimelineFragment newInstance(String title) {
        CareerTimelineFragment fragment = new CareerTimelineFragment();
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
        View v = inflater.inflate(R.layout.fragment_career_timeline, container, false);

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

        adapter = new CareerTimelineAdapter(data);
        recycler.setAdapter(adapter);
    }

    private void initData() {
        data = new ArrayList<>();

        CareerItem item1 = new CareerItem();
        item1.setCompany("Appturbo");
        item1.setStartDate("2012-03-05");
        item1.setEndDate("2015-03-27");
        item1.setPosition("CTO");
        item1.setDescription("As a CTO, I have built the IT team (5-10 people), the IT infrastructure, the different development methods and I also have participated to the conception of the different IT products of the company.\n" +
                "\n" +
                "I was also in charge of the strategic decisions for the IT department and ensure that the IT could support the rapid growth of the company.\n" +
                "\n" +
                "I also have mainly worked as a Back-end Lead Developer (PHP/MySQL, CodeIgniter) and as the main Android Developer (Java, corresponding APIs...).\n" +
                "\n");

        CareerItem item2 = new CareerItem();
        item2.setCompany("ILObjects");
        item2.setStartDate("2010-09-01");
        item2.setEndDate("2012-03-01");
        item2.setPosition("Software Engineer");
        item2.setDescription("I have developped an Android application dedicated to students having work scheduling problems.\n" +
                "\n" +
                "I also have worked on the conception of a professional software aimed to Industrial Products Designers. The software aimed to vizualize and edit Conceptual Graphs");

        data.add(item1);
        data.add(item2);

    }

    public String getTitle() {
        return title;
    }
}
