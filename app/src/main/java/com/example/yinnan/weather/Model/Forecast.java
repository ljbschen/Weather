package com.example.yinnan.weather.Model;

import com.example.yinnan.weather.R;

public class Forecast {
    private CurrentWeather mCurrent;
    private HourWeather[] mHour;
    private DailyWeather[] mDaily;

    public CurrentWeather getCurrent() {
        return mCurrent;
    }

    public void setCurrent(CurrentWeather current) {
        mCurrent = current;
    }

    public HourWeather[] getHour() {
        return mHour;
    }

    public void setHour(HourWeather[] hour) {
        mHour = hour;
    }

    public DailyWeather[] getDaily() {
        return mDaily;
    }

    public void setDaily(DailyWeather[] daily) {
        mDaily = daily;
    }

    public static int getIconId(String icon) {
        if (icon.equals("clear-day")) return R.drawable.sunny;
        else if (icon.equals("clear-night")) return R.drawable.clear_night;
        else if (icon.equals("partly-cloudy-day")) return R.drawable.partly_cloudy;
        else if (icon.equals("partly-cloudy-night")) return R.drawable.cloudy_night;
        else if (icon.equals("rain")) return R.drawable.rain;
        else if (icon.equals("snow")) return R.drawable.snow;
        else if (icon.equals("sleet")) return R.drawable.sleet;
        else if (icon.equals("wind")) return R.drawable.wind;
        else if (icon.equals("fog")) return R.drawable.fog;
        else return R.drawable.cloudy;
    }
}
