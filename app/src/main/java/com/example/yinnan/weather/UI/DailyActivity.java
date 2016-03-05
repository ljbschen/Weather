package com.example.yinnan.weather.UI;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.yinnan.weather.Adapters.DayAdapter;
import com.example.yinnan.weather.Model.DailyWeather;
import com.example.yinnan.weather.R;

import java.lang.reflect.Array;
import java.util.Arrays;

public class DailyActivity extends ListActivity {
    private DailyWeather[] mDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);

        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra("Days");
        mDays = Arrays.copyOf(parcelables, parcelables.length, DailyWeather[].class);
        //context, layout, data
        DayAdapter adapter = new DayAdapter(this, mDays);
        setListAdapter(adapter);

    }
}
