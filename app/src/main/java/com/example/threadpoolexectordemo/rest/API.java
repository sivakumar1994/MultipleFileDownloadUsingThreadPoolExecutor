package com.example.threadpoolexectordemo.rest;

import com.example.threadpoolexectordemo.model.FileDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    @GET("5de7adc03700007a02092b66")
    Call<List<FileDetails>> getFileDetails();
}
