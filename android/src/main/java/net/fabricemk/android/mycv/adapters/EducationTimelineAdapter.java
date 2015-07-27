package net.fabricemk.android.mycv.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.fabricemk.android.mycv.R;
import net.fabricemk.android.mycv.models.EducationItem;

import java.util.List;

public class EducationTimelineAdapter extends RecyclerView.Adapter<EducationTimelineAdapter.EducationItemViewHolder> {

    List<EducationItem> educationList;

    public EducationTimelineAdapter(List<EducationItem> list) {
        this.educationList = list;
    }

    @Override
    public int getItemCount() {
        return educationList.size();
    }

    @Override
    public EducationItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.education_list_item, viewGroup, false);
        EducationItemViewHolder viewHolder = new EducationItemViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EducationItemViewHolder educationItemViewHolder, int i) {
        educationItemViewHolder.school.setText(educationList.get(i).getSchool());
        educationItemViewHolder.diploma.setText(educationList.get(i).getDiploma());
        educationItemViewHolder.startDate.setText(educationList.get(i).getStartDate());
        educationItemViewHolder.endDate.setText(educationList.get(i).getEndDate());
        educationItemViewHolder.description.setText(educationList.get(i).getDescription());

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public static class EducationItemViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView school;
        TextView diploma;
        TextView startDate;
        TextView endDate;
        TextView description;

        EducationItemViewHolder(View itemView) {
            super(itemView);
            cv          = (CardView)itemView.findViewById(R.id.cv);
            school      = (TextView)itemView.findViewById(R.id.education_item_school);
            diploma     = (TextView)itemView.findViewById(R.id.education_item_diploma);
            startDate   = (TextView)itemView.findViewById(R.id.education_item_startdate);
            endDate     = (TextView)itemView.findViewById(R.id.education_item_enddate);
            description = (TextView)itemView.findViewById(R.id.education_item_description);
        }
    }

}
