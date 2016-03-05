package com.example.yinnan.weather.Model;

import android.os.Parcel;
import android.os.Parcelable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DailyWeather implements Parcelable {
    private long mTime;
    private String mSummary;
    private double mMaxTemp;
    private String mIcon;
    private String mTimeZone;

    public DailyWeather() {}

    private DailyWeather(Parcel in) {
        mTime = in.readLong();
        mSummary = in.readString();
        mMaxTemp = in.readDouble();
        mIcon = in.readString();
        mTimeZone = in.readString();
    }

    public static final Creator<DailyWeather> CREATOR = new Creator<DailyWeather>() {
        @Override
        public DailyWeather createFromParcel(Parcel in) {
            return new DailyWeather(in);
        }

        @Override
        public DailyWeather[] newArray(int size) {
            return new DailyWeather[size];
        }
    };

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public int getIconId() {
        return Forecast.getIconId(mIcon);
    }

    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        mTime = time;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public int getMaxTemp() {
        return (int)Math.round(mMaxTemp);
    }

    public void setMaxTemp(double maxTemp) {
        mMaxTemp = maxTemp;
    }

    public String getTimeZone() {
        return mTimeZone;
    }

    public void setTimeZone(String timeZone) {
        mTimeZone = timeZone;
    }

    public String getDayOfTheWeek() {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE");
        formatter.setTimeZone(TimeZone.getTimeZone(mTimeZone));
        Date date = new Date(mTime*1000);
        return formatter.format(date);
    }

    public String getDay() {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd");
        formatter.setTimeZone(TimeZone.getTimeZone(mTimeZone));
        Date date = new Date(mTime*1000);
        return formatter.format(date);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mTime);
        dest.writeString(mSummary);
        dest.writeDouble(mMaxTemp);
        dest.writeString(mIcon);
        dest.writeString(mTimeZone);
    }
}
