package com.example.shumbamoneyweather.Nertwork;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInst {
    private final static  String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    private static RetrofitInst mInstance;
    private Retrofit retrofit;

    private RetrofitInst (){

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static synchronized RetrofitInst getmInstance(){
        if(mInstance == null){
            mInstance = new RetrofitInst();
        }
        return mInstance;
    }

    public WeatherApi getAPInterface(){
        return retrofit.create(WeatherApi.class);
    }

}
