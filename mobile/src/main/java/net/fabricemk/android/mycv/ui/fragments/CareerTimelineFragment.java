package net.fabricemk.android.mycv.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.fabricemk.android.mycv.R;
import net.fabricemk.android.mycv.ui.adapters.CareerTimelineListAdapter;
import net.fabricemk.android.mycv.models.CareerItem;
import net.fabricemk.android.mycv.parsers.CareerJsonParser;
import net.fabricemk.android.mycv.ui.activities.CareerDetailsActivity;

import java.util.List;

/**
 * A fragment which shows the Job Career as a Timeline (for now just a vertical list)
 *
 * We can click on a Job Career to see more details
 */
public class CareerTimelineFragment extends Fragment
        implements IPageable, CareerTimelineListAdapter.OnItemClickListener {

    String title;

    RecyclerView recycler;
    CareerTimelineListAdapter adapter;

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

        initData();

        adapter = new CareerTimelineListAdapter(data);
        adapter.setOnItemClickListener(this);

        recycler.setAdapter(adapter);
    }

    private void initData() {
        data = CareerJsonParser.parseLocal(getActivity());
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void onItemClick(View view, CareerItem career) {
        CareerDetailsActivity.navigate((AppCompatActivity) getActivity(), view.findViewById(R.id.icon), career);
    }
}
