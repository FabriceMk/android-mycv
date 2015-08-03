package net.fabricemk.android.mycv.adapters;

import android.content.Context;
import android.os.Handler;
import android.support.v7.internal.widget.AdapterViewCompat;
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
import net.fabricemk.android.mycv.tools.AndroidTools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SkillListAdapter extends RecyclerView.Adapter<SkillListAdapter.SkillItemViewHolder>
    implements View.OnClickListener {

    Context ctxt;

    Map<String, SkillSubset> skillList;
    List<Skill> flatSkillList;

    private AdapterViewCompat.OnItemClickListener onItemClickListener;

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
        SkillItemViewHolder viewHolder = new SkillItemViewHolder(v);

        ctxt = viewGroup.getContext();

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SkillItemViewHolder holder, int position) {
        holder.text.setText(flatSkillList.get(position).getName());

        int resourceId = mappingIconIdFromName(flatSkillList.get(position).getIcon());

        Glide.with(ctxt)
                .load(resourceId)
                .centerCrop()
                .crossFade()
                .into(holder.icon);
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
                    //onItemClickListener.onItemClick(v, (Skill) v.getTag());
                }
            }, 200);
        }
    }

    private int mappingIconIdFromName(String name) {
        int iconId = R.drawable.ic_web; // Temp

        switch (name) {
            case "android": iconId = R.drawable.skill_android; break;
            case "java": iconId = R.drawable.skill_java; break;
            case "php": iconId = R.drawable.skill_php; break;
            case "html": iconId = R.drawable.skill_html; break;
            case "javascript": iconId = R.drawable.skill_javascript; break;
            case "ruby": iconId = R.drawable.skill_ruby; break;
            case "python": iconId = R.drawable.skill_python; break;
            case "ror": iconId = R.drawable.skill_ror; break;
            case "codeigniter": iconId = R.drawable.skill_codeigniter; break;
            case "sinatra": iconId = R.drawable.skill_sinatra; break;
            case "sass": iconId = R.drawable.skill_sass; break;
            case "aws": iconId = R.drawable.skill_aws; break;
            case "gae": iconId = R.drawable.skill_gae; break;
            case "git": iconId = R.drawable.skill_git; break;
            case "gulp": iconId = R.drawable.skill_gulp; break;
            case "intellij": iconId = R.drawable.skill_intellij; break;
            case "photoshop": iconId = R.drawable.skill_photoshop; break;
            case "sysadmin": iconId = R.drawable.skill_sysadmin; break;
            case "ai": iconId = R.drawable.skill_ia; break;
            case "fr": iconId = R.drawable.flag_france; break;
            case "en": iconId = R.drawable.flag_uk; break;
            case "jp": iconId = R.drawable.flag_japan; break;

        }

        return iconId;

    }

    public static class SkillItemViewHolder extends RecyclerView.ViewHolder {

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
