package com.example.shumbamoneyweather.Nertwork;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private final static  String BASE_URL = "https://api.openweathermap.org/data/2.5/";

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <S> S cteateService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

}


