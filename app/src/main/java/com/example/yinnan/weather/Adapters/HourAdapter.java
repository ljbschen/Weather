package com.example.yinnan.weather.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yinnan.weather.Model.HourWeather;
import com.example.yinnan.weather.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HourAdapter extends RecyclerView.Adapter<HourAdapter.HourViewHolder> {
    private HourWeather[] mHours;

    public HourAdapter (HourWeather[] hours) {
        mHours = hours;
    }

    @Override
    public HourViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hourly_list_item, parent, false);
        HourViewHolder viewHolder = new HourViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HourViewHolder holder, int position) {
        holder.bindHour(mHours[position]);
    }

    @Override
    public int getItemCount() {
        return mHours.length;
    }

    public class HourViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.temperatureLabel) TextView mTemperatureLabel;
        @Bind(R.id.summarLabel) TextView mSummaryLabel;
        @Bind(R.id.timeLabel) TextView mTimeLabel;
        @Bind(R.id.iconImageView) ImageView mIconImageView;

        public HourViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }

        public void bindHour(HourWeather hour) {
            mTimeLabel.setText(hour.getHour());
            mSummaryLabel.setText(hour.getSummary());
            mTemperatureLabel.setText(hour.getTemperature()+"");
            mIconImageView.setImageResource(hour.getIconId());
        }
    }
}
