package com.example.shumbamoneyweather.View.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shumbamoneyweather.Interfaces.DetailsInterface;
import com.example.shumbamoneyweather.Model.ForecastList;
import com.example.shumbamoneyweather.R;
import com.example.shumbamoneyweather.View.Adapters.WeatherAdapter;
import com.example.shumbamoneyweather.ViewModel.WeatherViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DetailsInterface {
    private static final String LOG_TAG = "hey";
    ArrayList<ForecastList> forecastListArrayList = new ArrayList<>();
    WeatherAdapter weatherAdapter;
    RecyclerView weatherRecyclerview;
    WeatherViewModel weatherViewModel;
    TextView txt;
    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weatherRecyclerview = findViewById(R.id.weather_forecast_rec);
        txt = findViewById(R.id.twn);

if (isOnline()){
    weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
    weatherViewModel.init();
    weatherViewModel.getWeatherRepository().observe(this,weather -> {
        List<ForecastList> forecastLists = weather.getList();
        forecastListArrayList.addAll(forecastLists);
        saveSharedPreferencesWeather(getApplicationContext(),forecastListArrayList);
        weatherAdapter.notifyDataSetChanged();

    });
    setupRecyclerView();

}else{
    forecastListArrayList.addAll(loadSharedPreferencesWeather(getApplicationContext()));

    setupRecyclerView();



}


    }


    private void setupRecyclerView() {
        if (weatherAdapter==null){
            weatherAdapter = new WeatherAdapter(MainActivity.this,forecastListArrayList);
            weatherRecyclerview.setLayoutManager(new LinearLayoutManager(this));
            weatherRecyclerview.setAdapter(weatherAdapter);
            weatherRecyclerview.setItemAnimator(new DefaultItemAnimator());
            weatherRecyclerview.setNestedScrollingEnabled(true);


        }else{
            weatherAdapter.notifyDataSetChanged();
        }
    }

    public static void saveSharedPreferencesWeather(Context context, ArrayList<ForecastList> forecastListArrayList) {
        SharedPreferences mPrefs = context.getSharedPreferences("Weathercahe", context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(forecastListArrayList);
        prefsEditor.putString("myJson", json);
        prefsEditor.commit();

    }

    public static ArrayList<ForecastList> loadSharedPreferencesWeather(Context context) {
        ArrayList<ForecastList> savedWeather = new ArrayList<ForecastList>();
        SharedPreferences mPrefs = context.getSharedPreferences("Weathercahe", context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("myJson", "");
        if (json.isEmpty()) {
            savedWeather = new ArrayList<ForecastList>();
        } else {
            Type type = new TypeToken< ArrayList<ForecastList>>() {
            }.getType();
            savedWeather = gson.fromJson(json, type);
        }

        return savedWeather;
    }
    public boolean isOnline() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnectedOrConnecting()){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void viewDetails(int pos) {
        Double rain = null;

        if(forecastListArrayList.get(pos).getRain() != null){
           rain = forecastListArrayList.get(pos).getRain().getRainfall();
        }else {
            rain =0.0;
        }
        Intent intent = new Intent(this,DetailsActivity.class);
        intent.putExtra("date", forecastListArrayList.get(pos).getDt());
        intent.putExtra("humidity",forecastListArrayList.get(pos).getMain().getHumidity());
        intent.putExtra("pop",forecastListArrayList.get(pos).getPop());
        intent.putExtra("cloud",forecastListArrayList.get(pos).getClouds().getAll());
        intent.putExtra("windSpeed",forecastListArrayList.get(pos).getWind().getSpeed());
        intent.putExtra("windDirection",forecastListArrayList.get(pos).getWind().getDeg());
        intent.putExtra("pressure",forecastListArrayList.get(pos).getMain().getPressure());
        intent.putExtra("weatherDesc",forecastListArrayList.get(pos).getWeather().get(0).getDescription());
        intent.putExtra("minTemp", forecastListArrayList.get(pos).getMain().getTempMin());
        intent.putExtra("maxTemp",forecastListArrayList.get(pos).getMain().getTempMax());
        intent.putExtra("temp",forecastListArrayList.get(pos).getMain().getTemp());
        intent.putExtra("weather",forecastListArrayList.get(pos).getWeather().get(0).getMain());
        intent.putExtra("rain",rain);
        intent.putExtra("icon",forecastListArrayList.get(pos).getWeather().get(0).getIcon());

        startActivity(intent);

    }
}