package com.example.yinnan.weather.UI;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.yinnan.weather.Model.CurrentWeather;
import com.example.yinnan.weather.Model.DailyWeather;
import com.example.yinnan.weather.Model.Forecast;
import com.example.yinnan.weather.Model.HourWeather;
import com.example.yinnan.weather.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private Forecast mForecast;
    @Bind(R.id.humidityTextView) TextView mHumidityTextView;
    @Bind(R.id.rainTextView) TextView mRainTextView;
    @Bind(R.id.temperatureTextView) TextView mTemperatureTextView;
    @Bind(R.id.timeTextView) TextView mTimeTextView;
    @Bind(R.id.summaryTextView) TextView mSummaryTextView;
    @Bind(R.id.iconImageView) ImageView mIconImageView;
    @Bind(R.id.refreshImageView) ImageView mRefreshImageView;
    @Bind(R.id.progressBar) ProgressBar mProgressBar;
    @Bind(R.id.dailyButton) Button mDailyButton;
    @Bind(R.id.hourlyButton) Button mHourlyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mRefreshImageView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
        final double latitude=37.8267;
        final double longitude=-122.423;
        getJsonData(latitude, longitude);
        mRefreshImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getJsonData(latitude, longitude);
            }
        });
        mHourlyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HourlyActivity.class);
                startActivity(intent);
            }
        });
        mDailyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DailyActivity.class);
                startActivity(intent);

            }
        });
    }

    private void updateDisplay(CurrentWeather currentWeather) {
        CurrentWeather cw = mForecast.getCurrent();
        mHumidityTextView.setText(currentWeather.getHumidity() + "");
        mRainTextView.setText(currentWeather.getChance() + "%");
        mTemperatureTextView.setText(currentWeather.getTemperature() + "");
        mTimeTextView.setText("At " + currentWeather.getFormattedTime() + " it will be:");
        mSummaryTextView.setText(currentWeather.getSummary());
        mIconImageView.setImageDrawable(getResources().getDrawable(currentWeather.getIconID()));
    }

    private Forecast getForecast(String jsonData) throws JSONException {
        Forecast forecast = new Forecast();
        forecast.setCurrent(getWeather(jsonData));
        forecast.setDaily(getDailyWeather(jsonData));
        forecast.setHour(getHourWeather(jsonData));
        return forecast;
    }

    private HourWeather[] getHourWeather(String jsonData) throws JSONException {
        JSONObject forecastInfo = new JSONObject(jsonData);
        JSONArray hourInfo = forecastInfo.getJSONObject("hourly").getJSONArray("data");
        HourWeather[] hourWeathers = new HourWeather[hourInfo.length()];
        for (int i=0;i<hourInfo.length();i++) {
            JSONObject currentHour = hourInfo.getJSONObject(i);
            hourWeathers[i] = new HourWeather();
            hourWeathers[i].setIcon(currentHour.getString("icon"));
            hourWeathers[i].setSummary(currentHour.getString("summary"));
            hourWeathers[i].setTemperature(currentHour.getDouble("temperature"));
            hourWeathers[i].setTimeZone(forecastInfo.getString("timezone"));
            hourWeathers[i].setTime(currentHour.getLong("time"));
        }
        return hourWeathers;
    }

    private DailyWeather[] getDailyWeather(String jsonData) throws JSONException {
        JSONObject forecastInfo = new JSONObject(jsonData);
        JSONArray dailyInfo = forecastInfo.getJSONObject("daily").getJSONArray("data");
        DailyWeather[] dailyWeathers = new DailyWeather[dailyInfo.length()];
        for (int i=0;i<dailyInfo.length();i++) {
            JSONObject currentDay = dailyInfo.getJSONObject(i);
            dailyWeathers[i] = new DailyWeather();
            dailyWeathers[i].setIcon(currentDay.getString("icon"));
            dailyWeathers[i].setSummary(currentDay.getString("summary"));
            dailyWeathers[i].setMaxTemp(currentDay.getDouble("temperatureMax"));
            dailyWeathers[i].setMinTemp(currentDay.getDouble("temperatureMin"));
            dailyWeathers[i].setTimeZone(forecastInfo.getString("timezone"));
            dailyWeathers[i].setTime(currentDay.getLong("time"));
        }
        return dailyWeathers;
    }

    private CurrentWeather getWeather(String jsonData) throws JSONException {
        JSONObject forecastInfo = new JSONObject(jsonData);
        CurrentWeather weather = new CurrentWeather();
        weather.setTimeZone(forecastInfo.getString("timezone"));
        JSONObject currently = forecastInfo.getJSONObject("currently");
        weather.setTemperature(currently.getDouble("temperature"));
        weather.setHumidity(currently.getDouble("humidity"));
        weather.setChance(currently.getDouble("precipProbability"));
        weather.setTime(currently.getLong("time"));
        weather.setSummary(currently.getString("summary"));
        weather.setIcon(currently.getString("icon"));
        return weather;
    }

    private void getJsonData(double latitude, double longitude) {
        //create http request and get json data from server;
        String apiKey = "00e4ad0345969fa8b1a81de0646517a5";
        String url = "https://api.forecast.io/forecast/" +
                apiKey + "/" + latitude + "," + longitude;

        if (isNetworkAvailable()) {
            toggleRefresh();
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    generateAlert("Call Failed!",
                            "Call Failed. Please check it and try again!",
                            "OK");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        try {
                            mForecast = getForecast(response.body().string());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    toggleRefresh();
                                    updateDisplay(mForecast.getCurrent());
                                }
                            });
                        } catch (JSONException e) {
                            generateAlert("JSONException!",
                                    "JSONException was caught!",
                                    "OK");
                        }
                    } else {
                        generateAlert("Response Failed!",
                                "Previous response failed. Please check it and try again!",
                                "OK");
                    }
                }
            });
        } else {
            generateAlert("No Internet Connection!",
                    "You don't have an Internet connection now. Please check it and try again!",
                    "OK");
            //Log.e(TAG, "No Network Connection. Please check and try again!");
        }
    }

    private void generateAlert(String title, String message, String pbutton) {
        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setButton(pbutton);
        dialog.show(getFragmentManager(), "Error_Dialog");
    }

    private void toggleRefresh() {
        if (mProgressBar.getVisibility() == View.VISIBLE) {
            mProgressBar.setVisibility(View.INVISIBLE);
            mRefreshImageView.setVisibility(View.VISIBLE);
        }
        else {
            mProgressBar.setVisibility(View.VISIBLE);
            mRefreshImageView.setVisibility(View.INVISIBLE);
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
}
