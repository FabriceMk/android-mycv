package net.fabricemk.android.mycv.ui.adapters;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.fabricemk.android.mycv.R;
import net.fabricemk.android.mycv.models.Skill;
import net.fabricemk.android.mycv.models.SkillSubset;
import net.fabricemk.android.mycv.tools.ResourcesMapperTools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This is the adapter for the RecyclerView used to display the Skills List
 */
public class SkillListAdapter extends RecyclerView.Adapter<SkillListAdapter.SkillItemViewHolder>
    implements View.OnClickListener {

    private Context ctxt;

    private Map<String, SkillSubset> skillList;
    private List<Skill> flatSkillList;

    private OnItemClickListener onItemClickListener;

    public SkillListAdapter(Map<String, SkillSubset> skillList) {
        this.skillList = skillList;

        flattenSkillList();
    }

    private void flattenSkillList() {
        flatSkillList = new ArrayList<>();
        for (SkillSubset s:
                skillList.values()) {
            flatSkillList.addAll(s.getSkills());
        }
    }

    @Override
    public int getItemCount() {
        return flatSkillList.size();
    }

    @Override
    public SkillItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.skill_list_item, viewGroup, false);
        v.setOnClickListener(this);

        SkillItemViewHolder viewHolder = new SkillItemViewHolder(v);

        ctxt = viewGroup.getContext();

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SkillItemViewHolder holder, int position) {
        Skill skill = flatSkillList.get(position);

        holder.text.setText(flatSkillList.get(position).getName());

        int resourceId = ResourcesMapperTools.getSkillIconId(ctxt, skill.getIcon());
        Glide.with(ctxt)
                .load(resourceId)
                .centerCrop()
                .crossFade()
                .into(holder.icon);

        holder.itemView.setTag(skill);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onClick(final View v) {
        // Give some time to the ripple to finish the effect
        if (onItemClickListener != null) {
            new Handler().postDelayed(new Runnable() {
                @Override public void run() {
                    onItemClickListener.onItemClick(v, (Skill) v.getTag());
                }
            }, 200);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    static class SkillItemViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView text;

        SkillItemViewHolder(View itemView) {
            super(itemView);
            icon    = (ImageView)itemView.findViewById(R.id.icon);
            text    = (TextView)itemView.findViewById(R.id.text);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, Skill skill);
    }

}
