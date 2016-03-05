package com.example.yinnan.weather.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yinnan.weather.Model.HourWeather;
import com.example.yinnan.weather.R;
import com.example.yinnan.weather.UI.HourlyActivity;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HourAdapter extends RecyclerView.Adapter<HourAdapter.HourViewHolder> {
    private HourWeather[] mHours;
    private Context mContext;

    public HourAdapter (Context context, HourWeather[] hours) {
        mContext = context;
        mHours = hours;
    }

    @Override
    public HourViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hourly_list_item, parent, false);
        return new HourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HourViewHolder holder, int position) {
        holder.bindHour(mHours[position]);
    }

    @Override
    public int getItemCount() {
        return mHours.length;
    }

    public class HourViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{
        TextView mTemperatureLabel, mSummaryLabel, mTimeLabel;
        ImageView mIconImageView;

        public HourViewHolder(View itemView) {
            super(itemView);
            mTemperatureLabel = (TextView) itemView.findViewById(R.id.temperatureLabel);
            mSummaryLabel = (TextView) itemView.findViewById(R.id.summaryLabel);
            mTimeLabel = (TextView) itemView.findViewById(R.id.timeLabel);
            mIconImageView = (ImageView) itemView.findViewById(R.id.iconImageView);
            itemView.setOnClickListener(this);
        }

        public void bindHour(HourWeather hour) {
            mTimeLabel.setText(hour.getHour());
            mSummaryLabel.setText(hour.getSummary());
            mTemperatureLabel.setText(hour.getTemperature()+"");
            mIconImageView.setImageResource(hour.getIconId());
        }

        @Override
        public void onClick(View v) {
            String time = mTimeLabel.getText().toString();
            String temperature = mTemperatureLabel.getText().toString();
            String condition = mSummaryLabel.getText().toString();
            String msg = String.format("At %s it will be %s degree and %s",
                    time, temperature, condition);
            Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
        }
    }
}
