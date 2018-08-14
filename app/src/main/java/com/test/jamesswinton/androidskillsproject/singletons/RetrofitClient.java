package com.test.jamesswinton.androidskillsproject.Singletons;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofitInstance = null;
    private static final String BASE_URL = "http://api.football-data.org/v2/";

    public static RetrofitInstance getInstance() {
        if (retrofitInstance == null) {
            retrofitInstance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                    .build();
        }
        return retrofitInstance;
    }

    private RetrofitInstance() {
    }
}
