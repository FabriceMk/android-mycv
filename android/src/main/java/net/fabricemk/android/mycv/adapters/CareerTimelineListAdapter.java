package net.fabricemk.android.mycv.adapters;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import net.fabricemk.android.mycv.R;
import net.fabricemk.android.mycv.models.CareerItem;
import net.fabricemk.android.mycv.tools.resources.CareerMapper;

import java.util.List;

public class CareerTimelineListAdapter extends RecyclerView.Adapter<CareerTimelineListAdapter.CareerItemViewHolder> {

    Context ctxt;

    List<CareerItem> careerList;

    private OnItemClickListener onItemClickListener;

    public CareerTimelineListAdapter(List<CareerItem> list) {
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
        final CareerItem item = careerList.get(i);

        careerItemViewHolder.company.setText(item.getCompany());
        careerItemViewHolder.position.setText(item.getPosition());

        String date = String.format(ctxt.getString(R.string.date_career),
                item.getStartDate(), item.getEndDate());
        careerItemViewHolder.date.setText(date);

        careerItemViewHolder.description.setText(item.getDescription());

        int iconId = CareerMapper.mappingIconIdFromName(item.getIcon());

        if (iconId != 0) {
            Glide.with(ctxt)
                    .load(iconId)
                    .centerCrop()
                    .crossFade()
                    .into(careerItemViewHolder.icon);
        }

        careerItemViewHolder.moreDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                // Give some time to the ripple to finish the effect
                if (onItemClickListener != null) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            onItemClickListener.onItemClick(v, item);
                        }
                    }, 200);
                }
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public static class CareerItemViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView company;
        TextView position;
        TextView date;
        TextView description;
        RoundedImageView icon;
        Button moreDetails;

        CareerItemViewHolder(View itemView) {
            super(itemView);
            cv          = (CardView)itemView.findViewById(R.id.cv);
            company     = (TextView)itemView.findViewById(R.id.career_item_company);
            position    = (TextView)itemView.findViewById(R.id.career_item_position);
            date        = (TextView)itemView.findViewById(R.id.career_item_date);
            description = (TextView)itemView.findViewById(R.id.career_item_description);
            icon        = (RoundedImageView)itemView.findViewById(R.id.career_item_icon);
            moreDetails = (Button)itemView.findViewById(R.id.career_item_details_button);
        }
    }


    public interface OnItemClickListener {
        void onItemClick(View view, CareerItem career);
    }

}
