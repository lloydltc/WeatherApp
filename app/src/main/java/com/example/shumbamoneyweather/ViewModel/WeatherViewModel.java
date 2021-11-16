package com.example.shumbamoneyweather.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shumbamoneyweather.Model.Weather;
import com.example.shumbamoneyweather.Repository.WeatherRepository;

public class WeatherViewModel extends ViewModel {
    private MutableLiveData<Weather> mutableLiveData;
    private WeatherRepository weatherRepository;
    public void init(){
        if (mutableLiveData != null){
            return;
        }
        weatherRepository = WeatherRepository.getInstance();
        mutableLiveData = weatherRepository.getWeather("Harare", "16","034da672af3e87a27b2bfb04a03baaa1");

    }
    public LiveData<Weather> getWeatherRepository() {
        return mutableLiveData;
    }

}
