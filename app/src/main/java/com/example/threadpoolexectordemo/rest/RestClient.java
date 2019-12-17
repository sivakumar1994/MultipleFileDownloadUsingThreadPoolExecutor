package com.example.threadpoolexectordemo.rest;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RestClient {

    private static RestClient restClient;

    private API api;

    private RestClient() {
    }

    private static final String baseUrl ="http://www.mocky.io/v2/";

    public static RestClient getInstance() {
        if (restClient == null)
            return restClient = new RestClient();
        else
            return restClient;
    }

    private  void setupRestClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.addInterceptor(httpLoggingInterceptor);
         okHttpClient.readTimeout(30, TimeUnit.SECONDS);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        api = retrofit.create(API.class);

    }

    public API get() {
        setupRestClient();
        return api;
    }
}
