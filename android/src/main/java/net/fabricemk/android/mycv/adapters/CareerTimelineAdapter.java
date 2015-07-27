package net.fabricemk.android.mycv.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.fabricemk.android.mycv.R;
import net.fabricemk.android.mycv.models.CareerItem;

import java.util.List;

public class CareerTimelineAdapter extends RecyclerView.Adapter<CareerTimelineAdapter.CareerItemViewHolder> {

    List<CareerItem> careerList;

    public CareerTimelineAdapter(List<CareerItem> list) {
        this.careerList = list;
    }

    @Override
    public int getItemCount() {
        return careerList.size();
    }

    @Override
    public CareerItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.career_list_item, viewGroup, false);
        CareerItemViewHolder viewHolder = new CareerItemViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CareerItemViewHolder careerItemViewHolder, int i) {
        careerItemViewHolder.company.setText(careerList.get(i).getCompany());
        careerItemViewHolder.position.setText(careerList.get(i).getPosition());
        careerItemViewHolder.startDate.setText(careerList.get(i).getStartDate());
        careerItemViewHolder.endDate.setText(careerList.get(i).getEndDate());
        careerItemViewHolder.description.setText(careerList.get(i).getDescription());

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public static class CareerItemViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView company;
        TextView position;
        TextView startDate;
        TextView endDate;
        TextView description;

        CareerItemViewHolder(View itemView) {
            super(itemView);
            cv          = (CardView)itemView.findViewById(R.id.cv);
            company     = (TextView)itemView.findViewById(R.id.career_item_company);
            position    = (TextView)itemView.findViewById(R.id.career_item_position);
            startDate   = (TextView)itemView.findViewById(R.id.career_item_startdate);
            endDate     = (TextView)itemView.findViewById(R.id.career_item_enddate);
            description = (TextView)itemView.findViewById(R.id.career_item_description);
        }
    }

}
