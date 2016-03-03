package com.example.yinnan.weather.Model;

import com.example.yinnan.weather.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class CurrentWeather {
    private double mTemperature;
    private double mHumidity;
    private double mChance;
    private long mTime;
    private String mIcon;
    private String mSummary;
    private String mTimeZone;

    public String getTimeZone() {
        return mTimeZone;
    }

    public void setTimeZone(String timeZone) {
        mTimeZone = timeZone;
    }

    public int getTemperature() {
        return (int)Math.round(mTemperature);
    }

    public void setTemperature(double temperature) {
        mTemperature = temperature;
    }

    public double getHumidity() {
        return mHumidity;
    }

    public void setHumidity(double humidity) {
        mHumidity = humidity;
    }

    public int getChance() {
        return (int)Math.round(mChance*100);
    }

    public void setChance(double chance) {
        mChance = chance;
    }

    public String getFormattedTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");
        formatter.setTimeZone(TimeZone.getTimeZone(getTimeZone()));
        Date time = new Date(mTime*1000);
        return formatter.format(time);
    }

    public void setTime(long time) {
        mTime = time;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public int getIconID() {
        //clear-day, clear-night, rain, snow, sleet, wind, fog, cloudy, partly-cloudy-day, or partly-cloudy-night
        if (mIcon.equals("clear-day")) return R.drawable.sunny;
        else if (mIcon.equals("clear-night")) return R.drawable.clear_night;
        else if (mIcon.equals("partly-cloudy-day")) return R.drawable.partly_cloudy;
        else if (mIcon.equals("partly-cloudy-night")) return R.drawable.cloudy_night;
        else if (mIcon.equals("rain")) return R.drawable.rain;
        else if (mIcon.equals("snow")) return R.drawable.snow;
        else if (mIcon.equals("sleet")) return R.drawable.sleet;
        else if (mIcon.equals("wind")) return R.drawable.wind;
        else if (mIcon.equals("fog")) return R.drawable.fog;
        else return R.drawable.cloudy;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }
}
