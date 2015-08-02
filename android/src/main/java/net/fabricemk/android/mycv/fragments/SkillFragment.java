package net.fabricemk.android.mycv.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.fabricemk.android.mycv.R;
import net.fabricemk.android.mycv.adapters.SkillListAdapter;
import net.fabricemk.android.mycv.models.SkillSubset;
import net.fabricemk.android.mycv.parsers.SkillJsonParser;

import java.util.Map;

public class SkillFragment extends Fragment {

    RecyclerView recycler;
    SkillListAdapter adapter;

    Map<String, SkillSubset> data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_skill_list, container, false);

        recycler = (RecyclerView) v.findViewById(R.id.recylerview);

        // The list won't change so we can add this optimization
        recycler.setHasFixedSize(true);

        // The recyclerView needs a LayoutManager to manage items
        // positioning
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        recycler.setLayoutManager(layoutManager);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);

        initData();

        adapter = new SkillListAdapter(data);
        recycler.setAdapter(adapter);
    }

    private void initData() {
        data = SkillJsonParser.parseLocal(getActivity()).getAllSkills();
    }

}
