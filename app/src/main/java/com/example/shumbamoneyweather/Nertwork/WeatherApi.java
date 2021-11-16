package com.example.shumbamoneyweather.Nertwork;

import com.example.shumbamoneyweather.Model.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("forecast")
    Call<Weather> getWeather(@Query("q") String city_name,
                             @Query("APPID") String api_key);

}
