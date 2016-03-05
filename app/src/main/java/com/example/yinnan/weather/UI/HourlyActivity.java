package com.example.yinnan.weather.UI;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.yinnan.weather.Model.HourWeather;
import com.example.yinnan.weather.R;

import java.util.Arrays;

public class HourlyActivity extends AppCompatActivity {
    HourWeather[] mHours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly);

        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra("Hours");
        mHours = Arrays.copyOf(parcelables, parcelables.length, HourWeather[].class);



    }

}
