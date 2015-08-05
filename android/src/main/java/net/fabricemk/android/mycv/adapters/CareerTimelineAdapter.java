package net.fabricemk.android.mycv.adapters;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import net.fabricemk.android.mycv.R;
import net.fabricemk.android.mycv.models.CareerItem;

import java.util.List;

public class CareerTimelineAdapter extends RecyclerView.Adapter<CareerTimelineAdapter.CareerItemViewHolder> {

    Context ctxt;

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

        ctxt = viewGroup.getContext();

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CareerItemViewHolder careerItemViewHolder, int i) {
        CareerItem item = careerList.get(i);

        careerItemViewHolder.company.setText(item.getCompany());
        careerItemViewHolder.position.setText(item.getPosition());

        String date = String.format(ctxt.getString(R.string.skill_date),
                item.getStartDate(), item.getEndDate());
        careerItemViewHolder.date.setText(date);

        careerItemViewHolder.description.setText(item.getDescription());

        Glide.with(ctxt)
                .load(mappingIconIdFromName(item.getIcon()))
                .centerCrop()
                .crossFade()
                .into(careerItemViewHolder.icon);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static int mappingIconIdFromName(String name) {
        int iconId = R.drawable.ic_web; // Temp

        switch (name) {
            case "appturbo": iconId = R.drawable.company_appturbo; break;
        }

        return iconId;

    }


    public static class CareerItemViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView company;
        TextView position;
        TextView date;
        TextView description;
        RoundedImageView icon;

        CareerItemViewHolder(View itemView) {
            super(itemView);
            cv          = (CardView)itemView.findViewById(R.id.cv);
            company     = (TextView)itemView.findViewById(R.id.career_item_company);
            position    = (TextView)itemView.findViewById(R.id.career_item_position);
            date        = (TextView)itemView.findViewById(R.id.career_item_date);
            description = (TextView)itemView.findViewById(R.id.career_item_description);
            icon        = (RoundedImageView)itemView.findViewById(R.id.career_item_icon);
        }
    }

}
