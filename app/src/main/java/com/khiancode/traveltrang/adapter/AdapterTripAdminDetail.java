package com.khiancode.traveltrang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.khiancode.traveltrang.BaseActivity;
import com.khiancode.traveltrang.R;
import com.khiancode.traveltrang.model.ListTravelTripAdminModel;
import com.khiancode.traveltrang.model.TripAdminDetailModel;
import com.khiancode.traveltrang.model.TripAdminModel;

import java.util.ArrayList;


public class AdapterTripAdminDetail extends RecyclerView.Adapter<AdapterTripAdminDetail.VersionViewHolder> {
    private ArrayList<ListTravelTripAdminModel> model;
    private Context context;
    private OnItemClickListener clickListener;

    public AdapterTripAdminDetail(Context applicationContext, ArrayList<ListTravelTripAdminModel> model) {
        this.context = applicationContext;
        this.model = model;
    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_travel_trip_detail, viewGroup, false);
        return new VersionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final VersionViewHolder versionViewHolder, final int i) {
        versionViewHolder.title.setText("วันที่ "+model.get(i).getDay());
        versionViewHolder.day.setText(model.get(i).getCount()+" สถานที่ท่องเที่ยว");
        Glide.with(context)
                .load(BaseActivity.BASE_URL_PICTURE + "/images/travel/" + model.get(i).getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.drawable.nopic)
                .into(versionViewHolder.image);
    }

    @Override
    public int getItemCount() {
        return model == null ? 0 : model.size();
    }

    class VersionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        TextView title, day;
        public VersionViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            day = itemView.findViewById(R.id.day);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(v, getPosition());
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

}
