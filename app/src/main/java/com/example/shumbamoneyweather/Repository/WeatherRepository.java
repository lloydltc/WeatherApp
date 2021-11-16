package com.example.shumbamoneyweather.Repository;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.shumbamoneyweather.Model.Weather;
import com.example.shumbamoneyweather.Nertwork.RetrofitInstance;
import com.example.shumbamoneyweather.Nertwork.WeatherApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {
    private static WeatherRepository weatherRepository;

    public static WeatherRepository getInstance(){
        if (weatherRepository == null){
            weatherRepository = new WeatherRepository();
        }
        return weatherRepository;
    }
    private WeatherApi weatherApi;
    public WeatherRepository(){

        weatherApi = RetrofitInstance.cteateService(WeatherApi.class);
    }
    public MutableLiveData<Weather> getWeather(String city, String count, String apikey){MutableLiveData<Weather>weatherData = new MutableLiveData<>();
    weatherApi.getWeather(city,apikey).enqueue(new Callback<Weather>() {
        @Override
        public void onResponse(Call<Weather> call, Response<Weather> response) {
            if (response.isSuccessful()){
                weatherData.setValue(response.body());
            }
        }

        @Override
        public void onFailure(Call<Weather> call, Throwable t) {

//           weatherData.setValue(null);

        }
    });
        return weatherData;
    }


}
