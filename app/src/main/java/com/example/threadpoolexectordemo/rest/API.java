package com.example.threadpoolexectordemo.rest;

import com.example.threadpoolexectordemo.model.FileDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    //video
//    @GET("5dfb84b12f00006800ff9ff4")
//    Call<List<FileDetails>> getFileDetails();
    //image
    @GET("5dfcc5723100007b00d2c152")
    Call<List<FileDetails>> getFileDetails();
}
