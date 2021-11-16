package com.example.shumbamoneyweather.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.shumbamoneyweather.Model.ForecastList;
import com.example.shumbamoneyweather.Model.Weather;
import com.example.shumbamoneyweather.Nertwork.RetrofitInst;
import com.example.shumbamoneyweather.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MvpArchitecture extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_architecture);
       getWeather();

    }
    public void getWeather(){
//        Toast.makeText(MvpArchitecture.this, " zvaita wangu jjjdjkbhhbdhhjvsdhjvcdhvcjhdcvhjdsbchdjbhjcd bhjcbhcdb hjcd bhjcd bhdc bcdhj hcjd hcjd hj h", Toast.LENGTH_SHORT).show();

        Call<Weather> call = RetrofitInst.getmInstance().getAPInterface().getWeather("harare","034da672af3e87a27b2bfb04a03baaa1");
        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
               try {
                   if (response.code()==200){
                       Weather weather = response.body();
                       Toast.makeText(MvpArchitecture.this, weather.getCod(), Toast.LENGTH_SHORT).show();
                   }
               }catch (Exception e) {
                   e.printStackTrace();
               }

            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Toast.makeText(MvpArchitecture.this, t.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }
}