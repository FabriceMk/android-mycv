package net.fabricemk.android.mycv.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.fabricemk.android.mycv.R;
import net.fabricemk.android.mycv.ui.adapters.SectionedGridRecyclerViewAdapter;
import net.fabricemk.android.mycv.ui.adapters.SkillListAdapter;
import net.fabricemk.android.mycv.models.Skill;
import net.fabricemk.android.mycv.models.SkillSubset;
import net.fabricemk.android.mycv.parsers.SkillJsonParser;
import net.fabricemk.android.mycv.ui.activities.IToolbarable;
import net.fabricemk.android.mycv.ui.activities.SkillDetailsActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SkillFragment extends Fragment implements SkillListAdapter.OnItemClickListener {

    private static final String KEY_RECYCLER_SCROLL_STATE = "RecyclerScrollState";

    Toolbar mToolbar;

    RecyclerView recycler;
    SkillListAdapter adapter;

    Map<String, SkillSubset> data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_skill_list, container, false);

        mToolbar = (Toolbar) v.findViewById(R.id.toolbar);

        recycler = (RecyclerView) v.findViewById(R.id.recylerview);

        // The list won't change so we can add this optimization
        recycler.setHasFixedSize(true);

        // The recyclerView needs a LayoutManager to manage items
        // positioning
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        recycler.setLayoutManager(layoutManager);


        initData();

        adapter = new SkillListAdapter(data);
        adapter.setOnItemClickListener(this);

        /*
         * Setting up the different sections headers
         */
        List<SectionedGridRecyclerViewAdapter.Section> sections =
                new ArrayList<SectionedGridRecyclerViewAdapter.Section>();

        int headersPosition = 0;

        for (Map.Entry<String, SkillSubset> entry : data.entrySet()) {
            sections.add(new SectionedGridRecyclerViewAdapter.Section(headersPosition, entry.getKey()));
            headersPosition += entry.getValue().getSkills().size();
        }

        SectionedGridRecyclerViewAdapter.Section[] dummy = new SectionedGridRecyclerViewAdapter.Section[sections.size()];
        SectionedGridRecyclerViewAdapter mSectionedAdapter = new
                SectionedGridRecyclerViewAdapter(getActivity(),
                R.layout.skill_section,
                R.id.section_text,
                recycler,
                adapter);
        mSectionedAdapter.setSections(sections.toArray(dummy));

        //Apply this adapter to the RecyclerView
        recycler.setAdapter(mSectionedAdapter);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);

        mToolbar.setTitle(getString(R.string.skills));
        ((IToolbarable)getActivity()).setupToolbar(mToolbar);
    }

    private void initData() {
        data = SkillJsonParser.parseLocal(getActivity()).getAllSkills();
    }

    @Override
    public void onItemClick(View view, Skill skill) {
        SkillDetailsActivity.navigate((AppCompatActivity) getActivity(), view.findViewById(R.id.icon), skill);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

}
