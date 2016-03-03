package com.example.yinnan.weather.Model;

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
}
