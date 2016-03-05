package com.example.yinnan.weather.UI;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yinnan.weather.Adapters.DayAdapter;
import com.example.yinnan.weather.Model.DailyWeather;
import com.example.yinnan.weather.R;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DailyActivity extends Activity {
    private DailyWeather[] mDays;
    @Bind(android.R.id.list) ListView mListView;
    @Bind(android.R.id.empty) TextView mEmptyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra("Days");
        mDays = Arrays.copyOf(parcelables, parcelables.length, DailyWeather[].class);
        //context, layout, data
        mListView.setAdapter(new DayAdapter(this, mDays));
        mListView.setEmptyView(mEmptyTextView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String dayOfTheWeek = mDays[position].getDayOfTheWeek();
                String condition = mDays[position].getSummary();
                String highTemp = mDays[position].getMaxTemp()+"";
                String msg = String.format("On %s the high will be %s and it will be %s",
                        dayOfTheWeek, highTemp, condition);
                Toast.makeText(DailyActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        });
    }
}
